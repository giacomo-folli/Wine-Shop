package com.example.gestorevini;

import javafx.scene.control.Alert;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe per controllare gli input dell'utente
 * false -> se ogni carattere è idoneo
 * true ->  se ci sono caratteri non idonei
 * */

public class StringMatch {
    // Create a matcher for the input String
    private Matcher matcher;
    // Create a pattern from regex
    private Pattern pattern;

    public boolean nameCheck(String name)
    {
        String regex = "[^a-zA-Z]";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name.replace(" ", ""));
        return matcher.find();
    }

    public boolean numCheck(String num)
    {
        String regex = "[^0-9]";
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(num.replace(" ", ""));
        return matcher.find();
    }

    public boolean fiscCheck(String fisc)
    {
        String regex = "^([A-Z]{6}[0-9LMNPQRSTUV]{2}[ABCDEHLMPRST]{1}[0-9LMNPQRSTUV]{2}[A-Z]{1}[0-9LMNPQRSTUV]{3}[A-Z]{1})$|([0-9]{11})$";        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(fisc);
        return !matcher.find();
    }

    public boolean mailCheck(String mail)
    {
        String regex = "^(.+)@(.+)$";
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(mail);
        return !matcher.find();
    }

    public boolean cvvCheck(String cvv)
    {
        String regex = "[^0-9]";
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(cvv);
        return matcher.find() || cvv.length() != 3;
    }

    public boolean globalUserCheck(String name, String surname, String cell, String fisc, String mail)
    {
        return !nameCheck(name) && !nameCheck(surname) && !numCheck(cell) && !fiscCheck(fisc) && !mailCheck(mail);
    }

    public boolean globalWineCheck(String name, String prod, String grape, String price, String year, String quantity)
    {
        return !nameCheck(name) && !nameCheck(prod) && !nameCheck(grape) && !numCheck(price) && !numCheck(year) && !numCheck(quantity);
    }

    public boolean globalCardCheck(String name, String card, String cvv)
    {
        return !nameCheck(name) && !numCheck(card) && !cvvCheck(cvv);
    }

    public void ErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Invalid input!");
        alert.setContentText("Check your input and try again!");
        alert.showAndWait();
    }
}


