package es.alex.taller.controller.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {
        String viewName = "user/welcome_user";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            viewName = "admin/welcome_admin";
        } else if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_GUEST"))) {
            viewName = "guest/welcome_guest";
        }

        return viewName;
    }
}
