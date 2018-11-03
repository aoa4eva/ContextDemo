package me.afua.contextdemo;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

@Controller
public class MainController {
    @RequestMapping("/")
    public @ResponseBody String showDefaultPage(Authentication appDetails)
    {
        return "Hello,"+appDetails.getName()+"!";
    }

    @RequestMapping("/addauthority")
    public @ResponseBody String addAuthority()
    {
        //Add permissions to current user (use the database to do this)
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        Authentication reAuth = new UsernamePasswordAuthenticationToken("user",new BCryptPasswordEncoder().encode("password"),authorities);
        SecurityContextHolder.getContext().setAuthentication(reAuth);
        return "You have new permissions!";
    }

    @RequestMapping("/admin")
    public @ResponseBody String onlyAdmin()
    {
        return "Hello, Admin user!";
    }
}
