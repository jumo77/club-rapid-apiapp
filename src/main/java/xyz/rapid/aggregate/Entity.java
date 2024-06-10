package xyz.rapid.aggregate;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Entity {
    protected String id;

    protected Entity(){
        this.id = UUID.randomUUID().toString();
    }

    protected Entity(String id){
        this.id = id;
    }

}
