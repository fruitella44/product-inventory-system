package com.fruitella.inventory.service;

import com.fruitella.inventory.entity.Users;

import java.util.List;

public interface UserService {
    List<Users> getAll();
    Users saveUser(Users user);
    Users findUserById(Long id);
    Users updateUser(Users user);
    void deleteUserById(Long id);
}
