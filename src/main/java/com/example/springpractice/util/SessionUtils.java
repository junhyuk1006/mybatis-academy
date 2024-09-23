package com.example.springpractice.util;

import com.example.springpractice.dto.User;
import jakarta.servlet.http.HttpSession;

public class SessionUtils {
    public static User getSessionUser(HttpSession session){
        return (User) session.getAttribute("user");
    }
}
