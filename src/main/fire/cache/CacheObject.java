package main.fire.cache;

import java.util.Map;

import org.json.simple.JSONObject;

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

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "CacheObject [obj=" + obj.toJSONString() + "]";

	}

	public int getInt(String key) {
		return Integer.parseInt(obj.get(key).toString());
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getAsMap() {
		return obj;
	}

}
