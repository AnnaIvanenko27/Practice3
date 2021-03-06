package ua.nure.ivanenko.Practice3;

/* Read text from part3.txt and convert first letter in all words 
 * whose length bigger then 3 to capital letter.
 */

import java.util.regex.*;

public class Part3 {
	public static String convert(String input) {
		StringBuilder sb = new StringBuilder();
		Pattern pattern = Pattern.compile("(?U)\\s*\\w+.?\\s*");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			if (matcher.group().length() > 3) {
				Character tmp = Character.toUpperCase(matcher.group().charAt(0));
				sb.append(matcher.group().replaceFirst("^\\p{javaLowerCase}", Character.toString(tmp)));
			} else {
				sb.append(matcher.group());
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(convert(Util.readFile("part3.txt")));
	}

}
