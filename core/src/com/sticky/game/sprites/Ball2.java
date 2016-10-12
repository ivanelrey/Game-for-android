package com.sticky.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sticky.game.StickyDemo;

/**
 * Created by Gomesito on 24/3/2016.
 */
public class Ball2 {
    private Vector3 position;

    private Rectangle ball2VerticalRect;
    private Rectangle ball2MiddleRect;
    private Rectangle ball2HorizontalRect;

    private Texture ball;

    public Ball2(float x, float y){
        position = new Vector3(x,y,0);
        ball2VerticalRect = new Rectangle(x + 10,y,8,22);
        ball2MiddleRect = new Rectangle(x + 5,y + 7,16,13);
        ball2HorizontalRect = new Rectangle(x + 3, y + 12 ,22, 6);
        ball = new Texture("balloonLeft.png");
    }

    public void update(float dt) {

        if(position.y <= 0)
            position.y = 0;
        if(position.y >= StickyDemo.HEIGHT / 2 - ball.getHeight())
            position.y = StickyDemo.HEIGHT / 2 - ball.getHeight() ;
        if(position.x >= StickyDemo.WIDTH / 2 - ball.getWidth())
            position.x = StickyDemo.WIDTH / 2 - ball.getWidth() ;

    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBall() {
        return ball;
    }

    public Rectangle getBall2VerticalRect() {
        return ball2VerticalRect;
    }

    public Rectangle getBall2MiddleRect() {
        return ball2MiddleRect;
    }

    public Rectangle getBall2HorizontalRect() {
        return ball2HorizontalRect;
    }


    public void followFinger(int i,float ARX,float ARY) {
        this.position.x = Gdx.input.getX(i)/ARX - ball.getWidth()/2 ;
        this.position.y = (Gdx.graphics.getHeight()/ARY - Gdx.input.getY(i)/ARY) + ball.getHeight();

        ball2VerticalRect.setPosition(this.position.x + 10, this.position.y);
        ball2MiddleRect.setPosition(this.position.x + 5, this.position.y + 7);
        ball2HorizontalRect.setPosition(this.position.x + 3, this.position.y + 12);
    }

    public void dispose(){
        ball.dispose();
    }
}

