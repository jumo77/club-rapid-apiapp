package xyz.rapid.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import xyz.rapid.aggregate.Entity;
import xyz.rapid.aggregate.entities.Member;
import xyz.rapid.aggregate.titles.Role;

@Getter
@Setter
public class MemberDto {
    private String id;
    private String name;
    private String teamId;
    private Role role;

    public MemberDto(Member member){
        BeanUtils.copyProperties(member, this);
    }
}
