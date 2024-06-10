package xyz.rapid.controller;

import xyz.rapid.aggregate.Data;
import xyz.rapid.store.DataStore;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IndexController {

    private final DataStore dataStore;

    public IndexController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @GetMapping("/maindata")
    public List<Data> sendMainData(){
        return dataStore.retrieveAllByCategory("main");
    }

    @GetMapping("/frontend")
    public List<Data> sendFrontEndData(){
        return dataStore.retrieveAllByCategory("frontend");
    }

    @GetMapping("/backend")
    public List<Data> sendBackEndData(){
        return dataStore.retrieveAllByCategory("backend");
    }

    @GetMapping("/mobile")
    public List<Data> sendMobileData(){
        return dataStore.retrieveAllByCategory("mobile");
    }

    @GetMapping("/data/{id}")
    public Data sendData(@PathVariable String id){
        return dataStore.retrieveById(id);
    }

    @PostMapping("/data")
    public String saveData(@RequestBody Data data){
        return dataStore.create(data);
    }
}
