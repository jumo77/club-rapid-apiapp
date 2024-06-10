package xyz.rapid.service.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import xyz.rapid.store.jpa.jpo.MemberJpo;

import java.util.ArrayList;
import java.util.Collection;

public class MemberDetail implements UserDetails {

    private final MemberJpo memberJpo;

    public MemberDetail(MemberJpo memberJpo) {
        this.memberJpo = memberJpo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return memberJpo.getRole().toString();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return memberJpo.getPassword();
    }

    @Override
    public String getUsername() {
        return memberJpo.getStdNum();
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
        return false;
    }
}
