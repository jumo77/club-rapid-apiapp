package xyz.rapid.store.jpa.jpo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.rapid.aggregate.Data;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Data")
public class DataJpo {

    @Id
    private String id;
    private String img;
    private String url;
    private String title;
    private String brief;
    private String category;
    private int indes;

    public DataJpo(Data data){
        BeanUtils.copyProperties(data, this);
    }

    public Data toDomain(){
        Data data = new Data();
        BeanUtils.copyProperties(this, data);
        return data;
    }
}
