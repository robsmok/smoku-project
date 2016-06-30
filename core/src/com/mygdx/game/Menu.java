package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Screen;

public class Menu implements Screen
{
    private Stage uiStage;
    private Game game;

    public Menu(Game g)
    {
        game = g;
        create();
    }

    public void create() 
    {        
        uiStage  = new Stage();

        BaseActor background = new BaseActor();
        background.setTexture( new Texture(Gdx.files.internal("tiles-menu.jpg")) );
        uiStage.addActor( background );

        BaseActor titleText = new BaseActor();
        titleText.setTexture( new Texture(Gdx.files.internal("")) );
        titleText.setPosition( 20, 100 );
        uiStage.addActor( titleText );

        BitmapFont font = new BitmapFont();
        String text = " Press S to start, M for main menu ";
        LabelStyle style = new LabelStyle( font, Color.YELLOW );
        Label instructions = new Label( text, style );
        instructions.setFontScale(2);
        instructions.setPosition(100, 50); 
        // repeating color pulse effect

        
        uiStage.addActor( instructions );
    }

    public void render(float dt) 
    {   
        // process input
        if (Gdx.input.isKeyPressed(Keys.S)) 
           // game.setScreen( new GdxSmokuProject(game) );
        
           if (Gdx.input.isKeyPressed(Keys.X)) 
            //game.setScreen( new SmokuMenu(game) );
           
           if (Gdx.input.isKeyPressed(Keys.M)) 
            //game.setScreen( new CheeseMenu(game) );


           
        // update
        uiStage.act(dt);

        // draw graphics
        Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {  }

    @Override
    public void pause()   {  }

    @Override
    public void resume()  {  }

    @Override
    public void dispose() {  }

    @Override
    public void show()    {  }

    @Override
    public void hide()    {  }
}