/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mygdx.game;

/**
 *
 * @author r.smoter
 */
public class ScoreBox {
    
    private int score;
    
    
    public ScoreBox(){
    } 
    
    
    
    public int getScore(){
        return score;
    };
    
    public String getScoreString(){
        String score2;
        score2 = Integer.toString(score);
        return score2;
    };
    
  
    
    
    public void setScore(int a){
        score += a;
    };

    
    

  
    
    
    
    
}
