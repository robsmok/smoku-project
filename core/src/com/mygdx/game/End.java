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
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class End extends Game implements Screen
{
    private Stage uiStage;
    private final Game game;
    private int wynik;
    
    private Label congratulaton;
    private BaseActor background;
    private Map<Integer, String> myMap;
    
            

    public End(Game g, int w)
    {       
        game = g;
        wynik = w;
        create();
    }
 

    public void create() 
    {        
    
        uiStage  = new Stage();

        ///////////////////////
        //file serv

    
        FileHandle file = Gdx.files.internal("score.txt");
        String fileScore = file.readString();
        
        String[] tokens = fileScore.split(";");

        myMap = new HashMap();
        for (String t : tokens)
        {
           String[] tok = t.split("::");
            
           String mystr = tok[0].replaceAll( "[^\\d]", "" );
           int number= Integer.parseInt(mystr);
            
           myMap.put(number, tok[1]);
        }
        
       List keys = new ArrayList(myMap.keySet());
       Collections.sort(keys);
       
        System.out.println(keys.size()+ "--------------");
 
	// Loop over String keys.
	for (Object key : keys) {
	    System.out.println(key + myMap.get(key));
 	}
        
        System.out.println(keys.size()+ "--------------");
        System.out.println(keys.size()+ "--------------");
 
        
        
        
        
        Map<String, String> yourMap = new HashMap<String, String>();
        yourMap.put("1", "one");
        yourMap.put("1", "onesdf");
        yourMap.put("2", "two");
        yourMap.put("3", "three");
        
        Map<String, String> sortedMap = new TreeMap<String, String>(yourMap);
        System.out.println(sortedMap);
 
        //file serv
        ///////////////////////
        
        
        
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

        
        Label tableScore = new Label( fileScore, style );
        tableScore.setFontScale(2);
        tableScore.setPosition(150, 210); 

        
        String text1 = " Wynik GRY " + wynik;
        congratulaton = new Label( text1, style );
        congratulaton.setFontScale(3);
        congratulaton.setPosition(100, 400); 
 
    
        uiStage.addActor(tableScore);
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