package com.example.armstorage.security.jwt;

import com.example.armstorage.entities.RoleEntity;
import com.example.armstorage.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final Long id;
    private final String phoneNumber;
    private final String password;
    private final RoleEntity role;

    public static UserDetails of(UserEntity userEntity) {
        return new UserDetailsImpl(
                userEntity.getId(),
                userEntity.getLogin(),
                userEntity.getPassword(),
                userEntity.getRole()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}