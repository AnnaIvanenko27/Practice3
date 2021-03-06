package ua.nure.ivanenko.Practice3;

/* Read text from part2.txt and 
 * output words with maximum and minimum length.
 */

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.*;

public class Part2 {
	public static String convert(String input) {
		Set<String> minSet = new LinkedHashSet<>();
		Set<String> maxSet = new LinkedHashSet<>();
		StringBuilder sb = new StringBuilder();
		Pattern pattern = Pattern.compile("(?iU)\\w+");
		Matcher matcher = pattern.matcher(input);
		int max = 0, min = 100;
		while (matcher.find()) {
			int tmp = matcher.group().length();
			if (tmp < min) {
				min = tmp;
			} else {
				if (tmp > max) {
					max = tmp;
				}
			}
		}
		matcher = pattern.matcher(input);
		while (matcher.find()) {
			if (matcher.group().length() == min) {
				minSet.add(matcher.group());
			} else {
				if (matcher.group().length() == max) {
					maxSet.add(matcher.group());
				}
			}
		}
		boolean flag = false;
		sb.append("Min: ");
		for (String s : minSet) {
			if (!flag) {
				sb.append(s);
				flag = true;
			} else {
				sb.append(", ").append(s);
			}
		}
		sb.append(System.lineSeparator());
		flag = false;
		sb.append("Max: ");
		for (String s : maxSet) {
			if (!flag) {
				sb.append(s);
				flag = true;
			} else {
				sb.append(", ").append(s);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(convert(Util.readFile("part2.txt")));
	}
}
