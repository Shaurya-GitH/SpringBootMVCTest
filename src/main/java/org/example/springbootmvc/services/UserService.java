package org.example.springbootmvc.services;

import org.example.springbootmvc.entities.Users;

public interface UserService {
     boolean checkLogin(Users user);
     boolean registerUser(Users user);
}
