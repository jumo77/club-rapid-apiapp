package xyz.rapid.service.logic;

import xyz.rapid.aggregate.entities.Team;
import xyz.rapid.service.TeamService;
import xyz.rapid.store.TeamStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceLogic implements TeamService {

    private final TeamStore teamStore;

    public TeamServiceLogic(TeamStore teamStore) {
        this.teamStore = teamStore;
    }

    @Override
    public String register(Team team) {
        if (teamStore.retrieve(team.getId()) != null
                || teamStore.retrieveByGoal(team.getGoal()) != null){
            return "Team already exist";
        }
        return teamStore.create(team);
    }

    @Override
    public Team findTeam(String id) {
        return teamStore.retrieve(id);
    }

    @Override
    public List<Team> findAllTeams() {
        return teamStore.retrieveAll();
    }

    @Override
    public boolean modifyTeam(Team team) {
        if (teamStore.retrieve(team.getId()) == null) return false;
        else {teamStore.update(team);
            return true;}
    }

    @Override
    public boolean deleteTeam(String id) {
        if (teamStore.retrieve(id) == null) return false;
        else {teamStore.delete(id);
            return true;}
    }

    @Override
    public boolean addMember(String teamId, String memberId) {
        if(teamStore.retrieve(teamId) == null) return false;
        else {teamStore.addMember(teamId, memberId);
            return true;}
    }
}
