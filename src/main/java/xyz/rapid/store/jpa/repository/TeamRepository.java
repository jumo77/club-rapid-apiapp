package xyz.rapid.store.jpa.repository;

import xyz.rapid.store.jpa.jpo.TeamJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamJpo, String> {
    TeamJpo findByGoal(String goal);
}
