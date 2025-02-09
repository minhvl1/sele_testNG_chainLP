package helpers;

import com.google.common.base.CaseFormat;

import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateHelpers {

    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String lower = upper.toLowerCase();

    public static final String digits = "0123456789";

    public static final String alphanum = upper + lower + digits;

    public static String randomString(int length, String characterSymbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (characterSymbols.length() < 2) throw new IllegalArgumentException();
        Random random = new SecureRandom();
        char[] symbols = characterSymbols.toCharArray();
        char[] buf = new char[length];

        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }

    public static String randomDigitString(int length) {
        return randomString(length, digits);
    }

    public static String randomAlphanumString(int length) {
        return randomString(length, alphanum);
    }

    public static String randomUppercaseString(int length) {
        return randomString(length, upper);
    }

    public static String randomLowercaseString(int length) {
        return randomString(length, lower);
    }


    public static String getStringWithRegex(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        String result = "";
        while (matcher.find()) {
            result = string.substring(matcher.start(), matcher.end());
        }
        return result;
    }

    public static Boolean verifyStringWithRegex(String string, String regex) {
        return Pattern.matches(regex, string);
    }

    public static String removeSpaces(String string) {
        return string.replaceAll("\\s", "");
    }
    public static String removeSpacesAndLower(String string) {
        return string.replaceAll("\\s", "").toLowerCase();
    }

    public static String toLowerCamelCase(String string) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, string);
    }

    public static String toUpperCamelCase(String string) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, string);
    }

    public static String getRanDomString(int leftLimit, int rightLimit, int targetStringLength) {
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String generateSpace(int i) {
        return new String(new char[i]).replace('\0', ' ');
    }

    public static int generateRandomDigits(int n) {
        int m = (int) Math.pow(10, n - 1);
        return m + new Random().nextInt(9 * m);
    }

    public static String getRandomMail(String domain) {
        return getRanDomString(97, 122, 10) + "@" + domain + ".com";
    }
}
