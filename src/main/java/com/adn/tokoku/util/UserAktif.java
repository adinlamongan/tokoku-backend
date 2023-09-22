package com.adn.tokoku.util;


import com.adn.tokoku.model.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class UserAktif {
    public int getUserId(){
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

}
