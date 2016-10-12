package com.sticky.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sticky.game.StickyDemo;
import com.sticky.game.sprites.Ball;
import com.sticky.game.sprites.Ball2;
import com.sticky.game.sprites.Nail;
import com.sticky.game.sprites.NailRightSide;
import com.sticky.game.sprites.Wall;

import java.util.Random;

/**
 * Created by Gomesito on 17/3/2016.
 */
public class PlayState extends State {
    private static final int NAIL_COUNT = 26;

    private   int NAIL_SPACING ;
    private Ball ball;
    private Ball2 ball2;
    private Texture bg;
    private Random rand;
    public static int score;
    float time;


    static float aspectRatioX;
    static float aspectRatioY;


    private BitmapFont font;

    private com.badlogic.gdx.utils.Array<Nail> nails;
    private com.badlogic.gdx.utils.Array<NailRightSide> nailsRight;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, StickyDemo.WIDTH / 2, StickyDemo.HEIGHT / 2);
        aspectRatioX = (float)Gdx.graphics.getWidth()/(2*cam.position.x);
        aspectRatioY = (float)Gdx.graphics.getHeight()/(2*cam.position.y);
        ball = new Ball(cam.position.x - (Gdx.graphics.getWidth()/aspectRatioX)/4,5*aspectRatioY);
        ball2 = new Ball2(cam.position.x + Gdx.graphics.getWidth()/aspectRatioX/4,5*aspectRatioY);
        rand = new Random();
        score = 0;
        time = 0;
        bg = new Texture("background.png");
        NAIL_SPACING = 0;
        nails = new com.badlogic.gdx.utils.Array<Nail>();
        nailsRight = new com.badlogic.gdx.utils.Array<NailRightSide>();

        StickyDemo.toggle = false;

        font = new BitmapFont();
        font.setColor(Color.RED);

        Gdx.input.setCatchBackKey(true);

        for (int i = 0; i < NAIL_COUNT; i++) {
            nails.add(new Nail(rand.nextInt(StickyDemo.WIDTH/4  - Wall.midWidth),StickyDemo.HEIGHT/2 + NAIL_SPACING));
            nailsRight.add(new NailRightSide(rand.nextInt(StickyDemo.WIDTH/4  - Wall.midWidth) + StickyDemo.WIDTH/4,StickyDemo.HEIGHT/2 + NAIL_SPACING));
            NAIL_SPACING = NAIL_SPACING + ball.getBall().getHeight();
        }
    }

    @Override
    protected void handleInput() {
        int i=0;

            if(Gdx.input.isTouched(i) && Gdx.input.isTouched(i+1) ) {
                    ball.followFinger(0,aspectRatioX,aspectRatioY);
                    ball2.followFinger(1,aspectRatioX,aspectRatioY);

            }
           // else if (Gdx.input.isTouched()) {
              //  if(abs(ball.getPosition().x-Gdx.){

                //}
               /* if (Gdx.input.getX(i) / aspectRatioX > Gdx.input.getX(i + 1) / aspectRatioX) {
                    ball2.followFinger(0, aspectRatioX, aspectRatioY);
                } else {
                    ball.followFinger(0, aspectRatioX, aspectRatioY);

                }*/



            if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
                  gsm.set(new MenuState(gsm));
            }


    }

    @Override
    public void update(float dt) {
        handleInput();
        ball.update(dt);
        ball2.update(dt);
        time = time +dt;
        if(time>1 ) {
            score = score + 1;
            time = 0;
        }
        for(int i = 0; i< nails.size; i++) {
            Nail nail = nails.get(i);
            NailRightSide nailRight =nailsRight.get(i);
            nail.update(dt);
            nailRight.update(dt);

            if (cam.position.y - (cam.viewportHeight / 2) > nail.getPosition().y + nail.getNail().getHeight()) {
                    nail.reposition();
                    nailRight.reposition();
            }
            if(ball.getBallVerticalRect().overlaps(nail.getNailAngleRect()) || ball.getBallVerticalRect().overlaps(nail.getNailTopRect())
                    || ball.getBallMiddleRect().overlaps(nail.getNailAngleRect()) || ball.getBallMiddleRect().overlaps(nail.getNailTopRect())
                    || ball.getBallHorizontalRect().overlaps(nail.getNailAngleRect()) || ball.getBallHorizontalRect().overlaps(nail.getNailTopRect())){
                ScoresState.newScore = score;
                ball.sound.play(0.3f);
                gsm.set(new GameOverState(gsm));
            }
            if(ball2.getBall2VerticalRect().overlaps(nailRight.getNailRightAngleRect()) || ball2.getBall2VerticalRect().overlaps(nailRight.getNailRightTopRect())
                    || ball2.getBall2MiddleRect().overlaps(nailRight.getNailRightAngleRect()) || ball2.getBall2MiddleRect().overlaps(nailRight.getNailRightTopRect())
                    || ball2.getBall2HorizontalRect().overlaps(nailRight.getNailRightAngleRect()) || ball2.getBall2HorizontalRect().overlaps(nailRight.getNailRightTopRect())){
                ScoresState.newScore = score;
                ball.sound.play(0.3f);
                gsm.set(new GameOverState(gsm));
            }
        }
        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(ball.getBall(), ball.getPosition().x, ball.getPosition().y);
        sb.draw(ball2.getBall(), ball2.getPosition().x, ball2.getPosition().y);

            for(Nail nail : nails) {
                sb.draw(nail.getNail(), nail.getPosition().x,nail.getPosition().y);
            }
            for(NailRightSide nailRight : nailsRight) {
                sb.draw(nailRight.getNail(), nailRight.getPosition().x,nailRight.getPosition().y);
            }

        font.draw(sb,"score = "+Integer.toString(score),cam.position.x -ball.getWidth(),cam.position.y*2);
        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        ball.dispose();
        ball2.dispose();
        font.dispose();
        for(Nail nail : nails)
            nail.dispose();
        for(NailRightSide nailRight : nailsRight)
            nailRight.dispose();
    }
}
