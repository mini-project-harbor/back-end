package com.enigma.pascal.api.services;

import com.enigma.pascal.api.models.UserModel;
import com.enigma.pascal.api.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public Page<UserModel> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepo.findAll(pageable);
    }

    public UserModel getUser(UserModel userModel) {
        UserModel cekUser = userRepo.findByUsernameAndPassword(userModel.getUsername(),userModel.getPassword());
        UserModel userInfo = new UserModel();
        if (cekUser != null) {
            userInfo.setId(cekUser.getId());
            userInfo.setUsername(cekUser.getUsername());
            return userInfo;
        } else {
            return null;
        }
    }
}
