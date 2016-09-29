/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mygdx.end;

/**
 *
 * @author r.smoter
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.BaseGame;
import com.mygdx.game.BaseEnd;
import com.mygdx.game.BaseScreen;
import com.mygdx.game.PhysicsActor;
import com.mygdx.game.ScoreBox;
import java.util.ArrayList;

public class GameEnd extends BaseScreen
{
    private PhysicsActor[] background;
    private PhysicsActor[] ground;
    
    private PhysicsActor player;
    
    private ArrayList<PhysicsActor> starList;
    private ArrayList<PhysicsActor> bulletList;
    private float starTimer;
    

    private PhysicsActor Napisy;

    
    private Label baseText;
    
    private PhysicsActor bg0;
    private PhysicsActor Napisy_szukaj;
    private Label baseText1;
    private PhysicsActor[] rangiArray;
    private String[] nazwyRangArray;
    
    
    public ScoreBox aabbcc;
    public Wynik tablicaWynikow;
    
    private TextField nick;
    private String txtNick;
    
    private Stan stan;
    private Label TextNick;
    private Label TextBoard;

  
    
   
    
    public GameEnd(BaseGame g, ScoreBox score) {
        super(g);
        aabbcc = score;
    }
   
    @Override
    public void create() 
    {        
        // background objects 
 
    
        stan = new Stan();
        
        Skin mSkin = new Skin(Gdx.files.internal("data/uiskin.json"));   
     
        nick = new TextField("", mSkin);
        nick.setSize(200, 40);
        nick.setPosition(100, 200);
        nick.setVisible(false);        
          
        
       nick.setTextFieldListener(new TextField.TextFieldListener() {

            
       @Override
            public void keyTyped(TextField textField, char key) {
                    txtNick= textField.getText();
            }
        });
  
        
        
        bg0 = new PhysicsActor();
        bg0.storeAnimation( "default", new Texture(Gdx.files.internal("smok/las.png")) );
        bg0.setPosition( -390, -170 );
        bg0.addAction(Actions.sequence(Actions.fadeOut(0)));

        mainStage.addActor( bg0 );
        mainStage.addActor(nick);
        //napisy
        
        Napisy = new PhysicsActor();
        BitmapFont font = new BitmapFont();
        String text = "";
        
        LabelStyle style = new LabelStyle( font, Color.YELLOW );
        baseText = new Label( text, style );
        baseText.setX(110);
        baseText.setY(270);
        baseText.setFontScale(3, 3);

        mainStage.addActor( baseText );

        String text2 = "Enter nick name";
        TextNick = new Label( text2, style );
        TextNick.setX(100);
        TextNick.setY(270);
        TextNick.setFontScale(3, 3);
        TextNick.setVisible(false);

        mainStage.addActor( TextNick );


        String text3 = "Score Table";
        TextBoard = new Label( text3, style );
        TextBoard.setX(100);
        TextBoard.setY(520);
        TextBoard.setFontScale(3, 3);
        TextBoard.setVisible(false);

        mainStage.addActor( TextBoard );

        
        
        ////////////////////////////////////

        tablicaWynikow = new Wynik();
        //tablicaWynikow.wynikGracza(aabbcc);
        //System.out.println(aabbcc.getScoreString());
    
    }

    @Override
    public void update(float dt) 
    {   

        bg0.addAction(Actions.sequence(Actions.fadeIn(5)));
       tablicaWynikow.wynikGracza(aabbcc);
       
       if(tablicaWynikow.get_CzyDodacWpis()==1 && (!(stan.getAddNick()))){ 
            nick.setVisible(true);
            TextNick.setVisible(true);
            mainStage.setKeyboardFocus(nick);
          }
       else {
       baseText.setText(tablicaWynikow.wynikGraczaWyswietl(aabbcc));
       TextBoard.setVisible(true);
       };
       ;
          
      // System.out.println(txtNick);    
      // baseText.setText(tablicaWynikow.wynikGraczaWyswietl(aabbcc));
          
 
    };
    
 
   
        // add enemies at regular interval
        
    

    // InputProcessor methods for handling discrete input
    @Override
    public boolean keyDown(int keycode)
    {
        if (keycode == Keys.P)    
            togglePaused();

        if (keycode == Keys.M)    ;
        
        if (keycode == Keys.R)    ;
        
        if (keycode == Keys.SPACE) ;   
        
        if (keycode == Keys.ENTER){
            if (!(stan.getAddNick())){
            tablicaWynikow.wynikGraczaDodaj(aabbcc,txtNick);
            stan.addNick(true);
            TextNick.setVisible(false);
            nick.setVisible(false);
        }
        } ;
        
        
        
        
            //////////////
            ////BULLET         

        return false;

    }

}