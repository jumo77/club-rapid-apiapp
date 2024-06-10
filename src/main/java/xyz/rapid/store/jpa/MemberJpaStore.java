package xyz.rapid.store.jpa;

import xyz.rapid.aggregate.entities.Member;
import xyz.rapid.aggregate.titles.Role;
import xyz.rapid.store.MemberStore;
import xyz.rapid.store.TeamStore;
import xyz.rapid.store.jpa.jpo.MemberJpo;
import xyz.rapid.store.jpa.jpo.TeamJpo;
import xyz.rapid.store.jpa.repository.MemberRepository;
import xyz.rapid.store.jpa.repository.TeamRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
public class MemberJpaStore implements MemberStore {
    private final MemberRepository memberRepository;
    private final TeamStore teamStore;

    public MemberJpaStore(MemberRepository memberRepository, TeamRepository teamRepository, TeamStore teamStore) {
        this.memberRepository = memberRepository;
        this.teamStore = teamStore;
    }

    @Override
    public String create(Member member) {
        memberRepository.save(new MemberJpo(member));
        return member.getId();
    }

    @Override
    public Member retrieve(String id) {
        if (memberRepository.existsById(id)) throw new NoSuchElementException("학번을 확인해주세요");
        return memberRepository.findById(id).get().toDomain();
    }

    @Override
    public List<Member> retrieveAll() {
        return memberRepository.findAll().stream()
                .map(MemberJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public String update(Member member) {
        memberRepository.save(new MemberJpo(member));
        return "변경되었습니다";
    }

    @Override
    public void delete(String id) {
        memberRepository.deleteById(id);
    }

    @Override
    public boolean exist(String id) {
        return memberRepository.existsById(id);
    }

    @Override
    public void addTeam(String memberId, String teamId, Role role) {
        MemberJpo jpo = memberRepository.findById(memberId).get();
        jpo.setTeamJpo(new TeamJpo(teamStore.retrieve(teamId)));
        jpo.setRole(role);
        memberRepository.save(jpo);
    }

    @Override
    public void changeRole(String id, Role role){
        MemberJpo jpo = memberRepository.findById(id).get();
        jpo.setRole(role);
        memberRepository.save(jpo);
    }
}
