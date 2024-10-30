package xyz.rapid.store;

import xyz.rapid.aggregate.entities.Member;
import xyz.rapid.aggregate.titles.Role;

import java.util.List;

public interface MemberStore {
    String create(Member member);

    Member retrieve(String id);

    List<Member> retrieveAll();

    String update(Member member);

    void delete(String id);

    boolean exist(String id);

    void addTeam(String memberId, String teamId, Role role);

    void changeRole(String id, Role role);
}
