package com.mygdx.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import static com.mygdx.Util.Util.*;




public abstract class Raindrop {

    private Texture   raindropImg;
    private Rectangle raindropCollision;
    private Sound     dropSound;

    public Raindrop() {
        dropSound = Gdx.audio.newSound(Gdx.files.internal("waterdrop_default_04.ogg"));
        raindropImg = new Texture(Gdx.files.internal("drop.png"));
    }

    public boolean isCollided(Rectangle OuterEntity){
         boolean isCollided = OuterEntity.overlaps(raindropCollision);
         if (isCollided)
             onCollideHandler();
         return isCollided;
    }

    public void shed(Number height){
        raindropCollision.y -= 300 * Gdx.graphics.getDeltaTime();
        raindropCollision.y = (float) clamp(raindropCollision.y,0,height);
    }

    private void spawn(int x,int y){
        raindropCollision.x = x;
        raindropCollision.y = y;
    }

    public abstract void onCollideHandler();



}
