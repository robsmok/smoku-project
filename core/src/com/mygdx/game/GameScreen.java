/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mygdx.game;

import com.mygdx.start.GameStart;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import java.util.ArrayList;


public class GameScreen extends  BaseScreen
{
    private PhysicsActor[] background;
    private PhysicsActor[] ground;
        


    private float enemySpeed;
    private float enemyTimer;
    
    private PhysicsActor Napisy;
    private SpriteBatch batch;

    private Label baseText;
    
    private PhysicsActor Napisy_szukaj;
    private Label baseText1;


    
    
    public GameScreen(BaseGame g)
    {
        super(g);
    }
    @SuppressWarnings("empty-statement")
    public void create() 
    {        
        // background objects 


        background = new PhysicsActor[2];
        PhysicsActor bg0 = new PhysicsActor();
        bg0.storeAnimation( "default", new Texture(Gdx.files.internal("smok/sky.jpg")) );
        bg0.setPosition( 0, -83 );
        bg0.setVelocityXY(-50,0);
        mainStage.addActor( bg0 );
        background[0] = bg0;

        PhysicsActor bg1 = bg0.clone();
        bg1.setX( bg0.getWidth() );
        mainStage.addActor(bg1);
        background[1] = bg1;
        
        ground = new PhysicsActor[2];
        
        PhysicsActor gr0 = new PhysicsActor();
        gr0.storeAnimation( "default", new Texture(Gdx.files.internal("ground.png")) );
        gr0.setPosition( 0, -30 );
        gr0.setVelocityXY(-200,0);
        gr0.setRectangleBoundary();
        mainStage.addActor( gr0 );
        ground[0] = gr0;
        
        PhysicsActor gr1 = gr0.clone();
        gr1.setX( gr0.getWidth() );
        mainStage.addActor(gr1);
        ground[1] = gr1;
        
    
        Napisy = new PhysicsActor();
        BitmapFont font = new BitmapFont();
        String text = "wynik " ;
        LabelStyle style = new LabelStyle( font, Color.BLACK );
        baseText = new Label( text, style );
        baseText.setX(10);
        baseText.setY(570);
        
        //mainStage.addActor( baseText );

        
        Napisy_szukaj = new PhysicsActor();
        String text2 = "Listen to the speaker voice.\n" +
        "Catch the batches that you hear.\n" +
        " \n" +
        "certainly you could use a rifle board <M>\n" +
        "and jump <SPACE>\n" +
        "\n" +
        "Good luck\n" +
        "Pressing the <SPACE> to start" ;
        
        LabelStyle style2 = new LabelStyle( font, Color.BLACK );
        baseText1 = new Label( text2, style2 );
        baseText1.setX(100);
        baseText1.setY(270);
        baseText1.setFontScale(2,2);
        mainStage.addActor( baseText1 );

        enemyTimer = 0;
        enemySpeed = -250;

    

    }

    @SuppressWarnings("empty-statement")
    public void update(float dt) 
    {   
        
        // manage background objects
        for (int i = 0; i < 2; i++)
        {
            PhysicsActor bg = background[i];
            if ( bg.getX() + bg.getWidth() < 0 )
                bg.setX( bg.getX() + 2 * bg.getWidth() );
            PhysicsActor gr = ground[i];
            if ( gr.getX() + gr.getWidth() < 0 )
                gr.setX( gr.getX() + 2 * gr.getWidth() );
        
            System.out.println( bg.getX() + 2 * bg.getWidth() );
        }

    }

    public boolean keyDown(int keycode)
    {
        if (keycode == Keys.P)    
            togglePaused();

        if (keycode == Keys.M)    
    
        if (keycode == Keys.R)    ;
    
        if (keycode == Keys.SPACE)    
            game.setScreen( new GameStart(game)); 
            
        return false;
    }

}