package com.gestion.competence.com.gestion.competence.dao.repositories;

import com.gestion.competence.com.gestion.competence.entities.Experience;
import com.gestion.competence.com.gestion.competence.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(int id);
    User save(User user);
    void delete(int id);
}
