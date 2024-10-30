package xyz.rapid.store.jpa;


import xyz.rapid.aggregate.Data;
import xyz.rapid.store.DataStore;
import xyz.rapid.store.jpa.jpo.DataJpo;
import xyz.rapid.store.jpa.repository.DataRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataJpaStore implements DataStore {

    private final DataRepository dataRepository;

    public DataJpaStore(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }


    @Override
    public List<Data> retrieveAllByCategory(String category) {
        return dataRepository.findAllByCategory(category, Sort.by(Sort.Direction.ASC, "indes")).stream()
                .map(DataJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Data retrieveById(String id) {
        return dataRepository.findById(id).get().toDomain();
    }

    @Override
    public String create(Data data) {
        dataRepository.save(new DataJpo(data));
        return data.getId();
    }
}
