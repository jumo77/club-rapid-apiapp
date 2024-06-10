package xyz.rapid.aggregate.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.rapid.aggregate.Entity;
import xyz.rapid.aggregate.titles.Role;


@Getter
@Setter
@NoArgsConstructor
public class Member extends Entity {

    private String name;
    private String password;
    private String phoneNum;
    private String teamId = null;
    private Role role = null;
    private String major;
    private String gender;
    private String grade;

    public Member(String stdNum, String name, String password, String phoneNum, String major, String gender, String grade){
        super(stdNum);  // id = stdNum
        this.name = name;
        this.password = password;
	this.phoneNum = phoneNum;
	this.major = major;
	this.gender = gender;
	this.grade = grade;
    }

}
