package com.iris.java.onlinejudge.web.redis;

import com.alibaba.fastjson.JSON;
import com.iris.java.onlinejudge.web.redis.PrefixKeys.KeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {
    @Autowired
    JedisPool jedisPool;


    /*
        获取单个对象
     */

    // 传入的Prefix，是个"接口"
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis = null;
        try{
            //第一步：生成真正的key
            String realKey = prefix.getPrefix() + key;

            jedis = jedisPool.getResource();
            // 连接池，注意：使用完后一定要释放，还给池
            // 所以：用 try-finally 语块

            String str = jedis.get(realKey);

            // 从redis里直接取出的任何对象，都是"字符串"（序列化生成的）
            // 所以要反序列化，还原原对象，最终返回原对象。
            // (反序列化的过程中，要指定：转成哪个类型的对象)
            T bean = stringToBean(str,clazz);

            return bean;
        }finally{
            returnToPool(jedis);
        }
    }


    /*
        设置对象
     */
    public <T> boolean set(KeyPrefix prefix,String key,T value){
        Jedis jedis = null;
        try{

            String realPrefix = prefix.getPrefix() + key;

            jedis = jedisPool.getResource();

            // 注意：往redis里放的，只能是string。
            // 所以要先序列化（转成string），再放入
            String str = beanToString(value);
            if(str == null || str.length()<=0){
                return false;
            }

            // 看 expire(过期时间）：
            int expire = prefix.expireSeconds();
            if(expire <= 0){ // 如果正被插入的数永不过期，则直接调用jedis.set()
                jedis.set(realPrefix,str);
            }
            else{
                jedis.setex(realPrefix,expire,str); // 如果有过期时间，则要调用jedis.setex() [相当于先set，再setExpire]
            }
            return true;
        }finally {
            returnToPool(jedis);
        }
    }


    /*

        判断给定的key是否存在

     */
    public boolean exit(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();

            String realKey = prefix.getPrefix()+key;

            return (jedis.exists(realKey));
        }finally{
            returnToPool(jedis);
        }

    }


    /*

    删除指定Key的redis缓存

 */
    public boolean delete(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();

            String realKey = prefix.getPrefix()+key;

            long ret = jedis.del(realKey);

            return ret>0;
        }finally{
            returnToPool(jedis);
        }

    }


    /*
     * 增加值（只在value为数字时可用）
     */
    public <T> Long incur(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;

            return jedis.incr(realKey); // 最终调用了jedis内置的incr方法

        }finally{

        }
    }



    /*
     * 减少值（只在value为数字时可用）
     */
    public <T> Long decur(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;

            return jedis.decr(realKey);
        }finally {
            returnToPool(jedis); // 最终调用了jedis内置的decr方法
        }
    }



    // String 反序列化成Bean （目前不支持列表类型）
    private <T> T stringToBean(String str,Class<T> clazz) {

        if(str == null || str.length()<=0 || clazz == null){ // 注意：参数校验必不可少
            return null;
        }


        if(clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        }else if(clazz == long.class || clazz == Long.class){
            return (T)Long.valueOf(str);
        }else if(clazz == String.class){
            return (T)str;
        }else{
            return JSON.toJavaObject( JSON.parseObject(str),clazz);
        }
    }


    private <T> String beanToString(T value) {
        // bean转字符串的情况，分为以下几类：
        // 1：null ； 2：基本类型； 3:直接就是String ； 4：复杂对象

        if(value == null){
            return null;
        }

        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class) {
            return "" + value;
        }else if(clazz == long.class || clazz == Long.class){
            return "" + value;
        }else if(clazz == String.class){
            return (String)value;
        }else{
            return JSON.toJSONString(value);
        }
    }

    private  void returnToPool(Jedis jedis) {
        if(jedis != null){
            jedis.close();
        }
    }


}
