package com.gestion.competence.com.gestion.competence.Services.ServiceImpl;

import com.gestion.competence.com.gestion.competence.Services.UserService;
import com.gestion.competence.com.gestion.competence.dao.repositories.UserRepository;
import com.gestion.competence.com.gestion.competence.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userrepository;


    @Override
    public List<User> findAll() {
        return userrepository.findAll();
    }

    @Override
    public User save(User user) {
        return userrepository.save(user);
    }

    @Override
    public void delete(int id) {
      userrepository.delete(id);
    }

    @Override
    public User findById(int id) {
        return userrepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
      userrepository.delete(id);
    }
}
