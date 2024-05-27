package com.nikhil.bookVerse.controller;

import com.google.firebase.internal.FirebaseService;
import com.nikhil.bookVerse.service.UserRoleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/secure/setAdmin")
    public String setAdmin(@RequestParam String uid){
        userRoleService.setAdminClaim(uid);
        return "Admin claim set successfully";
    }

    @GetMapping("/secure/verifyRole")
    public String verifyRole(@RequestHeader("Authorization") String authorization) {
        String idToken = authorization.replace("Bearer ", "");
        boolean isAdmin = userRoleService.isUserAdmin(idToken);
        return isAdmin ? "User is an admin." : "User is not an admin.";
    }
}
