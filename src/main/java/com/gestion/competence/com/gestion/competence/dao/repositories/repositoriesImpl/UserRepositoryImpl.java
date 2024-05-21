package com.gestion.competence.com.gestion.competence.dao.repositories.repositoriesImpl;

import com.gestion.competence.com.gestion.competence.dao.JDBC.DbConnex;
import com.gestion.competence.com.gestion.competence.dao.repositories.UserRepository;
import com.gestion.competence.com.gestion.competence.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private DbConnex dbConnex;

    @Override
    public List<User> findAll() {
        return dbConnex.findAllUsers();
    }

    @Override
    public User findById(int id) {
        return dbConnex.findUserById(id);
    }

    @Override
    public User save(User user) {
        return dbConnex.saveUser(user);
    }

    @Override
    public void delete(int id) {
        dbConnex.deleteUser(id);
    }
}
