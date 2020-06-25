package com.enigma.pascal.api.repositories;

import com.enigma.pascal.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepo extends JpaRepository<UserModel,Integer> {
    UserModel findByUsernameAndPassword(String username, String password);
}
