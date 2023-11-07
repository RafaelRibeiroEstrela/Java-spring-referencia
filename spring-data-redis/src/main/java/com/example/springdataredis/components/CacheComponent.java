package com.example.springdataredis.components;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Component
public class CacheComponent {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final long TTL_EXPIRE = 1;

    public CacheComponent(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String key, Object value) {
        // SALVA UM CHAVE - VALOR
        redisTemplate.opsForValue().set(key, value);
    }

    public void save(String key, Object value, long ttl) {
        // SALVA UM CHAVE - VALOR COM TEMPO DE VIDA
        redisTemplate.opsForValue().set(key, value, Duration.of(ttl, ChronoUnit.SECONDS));
    }

    public void saveValueInSet(String keySet, Object value) {
        // ADICIONA UM VALOR EM UM CONJUNTO
        redisTemplate.opsForSet().add(keySet, value);
    }

    public Object findValueByKey(String key) {
        // BUSCA UM VALOR POR CHAVE
        return redisTemplate.opsForValue().get(key);
    }

    public Set<Object> findSetByKey(String keySet) {
        // BUSCA UM CONJUNTO POR CHAVE
        return redisTemplate.opsForSet().members(keySet);
    }

    public void expireKey(String key) {
        redisTemplate.expire(key, Duration.of(TTL_EXPIRE, ChronoUnit.SECONDS));
    }

    public void removeValueFromSet(String keySet, Object value) {
        redisTemplate.opsForSet().remove(keySet, value);
        Set<Object> set = redisTemplate.opsForSet().members(keySet);
        if (set == null || set.isEmpty()) {
            redisTemplate.expire(keySet, Duration.of(TTL_EXPIRE, ChronoUnit.SECONDS));
        }
    }
}
