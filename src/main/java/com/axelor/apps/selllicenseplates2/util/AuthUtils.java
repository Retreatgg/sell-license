package com.axelor.apps.selllicenseplates2.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthUtils {

    public static String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication Object: {}", authentication);
        if (authentication != null) {
           log.info("getPrincipal: {}",  authentication.getPrincipal() instanceof User);
            return authentication.getName();
        }
        return null;
    }
}
