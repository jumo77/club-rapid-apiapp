package xyz.rapid.service.logic;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.rapid.service.dto.MemberDetail;
import xyz.rapid.store.MemberStore;
import xyz.rapid.store.jpa.jpo.MemberJpo;

@Service
public class MemberDetailServiceLogic implements UserDetailsService {

    private final MemberStore memberStore;

    public MemberDetailServiceLogic(MemberStore memberStore) {
        this.memberStore = memberStore;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MemberJpo memberJpo = new MemberJpo(memberStore.retrieve(username));
        if (memberStore.exist(username)) return new MemberDetail(memberJpo);
        else throw new UsernameNotFoundException("No user found");
    }
}
