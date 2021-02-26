package com.atlas.application.configs;

import com.atlas.application.entryPoints.Http401UnauthorizedEntryPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Profile("dev")
@EnableWebSecurity(debug = true)
@Configuration
public class DevWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Http401UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .csrf().disable()
            .httpBasic().disable()
            .formLogin().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
                .antMatchers("/protected").authenticated()
                .anyRequest().permitAll()
            .and()
            .exceptionHandling()
                .authenticationEntryPoint(unauthorizedEntryPoint);
    }
}
