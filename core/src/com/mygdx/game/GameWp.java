package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import javax.swing.text.StyledEditorKit;
import static jdk.nashorn.internal.runtime.Debug.id;

public class GameWp extends Game
{
    private Stage mainStage;
    private Stage uiStage;

    private BaseActor mousey;
    private BaseActor smiglo;
    private BaseActor floor;
    private BaseActor winText;   


    private float timeElapsed;
    private Label timeLabel;

 	// game world dimensions
    final int mapWidth = 600;
    final int mapHeight = 1000;
    // window dimensions
    final int viewWidth = 600;
    final int viewHeight = 480;
    
    private String txtVal = "a";
    private String txtVal1 = "a";
    private TextField txtSmiglo;
    

    private boolean mouseStop = false;
    private BaseActor tank;
    private TextField txtTank;
    
    private BaseActor hamvee;
    private TextField hamveeTxt;
    private String txtHamvee = "a";
    private long id;
    private ParticleEffect pe;
    private SpriteBatch batch;
    private ParticleActor baseExplosion;
    private ParticleActor explosion;
    
    public boolean tankExplode = true;
    public boolean heliExplode = true;
    private boolean hamveeExplode = true;
    
    private boolean endSound = true;
    
    
    private Sound sound2;

    private int score = 0;
    private BaseActor floor2;

    GameWp(Game game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

     
    
    public void create() 
    {        
        
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("Sounds_of_War.mp3")); 
        sound.loop(0.1f);
        
        mainStage = new Stage();
        Gdx.input.setInputProcessor(mainStage);
        
        uiStage = new Stage();
        timeElapsed = 0;

        floor = new BaseActor();
        floor.setTexture( new Texture(Gdx.files.internal("mapa600x1000.png")) );
        floor.setPosition( 0, 0 );
        mainStage.addActor( floor );

         Skin mSkin = new Skin(Gdx.files.internal("data/uiskin.json"));   
     
     hamveeTxt = new TextField("", mSkin);
     hamveeTxt.setSize(200, 40);
     hamveeTxt.setPosition(350, 570);
     hamveeTxt.setVisible(false);
  
     hamveeTxt.setTextFieldListener(new TextFieldListener() {
      
       @Override
            public void keyTyped(TextField textField, char key) {
                    txtHamvee= textField.getText();
            }
        });
     
     
     txtTank = new TextField("", mSkin);
     txtTank.setSize(200, 40);
     txtTank.setPosition(100, 150);
     txtTank.setVisible(false);
  
     txtTank.setTextFieldListener(new TextFieldListener() {
       @Override
            public void keyTyped(TextField textField, char key) {
                    txtVal1= textField.getText();
            }
        });

 
     txtSmiglo = new TextField("", mSkin);
     txtSmiglo.setSize(200, 40);
     txtSmiglo.setPosition(310, 350);
     txtSmiglo.setVisible(false);  
     txtSmiglo.setCursorPosition(mapWidth);
   
     
   
     
     txtSmiglo.setTextFieldListener(new TextFieldListener() {
       @Override
            public void keyTyped(TextField textField, char key) {
                    txtVal= textField.getText();
            }
            
     
        });

      
        hamvee = new BaseActor();
        hamvee.setTexture( new Texture(Gdx.files.internal("hamvee.jpg")) );
        hamvee.setPosition( 300, 600 );
        hamvee.setSize(250, 150);
        hamvee.addAction(fadeOut((float) 0.001));
        hamvee.setOrigin( hamvee.getWidth()/2, hamvee.getHeight()/2 );
        mainStage.addActor( hamvee );
   
        tank = new BaseActor();
        tank.setTexture( new Texture(Gdx.files.internal("tank.jpg")) );
        tank.setPosition( 100, 150 );
        tank.setSize(300, 200);
        tank.addAction(fadeOut((float) 0.001));
        tank.setOrigin( tank.getWidth()/2, tank.getHeight()/2 );   
        mainStage.addActor( tank );
            
        smiglo = new BaseActor();
        smiglo.setTexture( new Texture(Gdx.files.internal("smiglo.jpg")) );
        smiglo.setPosition( 200, 350 );
        smiglo.addAction(fadeOut((float) 0.001));
        smiglo.setOrigin( smiglo.getWidth()/2, smiglo.getHeight()/2 );
        mainStage.addActor( smiglo );
       
        mousey = new BaseActor();
        mousey.setTexture( new Texture(Gdx.files.internal("solider.png")) );
        mousey.setSize(110, 110);
        mousey.setOrigin( mousey.getWidth()/2, mousey.getHeight()/2 );
        mousey.setPosition( 100, 20 );
        mainStage.addActor(mousey);

       
                BitmapFont font = new BitmapFont();
                String text = "Time: 0";
                LabelStyle style = new LabelStyle( font, Color.NAVY );
                timeLabel = new Label( text, style );
                timeLabel.setFontScale(2);
                timeLabel.setPosition(500,440); // sets bottom left (baseline) corner?
                uiStage.addActor( timeLabel );

        
        baseExplosion = new ParticleActor();
        baseExplosion.load("dymek10", "");
        
        mainStage.addActor(txtSmiglo);
        mainStage.addActor(txtTank);
        mainStage.addActor(hamveeTxt);      


    }

