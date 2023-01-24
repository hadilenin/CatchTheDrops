package com.mygdx.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import static com.mygdx.Util.Util.*;

public class Bucket {

    Texture bucketImg;
    Rectangle bucketCollision;

    public Bucket(float x, float y){
        bucketImg = new Texture(Gdx.files.internal("bucket.png"));
        bucketCollision = new Rectangle();
        bucketCollision.width = bucketImg.getWidth();
        bucketCollision.height = bucketImg.getHeight();
        bucketCollision.x = x;
        bucketCollision.y = y;
    }

    public Bucket(){
        bucketImg = new Texture(Gdx.files.internal("bucket.png"));
        bucketCollision = new Rectangle();
        bucketCollision.width = bucketImg.getWidth();
        bucketCollision.height = bucketImg.getHeight();
    }

    public float moveBucket(){
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            bucketCollision.x += 400 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            bucketCollision.x -= 400 * Gdx.graphics.getDeltaTime();
        bucketCollision.x = clamp(bucketCollision.x,0,800 - bucketCollision.width).floatValue();
        return bucketCollision.x;
    }

    public float getPosX(){
        return this.bucketCollision.x;
    }
    public float getPosY(){
        return this.bucketCollision.y;
    }

    public Texture getTexture(){
        return this.bucketImg;
    }

    public Rectangle getCollision(){
        return this.bucketCollision;
    }

}
