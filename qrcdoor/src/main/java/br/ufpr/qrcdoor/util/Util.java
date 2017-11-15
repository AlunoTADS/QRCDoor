package br.ufpr.qrcdoor.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class Util {
	
	public static HashMap<String, List<String>> insertOrUpdateHashMap(HashMap<String, List<String>> hashMap, String key, final String value) {
		if (hashMap.containsKey(key)) {
			hashMap.get(key).add(value);
		} else {
			hashMap.put(key, new ArrayList<String>(){{
				add(value);
			}});
		}
		return hashMap;
	}
	
    public static String getContainsLikePattern(String searchTerm) {
        if (StringUtils.isEmpty(searchTerm)) {
            return "%";
        }
        else {
            return "%" + searchTerm.toLowerCase() + "%";
        }
    }
    
    public static Map<String, String> cleanQueryMap(Map<String, String> query) {
    	Map<String, String> map = new HashMap<String, String>(query);
    	try {
    		map.remove("size");
    	} catch(Exception e) {
    	}
    	try {
    		map.remove("sort");
    	} catch(Exception e) {
    	}
    	try {
    		map.remove("page");    		
    	} catch(Exception e) {
    	}
    	return map;
    }

}
