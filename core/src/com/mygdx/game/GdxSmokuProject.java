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
import com.badlogic.gdx.math.MathUtils;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import static jdk.nashorn.internal.runtime.Debug.id;

public class GdxSmokuProject extends Game
{
    private Stage mainStage;
    private Stage uiStage;

    private BaseActor mousey;
    private BaseActor cheese;
    private BaseActor floor;
    private BaseActor winText;   
    private boolean win;

    private float timeElapsed;
    private Label timeLabel;

 	// game world dimensions
    final int mapWidth = 800;
    final int mapHeight = 800;
    // window dimensions
    final int viewWidth = 640;
    final int viewHeight = 480;
    private TextField txtUsername;
    
    private String txtVal = "a";
    private String txtVal1 = "a";
    

    private boolean mouseStop = false;
    private BaseActor tank;
    private TextField txtUsername1;
    
    private BaseActor hamvee;
    private TextField hamveeTxt;
    private String txtHamvee = "a";
    private long id;
    
    
    public void create() 
    {        
        
        
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("Sounds_of_War.mp3")); 
        sound.loop(0.1f);
  
        
        
        mainStage = new Stage();
        Gdx.input.setInputProcessor(mainStage);
        
        uiStage = new Stage();
        timeElapsed = 0;

        floor = new BaseActor();
        floor.setTexture( new Texture(Gdx.files.internal("mapa.png")) );
        floor.setPosition( 0, 0 );
        mainStage.addActor( floor );
     
     
     Skin mSkin = new Skin(Gdx.files.internal("data/uiskin.json"));   
        
     
     
     
     hamveeTxt = new TextField("", mSkin);
     hamveeTxt.setMessageText("I recognized the unit with ?");
     hamveeTxt.setSize(200, 40);
     hamveeTxt.setPosition(410, 590);
     hamveeTxt.setVisible(false);
  
     hamveeTxt.setTextFieldListener(new TextFieldListener() {
      
       @Override
            public void keyTyped(TextField textField, char key) {
                    txtHamvee= textField.getText();
            }
        });
     
     
     txtUsername1 = new TextField("", mSkin);
     txtUsername1.setMessageText("I recognized the unit with ?");
     txtUsername1.setSize(200, 40);
     txtUsername1.setPosition(100, 450);
     txtUsername1.setVisible(false);
  
     txtUsername1.setTextFieldListener(new TextFieldListener() {
       @Override
            public void keyTyped(TextField textField, char key) {
                    txtVal1= textField.getText();
            }
        });

     txtUsername = new TextField("", mSkin);
     txtUsername.setMessageText("I recognized the unit with ?");
     txtUsername.setSize(200, 40);
     txtUsername.setPosition(420, 360);
     txtUsername.setVisible(false);
  
     txtUsername.setTextFieldListener(new TextFieldListener() {
       @Override
            public void keyTyped(TextField textField, char key) {
                    txtVal= textField.getText();
            }
        });

     
     
          
        hamvee = new BaseActor();
        hamvee.setTexture( new Texture(Gdx.files.internal("hamvee.jpg")) );
        hamvee.setPosition( 400, 600 );
        hamvee.setSize(250, 150);
        hamvee.addAction(fadeOut((float) 0.001));
        hamvee.setOrigin( hamvee.getWidth()/2, hamvee.getHeight()/2 );
        mainStage.addActor( hamvee );
   
        tank = new BaseActor();
        tank.setTexture( new Texture(Gdx.files.internal("tank.jpg")) );
        tank.setPosition( 10, 400 );
        tank.setSize(300, 200);
        tank.addAction(fadeOut((float) 0.001));
        tank.setOrigin( tank.getWidth()/2, tank.getHeight()/2 );
        
        
        mainStage.addActor( tank );
        
        
        cheese = new BaseActor();
        cheese.setTexture( new Texture(Gdx.files.internal("smiglo.jpg")) );
        cheese.setPosition( 400, 300 );
        cheese.addAction(fadeOut((float) 0.001));
        cheese.setOrigin( cheese.getWidth()/2, cheese.getHeight()/2 );
        mainStage.addActor( cheese );

       
        mousey = new BaseActor();
        mousey.setTexture( new Texture(Gdx.files.internal("solider.png")) );
        mousey.setSize(150, 210);
        mousey.setOrigin( mousey.getWidth()/2, mousey.getHeight()/2 );
        mousey.setPosition( 20, 20 );
        mainStage.addActor(mousey);

       
        BitmapFont font = new BitmapFont();
        String text = "Time: 0";
        LabelStyle style = new LabelStyle( font, Color.NAVY );
        timeLabel = new Label( text, style );
        timeLabel.setFontScale(2);
        timeLabel.setPosition(500,440); // sets bottom left (baseline) corner?
        uiStage.addActor( timeLabel );

        mainStage.addActor(txtUsername);
        mainStage.addActor(txtUsername1);
        mainStage.addActor(hamveeTxt);        
        
        win = false;
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

        // check win condition: mousey must be overlapping cheese
        Rectangle cheeseRectangle = cheese.getBoundingRectangle();
        Rectangle tankRectangle = tank.getBoundingRectangle();
        Rectangle hamveeRectangle = hamvee.getBoundingRectangle();
     
    
        Rectangle mouseyRectangle = mousey.getBoundingRectangle();

        if (cheeseRectangle.overlaps(mouseyRectangle ) )
        {

            mouseStop = true;
            cheese.addAction(fadeIn((float) 0.8));
            txtUsername.setVisible(true);
        }
        
        //TANK reactangle SOLIDER
        if (tankRectangle.overlaps(mouseyRectangle ) )
        {
            mouseStop = true;
            tank.addAction(fadeIn((float) 0.8));
            txtUsername1.setVisible(true);
        }


        //Hamvee reactangle SOLIDER
        if (hamveeRectangle.overlaps(mouseyRectangle ) )
        {
                 
            mouseStop = true;
            hamvee.addAction(fadeIn((float) 0.8));          
            hamveeTxt.setVisible(true);
        }
        
        
        
        
        
        
        

        if (!win)
        {
            timeElapsed += dt;
            timeLabel.setText( "Time: " + (int)timeElapsed );
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

        
        if ( (txtVal.equalsIgnoreCase("helicopter")) && (txtUsername.isVisible()) ) { 
                mouseStop = false;
                txtUsername.setVisible(false);
                cheese.setVisible(false);
                }
        
         if ( txtVal1.equalsIgnoreCase("tank") && txtUsername1.isVisible()) { 
                mouseStop = false;
                txtUsername1.setVisible(false);
                tank.setVisible(false);
                }
         
         if ( txtHamvee.equalsIgnoreCase("hamvee") && hamveeTxt.isVisible()) { 
                mouseStop = false;
                hamveeTxt.setVisible(false);
                hamvee.setVisible(false);
                }

         
        
        mainStage.draw();
        uiStage.draw();

        mainStage.draw();
        uiStage.draw();
    }
}