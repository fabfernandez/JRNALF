package com.mercadolibre.finalchallengedemo.unit.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.finalchallengedemo.model.SubsidiaryEntity;
import com.mercadolibre.finalchallengedemo.model.UserEntity;
import com.mercadolibre.finalchallengedemo.exceptions.UserNotFoundException;
import com.mercadolibre.finalchallengedemo.repository.IUserRepository;
import com.mercadolibre.finalchallengedemo.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    IUserRepository userRepository = Mockito.mock(IUserRepository.class);
    UserServiceImpl userService;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(){
        this.userService = new UserServiceImpl(userRepository);
    }

    @Test
    void loginFail() {
        UserEntity userEntity = null;
        when(userRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(userEntity));
        assertThrows(UserNotFoundException.class, () -> userService.checkUser("holis","badpass"),
                "User not found");
    }

    @Test
    void invalidCredentials() throws IOException, UserNotFoundException {
        SubsidiaryEntity subsidiaryEntity = objectMapper.readValue(new File("src/test/resources/subsidiaryCasaMatriz.json"),
                new TypeReference<>() {});
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("gembleton0");
        userEntity.setPassword("IH4YH7kAk");
        userEntity.setSubsidiary(subsidiaryEntity);
        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(userEntity));
        assertThrows(UserNotFoundException.class,() -> userService.getJWTToken("user","mal"));
    }

    @Test
    void loginOk() throws IOException, UserNotFoundException {
        SubsidiaryEntity subsidiaryEntity = objectMapper.readValue(new File("src/test/resources/subsidiaryCasaMatriz.json"),
                new TypeReference<>() {});
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("gembleton0");
        userEntity.setPassword("IH4YH7kAk");
        userEntity.setSubsidiary(subsidiaryEntity);
        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(userEntity));
        String res = userService.getJWTToken("gembleton0","IH4YH7kAk");
        assertTrue(!res.isEmpty());
    }
}
