package com.example.data.user;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Value
@Document(collection = "users")
public class User implements UserDetails {
    @Id
    String id;
    String username;
    String password;
    String email;
    UserRole role;
    List<String> countriesTracked;

    public User changeName(String name){
        return new User(id,name,password,email,role,countriesTracked);
    }

    public User addToCountriesTracked(List<String> countriesTracked) {
        this.countriesTracked.addAll(countriesTracked);
        return new User(id,username,password,email,role,this.countriesTracked);
    }

    public static User of(String id,String username,String password,String email,UserRole role,List<String> countriesTracked){
        return new User(id,username,BCrypt.hashpw(password,BCrypt.gensalt()),email,role,countriesTracked);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
