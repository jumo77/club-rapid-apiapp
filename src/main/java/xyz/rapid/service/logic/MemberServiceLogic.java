package xyz.rapid.service.logic;

import com.google.gson.Gson;
import xyz.rapid.aggregate.entities.Member;
import xyz.rapid.aggregate.titles.Role;
import xyz.rapid.service.MemberService;
import xyz.rapid.service.dto.MemberDto;
import xyz.rapid.service.dto.MemberIO;
import xyz.rapid.store.MemberStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MemberServiceLogic implements MemberService {

    private final MemberStore memberStore;

    public MemberServiceLogic(MemberStore memberStore) {
        this.memberStore = memberStore;
    }

    @Override
    public String register(Member member) {
        System.out.println(new Gson().toJson(member));
	    if (memberStore.exist(member.getId())) return "Member already exist";
        else return memberStore.create(member);
    }

    @Override
    public Member findMember(String id) {
        if (!memberStore.exist(id)) return null;
        else return memberStore.retrieve(id);
    }

    @Override
    public MemberDto findMemberDto(String id) {
        return new MemberDto(memberStore.retrieve(id));
    }

    @Override
    public Member findMemberWithVerification(MemberIO io) {
        Member member = memberStore.retrieve(io.getId());
        if (!member.getPassword().equals(io.getPassword())) throw new NoSuchElementException("비밀번호를 확인해주세요");
        else if (!member.getName().equals(io.getName())) throw new NoSuchElementException("이름을 확인해주세요");
        else if (!member.getPhoneNum().equals(io.getPhoneNum())) throw new NoSuchElementException("전화번호를 확인해주세요");
        else return member;
    }

    @Override
    public List<Member> findAllMember() {
        return memberStore.retrieveAll();
    }

    @Override
    public List<MemberDto> findAllMemberDto() {
        return memberStore.retrieveAll().stream()
                .map(MemberDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public String modifyMember(Member member) {
        Member prevMember = memberStore.retrieve(member.getId());
        if (!prevMember.getName().equals(member.getName())) throw new NoSuchElementException("이름을 확인해주세요");
        else if (!prevMember.getPhoneNum().equals(member.getPhoneNum())) throw new NoSuchElementException("전화번호를 확인해주세요");
        else return memberStore.update(member);
    }

    @Override
    public boolean deleteMember(String id) {
        if (memberStore.exist(id)) return false;
        else {
            memberStore.delete(id);
            return true;
        }
    }

    @Override
    public String addTeam(String memberId, String teamId, Role role) {
        Member member = memberStore.retrieve(memberId);
        if (member.getTeamId() != null ) throw new NoSuchElementException("이미 팀이 존재합니다.");
        else {
            memberStore.addTeam(memberId,teamId,role);
            return "성공적으로 가입되었습니다.";
        }
    }

    @Override
    public void changeRole(String id, Role role) {
        Member member = memberStore.retrieve(id);
        if (member.getTeamId() == null ) throw new NoSuchElementException("팀에 가입한 뒤 가능합니다");
        else memberStore.changeRole(id,role);
    }
}
