/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 *
 * @author r.smoter
 */
public class Sekwencja {

  private String poszukiwany = "";
  private String odnaleziony = "1";
  private int odnaleziono = 0;
  private final Sound ooweeeSound = Gdx.audio.newSound(Gdx.files.internal("smok/ooweee.mp3"));
  private boolean gameOwer = false;
  
  private final Music find2ndPrivate    = Gdx.audio.newMusic(Gdx.files.internal("stopnie/find2ndPrivate.mp3"));
  private final Music findSpecialist    = Gdx.audio.newMusic(Gdx.files.internal("stopnie/findSpecjalist.mp3"));
  private final Music findPrivate1stClass    = Gdx.audio.newMusic(Gdx.files.internal("stopnie/findPrivate1stClass.mp3"));
  
 
    public void Sekwencja(){
    }
    

    ScoreBox scoreBox = new ScoreBox();
    String[] tablica = new String[]{ "rangSpecialist", "rangPrivate1stClass", "rangnd2Private" };
    String[] tablicaNazwy = new String[]{ "Specialist", "Private 1st Class", "2nd Private" };
    
    
            public void szukaj(){
           
            if (odnaleziono ==  0){ this.poszukiwany = tablica[0]; findSpecialist.play();odnaleziono++;}
            if (odnaleziono ==  3){ this.poszukiwany = tablica[1]; findPrivate1stClass.play();odnaleziono++;}
            if (odnaleziono ==  6){ this.poszukiwany = tablica[2];find2ndPrivate.play(); odnaleziono++;}
            if (odnaleziono ==  9){ System.out.println("KOONIEC"); gameOwer = true; }
 
            };
    
 
    public void setOdnaleziony (String nazwa){
        this.odnaleziony = nazwa;
        }    
    
   
    public String getPoszukiwany (){
        
        int a=0;
        for (int i = 0; i < tablica.length; i++) {
             
            if (poszukiwany.equals(tablica[i])){ a=i; }
            
        }
 
        return tablicaNazwy[a];
        }    
   
    
     public void wynik(ScoreBox sc){
     
         
          
       if ( poszukiwany.equals(odnaleziony)) 
                { 
                  sc.setScore(1000);
                  odnaleziono++;
                  ooweeeSound.play(0.1f);}
       else if ( odnaleziony.equals("star"))
                { }       
       else     {sc.setScore(-500);}
     
         
         
         
         
     }
    
    
    
    

 
    public boolean getGameOwer(){
    
        return gameOwer;
        
    
    }
    
    
    }
    
    
    
    

