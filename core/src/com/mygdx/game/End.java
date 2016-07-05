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
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

public class End extends Game implements Screen
{
    private Stage uiStage;
    private Game game;
    private int wynik;
    
    private Label congratulaton;
    private BaseActor background;
    
        
            

    public End(Game g, int w)
    {       
        game = g;
        wynik = w;
        create();
    }
 

    
    

    public void create() 
    {        
        uiStage  = new Stage();

        background = new BaseActor();
        background.setTexture( new Texture(Gdx.files.internal("moro.jpg")) );
        uiStage.addActor(background);
        
        System.out.println(wynik);  
       
        BitmapFont font = new BitmapFont();
        String text = " KONIEC";
        LabelStyle style = new LabelStyle( font, Color.WHITE );
        Label instructions = new Label( text, style );
        instructions.setFontScale(3);
        instructions.setPosition(100, 80); 

  
        String text1 = " Wynik GRY " + wynik;
        congratulaton = new Label( text1, style );
        congratulaton.setFontScale(3);
        congratulaton.setPosition(100, 400); 
 

        
        
        
        
        
        
        uiStage.addActor(instructions);
        uiStage.addActor(congratulaton);

    
    }

    public void render(float dt) 
    {   
        
background.addAction(Actions.fadeIn(0));
       

        // process input
        if (Gdx.input.isKeyPressed(Keys.SPACE)) 
           game.setScreen( new GameWp(game) );
        
           if (Gdx.input.isKeyPressed(Keys.X)){
            game.setScreen(this);
           } 
            //game.setScreen( new SmokuMenu(game) );
           
           if (Gdx.input.isKeyPressed(Keys.M)) {
            //game.setScreen( new GameWp(game,1))

               game.pause();
           }


         
        // update
        uiStage.act(dt);

        // draw graphics
        Gdx.gl.glClearColor(0, 0, 0, 0);
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
    public void show()    {  
   
    }

    @Override
    public void hide()    {  }


}