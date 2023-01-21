package com.mygdx.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bucket {

    Texture bucketImg;
    Rectangle bucketCollision;

    public Bucket(){
        bucketImg = new Texture(Gdx.files.internal("bucket.png"));
        bucketCollision = new Rectangle();
        bucketCollision.width = bucketImg.getWidth();
        bucketCollision.height = bucketImg.getHeight();

    }
}
