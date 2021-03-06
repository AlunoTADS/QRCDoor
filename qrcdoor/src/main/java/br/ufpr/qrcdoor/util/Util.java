package br.ufpr.qrcdoor.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

}
