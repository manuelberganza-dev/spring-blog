package com.manuelberganza.blog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {

    @Bean
    public UserDetailsManager usersCustom(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("select username,password,estatus from Usuarios u where username=?");
        users.setAuthoritiesByUsernameQuery("select u.username,p.nombre from UsuariosPerfiles up " +
                "inner join Usuarios u on u.id = up.idUsuario " +
                "inner join Perfiles p on p.id = up.idPerfil " +
                "where u.username=?");
        return users;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/bootstrap/**","/css/**","/js/**", "/tinymce/**").permitAll()
                .requestMatchers( "/login", "/registro").permitAll()
                .requestMatchers("/home/**").hasAnyAuthority("USUARIO")
                .anyRequest().authenticated());

        http.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/").permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
