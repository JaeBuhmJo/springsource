import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main2 {

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			String[] orders = { "alex pizza pasta", "alex pizza pizza", "alex noodle", "bob pasta",
					"bob noodle sandwich pasta", "bob steak noodle" };

			int maxOrder = 0;
			Map<String, Set<String>> orderMap = new HashMap<String, Set<String>>();
			for (int i = 0; i < orders.length; i++) {
				StringTokenizer st = new StringTokenizer(orders[i]);
				String name = st.nextToken();
				if (!orderMap.keySet().contains(name)) {
					orderMap.put(name, new HashSet<String>());
				}
				while (st.hasMoreTokens()) {
					orderMap.get(name).add(st.nextToken());
				}
				maxOrder = Math.max(maxOrder, orderMap.get(name).size());
			}

			System.out.println(orderMap);
			System.out.println(maxOrder);

			ArrayList<String> list = new ArrayList<String>();
			for (Map.Entry<String, Set<String>> entry : orderMap.entrySet()) {
				if (entry.getValue().size() == maxOrder) {
					list.add(entry.getKey());
				}
			}

			Collections.sort(list);
			System.out.println(Arrays.toString(list.toArray()));
		}
	}
}
