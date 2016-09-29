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
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import java.util.HashMap;

public class AnimatedActor extends BaseActor
{
    private float elapsedTime;
    private Animation activeAnim;
    private String activeName;
    private HashMap<String,Animation> animationStorage;
    
    public AnimatedActor()
    {
        super();
        elapsedTime = 0;
        activeAnim = null;
        activeName = null;
        animationStorage = new HashMap<String,Animation>();
    }

    public void storeAnimation(String name, Animation anim)
    {
        animationStorage.put(name, anim);
        if (activeName == null)
            setActiveAnimation(name);
    }
    
    public void storeAnimation(String name, Texture tex)
    {
        TextureRegion reg = new TextureRegion(tex);
        TextureRegion[] frames = { reg };
        Animation anim = new Animation(1.0f, frames);
        storeAnimation(name, anim);
    }
    
    public void setActiveAnimation(String name)
    {
        if ( !animationStorage.containsKey(name) )
        {
            System.out.println("No animation: " + name);
            return;
        }
            
        activeName = name;
        activeAnim = animationStorage.get(name);
        elapsedTime = 0;
        
        Texture tex = activeAnim.getKeyFrame(0).getTexture();
        setWidth( tex.getWidth() );
        setHeight( tex.getHeight() );
    }
    
    public String getAnimationName()
    {  
        return activeName;  
    }

    public void act(float dt)
    {
        super.act( dt );
        elapsedTime += dt;
    }

    public void draw(Batch batch, float parentAlpha) 
    {
        region.setRegion( activeAnim.getKeyFrame(elapsedTime) );
        super.draw(batch, parentAlpha);
    }
    
    public void copy(AnimatedActor original)
    {
        super.copy(original);
        this.elapsedTime = 0;
        this.animationStorage = original.animationStorage; // sharing a reference
        this.activeName = new String(original.activeName);
        this.activeAnim = this.animationStorage.get( this.activeName );
    }

    public AnimatedActor clone()
    {
        AnimatedActor newbie = new AnimatedActor();
        newbie.copy( this );
        return newbie;
    }	
}