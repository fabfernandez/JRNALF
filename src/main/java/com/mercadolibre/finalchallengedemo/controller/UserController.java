package com.mercadolibre.finalchallengedemo.controller;

import com.mercadolibre.finalchallengedemo.dtos.UserDTO;
import com.mercadolibre.finalchallengedemo.exceptions.UserNotFoundException;
import com.mercadolibre.finalchallengedemo.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping("login")
    public UserDTO login(@RequestParam("user") String username, @RequestParam("password") String pwd) throws UserNotFoundException {
        String token = service.getJWTToken(username, pwd);
        UserDTO user = new UserDTO();
        user.setUser(username);
        user.setToken(token);
        return user;
    }
}