package com.mygdx.game;

import com.badlogic.gdx.Game;

public class GdxSmokuProject extends Game
{
    @Override
    public void create() 
    {  
        Menu cm = new Menu(this);
        setScreen( cm );
    }
}