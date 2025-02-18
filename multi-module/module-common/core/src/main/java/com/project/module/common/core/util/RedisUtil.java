package com.project.module.common.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis 사용 util
 */
@Slf4j
@Component
public  class RedisUtil {

    /**
     * The Redis template.
     */
    @Autowired
    RedisTemplate<String, String> redisTemplate;


    /**
     * methodName : getData
     * author :  
     * description : Redis에서 key에 해당하는 값을 가져온다.
     *
     * @param key the key
     * @return object data
     */
    public String getData(String key) {
        return  redisTemplate.opsForValue().get(key);
    }

    /**
     * methodName : getData
     * author :  
     * description : Redis의 HashOps 에서 key에 해당하는 값을 가져온다.
     *
     * @param hashKey
     * @param key
     * @return object
     */
    public String getData(String hashKey, String key){
        return (String) redisTemplate.opsForHash().get(hashKey,key);
    }

    /**
     * methodName : setData
     * author : 
     * description : Redis에 정보를 저장한다.
     *
     * @param key
     * @param value
     */
    public void setData(String key, String value){ redisTemplate.opsForValue().set(key, value);  }

    /**
     * methodName : setDataExpire
     * author : 
     * description : 만료기간을 가진 Data를 Redis에 저장한다.
     *
     * @param key      the key
     * @param value    the value
     * @param duration the duration
     */
    public void setDataExpire(String key, String value, long duration) {
        redisTemplate.opsForValue().set(key, value, duration, TimeUnit.MILLISECONDS);
    }


    /**
     * methodName : setAllData
     * author : 
     * description : Redis에 Map 형태의 데이터를 저장한다.
     *
     * @param hashKey
     * @param keyValueMap
     */
    public void setAllData(String hashKey, Map<String, String> keyValueMap){redisTemplate.opsForHash().putAll(hashKey, keyValueMap);  }


    /**
     * methodName : setData
     * author : 
     * description : Redis의 hashops에 데이터를 저장한다.
     *
     * @param hashKey
     * @param key
     * @param value
     */
    public void setData(String hashKey, String key, String value){ redisTemplate.opsForHash().put(hashKey, key, value);  }


    /**
     * methodName : deleteData
     * author : 
     * description : Redis에서 key에 해당하는 Data를 삭제한다.
     *
     * @param key
     * @return boolean
     */
    public boolean deleteData(String key) {
        try {
            return redisTemplate.delete(key);
        } catch (Exception ex) {
            log.error("Redis Error", ex);
            return false;
        }
    }

    /**
     * methodName : deleteData
     * author : 
     * description : Redis의 hashops에서 Key에 해당하는 데이터를 삭제한다.
     *
     * @param hashKey
     * @param key
     * @return long
     */
    public long deleteData(String hashKey, String key) {
        try {
            return redisTemplate.opsForHash().delete(hashKey, key);
        } catch (Exception ex) {
            log.error("Redis Error", ex);
            return 0L;
        }
    }

    /**
     * methodName : deleteData
     * author : 
     * description : Redis에서 Collection 정보를 삭제한다
     *
     * @param hashKeyCollection
     */
    public void deleteData(Collection<String> hashKeyCollection) {
        try {
            redisTemplate.delete(hashKeyCollection);
        } catch (Exception ex) {
            log.error("Redis Error", ex);
        }
    }

    /**
     * methodName : hasKey
     * author : 
     * description : Key에 해당하는 값이 있는지 확인
     *
     * @param key
     * @return boolean
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * methodName :  getExpire
     * author : 
     * description : Key에 해당하는 값의 Expire 정보를 얻는다.
     *
     * @param key  the hash key
     * @return the expire
     */
    public Long getExpire(String key) {
        try {
            return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
        } catch (Exception ex) {
            log.error("Redis Error", ex);
            return 0L;
        }
    }

    /**
     * methodName :  deleteHashData
     * author : kjm
     * description : hashKey가 가진 키를 모두 삭제한다.
     *
     * @param hashKey
     * @return Long
     */
    public Long deleteHashData(String hashKey) {
        try {
            Set<Object> keySet = redisTemplate.opsForHash().keys(hashKey);
            if (keySet.size()> 0) {
                return redisTemplate.opsForHash().delete(hashKey, keySet.toArray());
            } else {
                return 0L;
            }
        } catch (Exception ex) {
            log.error("Redis Error", ex);
            return 0L;
        }
    }

    /**
     * methodName :  deleteAllData
     * author : kjm
     * description : 키를 패턴으로 찾아 일치하는 키를 모두 삭제한다.
     *
     * @param key
     * @return boolean
     */
    public boolean deleteAllData(String key) {
        try {
            redisTemplate.keys(key + "*").forEach(this::deleteData);
            return true;
        } catch(Exception ex) {
            log.error("Redis Error", ex);
            return false;
        }
    }
}
