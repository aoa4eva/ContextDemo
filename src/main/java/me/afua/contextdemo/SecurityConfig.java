package me.afua.contextdemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/","/addauthority").hasAuthority("USER")
                .antMatchers("/admin").hasAuthority("ADMIN")
                .and().formLogin();
        http.headers().frameOptions().disable().and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password(new BCryptPasswordEncoder().encode("password"))
                .authorities("USER").and().passwordEncoder(new BCryptPasswordEncoder());
    }
}
