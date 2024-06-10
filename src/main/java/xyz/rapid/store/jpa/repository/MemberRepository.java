package xyz.rapid.store.jpa.repository;


import xyz.rapid.store.jpa.jpo.MemberJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberJpo, String> {

}
