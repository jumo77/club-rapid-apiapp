package xyz.rapid.store.jpa.jpo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.rapid.aggregate.entities.Member;
import xyz.rapid.aggregate.titles.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Member")
public class MemberJpo {

    @Id
    private String stdNum;
    @Column(nullable = false)
    @NonNull
    private String password;
    @Column(nullable = false)
    @NonNull
    private String name;
    @Column(nullable = false)
    @NonNull
    private String phoneNum;
    @Column(nullable = false)
    @NonNull
    private String major;
    @Column(nullable = false)
    @NonNull
    private String gender;
    @Column(nullable = false)
    @NonNull
    private String grade;


    private Role role=null;

    @ManyToOne(targetEntity = TeamJpo.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private TeamJpo teamJpo;



    public MemberJpo(Member member){
        this.stdNum=member.getId();
        BeanUtils.copyProperties(member, this);
    }

    public Member toDomain(){
        Member member = new Member(null, null, null, null, null, null, null);
        BeanUtils.copyProperties(this, member);
        return member;
    }
}
