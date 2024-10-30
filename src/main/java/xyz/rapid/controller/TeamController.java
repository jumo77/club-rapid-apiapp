package xyz.rapid.controller;

import xyz.rapid.aggregate.entities.Member;
import xyz.rapid.aggregate.entities.Team;
import xyz.rapid.aggregate.titles.Role;
import xyz.rapid.service.MemberService;
import xyz.rapid.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final MemberService memberService;
    private final TeamService teamService;


    public TeamController(MemberService memberService, TeamService teamService) {
        this.memberService = memberService;
        this.teamService = teamService;
    }

    @GetMapping
    public String teamPage(){
        return "team";
    }

    @PostMapping("/register")
    public String registerTeam(@RequestBody Team team){
        return teamService.register(team);
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable String id){
        return teamService.findTeam(id);
    }

    @GetMapping("/search")
    public List<Team> getTeamsByName(){
        return teamService.findAllTeams();
    }

    @PutMapping("/modify")
    public boolean modifyTeam(@RequestBody Team team){
        return teamService.modifyTeam(team);
    }

    @PutMapping("/addMember")
    public boolean addMember(@RequestBody Team team, @RequestBody String memberId){
        return teamService.addMember(team.getId(), memberId);
    }

    @DeleteMapping("/delete")
    public boolean deleteTeam(@RequestBody Team team, @RequestBody String memberId){
        if (team.getMembersId().indexOf(memberId)!=0) return false;
        else return teamService.deleteTeam(team.getId());
    }

    @PutMapping("/changeLeader")
    public boolean changeLeader(@RequestBody String leaderId, @RequestBody String pre_leaderId){
        Member leader = memberService.findMember(leaderId);
        Team team = teamService.findTeam(leader.getTeamId());
        if (leader.getRole() != Role.Leader || !memberService.findMember(pre_leaderId).getTeamId().equals(team.getId())) return false;
        else {
            memberService.changeRole(leaderId, Role.Member);
            memberService.changeRole(pre_leaderId, Role.Leader);
            return true;
        }
    }



}
