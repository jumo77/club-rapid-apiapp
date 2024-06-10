package xyz.rapid.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberIO {

    private String id;
    private String name;
    private String password;
    private String phoneNum;
}
