package by.andrey.springcourse.Project2Boot.security;

// Это класс-обертка над сущностью


import by.andrey.springcourse.Project2Boot.models.Userr;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class UserrDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final Userr userr;

    public UserrDetails(Userr userr) {
        this.userr = userr;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //Все права пользователя
        return Collections.singletonList(new SimpleGrantedAuthority(userr.getRole()));
    }

    @Override
    public String getPassword() {
        return this.userr.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userr.getUsername();
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

    public Userr getUserr() {
        return userr;
    }
}
