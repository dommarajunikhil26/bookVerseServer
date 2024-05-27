package com.nikhil.bookVerse.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.internal.FirebaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserRoleService {

    private static  final Logger logger = LoggerFactory.getLogger(FirebaseService.class);

    public void setAdminClaim(String uid){
        try{
            Map<String, Object> claims = new HashMap<>();
            claims.put("admin", true);
            FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
        } catch (FirebaseAuthException e){
            logger.error("Error setting admin claim for UID: {}", uid, e);
        }
    }

    public boolean isUserAdmin(String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            Boolean isAdmin = decodedToken.getClaims().get("admin") != null && (Boolean) decodedToken.getClaims().get("admin");
            return Boolean.TRUE.equals(isAdmin);
        } catch (FirebaseAuthException e) {
            logger.error("Error verifying admin role for token: {}", idToken, e);
            return false;
        }
    }

}
