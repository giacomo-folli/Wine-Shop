package com.example.gestorevini;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatch {
    // Create a matcher for the input String
    private Matcher matcher;
    // Create a pattern from regex
    private Pattern pattern;

    public boolean nameCheck(String name)
    {
        String regex = "[^a-zA-Z]";
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(name);
        return matcher.find();
    }

    public boolean cellCheck(String cell)
    {
        String regex = "[^0-9]";
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(cell);
        return matcher.find();
    }
}


