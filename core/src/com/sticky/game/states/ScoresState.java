package com.sticky.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sticky.game.StickyDemo;

/**
 * Created by Gomesito on 27/3/2016.
 */
public class ScoresState extends State{

    String highScore;
    FileHandle file;
    public static int newScore = 0;


    private Texture bg;

    private BitmapFont font;

    public ScoresState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, StickyDemo.WIDTH / 2, StickyDemo.HEIGHT / 2);

        StickyDemo.toggle = true;

        bg = new Texture("background.png");
        if(!Gdx.files.local("HighScore.txt").exists()){
            file = Gdx.files.local("HighScore.txt");
            file.writeString("0", false);
            highScore = file.readString();

        }
        else {
            file = Gdx.files.local("HighScore.txt");


            highScore = file.readString();

            setHighScore(newScore);
        }

        font = new BitmapFont();
        font.setColor(Color.YELLOW);
        font.getData().setScale(2);

        Gdx.input.setCatchBackKey(true);
    }

    public void setHighScore(int score) {
        String newScore = Integer.toString(score);
        if(Integer.parseInt(newScore)>Integer.parseInt(highScore)) {
            highScore = newScore;
            file.writeString(highScore, false);
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        font.draw(sb,"HIGH SCORE: "+highScore, cam.position.x-cam.position.x/2- font.getSpaceWidth()/2, cam.position.y+ font.getCapHeight()/2 +cam.position.y/4 );

        font.draw(sb,"LAST SCORE: "+Integer.toString(newScore), cam.position.x-cam.position.x/2- font.getSpaceWidth()/2, cam.position.y+ font.getCapHeight()/2);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        font.dispose();
    }
}
