package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.Objects.Bucket;
import com.mygdx.Objects.Raindrop;

import java.util.Iterator;
import java.util.LinkedList;

public class MyGdxGame extends ApplicationAdapter {

    private Texture raindropImg;
    private Sound dropSound;
    private Music ambientRainMusic;
    private Music backgroundMusic;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private LinkedList<Raindrop> rainDrops;
    private long lastDropTime;
    private Bucket bucket;

    @Override
    public void create() {
        bucket = new Bucket(800 / 2 - 64, 20);


        raindropImg = new Texture(Gdx.files.internal("drop.png"));
        dropSound = Gdx.audio.newSound(Gdx.files.internal("waterdrop_default_04.ogg"));
        ambientRainMusic = Gdx.audio.newMusic(Gdx.files.internal("amb_rain_lp.wav"));
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Summer.mp3"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        rainDrops = new LinkedList<>();

        ambientRainMusic.setLooping(true);
        ambientRainMusic.setVolume(0.5f);
        ambientRainMusic.play();
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.6f);
        backgroundMusic.play();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        if (TimeUtils.nanoTime() - lastDropTime > Math.pow(10d, 9d))
            spawnRaindrop();

        bucket.moveBucket();

        batch.begin();
        batch.draw(bucket.getTexture(), bucket.getPosX(), bucket.getPosY());
        batch.end();

        shedRaindrop();
    }

    @Override
    public void dispose() {
        dropSound.dispose();
        batch.dispose();
        ambientRainMusic.dispose();
        raindropImg.dispose();
    }

    private void spawnRaindrop() {
        rainDrops.add(new Raindrop(MathUtils.random(0, 800 - 64), 480));
        lastDropTime = TimeUtils.nanoTime();
    }

    private void shedRaindrop() {
        Iterator<Raindrop> itr = rainDrops.iterator();
        while(itr.hasNext()){
            Raindrop r = itr.next();
            r.moveDrop();
            if (r.isCollided(bucket.getCollision()))
                itr.remove();
        }
    }
}
