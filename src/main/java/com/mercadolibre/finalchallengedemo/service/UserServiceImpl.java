package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.UserDTO;
import com.mercadolibre.finalchallengedemo.entities.UserEntity;
import com.mercadolibre.finalchallengedemo.exceptions.UserNotFoundException;
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
    String SECRET = "SECRET";
    private final String SECRET_VALUE = System.getenv(SECRET);

    private final com.mercadolibre.finalchallengedemo.repositories.IUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserEntity checkUser(String username, String password) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(username).orElse(null);
        if(userEntity == null){
            throw new UserNotFoundException("User not found");
        }
        if (!username.equals(userEntity.getUsername()) || !password.equals(userEntity.getPassword()))
            throw new UserNotFoundException(" Incorrect user or password ");
        return userEntity;
    }

    @Override
    public String getJWTToken(String username, String pwd) throws UserNotFoundException{
        UserEntity userEntity = checkUser(username, pwd);
        String secretKey = SECRET_VALUE;
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
                .setExpiration(new Date(System.currentTimeMillis() + 60000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}