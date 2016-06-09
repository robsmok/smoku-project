package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

public class GdxSmokuProject extends Game
{
    private Stage mainStage;
    private AnimatedActor mousey;
    private BaseActor cheese;
    private BaseActor floor;
    private BaseActor winText;   
    private boolean win;
    
    @Override
    public void create() 
    {        
        mainStage = new Stage();
        
        floor = new BaseActor();
        floor.setTexture( new Texture(Gdx.files.internal("tiles.jpg")) );
        floor.setPosition( 0, 0 );
        mainStage.addActor( floor );
        
        cheese = new BaseActor();
        cheese.setTexture( new Texture(Gdx.files.internal("cheese.png")) );
        cheese.setPosition( 400, 300 );
        cheese.setOrigin( cheese.getWidth()/2, cheese.getHeight()/2 );
        mainStage.addActor( cheese );
        
        mousey = new AnimatedActor();
        
        TextureRegion[] frames = new TextureRegion[4];
       
        for (int n = 0; n < 4; n++)
        {   
            String fileName = "mouse" + n + ".png";
            Texture tex = new Texture(Gdx.files.internal(fileName));
                tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
            frames[n] = new TextureRegion( tex );
        }
        
        Array<TextureRegion> framesArray = new Array<TextureRegion>(frames);
        Animation anim = new Animation(0.001f, framesArray, Animation.PlayMode.LOOP_PINGPONG);

        mousey.setAnimation( anim );
        mousey.setOrigin( mousey.getWidth()/2, mousey.getHeight()/2 );
        mousey.setPosition( 20, 20 );
        mainStage.addActor(mousey);
        
        winText = new BaseActor();
        winText.setTexture( new Texture(Gdx.files.internal("you-win.png")) );
        winText.setPosition( 170, 60 );
        winText.setVisible( false );
        mainStage.addActor( winText );
                
        win = false;
    }

    @Override
    public void render() 
    {   
        // process input
        mousey.velocityX = 0;
        mousey.velocityY = 0;
        
        if (Gdx.input.isKeyPressed(Keys.LEFT)) 
            mousey.velocityX -= 100;
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            mousey.velocityX += 100;;
        if (Gdx.input.isKeyPressed(Keys.UP)) 
            mousey.velocityY += 100;
        if (Gdx.input.isKeyPressed(Keys.DOWN)) 
            mousey.velocityY -= 100;

        // update
        float dt = Gdx.graphics.getDeltaTime();
        mainStage.act(dt);
        
        // check win condition: mousey must be overlapping cheese
        Rectangle cheeseRectangle = cheese.getBoundingRectangle();
        Rectangle mouseyRectangle = mousey.getBoundingRectangle();
        
        if ( !win && cheeseRectangle.contains( mouseyRectangle ) )
        {
            win = true;
            
            Action spinShrinkFadeOut = Actions.parallel(
                Actions.alpha(1),         // set transparency value
                Actions.rotateBy(360, 1), // rotation amount, duration
                Actions.scaleTo(0,0, 2),  // x amount, y amount, duration
                Actions.fadeOut(1)        // duration of fade in
            );

            cheese.addAction( spinShrinkFadeOut );
               
            Action fadeInColorCycleForever = Actions.sequence( 
                Actions.alpha(0),   // set transparency value
                Actions.show(),     // set visible to true
                Actions.fadeIn(2),  // duration of fade out
                Actions.forever(    
                    Actions.sequence(
                        // color shade to approach, duration
                        Actions.color( new Color(1,0,0,1), 1 ),
                        Actions.color( new Color(0,0,1,1), 1 )
                    )
                )
            );
            
            winText.addAction( fadeInColorCycleForever );
        }

        // draw graphics
        Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        mainStage.draw();
    }
}