    public void render() 
    {   
 

      

// process input
        mousey.velocityX = 0;
        mousey.velocityY = 0;

if (!mouseStop){
        if (Gdx.input.isKeyPressed(Keys.LEFT)) 
            mousey.velocityX -= 100;
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            mousey.velocityX += 100;;
        if (Gdx.input.isKeyPressed(Keys.UP)) 
            mousey.velocityY += 100;
        if (Gdx.input.isKeyPressed(Keys.DOWN)) 
            mousey.velocityY -= 100;
  
}
        // update
     
            float dt = Gdx.graphics.getDeltaTime();
            mainStage.act(dt);
            uiStage.act(dt);
   

		// bound mousey to the rectangle defined by mapWidth, mapHeight
        mousey.setX( MathUtils.clamp( mousey.getX(), 0,  mapWidth - mousey.getWidth() ));
        mousey.setY( MathUtils.clamp( mousey.getY(), 0,  mapHeight - mousey.getHeight() ));

        // check win condition: mousey must be overlapping smiglo
        Rectangle smigloRectangle = smiglo.getBoundingRectangle();
        Rectangle tankRectangle = tank.getBoundingRectangle();
        Rectangle hamveeRectangle = hamvee.getBoundingRectangle();
     
    
        Rectangle mouseyRectangle = mousey.getBoundingRectangle();

        if(heliExplode){ //if helicopter not expolode
            
        if (smigloRectangle.overlaps(mouseyRectangle ) )
        {
        
            smiglo.addAction(fadeIn((float) 0.8));
            txtSmiglo.setVisible(true);
            mainStage.setKeyboardFocus(txtSmiglo);
            
            
                mouseStop = true;
            }
        }
        
        //TANK reactangle SOLIDER
        if(tankExplode){ //if tank no explode
        if (tankRectangle.overlaps(mouseyRectangle ) )
        {
            tank.addAction(fadeIn((float) 0.8));
            txtTank.setVisible(true);
            mainStage.setKeyboardFocus(txtTank);
            
            
            mouseStop = true;}
    }
 

        //Hamvee reactangle SOLIDER
        if(hamveeExplode){ //if hamvee no explode
        if (hamveeRectangle.overlaps(mouseyRectangle ) )
        {
                 
            
            hamvee.addAction(fadeIn((float) 0.8));          
            hamveeTxt.setVisible(true);
            mainStage.setKeyboardFocus(hamveeTxt);
        
            
            mouseStop = true;}
            
        }
        
        


        // draw graphics
        Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // camera adjustment
        Camera cam = mainStage.getCamera();
       
        // center camera on player
        cam.position.set( mousey.getX() + mousey.getOriginX(), 
            mousey.getY() + mousey.getOriginY(), 0 );

        // bound camera to layout
        cam.position.x = MathUtils.clamp(cam.position.x, viewWidth/2,  mapWidth - viewWidth/2);
        cam.position.y = MathUtils.clamp(cam.position.y, viewHeight/2, mapHeight - viewHeight/2);
        cam.update();

        
        if ( (txtVal.equalsIgnoreCase("helicopter")) && (txtSmiglo.isVisible()) ) { 
                mouseStop = false;
                txtSmiglo.setVisible(false);
                smiglo.setVisible(false);
               
 
                ParticleActor explosion_heli = baseExplosion.clone();
                        explosion_heli.setPosition( 400, 490 );
                         
                    if(heliExplode){
                        explosion_heli.start();  
                        Sound sound2 = Gdx.audio.newSound(Gdx.files.internal("grenade.mp3")); 
                        sound2.play();
                        mainStage.addActor(explosion_heli);          
                        score++;
                     heliExplode = false;    
                    };
                }

        
        
         if ( txtVal1.equalsIgnoreCase("tank") && txtTank.isVisible()) { 
                mouseStop = false;
                txtTank.setVisible(false);
                tank.setVisible(false);
               
                ParticleActor explosion = baseExplosion.clone();
                         explosion.setPosition( 180, 290 );
                         
                    if(tankExplode){
                         explosion.start();  
                              
                        Sound sound2 = Gdx.audio.newSound(Gdx.files.internal("grenade.mp3")); 
                        sound2.play();
                
                         mainStage.addActor(explosion);
                         score++;
                    tankExplode = false;    
                    };
                        
                

                
                
                }
         
         if ( txtHamvee.equalsIgnoreCase("hamvee") && hamveeTxt.isVisible()) { 
                mouseStop = false;
                hamveeTxt.setVisible(false);
                hamvee.setVisible(false);
 
                        ParticleActor explosion_hamvee = baseExplosion.clone();
                         explosion_hamvee.setPosition( 400, 620 );
                         
                    if(hamveeExplode){
                         explosion_hamvee.start();  
                              
                        Sound sound2 = Gdx.audio.newSound(Gdx.files.internal("grenade.mp3")); 
                        sound2.play();
                
                         mainStage.addActor(explosion_hamvee);          
                    score++;
                    hamveeExplode = false;    
 
                    }
         
         }
         
        mainStage.draw();
 
        System.out.println(score);
        
        if( score == 3){
                    if (endSound){
                    Sound sound_morse = Gdx.audio.newSound(Gdx.files.internal("Morse.mp3")); 
                        sound_morse.play();
                        
                    }
                    endSound = false;
                    
        };
        
    }

}