/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.end;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.ScoreBox;

/**
 *
 * @author r.smoter
 */
public class Wynik {

    private int DodacWpis;
    private boolean WpisDodany = true;

    public Preferences prefs = Gdx.app.getPreferences("Pref");

    void show(String abc) {

        String[] abcd = score_to_array(abc);
        for (int i = 0; i < abcd.length; i++) {
            System.out.println(abcd[i]);
        }

    }

    String[] score_to_array(String name) {
        String[] wynikOsoba = name.split(";");
        return wynikOsoba;
    }

    // dodaje nowy wpis do schowka i utrwala go
    void score_add(String score_old, String score_add) {

        String scoreAddNew = score_add;
        String scoreAddNewOut = " ";
        int tag = 0;

        String[] abcd = score_to_array(score_old);

        // wiersz wejściowy parsowanie do cyfry
        String[] scoreAddNew_nr = scoreAddNew.split(",");
        String mystr1 = scoreAddNew_nr[0].replaceAll("[^\\d]", "");
        int number1 = Integer.parseInt(mystr1);

        for (int i = 0; i < 6; i++) {

            String[] scoreAddSplit = abcd[i].split(",");
            String mystr = scoreAddSplit[0].replaceAll("[^\\d]", "");
            int number = Integer.parseInt(mystr);

                // jeśli linia do dodania jest wieksza od podanej w liście dodaj do listy
            if ((number <= number1) && (tag == 0)) {

                scoreAddNewOut = scoreAddNewOut + scoreAddNew + ";";
                tag = 1;
            };

            // bulid insert to add to base
            scoreAddNewOut = scoreAddNewOut + abcd[i] + ";";

        }

        String scoreAddNewOutBezSpacji = scoreAddNewOut.replaceAll(" ", "");
        prefs.putString("name", scoreAddNewOutBezSpacji);
        prefs.flush();

       // show(scoreAddNewOut);
        WpisDodany = true;
    }

    void score_addOrNot(String score_old, String score_add) {

        // String scoreAddNew = score_add;
        String scoreAddNewOut = "";
        int tag = 0;
        int s = 0;

        String[] abcd = score_to_array(score_old);

        // wiersz wejściowy parsowanie do cyfry
        //     String[] scoreAddNew_nr  = scoreAddNew.split(",");             
        //     String mystr1 = scoreAddNew_nr[0].replaceAll( "[^\\d]", "" );
        int number1 = Integer.parseInt(score_add);

        for (int i = 0; i < 6; i++) {
            String[] scoreAddSplit = abcd[i].split(",");
            String mystr = scoreAddSplit[0].replaceAll("[^\\d]", "");
            int number = Integer.parseInt(mystr);

            // jeśli linia do dodania jest wieksza od podanej w liście dodaj do listy
            if ((number <= number1) && (tag == 0)) {

                DodacWpis = 1;

            };
        }
    }

    public int get_CzyDodacWpis() {
        return DodacWpis;
    }

    String get_wyniki() {

        String name = prefs.getString("name", "No name stored");

        //jesli string jeszce nie istnieje
        if (name == "No name stored") {
            prefs.putString("name", "0,empty;0,empty;0,empty;0,empty;0,empty;0,empty;0,empty;0,empty;");
            prefs.flush();
            name = prefs.getString("name", "No name stored");
        }

          //String name2 = name.replace(";", "\n").replace(",", " -- ");
        return name;
    }

    public String wynikGraczaWyswietl(ScoreBox test) {

      //  System.out.println(test.getScoreString());
        score_addOrNot(get_wyniki(), test.getScoreString());
        String name2 = get_wyniki().replace(";", "\n").replace(",", " -- ");
        return name2;
    }

    public void wynikGraczaDodaj(ScoreBox test, String name) {

      //  System.out.println(test.getScoreString());
        score_add(get_wyniki(), test.getScoreString() + "," + name);

    }

    public void wynikGracza(ScoreBox test) {
        score_addOrNot(get_wyniki(), test.getScoreString());
    }

}
