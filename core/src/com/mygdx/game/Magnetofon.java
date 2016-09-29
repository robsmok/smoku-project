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
public class Magnetofon {
      
    int a=0;
    
    private final Sound pistolExplode = Gdx.audio.newSound(Gdx.files.internal("pistol-shot.wav"));
    private final Sound soundJump = Gdx.audio.newSound(Gdx.files.internal("smok/jump.wav"));
    private final Sound ex7 = Gdx.audio.newSound(Gdx.files.internal("explosion7.wav"));
    private final Sound soundSpecialist = Gdx.audio.newSound(Gdx.files.internal("stopnie/Specialist.mp3"));
    private final Sound soundBubble = Gdx.audio.newSound(Gdx.files.internal("smok/bubble1.wav"));
    private final Music soundExplode = Gdx.audio.newMusic(Gdx.files.internal("smok/explode.wav"));
    private final Music gameOver = Gdx.audio.newMusic(Gdx.files.internal("game-over.wav"));
    

    
    public void Magnetofon(){
    }
    
    
    public void play(String nazwa){
    
        if (nazwa.equals("pistol-shot")){ pistolExplode.play();}
        if (nazwa.equals("jump")){ soundJump.play();}
        if (nazwa.equals("expl7")){ ex7.play();}
    
        if (nazwa.equals("spec")){ soundSpecialist.play();}
        if (nazwa.equals("bulb")){ soundBubble.play();}
        
        if (nazwa.equals("explode1")){ soundExplode.play();}
        if (nazwa.equals("gover")){ gameOver.play();}
    
    }
    
    public void playGover(){
        if (a<3){
        gameOver.play();
        a++;}
    }
    
    
    
}
