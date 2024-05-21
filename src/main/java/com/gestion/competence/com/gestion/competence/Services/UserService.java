package com.gestion.competence.com.gestion.competence.Services;

import com.gestion.competence.com.gestion.competence.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User save(User user);

    void delete(int id);

    User findById(int id);

    void deleteById(int id);
}
