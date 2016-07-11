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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import java.util.Map;

public class End extends Game implements Screen
{
    private Stage uiStage;
    private final Game game;
    private int wynik;
    
    private Label congratulaton;
    private BaseActor background;
    private Map<Integer, String> myMap;
    int scorE;
    int a=0;    //loop
    int b=0;    //loop
    private String wyjscie="";
    private TextField nick;
    private String txtNick;
    
    private Label tableScore;
    private boolean add_score;
            

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
        String fileScore_end = fileScore;
        
        fileScore = fileScore.replace("\n", "").replace("\r", "");
        System.out.println(fileScore);
        
        String[] tokens = fileScore.split(";");

        for (int i = 0; i < 5; i++) {
            
           String[] tok = tokens[i].split("::");
            
           String mystr = tok[0].replaceAll( "[^\\d]", "" );
           int number= Integer.parseInt(mystr);
        
           if (wynik <= number){
               if( (!(a>0)))
                   wyjscie = wyjscie + wynik +":: <-!!--!!->;\n"; 
                   add_score = true;
               a++;
               }
           
           wyjscie = wyjscie +tok[0]+"::"+tok[1]+";\n";
        }
        
        System.out.println("------i-------");
        System.out.println(wyjscie);       
        System.out.println("------i-------");
        
        FileHandle file2 = Gdx.files.local("score.txt");
        file2.writeString(wyjscie, false);
        
       
        //file serv
        ///////////////////////
        
        
        background = new BaseActor();
        background.setTexture( new Texture(Gdx.files.internal("moro.jpg")) );
        
        uiStage.addActor(background);
        Gdx.input.setInputProcessor(uiStage);
        
        BitmapFont font = new BitmapFont();
        String text = " KONIEC";
        LabelStyle style = new LabelStyle( font, Color.WHITE );
        Label instructions = new Label( text, style );
        instructions.setFontScale(3);
        instructions.setPosition(100, 80); 

        Skin mSkin = new Skin(Gdx.files.internal("data/uiskin.json"));   
     
        nick = new TextField("", mSkin);
        nick.setSize(200, 40);
        nick.setPosition(100, 200);
        nick.setVisible(false);        
        uiStage.addActor(nick);  
        
       nick.setTextFieldListener(new TextFieldListener() {
            
       @Override
            public void keyTyped(TextField textField, char key) {
                    txtNick= textField.getText();
            }
        });
        
        
        
        tableScore = new Label( wyjscie, style );
        tableScore.setFontScale(2);
        tableScore.setPosition(150, 180);
        tableScore.setVisible(false);
        
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
          Gdx.app.exit();;
        
        if (Gdx.input.isKeyPressed(Keys.SPACE)) 
           game.setScreen( new GameWp(game) );
        
        
           if (Gdx.input.isKeyPressed(Keys.ENTER)){
            
           /////////////////
           /////////////////
               if(add_score){
               FileHandle file2 = Gdx.files.local("score.txt");
               
               String wyjscie2 = wyjscie.replace("<-!!--!!->", txtNick);           
               file2.writeString(wyjscie2, false);
               
               String abc = file2.readString();
               tableScore.setText(abc);
               
               System.out.println(wyjscie2);
               nick.setVisible(false);
               add_score=false;
               }
           /////////////////
           /////////////////           
           } 
            //game.setScreen( new SmokuMenu(game) );
   
           
        
           if(add_score){    
                nick.setVisible(true);
                uiStage.setKeyboardFocus(nick);
           
           }else
                   
               tableScore.setVisible(true);
               
          System.out.println(txtNick);     
           
           

         
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
    public void show()    {  }

    @Override
    public void hide()    {  }


}





