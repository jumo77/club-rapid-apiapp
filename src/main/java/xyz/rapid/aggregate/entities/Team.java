package xyz.rapid.aggregate.entities;

import lombok.Getter;
import lombok.Setter;
import xyz.rapid.aggregate.Entity;

import java.util.List;

@Getter
@Setter
public class Team extends Entity {

    private String name;
    private String goal;
    private List<String> membersId;

    public Team(){
        super();
    }

    public Team(String name, String goal){
        this();
        this.name = name;
        this.goal = goal;
    }

}
