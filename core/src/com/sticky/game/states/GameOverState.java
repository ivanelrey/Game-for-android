package com.sticky.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sticky.game.StickyDemo;
import com.sticky.game.sprites.HighScore;

/**
 * Created by Gomesito on 2/4/2016.
 */
public class GameOverState extends State{

    private Texture gameOver;
    private BitmapFont font;
    private String highScoreString;
    private float highScoreStringPosX;

    private Rectangle playAgainRect;
    private Rectangle menuRect;

    float aspectRatioX;
    float aspectRatioY;

    HighScore highScore;

    protected GameOverState(GameStateManager gsm) {
        super(gsm);

        cam.setToOrtho(false, StickyDemo.WIDTH / 2, StickyDemo.HEIGHT / 2);
        gameOver = new Texture("GameOver.png");

        StickyDemo.toggle = true;

        font = new BitmapFont();
        font.setColor(Color.YELLOW);
        font.getData().setScale(2);

        aspectRatioX = (float)Gdx.graphics.getWidth()/(2*cam.position.x);
        aspectRatioY = (float)Gdx.graphics.getHeight()/(2*cam.position.y);

        playAgainRect = new Rectangle(308*aspectRatioX,223*aspectRatioY,294*aspectRatioX,70*aspectRatioY);
        menuRect = new Rectangle(354*aspectRatioX,300*aspectRatioY,210*aspectRatioX,47*aspectRatioY);

        highScore = new HighScore();
        if (highScore.setHighScore(PlayState.score)){
            highScoreString = " NEW HIGH SCORE";
            highScoreStringPosX = 1*aspectRatioX+cam.position.x;
        }else {
            highScoreString = "NEW SCORE";
            highScoreStringPosX = 22*aspectRatioX+cam.position.x;
        }

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            gsm.set(new MenuState(gsm));
        }
        if(Gdx.input.justTouched()) {
            if(playAgainRect.contains(Gdx.input.getX(),Gdx.input.getY()))
                gsm.set(new PlayState(gsm));
            if(menuRect.contains(Gdx.input.getX(),Gdx.input.getY()))
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
        sb.draw(gameOver, 0, 0);
        font.draw(sb,highScoreString,highScoreStringPosX,cam.position.y+23*aspectRatioY);
        font.draw(sb,Integer.toString(PlayState.score),cam.position.x+cam.position.x/2-17*aspectRatioX,cam.position.y);
        sb.end();

    }

    @Override
    public void dispose() {
        gameOver.dispose();
        font.dispose();
    }
}
