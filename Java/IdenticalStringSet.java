package com.github.yuan0122;

import java.util.HashMap;
import java.util.Map;

public class IdenticalStringSet {

	public static boolean allStringSetsIdentical(String[][] sets) {
		if (sets.length <= 1) {
			return true;
		}
		// construct a map that maps each string to the last index of sets that this string comes from
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String s : sets[0]) {
			// initialize the map with all strings in sets[0] and index 0
			map.put(s, 0);
		}
		for (int i = 1; i < sets.length; i++) {
			// the variable count counts the number of distinct strings in this set
			int count = 0;
			for (String s : sets[i]) {
				if (!map.containsKey(s)) {
				// once we found a string that was not in map, then we can directly return false
					return false;
				} else if (map.get(s) == i - 1) {
				// if this string is in map, update its index
					map.put(s, i);
					count++;
				}
			}
			// if the distinct number of string in current set doesn't equal to map's size
			// it means, there is not enough distinct strings in this set, then just return false
			if (count != map.size()) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String[][] sets = new String[][] { {}, {}, {}, {} };
		System.out.println(allStringSetsIdentical(sets));

		sets = new String[][] { { "a", "a", "a" }, { "a", "a" },
				{ "a", "a", "a" } };
		System.out.println(allStringSetsIdentical(sets));

		sets = new String[][] { { "a", "a", "b" }, { "a", "b" },
				{ "b", "b", "a" } };
		System.out.println(allStringSetsIdentical(sets));

		sets = new String[][] { { "a" }, { "b" } };
		System.out.println(allStringSetsIdentical(sets));
	}
}
