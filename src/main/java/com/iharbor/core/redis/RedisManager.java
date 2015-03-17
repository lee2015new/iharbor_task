package com.iharbor.core.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.iharbor.common.session.Session;

@Service
public class RedisManager {

	@Autowired
	RedisTemplate<Serializable, Serializable> redisTemplate;
	private static final Logger logger = Logger.getLogger(RedisManager.class);
	private boolean enableSessionTimeOut = false;
    private static final String DEFAULT_SESSION_KEY_PREFIX = "IHARBOR:SESSION:";
    private static final long DEFAULT_SESSION_TIMEOUT = 1800;
    private static final String DEFAULT_SYS_KEY_SECRET_PREFIX = "IHARBOR:SYSKEY_SECRET:";
    
    private Long timeOut = DEFAULT_SESSION_TIMEOUT;
    private String keyPrefix = DEFAULT_SESSION_KEY_PREFIX;
    private String keySecretPrefix = DEFAULT_SYS_KEY_SECRET_PREFIX;

	public void save(final String key, final Object value) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(serializeKey(key), serializeValue(value));
				return null;
			}
		});
	}
	
	public void save(final String key, final Object value, final Object value2) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.hSet(serializeKey(key), serializeValue(value), serializeValue(value2));
				return null;
			}
		});
	}


	public Object read(final String key) {
		return redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] keyBytes = serializeKey(key);
				if (connection.exists(keyBytes)) {
					byte[] valueBytes = connection.get(keyBytes);
					return deserializeValue(valueBytes);
				}
				return null;
			}
		});
	}
	
	public Object read(final String key, final Object filed) {
		return redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] keyBytes = serializeKey(key);
				if (connection.exists(keyBytes)) {
					byte[] valueBytes = connection.hGet(keyBytes, serializeValue(filed));
					return deserializeValue(valueBytes);
				}
				return null;
			}
		});
	}


	public Long delete(final String key) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.del(serializeKey(key));
			}
		});
	}


	public Boolean hSet(final String key, final byte[] field, final byte[] value) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.hSet(serializeKey(key), field, value);
			}
		});
	}


	public Object hGet(final String key, final byte[] field) {
		return redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] value = connection.hGet(serializeKey(key), field);
				return deserializeValue(value);
			}

		});
	}


	public void hmSet(final String key, final Map<Object, Object> mapObject) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				
				Map<byte[], byte[]> mapByte = new HashMap<byte[], byte[]>(mapObject.size());
				
				for (final Entry<Object, Object> entry : mapObject.entrySet()) {
					byte[] mapKey = serializeValue(entry.getKey());
					byte[] mapValue = serializeValue(entry.getValue());
					mapByte.put(mapKey, mapValue);
				}

				connection.hMSet(serializeKey(key), mapByte);
				
				mapByte = null;// release

				return null;
			}

		});
	}
	

	public List<Object> hmGet(final String key, final Object... field) {
		return redisTemplate.execute(new RedisCallback<List<Object>>() {
			public List<Object> doInRedis(RedisConnection connection)
					throws DataAccessException {
				
				List<byte[]> redisRet = null;
				List<Object> ret = new ArrayList<Object>();
				if (field.length > 1) {
					byte[][] fields = new byte[field.length][];
					int i = 0;
					for (Object object : field) {
						fields[i] = serializeValue(object);
						i++;
					}
					redisRet = connection.hMGet(serializeKey(key), fields);
				}
				
				else {
					redisRet = connection.hMGet(serializeKey(key), serializeValue(field[0]));
				}
				
				if (null != redisRet) {
					for (byte[] value : redisRet) {
						if (value != null)
							ret.add(deserializeValue(value));
					}
				}
				
				return ret.size() > 0 ? ret : null;
			}
		});
	}
	

	@SuppressWarnings("unchecked")
	public Map<Object, Object> hGetAll(final String key) {
		return (Map<Object, Object>) redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				Map<byte[], byte[]> mapByte = connection.hGetAll(serializeKey(key));
				Map<Object, Object> mapObject = new HashMap<Object, Object>(mapByte.size());
				
				for (final Entry<byte[], byte[]> entry : mapByte.entrySet()) {
					Object mapKey = deserializeValue(entry.getKey());
					Object mapValue = deserializeValue(entry.getValue());
					mapObject.put(mapKey, mapValue);
				}
				
				mapByte = null;// release
				
				return mapObject;
			}
			
		});
	}
	

	public Long lPush(final String key, final Object... values) {
		return (Long) redisTemplate.execute(new RedisCallback<Object>() {

			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				Long count = new Long(0);
				for (Object value : values) {
					byte[] keyByte = serializeKey(key);
					byte[] val = serializeValue(value);
					count = connection.lPush(keyByte, val);
				}
				return count;
			}
			
		});
	}
	

	public List<Object> lRange(final String key, final long begin, final long end) {
		return (List<Object>) redisTemplate.execute(new RedisCallback<List<Object>>() {

			public List<Object> doInRedis(RedisConnection connection)
					throws DataAccessException {
				List<byte[]> retByteLst = connection.lRange(serializeKey(key), begin, end);
				if (null != retByteLst) {
					List<Object> retValLst = new ArrayList<Object>(retByteLst.size());
					for (byte[] retValByte : retByteLst) {
						Object val = deserializeValue(retValByte);
						retValLst.add(val);
					}
					return retValLst;
				}
				return null;
			}
			
		});
	}
	

	public Object lPop(final String key) {
		return redisTemplate.execute(new RedisCallback<Object>(){

			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] value = connection.lPop(serializeKey(key));
				return deserializeValue(value);
			}
			
		});
	}
	

	public Object rPop(final String key) {
		return redisTemplate.execute(new RedisCallback<Object>(){

			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] value = connection.rPop(serializeKey(key));
				return deserializeValue(value);
			}
			
		});
	}
	
    public void addSession(final String sessionId,Session session) {
		if(enableSessionTimeOut){
			save(keyPrefix+sessionId, this.timeOut, session);
		}else{
			save(keyPrefix+sessionId, session);
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("添加session：" + sessionId);
		}
    }

    public Session getSession(final String sessionId) {
    	Session rtn = null;
    	if(enableSessionTimeOut){
    		//刷新session到期时间 
    		redisTemplate.execute(new RedisCallback<Object>(){
        		public Boolean doInRedis(RedisConnection connection){
        			Boolean count = connection.expire((keyPrefix+sessionId).getBytes(), timeOut);
        			connection.close();
        			return count;
        		}
        	});
    	}
    	
    	if(logger.isDebugEnabled()){
    		logger.debug("获取session ：" + sessionId);
    	}
    	
    	if(enableSessionTimeOut){
    		rtn = (Session)read(keyPrefix+sessionId, this.timeOut);
    	}else{
    		rtn = (Session)read(keyPrefix+sessionId);
    	}
        return rtn;
    }
    
    public void addSysKeySecret(final String key, final String secret) {
			save(keySecretPrefix+key, secret);
    }
    
    public String findSysKeySecret(final String key){
    	return String.valueOf(read(keySecretPrefix+key));
    }
	

	protected byte[] serializeKey(final String key) {
		return redisTemplate.getStringSerializer().serialize(key);
	}

	@SuppressWarnings("unchecked")
	protected byte[] serializeValue(final Object value) {
		RedisSerializer<Object> reidsSerializer = (RedisSerializer<Object>) redisTemplate
				.getValueSerializer();
		return reidsSerializer.serialize(value);
	}

	protected Object deserializeValue(final byte[] value) {
		return redisTemplate.getValueSerializer().deserialize(value);
	}

}
