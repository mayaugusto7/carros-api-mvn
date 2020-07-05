package br.com.livro.util;

import javax.servlet.ServletException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    private static final Pattern regexAll = Pattern.compile("/carros");

    private static final Pattern regexById = Pattern.compile("/carros-mvn/carros/([0-9]*)");

    public static Long matchId(String requestUri) throws ServletException {

        Matcher matcher = regexById.matcher(requestUri);

        if (matcher.find() && matcher.groupCount() > 0) {
            String s = matcher.group(1);
            if (s != null && s.trim().length() > 0) {
                Long id = Long.parseLong(s);
                return id;
            }
        }

        return null;
    }

    public boolean matchAll(String requestUri) throws ServletException {
        Matcher matcher = regexAll.matcher(requestUri);
        return matcher.find();
    }

}
