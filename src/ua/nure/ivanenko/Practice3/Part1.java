package ua.nure.ivanenko.Practice3;

/* Read text from part1.txt and 
 * output input data four different way:
 * 1. convert1(): login ==> email
 * 2. convert2(): name (email: email)
 * 3. convert3(): mail domain ==> list of relevant logins
 * 4. convert4(): login;name;email;random password
 */

import java.util.regex.*;
import java.util.*;

public class Part1 {

	public static String convert1(String input) {
		StringBuilder sb = new StringBuilder();
		Pattern pattern = Pattern.compile("(?iU)(\\S+);(\\S+ \\S+);(.*)");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			sb.append(matcher.group(1)).append(" ==> ").append(matcher.group(3)).append(System.lineSeparator());
		}
		return sb.toString();
	}

	public static String convert2(String input) {
		StringBuilder sb = new StringBuilder();
		Pattern pattern = Pattern.compile("(?iU)(\\S+);(\\S+ \\S+);(.*)");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			sb.append(matcher.group(2)).append(" (email: ").append(matcher.group(3)).append(")")
					.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public static String convert3(String input) {
		StringBuilder sb = new StringBuilder();
		List<String> domens = new ArrayList<>();
		Pattern pattern = Pattern.compile("(?iU)(\\S+);(\\S+) (\\S+);(\\S+@(.*))");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			if (domens.contains(matcher.group(5))) {
				continue;
			}
			String tmp = matcher.group(5);
			domens.add(tmp);
			sb.append(tmp).append(" ==> ");
			Matcher matcher2 = pattern.matcher(input);
			boolean flag = false;
			while (matcher2.find()) {
				if (matcher2.group(5).equals(tmp)) {
					if (!flag) {
						sb.append(matcher2.group(1));
						flag = true;
					} else {
						sb.append(", ").append(matcher2.group(1));
					}
				}
			}
			flag = false;
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public static String convert4(String input) {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		Pattern pattern = Pattern.compile("(?iU)(\\S+;\\S+;.*)");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			sb.append(matcher.group());
		}
		sb.append(";Password").append(System.lineSeparator());
		Pattern pattern2 = Pattern.compile("(\\S+;\\S+ \\S+;.*)");
		Matcher matcher2 = pattern2.matcher(input);
		while (matcher2.find()) {
			sb.append(matcher2.group(0)).append(";");
			for (int i = 0; i < 4; i++) {
				sb.append(rand.nextInt(10));
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String fileName = "part1.txt";
		System.out.println(convert1(Util.readFile(fileName)));
		System.out.println(convert2(Util.readFile(fileName)));
		System.out.println(convert3(Util.readFile(fileName)));
		System.out.println(convert4(Util.readFile(fileName)));
	}
}
