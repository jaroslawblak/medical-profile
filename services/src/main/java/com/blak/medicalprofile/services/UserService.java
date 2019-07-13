package com.blak.medicalprofile.services;

import com.blak.medicalprofile.dao.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User generateUserKey(User user){
        return user.userKey(String.valueOf(user.hashCode()).substring(0, 4));
    }
}
