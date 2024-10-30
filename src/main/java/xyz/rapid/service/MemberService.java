package xyz.rapid.service;

import xyz.rapid.aggregate.entities.Member;
import xyz.rapid.aggregate.titles.Role;
import xyz.rapid.service.dto.MemberDto;
import xyz.rapid.service.dto.MemberIO;

import java.util.List;

public interface MemberService {
     String register(Member member);
     Member findMember(String id);
     MemberDto findMemberDto(String id);
     Member findMemberWithVerification(MemberIO io);
     List<Member> findAllMember();
     List<MemberDto> findAllMemberDto();
     String modifyMember(Member member);
     boolean deleteMember(String id);
     String addTeam(String memberId, String teamId, Role role);
     void changeRole(String id, Role role);

}
