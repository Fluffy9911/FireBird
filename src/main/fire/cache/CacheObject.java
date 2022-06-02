package main.fire.cache;

import java.util.Map;

import org.json.simple.JSONObject;

import main.fire.exception.CacheException;

public class CacheObject {

	JSONObject obj;

	public CacheObject() {
		this.obj = new JSONObject();

	}

	@SuppressWarnings("unchecked")
	public CacheObject add(String key, Object object) {
		obj.put(key, object);
		return this;
	}

	public CacheObject add(Object key, Object object) {
		obj.put(key, object);
		return this;
	}

	public JSONObject getObj() throws CacheException {
		// if (obj.isEmpty())
		// throw new CacheException();
		return obj;
	}

	public void setObj(JSONObject obj) throws CacheException {
		// if (obj.isEmpty())
		// throw new CacheException();
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "CacheObject [obj=" + "]";

	}

	public int getInt(String key) {
		return Integer.parseInt(obj.get(key).toString());
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getAsMap() {
		return obj;
	}

}
