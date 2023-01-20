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

import java.util.Iterator;

public class MyGdxGame extends ApplicationAdapter {

    private Texture raindropImg;
    private Texture bucketImg;
    private Sound dropSound;
    private Music ambientRainMusic;
    private Music backgroundMusic;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Rectangle bucket;
    private Array<Rectangle> rainDrops;
    private long lastDropTime;

    @Override
    public void create() {
        bucketImg = new Texture(Gdx.files.internal("bucket.png"));
        raindropImg = new Texture(Gdx.files.internal("drop.png"));
        dropSound = Gdx.audio.newSound(Gdx.files.internal("waterdrop_default_04.ogg"));
        ambientRainMusic = Gdx.audio.newMusic(Gdx.files.internal("amb_rain_lp.wav"));
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Summer.mp3"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2;
        bucket.y = 20;
        bucket.width = 64;
        bucket.height = 64;

        rainDrops = new Array<>();

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

        if (TimeUtils.nanoTime() - lastDropTime > Math.pow(10d,9d))
            spawnRaindrop();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            bucket.x += 400 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            bucket.x -= 400 * Gdx.graphics.getDeltaTime();

        if (bucket.x < 0)
            bucket.x = 0;
        if (bucket.x > 800 - 64)
            bucket.x = 800 - 64;

        batch.begin();
        batch.draw(bucketImg, bucket.x, bucket.y);
        batch.end();

        shedRaindrop();
    }

    @Override
    public void dispose() {
        dropSound.dispose();
        batch.dispose();
        ambientRainMusic.dispose();
        bucketImg.dispose();
        raindropImg.dispose();
    }

    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.height = raindrop.width = 64;
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        rainDrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    private void shedRaindrop() {
        Iterator<Rectangle> iter = rainDrops.iterator();
        while (iter.hasNext()){
            Rectangle raindrop = iter.next();
            raindrop.y -= 300 * Gdx.graphics.getDeltaTime();
            if (raindrop.y < 0)
                iter.remove();
            if (bucket.overlaps(raindrop)){
                long soundID = dropSound.play();
                dropSound.setVolume(soundID,1f);
                iter.remove();
            }
            batch.begin();
            batch.draw(raindropImg,raindrop.x,raindrop.y);
            batch.end();
        }
    }
}
