package xyz.rapid.service;

import xyz.rapid.aggregate.entities.Team;

import java.util.List;

public interface TeamService {
    String register(Team team);
    Team findTeam(String id);
    List<Team> findAllTeams();
    boolean modifyTeam(Team team);
    boolean deleteTeam(String id);
    boolean addMember(String teamId, String memberId);

}
