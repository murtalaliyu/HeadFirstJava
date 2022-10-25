package _5;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	public static void main(String[] args) {
		Test test = new Test();
		test.computeIfAbsent();
		test.computeIfPresent();
	}
	
	private void computeIfPresent() {
		Map<String, Integer> metrics = new HashMap<>();
		
		// probably other stuff happens here...
		
		// ----------- Repeatable pattern starts here
		
		String metric = "m1";
		metrics.put(metric, 0);
		
		// See if the metric exists as a key in the map.
		// Alternatively, you could do a "get" and see
		// if the result is null or not.
		if (metrics.containsKey(metric)) {
			Integer integer = metrics.get(metric);	// If it's in the map, get the value
			metrics.put(metric, ++integer);	// Increment the value, and put it back in the map
		}
		
		// ----------- Repeatable pattern ends here
		
		/* ------------------------------------------------ */
		
		// ----------- Java 8 feature (computeIfPresent)
		
		// returns the new value if the key was in the map (or null if not)
		metrics.computeIfPresent(metric, (key, value) -> ++value);
		metrics.forEach((key, value) -> System.out.println(key + ", " + value));
	}
	
	private void computeIfAbsent() {
		Map<String, Actions> custActs = new HashMap<>();
		String usr = "usr1";
		
		// probably other stuff happens here...
		
		// ----------- Repeatable pattern starts here
		
		// See if there's an Actions object for the username
		Actions actions = custActs.get(usr);
		if (actions == null) {
			// The value doesn't exist...
			// ...so create a new Actions and add it to 
			// the Map with the username as the key.
			actions = new Actions(usr);
			custActs.put(usr, actions);
		}
		// do something with actions
		
		// ----------- Repeatable pattern ends here
		
		/* ------------------------------------------------ */
		
		// ----------- Java 8 feature (computeIfAbsent)
		
		// This is EITHER the existing Actions object OR the Actions object
		// created by the lambda if the username wasn't in the Map.
		Actions betterActions = 
				custActs.computeIfAbsent(usr, name -> new Actions(name));
		
	}

}
