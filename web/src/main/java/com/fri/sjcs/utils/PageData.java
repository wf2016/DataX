package com.fri.sjcs.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@SuppressWarnings("rawtypes")
public class PageData extends HashMap implements Map{
	
	private static final long serialVersionUID = 1L;
	
	Map map = null;
	HttpServletRequest request;
	
	@SuppressWarnings("unchecked")
	public PageData(HttpServletRequest request){
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap(); 
		Iterator entries = properties.entrySet().iterator(); 
		Entry entry;
		String name = "";  
		String value = "";  
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
			name = (String) entry.getKey(); 
			Object valueObj = entry.getValue(); 
			if(null == valueObj){ 
				value = ""; 
			}else if(valueObj instanceof String[]){ 
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){ 
					 value = values[i] + ",";
				}
				value = value.substring(0, value.length()-1); 
			}else{
				value = valueObj.toString(); 
			}
			returnMap.put(name, value); 
		}
		map = returnMap;
//		if(map.get("pageNo")==null){
//			map.put("pageNo", 1);
//		}
//		if(map.get("pageSize")==null){
//			map.put("pageSize", 10);
//		}
	}
	
	public PageData() {
		map = new HashMap();
	}
	
	@Override
	public Object get(Object key) {
		Object obj = null;
		if(map.get(key) instanceof Object[]) {
			Object[] arr = (Object[])map.get(key);
			obj = request == null ? arr:(request.getParameter((String)key) == null ? arr:arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}
	
	
	/***
	 * 将key/value进行错位转换
	 * @return
	 */
	public Map<String,List<String>> reverse(){
		Map<String,List<String>> newobj=new HashMap<String,List<String>>();
		Set<String> keyset=this.keySet();
		for(String key:keyset){
			String value=this.getString(key);
			List<String> keyarr=newobj.get(value);
			if(keyarr==null){
				keyarr=new ArrayList<String>();
			}
			keyarr.add(key);
			newobj.put(value, keyarr);
		}
		
		return  newobj;
	}
	/**
	 * 获取String 类型数据
	 * @param key
	 * @return
	 */
	public String getString(Object key) {
		String value="";
		try{
			if(get(key)!=null){
				value=get(key).toString();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return value;
	}
	
	
	
	
	/**
	 * 获取String 类型数据
	 * @param key
	 * @return
	 */
	public Integer getInt(Object key) {
		Integer value=0;
		try{
			if(get(key)!=null&&StringUtils.isNotBlank(get(key).toString())){
				String v=get(key).toString();
				if(v.indexOf(".")>=0){
					v=v.substring(0, v.indexOf("."));
				}
				value=new Integer(v);
			}
		}catch(Exception e){
			System.out.println("=========="+get(key));
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 获取Boolean 类型数据
	 * @param key
	 * @return
	 */
	public Boolean getBoolean(Object key) {
		Boolean value=false;
		try{
			if(get(key)!=null&&StringUtils.isNotBlank(get(key).toString())){
				value=Boolean.parseBoolean(get(key).toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 获取String 类型数据
	 * @param key
	 * @return
	 */
	public Double getDouble(Object key) {
		Double value=0.0;
		try{
			if(get(key)!=null&&StringUtils.isNotBlank(get(key).toString())){
				value=(Double)get(key);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 获取String 类型且添加引号的字符串
	 * @param key
	 * @return
	 */
	public String getSpacilString(Object key) {
		String value=null;
		try{
			if(get(key)!=null&&StringUtils.isNotBlank(get(key).toString())){
				value="'"+(String)get(key)+"'";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return value;
	}
	
	/***
	 * 通用获取分页起始页码数据
	 * @param key
	 * @param value
	 * @return
	 */
	public static int getStartNum(int pageNo,int pageSize){
		int startnum=0;
		try{
			if(pageNo>0){
				startnum=(pageNo-1)*pageSize;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return startnum;
	}
	
	/***
	 * 通用获取总页数
	 * @param key
	 * @param value
	 * @return
	 */
	public static int getTatolPage(int resultCount,int pageSize){
		int totalPage=0;
		try{
			if(resultCount>0&&pageSize>0){
				totalPage=(resultCount+pageSize-1)/pageSize;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return totalPage;
	}
	
	
	/***
	 * 通用获取总页数
	 * @param key
	 * @param value
	 * @return
	 */
	public List<String> getListByFgf(String key,String fgf){
		List<String> list=null;
		try{
			if(StringUtils.isNotBlank(key)&&StringUtils.isNotBlank(fgf)){
				if(get(key)!=null){
					String value=(String)get(key);
					if(StringUtils.isNotBlank(value)){
						String[] arr=value.split(fgf);
						if(arr!=null&&arr.length>0){
							list=new ArrayList<String>();
							for(String r:arr){
								list.add("'"+r.trim()+"'");
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}
	
	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}
	
}
