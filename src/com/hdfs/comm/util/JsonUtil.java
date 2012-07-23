package com.hdfs.comm.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import net.sf.json.xml.XMLSerializer;

import org.apache.log4j.Logger;

public class JsonUtil {
	
	private final static Logger logger = Logger.getLogger(JsonUtil.class);
	
	private static XMLSerializer serializer = new XMLSerializer();

	static {
		serializer.setRootName("root");
		serializer.setTypeHintsEnabled(false);
		serializer.setTrimSpaces(true);
	}
	
	//褰撳墠json鏍煎紡瀛楃涓叉牸寮忔槸鍚﹁壇濂�
	public static boolean isJSON(String jsonString) {
		return JSONUtils.mayBeJSON(jsonString);
	}
	
	//灏唈son鏍煎紡鐨勫瓧绗︿覆杞垚涓篗ap瀵硅薄
	public static Map<String, Object> toMap(String jsonString) {
		return toMap(toJSONObject(jsonString));
	}
	
	//灏唜ml鏂囦欢娴佽浆鎹㈡垚Map瀵硅薄
	public static Map<String, Object> toMap(InputStream  is) {
		return toMap(toJSONObject(is));
	}
	
	//灏咼SON瀵硅薄杞崲鎴怣ap瀵硅薄
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(JSONObject jsonObject) {
		//return JSONUtils.getProperties(jsonObject);
		Map<String, Object> map  = new HashMap <String, Object> ();
		Iterator keys = jsonObject.keys();
		String key = null;
		Object value = null;
		while(keys.hasNext()) {
			key = keys.next().toString();
			value = jsonObject.get(key);
			if(value == null) {
				continue;
			}
			if(value instanceof JSONNull) {
				continue;
			} else if(value instanceof JSONObject) {
				if(((JSONObject)value).isNullObject()) {
					continue;
				}
				if(((JSONObject)value).isEmpty()) { 
					continue;
				}
			} else if(value instanceof JSONArray) {
				if(((JSONArray)value).isEmpty()) {
					continue;
				}
			}
			if(value instanceof Number) {
				map.put(key, value.toString());
			} else {
				map.put(key, value);
			}
		}
		return map;
	}
	
	//灏咼SON鏁扮粍杞崲鎴怞ava闆嗗悎瀵硅薄
	@SuppressWarnings("unchecked")
	public static List toList(Object object, Class objectClass) {
		if(object instanceof JSONArray) {
			return  (List) JSONArray.toCollection((JSONArray)object, objectClass);
		} else {
			logger.warn("The object is not a instance of JSONArray");
			return null;
		}
	}
	
	//灏咼SON鏍煎紡鐨勫瓧绗︿覆杞垚JSON鏁扮粍
	public static JSONArray toJSONArray (String jsonString) {
		return JSONArray.fromObject(jsonString);
	}
	//灏咼SON鏍煎紡鐨勫瓧绗︿覆杞垚JSON鏁扮粍
	public static JSONArray toJSONArray (Object obj) {
		return JSONArray.fromObject(obj);
	}
	
	
	//璇诲彇xml鏂囦欢娴侊紝骞跺皢鍏惰浆鎹㈡垚JSON瀵硅薄
	public static JSONObject toJSONObject (InputStream is) {
		return ((JSONObject) serializer.readFromStream(is));
	}
	
	public static JSON toJSON (InputStream is) {
		return serializer.readFromStream(is);
	}
	
	//灏咼SON鏍煎紡鐨勫瓧绗︿覆杞垚JSON瀵硅薄
	public static JSONObject toJSONObject (String jsonString) {
		return JSONObject.fromObject(jsonString);
	}
	
	//灏咼SON鏍煎紡鐨勫瓧绗︿覆杞垚瀵瑰簲XML鏍煎紡鐨勫瓧绗︿覆
	public static String toXmlString(String jsonString) {
		return serializer.write(toJSONObject(jsonString));
	}
	
	public static InputStream toStream(String xmlString) {
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncoding");
		}
		return is;
	}
	
	public static String toXmlString(JSONObject jsonObject) {
		return serializer.write(jsonObject);
	}
	
	public static String toXmlString(JSONObject jsonObject, String subName) {
		if(subName != null && !"".equals(subName)){
			serializer.setElementName(subName);
		}
		return serializer.write(jsonObject);
	}
	//灏咼SON瀵硅薄杞崲鎴怞ava瀵硅薄
	public static Object toJava (JSON json) {
		return JSONSerializer.toJava(json);
	}
	
	//灏咼SON瀵硅薄杞崲鎴怞ava瀵硅薄锛屾寜鎸囧畾閰嶇疆杞崲
	public static Object toJava (JSON json, JsonConfig jsonConfig) {
		return JSONSerializer.toJava(json, jsonConfig);
	}
	
	//灏咼ava瀵硅薄杞崲鎴怞SON瀵硅薄
	public static JSONObject toJSONObject (Object object) {
		JsonConfig jsonConfig = new JsonConfig();
		return (JSONObject)JSONSerializer.toJSON(object, jsonConfig);
	}
	
	public static InputStream toByteArrayStream(String string) {
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(string.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncoding");
		}
		return is;
	}
	
	public static void main(String[] args) {
		//鍙栧埌绠�鍗曞璞�
		//String stkey = (String) map.get("stkey");
		//鍙栧埌闆嗗悎瀵硅薄
		//List<User> userList = JsonUtil.toList(map.get("userList"), com.aspire.community.cbp.domain.profile.dto.User.class);
		
//		Map mapa=new HashMap();
//		Map map=new HashMap();
//		map.put("1", "a");
//		map.put("2", "b");
//		
//		String userId="34545";
//		
//		mapa.put("mobileList", map);
//		mapa.put("userId", userId);
		
//		JSONArray j=JSONArray.fromObject(mapa);
//		
//		//System.out.println(j.toString());
//		
//		String jsonStr="{\"userId\":\"34545\",\"mobileList\":{\"2\":\"b\",\"1\":\"a\"}}";
//		mapa=toMap(jsonStr);
//		map=toMap((JSONObject)mapa.get("mobileList"));
//		System.out.println(map.size());
//		Iterator it=map.keySet().iterator();
//		while(it.hasNext()){
//			String name=(String)it.next();
//			System.out.println(map.get(name));
//		}
	}
}
