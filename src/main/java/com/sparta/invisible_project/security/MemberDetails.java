package com.sparta.invisible_project.security;

import com.sparta.invisible_project.model.Authority;
import com.sparta.invisible_project.model.Members;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class MemberDetails implements UserDetails {

    private final Members member;

    public MemberDetails(Members member) {
        this.member = member;
    }

    public Members getMembers() {
        return member;
    }

    @Override
    public String getPassword() {
        return member.getMembers_password();
    }

    @Override
    public String getUsername() {
        return member.getMembers_name();
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Authority authority = member.getAuthority();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority( authority.toString() );

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add( simpleGrantedAuthority );

        return authorities;
    }
}