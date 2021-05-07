package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.entities.UserEntity;
import com.mercadolibre.finalchallengedemo.exceptions.UserNotFoundException;

public interface IUserService {
    UserEntity checkUser(String username, String password) throws UserNotFoundException;
    String getJWTToken(String username, String pwd) throws UserNotFoundException;
}
