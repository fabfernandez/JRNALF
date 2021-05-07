package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.entities.UserEntity;

public interface IUserService {
    UserEntity checkUser(String username, String password) throws IllegalAccessException;
    String getJWTToken(String username, String pwd) throws IllegalAccessException;
}
