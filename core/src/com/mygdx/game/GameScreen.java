/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mygdx.game;

/**
 *
 * @author r.smoter
 */
import com.mygdx.end.GameEnd;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends  BaseScreen
{
    private PhysicsActor[] background;
    private PhysicsActor[] ground;
    
    private PhysicsActor player;
    
    private PhysicsActor baseEnemy;
    private ArrayList<PhysicsActor> enemyList;
    private float enemySpeed;
    
    private float enemyTimer;
    private boolean bulletTimer=false;
    private float bulletTimerMs;
    
    private ArrayList<PhysicsActor> starList;
    private ArrayList<PhysicsActor> bulletList;
    private float starTimer;
    
    private AnimatedActor baseSparkle;
    private AnimatedActor baseExplosion;
    
    private ArrayList<BaseActor> removeList;
    private boolean gameOver = false;
    
    // game world dimensions
    final int mapWidth = 800;
    final int mapHeight = 600;
    private PhysicsActor Napisy;
    private SpriteBatch batch;

    
    private Label baseText;
    private boolean bulletFire = false;
    
    private int wynik = 1;
    
    private final Sound soundPrivate1stClass = Gdx.audio.newSound(Gdx.files.internal("stopnie/Private1stClass.mp3"));
    private final Sound soundTree = Gdx.audio.newSound(Gdx.files.internal("stopnie/2ndPrivate.mp3"));

    private PhysicsActor baseStar_;
    private PhysicsActor baseStar;

    private PhysicsActor rangSpecialist;
    private PhysicsActor rangPrivate1stClass;
    private PhysicsActor rangnd2Private;
    
    private Magnetofon magnetofon;
    private PhysicsActor bullet;
    
    private PhysicsActor Napisy_szukaj;
    private Label baseText1;
    private PhysicsActor[] rangiArray;
    private PhysicsActor sand;
    private String[] nazwyRangArray;
    private Sekwencja Sekwencja;
    
    
    public ScoreBox score;
    
    public GameScreen(BaseGame g)
    {
        super(g);
    }
    @SuppressWarnings("empty-statement")
    public void create() 
    {        
        // background objects 
        
        magnetofon = new Magnetofon();
        score = new  ScoreBox();
        
        background = new PhysicsActor[2];
        PhysicsActor bg0 = new PhysicsActor();
        bg0.storeAnimation( "default", new Texture(Gdx.files.internal("smok/sky.jpg")) );
        bg0.setPosition( 0, -83 );
        bg0.setVelocityXY(-50,0);
        mainStage.addActor( bg0 );
        background[0] = bg0;
        
        Sekwencja = new Sekwencja();
        
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
        
        // player
        
        player = new PhysicsActor();
        Animation anim = GameUtils.parseImageFiles( 
            "planeGreen", ".png", 3, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        player.storeAnimation( "default", anim );
        player.setPosition(100,300);
        player.setAccelerationXY(0, -600); // gravity
        player.setOriginCenter();
        player.setEllipseBoundary();
        mainStage.addActor( player );

        
        
        bullet = new PhysicsActor();
        Texture bulletTexture = new Texture(Gdx.files.internal("bullets_.png")) ;
        bullet.storeAnimation( "default", bulletTexture );
        bullet.setPosition(200,300);
        bullet.setAccelerationXY(250, -40); // gravity
        bullet.setOriginCenter();
        bullet.setEllipseBoundary();
       // mainStage.addActor( bullet );
        
        
        
        
        // stars        // stars        // stars        // stars
        // stars        // stars        // stars        // stars
        
        rangiArray = new PhysicsActor[]{rangSpecialist,rangPrivate1stClass,rangnd2Private}; 
        
        rangSpecialist      = nowaRanga(rangSpecialist, "stopnie/Specialist.png");
        rangPrivate1stClass = nowaRanga(rangPrivate1stClass, "stopnie/Private1stClass.png");
        rangnd2Private      = nowaRanga(rangnd2Private, "stopnie/2ndPrivate.png");
        
        
 
        baseStar = new PhysicsActor();
        Texture starTex4 = new Texture(Gdx.files.internal("smok/chmura.png"));
        starTex4.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        baseStar.storeAnimation( "default", starTex4 );
        baseStar.setVelocityXY(-200,0);
        baseStar.setOriginCenter();
        baseStar.setEllipseBoundary();
        
        starList    = new ArrayList<PhysicsActor>();
        bulletList  = new ArrayList<PhysicsActor>();
        
        starTimer = 0;
        
        
        //napisy
        
        Napisy = new PhysicsActor();
        BitmapFont font = new BitmapFont();
        String text = "wynik " ;
        LabelStyle style = new LabelStyle( font, Color.BLACK );
        baseText = new Label( text, style );
        baseText.setX(10);
        baseText.setY(570);
        baseText.setFontScale(1,1);
        mainStage.addActor( baseText );

        ////////////////////////////////////
        //napisy
        
        Napisy_szukaj = new PhysicsActor();
        String text2 = "find " ;
        LabelStyle style2 = new LabelStyle( font, Color.BLACK );
        baseText1 = new Label( text2, style2 );
        baseText1.setX(300);
        baseText1.setY(570);
        baseText1.setFontScale(1,1);
        mainStage.addActor( baseText1 );

        ////////////////////////////////////
        
        
        
        // baseSparkle
        
        baseSparkle = new AnimatedActor();
        Animation sparkleAnim = GameUtils.parseSpriteSheet(
            "sparkle.png", 8,8, 0.01f, PlayMode.NORMAL);
        baseSparkle.storeAnimation( "default", sparkleAnim );
        baseSparkle.setWidth(64);
        baseSparkle.setHeight(64);
        baseSparkle.setOriginCenter();
        
        
        // enemy
        
        baseEnemy = new PhysicsActor();
        Animation redAnim = GameUtils.parseImageFiles( 
            "planeRed", ".png", 3, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        baseEnemy.storeAnimation( "default", redAnim );
        baseEnemy.setWidth( baseEnemy.getWidth() * 1.25f );
        baseEnemy.setHeight( baseEnemy.getHeight() * 1.25f );
        baseEnemy.setOriginCenter();
        baseEnemy.setEllipseBoundary();
        
        enemyTimer = 0;
        enemySpeed = -250;
        enemyList = new ArrayList<PhysicsActor>();
        
        // explosion
        
        baseExplosion = new AnimatedActor();
        Animation explosionAnim = GameUtils.parseSpriteSheet(
            "explosion.png", 6, 6, 0.03f, PlayMode.NORMAL);
        baseExplosion.storeAnimation( "default", explosionAnim );
        baseExplosion.setWidth(96);
        baseExplosion.setHeight(96);
        baseExplosion.setOriginCenter();
        
        removeList = new ArrayList<BaseActor>();

        
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
   
        
        
    
        //Sprawdza czy już koniec
        if (Sekwencja.getGameOwer()) {gameOver= true; player.destroy();}
        if ( gameOver ) { magnetofon.playGover();
                               game.setScreen( new GameEnd(game, score) );
                               return;}
        
        
        // for continuous velocity adjustment,
        //   uncomment the code below.
        // if (Gdx.input.isKeyPressed(Keys.SPACE))
        //    player.addVelocityXY(0, 25);
            
        // add new stars at regular interval
        starTimer += dt ;
        
       

        // // // SEKWENCJA // // // // //        
        Sekwencja.szukaj();
        
        
        
        if (starTimer > 1)
        {
            starTimer = 0;
                        
            int zmienna = MathUtils.random(100,500);
            int n = MathUtils.random(1,4);
            
            PhysicsActor star;

            if (n==1) {
            star = rangSpecialist.clone();    
            star.setName("rangSpecialist");
            
            
            }
            else if (n==2)
            {   
                star = rangPrivate1stClass.clone();
                star.setName("rangPrivate1stClass");
            }
            else if (n==3)
            {    
                star = rangnd2Private.clone();
                star.setName("rangnd2Private");
           
            }
            else if (n==4)
            {   
                star = baseStar.clone();
                star.setName("star");
            }
 
            else {
                star = baseStar;    

            };
                        

            star.setPosition( 900, zmienna );
            starList.add( star );
            
            star.setParentList( starList );
            mainStage.addActor( star );
            
        }
        
        // add enemies at regular interval

        enemyTimer += dt;
        if (enemyTimer > 3)
        {
            enemyTimer = 0;
            if (enemySpeed > -800)
                enemySpeed -= 15;
            PhysicsActor enemy = baseEnemy.clone();
            enemy.setPosition( 900, MathUtils.random(100,500) );
            enemy.setVelocityXY(enemySpeed, 0);
            enemy.setRotation(10);
            enemy.addAction( Actions.forever(
                Actions.sequence( Actions.rotateBy(-20,1), Actions.rotateBy(20,1) ) ));
                
            enemyList.add( enemy );
            enemy.setParentList( enemyList );
            mainStage.addActor( enemy );
        }
        
        // collision detection
        
        if ( player.getY() > mapHeight - player.getHeight() )
        {
            player.setVelocityXY(0,0);
            player.setY( mapHeight - player.getHeight() );
        }
        
        for (int i = 0; i < 2; i++)
        {
            PhysicsActor gr = ground[i];
            if ( player.overlaps(gr, true) ) // check for collision; resolve = true; no need to store result sometimes?
            {
                player.setVelocityXY(0,0);
            }
        }
        removeList.clear();
   
        for (PhysicsActor star : starList )
        {
            
             //System.out.println(star.getName());
            if ( star.getX() + star.getWidth() < 0)
                removeList.add(star);
            
            
           /// bullet in you head
                  for (int i = 0; i < bulletList.size(); i++) {
                      
                       //wywal z listy buulet aout
                       if (bulletList.get(i).getY() < 0){
                       bulletList.get(i).destroy();
                       bulletList.remove(i);}
                       
                       if ((!bulletList.isEmpty()) ){
                       if (star.overlaps(bulletList.get(i), false)) {
                       
                       bulletList.get(i).destroy();
                       bulletList.remove(i);
                       score.setScore(16);
                       removeList.add(star);
                       magnetofon.play("expl7");
                       
                AnimatedActor sparkle = baseSparkle.clone();
                sparkle.moveToOrigin(star);
                sparkle.addAction( Actions.sequence( Actions.delay(0.64f), Actions.removeActor() ) );
                mainStage.addActor(sparkle);
                       
                       
                   }}

                   }
 
            
            if ( player.overlaps(star, false) )
            {
                removeList.add(star);
               // System.out.println(star.getName() + " -----deed");
                
                if(star.getName().equals("rangSpecialist")){
                magnetofon.play("spec");
                Sekwencja.setOdnaleziony("rangSpecialist");                
                    } 
                else if(star.getName().equals("rangPrivate1stClass")){
                soundPrivate1stClass.play();
                Sekwencja.setOdnaleziony("rangPrivate1stClass");
                 
                    }
                else if(star.getName().equals("rangnd2Private")){
                soundTree.play();                
                Sekwencja.setOdnaleziony("rangnd2Private");
                    }
                else if(star.getName().equals("star")){
                Sekwencja.setOdnaleziony("star");
                    }
               
                
                magnetofon.play("bulb");                

                AnimatedActor sparkle = baseSparkle.clone();
                sparkle.moveToOrigin(star);
                sparkle.addAction( Actions.sequence( Actions.delay(0.64f), Actions.removeActor() ) );
                mainStage.addActor(sparkle);
                             
                //wynik = Sekwencja.wynik(wynik);                
                //score.setScore(wynik);
                
                Sekwencja.wynik(score);
            }
        }
        
    
        
        for (PhysicsActor enemy : enemyList )
        {
            if ( enemy.getX() + enemy.getWidth() < 0)
                removeList.add(enemy);
                
            if ( player.overlaps(enemy, false) )
            {
                AnimatedActor explosion = baseExplosion.clone();
                explosion.moveToOrigin(player);
                explosion.addAction( Actions.sequence( Actions.delay(1.08f), Actions.removeActor() ) );
                mainStage.addActor(explosion);
                magnetofon.play("explode1");
                removeList.add(player);
                gameOver = true;
               
                
            }
        }
        
        for (BaseActor ba : removeList)
        {
            ba.destroy();
        }
    
    
    
        // show score

        baseText.setText("Wynik "+ score.getScore() );
        baseText1.setText("find -> " + Sekwencja.getPoszukiwany());

        
        //interwał co ile ma strzelać
        bulletTimerMs +=dt;
        if (bulletTimerMs > 0.2) {bulletTimer=true;};
        
        
    
    
    
    }

    // InputProcessor methods for handling discrete input
    public boolean keyDown(int keycode)
    {
        if (keycode == Keys.P)    
            togglePaused();

        if (keycode == Keys.M)    
            
            bullet();
        if (keycode == Keys.R)    
            game.setScreen( new GameScreen(game) );
  
        if (keycode == Keys.Y)    
            game.setScreen( new GameEnd(game, score)); 
            

        
        
        if (keycode == Keys.SPACE)    
        { player.setVelocityXY(0,300);
           magnetofon.play("jump");} 

        return false;
    }
    
    public void bullet(){
         
        if(bulletTimer){
            
         PhysicsActor bullet_n;
         bullet_n = bullet.clone();
         bullet_n.setPosition( 190, player.getY()+10 );
         bulletList.add( bullet_n );
         mainStage.addActor( bullet_n );
         bulletTimerMs=0;
         bulletTimer = false;
         magnetofon.play("pistol-shot");
        }
            
    }            
            //////////////
            ////BULLET         


public  PhysicsActor nowaRanga(PhysicsActor aaa, String nazwaPliku){

        aaa = new PhysicsActor();
        Texture starTex = new Texture(Gdx.files.internal(nazwaPliku));
        starTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        aaa.storeAnimation( "default", starTex );
        aaa.setVelocityXY(-200,0);
        aaa.setOriginCenter();
        aaa.setEllipseBoundary();
        return aaa;
    }
        

}