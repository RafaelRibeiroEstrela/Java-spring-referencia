package com.example.springdataredis.services.impl;

import com.example.springdataredis.caches.StateCache;
import com.example.springdataredis.dtos.StateDTO;
import com.example.springdataredis.models.State;
import com.example.springdataredis.services.StateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    private final StateCache cache;

    public StateServiceImpl(StateCache cache) {
        this.cache = cache;
    }

    /* Exemplo de comando executado no REDIS
    1699118951.373068 [0 172.18.0.1:39186] "SMEMBERS" "states"
    1699118951.373962 [0 172.18.0.1:39186] "HGETALL" "states:6e7230c0-3f5b-4b96-9fa4-cd2068d4e34d"
    1699118951.375009 [0 172.18.0.1:39186] "HGETALL" "states:03417ab9-e64f-4e52-b1e0-43a5b44daace"
     */
    @Override
    public List<StateDTO> findAll() {
        List<State> list = new ArrayList<>();
        cache.findAll().forEach(list::add);
        return list.stream().map(StateDTO::new).toList();
    }

    /* Exemplo de comando executado no REDIS
    1699118859.705281 [0 172.18.0.1:39186] "HGETALL" "states:6e7230c0-3f5b-4b96-9fa4-cd2068d4e34d"
     */
    @Override
    public StateDTO findById(String id) {
        return new StateDTO(cache.findById(id).orElseThrow(() -> new RuntimeException("")));
    }

    /* Exemplo de comando executado no REDIS
    127.0.0.1:6379> monitor
    1699118744.975568 [0 172.18.0.1:39186] "HELLO" "3"
    1699118745.004325 [0 172.18.0.1:39186] "SISMEMBER" "states" "6e7230c0-3f5b-4b96-9fa4-cd2068d4e34d"
    1699118745.008742 [0 172.18.0.1:39186] "DEL" "states:6e7230c0-3f5b-4b96-9fa4-cd2068d4e34d"
    1699118745.009970 [0 172.18.0.1:39186] "HMSET" "states:6e7230c0-3f5b-4b96-9fa4-cd2068d4e34d" "_class" "com.example.springdataredis.models.State" "id" "6e7230c0-3f5b-4b96-9fa4-cd2068d4e34d" "name" "Rio de Janeiro" "uf" "RJ"
    1699118745.010776 [0 172.18.0.1:39186] "SADD" "states" "6e7230c0-3f5b-4b96-9fa4-cd2068d4e34d"

    127.0.0.1:6379> keys *
    1) "states:6e7230c0-3f5b-4b96-9fa4-cd2068d4e34d"
    2) "states"
     */
    @Override
    public StateDTO save(StateDTO stateDTO) {
        State state = new State();
        copyDTOToEntity(stateDTO, state);
        return new StateDTO(cache.save(state));
    }

    /*
    1699119045.015386 [0 172.18.0.1:39186] "HGETALL" "states:03417ab9-e64f-4e52-b1e0-43a5b44daace"
    1699119045.016639 [0 172.18.0.1:39186] "DEL" "states:03417ab9-e64f-4e52-b1e0-43a5b44daace"
    1699119045.017621 [0 172.18.0.1:39186] "HMSET" "states:03417ab9-e64f-4e52-b1e0-43a5b44daace" "_class" "com.example.springdataredis.models.State" "id" "03417ab9-e64f-4e52-b1e0-43a5b44daace" "name" "S\xc3\xa3o Paulo" "uf" "SP"
     */
    @Override
    public StateDTO update(String id, StateDTO stateDTO) {
        State state = cache.findById(id).orElseThrow(() -> new RuntimeException(""));
        copyDTOToEntity(stateDTO, state);
        return new StateDTO(cache.save(state));
    }

    /*
    1699119217.656085 [0 172.18.0.1:39186] "HGETALL" "states:6e7230c0-3f5b-4b96-9fa4-cd2068d4e34d"
    1699119217.657229 [0 172.18.0.1:39186] "DEL" "states:6e7230c0-3f5b-4b96-9fa4-cd2068d4e34d"
    1699119217.657955 [0 172.18.0.1:39186] "SREM" "states" "6e7230c0-3f5b-4b96-9fa4-cd2068d4e34d"
    1699119217.658556 [0 172.18.0.1:39186] "SMEMBERS" "states:6e7230c0-3f5b-4b96-9fa4-cd2068d4e34d:idx"
    1699119217.659217 [0 172.18.0.1:39186] "DEL" "states:6e7230c0-3f5b-4b96-9fa4-cd2068d4e34d:idx"
     */
    @Override
    public void delete(String id) {
        cache.deleteById(id);
    }

    private void copyDTOToEntity(StateDTO stateDTO, State state) {
        state.setName(stateDTO.getName());
        state.setUf(stateDTO.getUf());
    }
}
