package com.sticky.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.sticky.game.StickyDemo;

import javafx.scene.shape.Circle;

/**
 * Created by Gomesito on 17/3/2016.
 */
public class Ball {
    private Vector3 position;


    private Rectangle ballVerticalRect;
    private Rectangle ballMiddleRect;
    private Rectangle ballHorizontalRect;
    private Rectangle posForFingerRect;
    private  Texture ball;
    public Sound sound;

    public Ball(float x, float y){
        position = new Vector3(x,y,0);
        ballVerticalRect = new Rectangle(x + 10,y,8,22);
        ballMiddleRect = new Rectangle(x + 5,y + 7,16,13);
        ballHorizontalRect = new Rectangle(x + 3, y + 12 ,22, 6);
        posForFingerRect = new Rectangle(x-15,y-15,22+15,22+15);
        ball = new Texture("balloonLeft.png");
        sound = Gdx.audio.newSound(Gdx.files.internal("BalloonPop.mp3"));
    }

    public void update(float dt) {

        if(position.y <= 0)
            position.y = 0;
        if(position.y >= StickyDemo.HEIGHT / 2 - ball.getHeight())
            position.y = StickyDemo.HEIGHT / 2 - ball.getHeight() ;
        if(position.x <= 0)
            position.x = 0;

    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBall() {
        return ball;
    }

    public Rectangle getBallVerticalRect() {
        return ballVerticalRect;
    }

    public Rectangle getBallMiddleRect() {
        return ballMiddleRect;
    }

    public Rectangle getBallHorizontalRect() {
        return ballHorizontalRect;
    }

    public Rectangle getPosForFingerRect() {
        return posForFingerRect;
    }

    public int getWidth() {
        return ball.getWidth();
    }

    public void dispose(){
        ball.dispose();
    }

    public void followFinger(int i,float ARX,float ARY) {
        this.position.x = Gdx.input.getX(i)/ARX - ball.getWidth()/2;
        this.position.y = Gdx.graphics.getHeight()/ARY-Gdx.input.getY(i)/ARY + ball.getHeight() ;
        ballVerticalRect.setPosition(this.position.x + 10, this.position.y);
        ballMiddleRect.setPosition(this.position.x + 5, this.position.y + 7);
        ballHorizontalRect.setPosition(this.position.x + 3, this.position.y + 12);
        posForFingerRect.setPosition(this.position.x-15,this.position.y-15 );
    }
}
