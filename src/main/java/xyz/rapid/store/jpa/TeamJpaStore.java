package xyz.rapid.store.jpa;

import xyz.rapid.aggregate.entities.Team;
import xyz.rapid.store.TeamStore;
import xyz.rapid.store.jpa.jpo.TeamJpo;
import xyz.rapid.store.jpa.repository.TeamRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TeamJpaStore implements TeamStore {
    private final TeamRepository teamRepository;

    public TeamJpaStore(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public String create(Team team) {
        teamRepository.save(new TeamJpo(team));
        return team.getId();
    }

    @Override
    public Team retrieve(String id) {
        return teamRepository.findById(id).get().toDomain();
    }

    @Override
    public List<Team> retrieveAll() {
        return teamRepository.findAll().stream()
                .map(TeamJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Team retrieveByGoal(String goal) {
        return teamRepository.findByGoal(goal).toDomain();
    }

    @Override
    public void update(Team team) {
        teamRepository.save(new TeamJpo(team));
    }

    @Override
    public void delete(String id) {
        teamRepository.deleteById(id);
    }

    @Override
    public void addMember(String teamId, String memberId) {
        Team team = teamRepository.findById(teamId).get().toDomain();
        team.getMembersId().add(memberId);
        teamRepository.save(new TeamJpo(team));
    }
}
