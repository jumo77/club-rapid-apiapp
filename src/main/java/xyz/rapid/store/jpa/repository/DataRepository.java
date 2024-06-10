package xyz.rapid.store.jpa.repository;

import xyz.rapid.store.jpa.jpo.DataJpo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataRepository extends JpaRepository<DataJpo, String> {
    List<DataJpo> findAllByCategory(String category, Sort sort);
}
