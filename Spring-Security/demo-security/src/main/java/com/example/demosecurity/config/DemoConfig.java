package com.example.demosecurity.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Configuration
@Controller()
public class DemoConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Karan").password("karan123").authorities("qa")
                .and()
                .withUser("Rashmi").password("rashmi123").authorities("dev");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/testcode").hasAuthority("qa")
                .antMatchers("/developcode").hasAuthority("dev")
                .antMatchers("/accessserver/**").hasAnyAuthority("qa", "dev")
                .antMatchers("/home").permitAll()
                .and().formLogin();
    }

    @Bean
    PasswordEncoder getPE(){
        return NoOpPasswordEncoder.getInstance();
    }
}
