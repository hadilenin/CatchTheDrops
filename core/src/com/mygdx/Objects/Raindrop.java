package com.mygdx.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import static com.mygdx.Util.Util.*;




public class Raindrop {

    private Texture   raindropImg;
    private Rectangle raindropCollision;
    private Sound     dropSound;

    public Raindrop(float x,float y) {
        dropSound = Gdx.audio.newSound(Gdx.files.internal("waterdrop_default_04.ogg"));
        raindropImg = new Texture(Gdx.files.internal("drop.png"));
        raindropCollision.width = raindropImg.getWidth();
        raindropCollision.height = raindropImg.getHeight();
        raindropCollision.x = x;
        raindropCollision.y = y;
    }

    public boolean isCollided(Rectangle OuterEntity) {
         boolean isCollided = OuterEntity.overlaps(raindropCollision);
         if (isCollided) {
             dropSound.play(1f);
             dispose();
         }
         return isCollided;
    }

    public float moveDrop(){
        raindropCollision.y -= 300 * Gdx.graphics.getDeltaTime();
        raindropCollision.y =  clamp(raindropCollision.y,0f,480f);
        return raindropCollision.y;
    }

    public void spawn(int x,int y){
        raindropCollision.x = x;
        raindropCollision.y = y;
    }

    private void dispose(){
        raindropImg.dispose();
    }

}
