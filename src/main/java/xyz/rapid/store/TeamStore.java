package xyz.rapid.store;

import xyz.rapid.aggregate.entities.Team;

import java.util.List;

public interface TeamStore {
    String create(Team team);
    Team retrieve(String id);
    List<Team> retrieveAll();
    Team retrieveByGoal(String goal);
    void update(Team team);
    void delete(String id);
    void addMember(String teamId, String memberId);
}
