package xyz.rapid.controller;

import xyz.rapid.aggregate.entities.Member;
import xyz.rapid.service.MemberService;
import org.springframework.web.bind.annotation.*;
import xyz.rapid.service.dto.MemberDto;
import xyz.rapid.service.dto.MemberIO;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String memberPage(){
        return "member";
    }

    @PostMapping
    public String register(@RequestBody Member member){
	    return memberService.register(member);
    }

    @GetMapping("/{id}")
    public MemberDto getMemberDtoById(@PathVariable String id){
        return memberService.findMemberDto(id);
    }

    @GetMapping("/info")
    public Member getMemberById(@RequestBody MemberIO io){
       return memberService.findMemberWithVerification(io);
    }

    @GetMapping("/search")
    public List<MemberDto> getMembers(){
        return memberService.findAllMemberDto();
    }

    @PutMapping
    public String changeMemberInfo(@RequestBody Member member){
        return memberService.modifyMember(member);
    }

    @DeleteMapping
    public boolean deleteMember(@RequestBody Member member){
        return memberService.deleteMember(member.getId());
    }

}
