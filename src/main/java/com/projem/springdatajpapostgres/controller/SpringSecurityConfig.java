package com.projem.springdatajpapostgres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/api/m1/kisi").hasAnyRole("USER")
                .antMatchers("/api/m1/kisi/{id}").hasAnyRole("ADMIN")
                .antMatchers("/api/m2/adres").hasAnyRole("USER")
                .antMatchers("/api/m2/adres/{id}").hasAnyRole("ADMIN")
                .and()
                .csrf().disable();
    }

    @Autowired
    public void configureAuthGlobal(AuthenticationManagerBuilder auth){
        try {
            auth.inMemoryAuthentication()
                    .withUser("user").password(passwordEncoder().encode("1")).roles("USER")
                    .and()
                    .withUser("admin").password(passwordEncoder().encode("12")).roles( "ADMIN");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

