package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.UserDTO;
import com.devsuperior.dsmovie.entities.User;
import com.devsuperior.dsmovie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User save(String email){
        var userOpt = repository.findByEmail(email);
        if(!userOpt.isPresent()) {
            var user = new User(email);
            return repository.save(user);
        }
        return userOpt.get();
    }
}
