package com.sticky.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sticky.game.AdHandler;
import com.sticky.game.StickyDemo;



/**
 * Created by Gomesito on 17/3/2016.
 */
public class MenuState extends State {

    private Texture background;
    private Texture playBtn;
    private Rectangle playBtnRect;
    private Texture exitBtn;
    private Rectangle exitBtnRect;
    private Texture scoresBtn;
    private Rectangle scoresBtnRect;
    static float aspectRatioX;
    static float aspectRatioY;


    private BitmapFont font;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, StickyDemo.WIDTH / 2, StickyDemo.HEIGHT / 2);
        background = new Texture("background.png");
        playBtn = new Texture("play.png");
        exitBtn = new Texture("exit.png");
        scoresBtn = new Texture("scores.png");

        StickyDemo.toggle = true;

        aspectRatioX = (float)Gdx.graphics.getWidth()/(2*cam.position.x);
        aspectRatioY = (float)Gdx.graphics.getHeight()/(2*cam.position.y);
        playBtnRect = new Rectangle(aspectRatioX*cam.position.x - (playBtn.getWidth()/2)*aspectRatioX,cam.position.y*aspectRatioY - scoresBtn.getHeight()*aspectRatioY/2 - playBtn.getHeight()*aspectRatioY/2 - playBtn.getHeight()*aspectRatioY ,playBtn.getWidth()*aspectRatioX,playBtn.getHeight()*aspectRatioY);
        exitBtnRect = new Rectangle(aspectRatioX*cam.position.x - (exitBtn.getWidth()/2)*aspectRatioX,cam.position.y*aspectRatioY + exitBtn.getHeight()/2*aspectRatioY + scoresBtn.getHeight()*aspectRatioY / 2,exitBtn.getWidth()*aspectRatioX,exitBtn.getHeight()*aspectRatioY);
        scoresBtnRect = new Rectangle(aspectRatioX*cam.position.x - (scoresBtn.getWidth()/ 2)*aspectRatioX,aspectRatioY*cam.position.y - (scoresBtn.getHeight()/2)* aspectRatioY,scoresBtn.getWidth()*aspectRatioX,scoresBtn.getHeight()*aspectRatioY);

        font = new BitmapFont();
        font.setColor(Color.RED);

        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            if(playBtnRect.contains(Gdx.input.getX(),Gdx.input.getY()))
                 gsm.set(new PlayState(gsm));
            if(scoresBtnRect.contains(Gdx.input.getX(),Gdx.input.getY()))
                gsm.set(new ScoresState(gsm));
            if(exitBtnRect.contains(Gdx.input.getX(),Gdx.input.getY()))
                Gdx.app.exit();
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
        sb.draw(background, 0, 0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y +scoresBtn.getHeight()/2 + 15 );
        sb.draw(scoresBtn, cam.position.x - scoresBtn.getWidth() / 2, cam.position.y - scoresBtn.getHeight() / 2);
        sb.draw(exitBtn, cam.position.x - exitBtn.getWidth() / 2, cam.position.y - scoresBtn.getHeight()/2 - exitBtn.getHeight() -15 );
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        scoresBtn.dispose();
        exitBtn.dispose();
    }
}
