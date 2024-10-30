package xyz.rapid.store;

import xyz.rapid.aggregate.Data;

import java.util.List;

public interface DataStore {

    List<Data> retrieveAllByCategory(String category);
    Data retrieveById(String id);
    String create(Data data);
}
