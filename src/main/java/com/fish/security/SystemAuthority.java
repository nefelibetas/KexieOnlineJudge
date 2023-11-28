package com.fish.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SystemAuthority implements GrantedAuthority {
    private final String role;
    @JsonCreator
    public SystemAuthority(@JsonProperty("authority") String role) {
        this.role = role;
    }
    @Override
    public String getAuthority() {
        return this.role;
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof SimpleGrantedAuthority simpleGrantedAuthority) {
            return this.role.equals(simpleGrantedAuthority.getAuthority());
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.role.hashCode();
    }
    @Override
    public String toString() {
        return this.role;
    }
}
