package com.mygdx.Objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Shape2D;

public abstract class Sprite2D {
    private Texture texture;
    private Shape2D collision;
    private Sound   sound;

    public Sprite2D(Texture texture,Shape2D collision,Sound sound){
        this.texture = texture;
        this.collision = collision;
        this.sound = sound;
    }
    public abstract boolean isCollided(Shape2D outerEntity);
    public abstract void onCollision();
}
