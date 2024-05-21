package com.gestion.competence.com.gestion.competence.dao.JDBC;

import com.gestion.competence.com.gestion.competence.entities.CV;
import com.gestion.competence.com.gestion.competence.entities.Competence;
import com.gestion.competence.com.gestion.competence.entities.Experience;
import com.gestion.competence.com.gestion.competence.entities.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DbConnex {
    String url = "jdbc:mysql://localhost:3306/gestioncompetence";
    String username = "root";
    String password = "";

    Connection con;

    {
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Methods for CV

    public List<CV> findAllCVs() {
        List<CV> cvs = new ArrayList<>();
        String sql = "SELECT * FROM cvs";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int userId = rs.getInt("user_id");

                User user = findUserById(userId);

                CV cv = new CV(id, name, user);
                cvs.add(cv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cvs;
    }

    public void deleteCvbyId(int id) {
        String sql = "DELETE FROM cvs WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CV saveCV(CV cv) {
        String sql = "INSERT INTO cvs (name, user_id) VALUES (?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, cv.getName());
            pstmt.setInt(2, cv.getUserId());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                cv.setId(rs.getInt(1));
            }
            Integer userId = cv.getUserId();
            if (userId == null) {
                throw new IllegalArgumentException("User ID cannot be null");
            }
            cv.getCompetences().forEach(c -> {
                int idComp=this.saveCompetences(c).getId();
                c.setId(idComp);
                try {
                    this.comptenceCV(cv.getId(),idComp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cv;
    }

    private void comptenceCV(int id, int idComp) throws SQLException {
        String sql="INSERT INTO competencecv (cv_id, competence_id) VALUES (?,?)";
        try (PreparedStatement pstmt=con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, idComp);
            pstmt.executeUpdate();
        }
    }

    public CV findCvById(int id) {
        String sql = "SELECT id, name, user_id FROM cvs WHERE id = ?";
        CV cv = null;

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    int userId = rs.getInt("user_id");

                    User user = findUserById(userId);

                    cv = new CV(id, name, user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cv;
    }

    // Methods for Experience

    public List<Experience> findAllExperiences() {
        List<Experience> experiences = new ArrayList<>();
        String sql = "SELECT * FROM experiences";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String experience = rs.getString("experience");
                String description = rs.getString("description");
                int cvId = rs.getInt("cv_id");

                CV cv = findCvById(cvId);

                Experience exp = new Experience(id, experience, description, cv);
                experiences.add(exp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return experiences;
    }

    public Experience findExperiencebyId(int id) {
        String sql = "SELECT * FROM experiences WHERE id = ?";
        Experience experience = null;

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String exp = rs.getString("experience");
                    String description = rs.getString("description");
                    int cvId = rs.getInt("cv_id");

                    CV cv = findCvById(cvId);

                    experience = new Experience(id, exp, description, cv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return experience;
    }

    public Experience saveExperience(Experience experience) {
        String sql = "INSERT INTO experiences (experience, description, cv_id) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, experience.getExperience());
            pstmt.setString(2, experience.getDescription());
            pstmt.setInt(3, experience.getCv().getId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 1) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        experience.setId(id);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return experience;
    }

    public void deleteExperience(int id) {
        String sql = "DELETE FROM experiences WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Methods for User

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, email, phone FROM users";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                User user = new User(id, name, email, phone);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User findUserById(int id) {
        String sql = "SELECT id, name, email, phone FROM users WHERE id = ?";
        User user = null;

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");

                    user = new User(id, name, email, phone);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User saveUser(User user) {
        String sql = "INSERT INTO users (name, email, phone) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPhone());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 1) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        user.setId(id);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void deleteAllCVs(int id) {
        String sql = "DELETE FROM cvs WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Methods for Competence

    public List<Competence> findAllCompetences() {
        List<Competence> competences = new ArrayList<>();
        String sql = "SELECT * FROM competences";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String competenceName = rs.getString("competenceName");

                Competence competence = new Competence(id, competenceName);
                competences.add(competence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return competences;
    }

    public Competence findCompetencesById(int id) {
        String sql = "SELECT id, competenceName FROM competences WHERE id = ?";
        Competence competence = null;

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String competenceName = rs.getString("competenceName");

                    competence = new Competence(id, competenceName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return competence;
    }

    public Competence saveCompetences(Competence competence) {
        String sql = "INSERT INTO competences (competenceName) VALUES (?)";
        int id = 0;
        try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, competence.getCompetenceName());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 1) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        id = generatedKeys.getInt(1);
                        competence.setId(id);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return competence;
    }

    public void deleteCompetences(int id) {
        String sql = "DELETE FROM competences WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
