package ru.kpfu.itis.dmitryivanov.service.impl;


import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitryivanov.model.User;
import ru.kpfu.itis.dmitryivanov.security.JWTToken;
import ru.kpfu.itis.dmitryivanov.service.SecurityService;
import ru.kpfu.itis.dmitryivanov.service.UserService;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    UserService userService;
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();


    @Override
    public User getCurrentUser() {
        return userService.findOneByUsername(getCurrentUserUsername());
    }

    @Override
    public String getCurrentUserUsername() {
        JWTToken jwtToken = (JWTToken) SecurityContextHolder.getContext().getAuthentication();
        ReadOnlyJWTClaimsSet claimsSet = jwtToken.getClaims();
        return (String) claimsSet.getClaim("username");
    }

    @Override
    public String generateToken(String email, String password) {
        JWTClaimsSet claimsSet = new JWTClaimsSet();
        claimsSet.setClaim("username", email);
        claimsSet.setClaim("password", password);
//        claimsSet.setSubject(email + encoder.encode(password));

        return this.signAndSerializeJWT(claimsSet, "jfodfusdhfiaushfiqwuehfqwuhfsdhflakhewqoifhkadlkahfiqwuehlchushfiuwhuw" +
                "asddddddddddddddddddddddd" +
                "asdasdasdasdasdasdasdadas" +
                "asdasdasdasdasdqwdqdqdqsd" +
                "qsdqsdqsdqsdqdqsdqsdqdqsd");
    }


    private String signAndSerializeJWT(JWTClaimsSet claimsSet, String secret) {
        JWSSigner signer = new MACSigner(secret);
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);
        try {
            signedJWT.sign(signer);
            return signedJWT.serialize();
        } catch (JOSEException e) {
            e.printStackTrace();
            return null;
        }
    }
}
