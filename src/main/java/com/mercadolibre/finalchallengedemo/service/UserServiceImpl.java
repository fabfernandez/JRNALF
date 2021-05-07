package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.UserDTO;
import com.mercadolibre.finalchallengedemo.entities.UserEntity;
import com.mercadolibre.finalchallengedemo.repositories.IUserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements IUserService {

    private final com.mercadolibre.finalchallengedemo.repositories.IUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserEntity checkUser(String username, String password) throws IllegalAccessException {
        UserEntity userEntity = userRepository.findById(username).orElseThrow();
        if (!username.equals(userEntity.getUsername()) && !password.equals(userEntity.getPassword()))
            throw new IllegalAccessException();
        return userEntity;
    }

    @Override
    public String getJWTToken(String username, String pwd) throws IllegalAccessException {
        UserEntity userEntity = checkUser(username, pwd);
        String secretKey = "T5k6URuuKWT";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(userEntity.getSubsidiary().getId().toString());
        String token = Jwts
                .builder()
                .claim("username", userEntity.getUsername())
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}