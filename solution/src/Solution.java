import java.util.HashMap;
import java.util.Map;

class Solution {

	public int solution(String str1, String str2) {
		Map<String, Integer> map1 = getMap(str1);
		Map<String, Integer> map2 = getMap(str2);
		int inter = 0;
		int union = 0;

		for (Map.Entry<String, Integer> entry : map1.entrySet()) {
			String key = entry.getKey();
			if (map2.containsKey(key)) {
				inter += Math.min(map1.get(key), map2.get(key));
				union += Math.max(map1.get(key), map2.get(key));
				map2.remove(key);
			} else {
				union += map1.get(key);
			}
		}
		for (Integer value : map2.values()) {
			union += value;
		}

		int answer = 0;
		if (union == 0) {
			answer = 65536;
		} else {
			answer = (int) ((inter / (double) union) * 65536);
		}
		return answer;
	}

	public Map<String, Integer> getMap(String str) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		str = str.toLowerCase();
		for (int i = 0; i < str.length() - 1; i++) {
			if (Character.isLetter(str.charAt(i)) && Character.isLetter(str.charAt(i + 1))) {
				String string = str.substring(i, i + 2);
				map.put(string, map.getOrDefault(string, 0) + 1);
			}
		}
		return map;
	}
}