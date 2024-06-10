package xyz.rapid.store.jpa.jpo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.rapid.aggregate.entities.Team;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Team")
public class TeamJpo {

    @Id
    private String id;
    private String name;
    private String goal;

    @OneToMany(mappedBy = "teamJpo", targetEntity = MemberJpo.class)
    private List<MemberJpo> memberJpos = new ArrayList<>();

    public TeamJpo(Team team){
        BeanUtils.copyProperties(team, this);
    }

    public Team toDomain(){
        Team team= new Team();
        BeanUtils.copyProperties(this, team);
        return team;
    }
}
