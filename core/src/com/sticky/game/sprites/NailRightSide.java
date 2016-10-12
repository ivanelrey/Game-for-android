package com.sticky.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sticky.game.StickyDemo;

import java.util.Random;

/**
 * Created by Gomesito on 25/3/2016.
 */
public class NailRightSide {
    public static final int NAIL_WIDTH = 52;

    private static final int FLUCTUATION = 130;
    private static final int NAIL_GAP = 30;
    private static final int LOWEST_OPENING = 50;
    private static final int MAXIMUM_OPENING = 100;
    private  int MOVEMENT;

    private Vector3 position;
    private Vector3 velocity;

    private Rectangle nailAngleRect;
    private Rectangle nailTopRect;
    private Random rand;

    private Texture nail;
    float time;

    public NailRightSide(int x,int y) {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        nail =  new Texture("nail.png");
        nailAngleRect = new Rectangle(x + 12,y,3,13);
        nailTopRect = new Rectangle(x + 9,y + 14,10,4);
        MOVEMENT =60;
        time = 0;
        rand = new Random();

        position = new Vector3(x,y,0);
    }

    public void update(float dt) {
        velocity.add(0, 0, 0);
        velocity.scl(dt);
        position.add(0, -MOVEMENT * dt, 0);
        nailAngleRect.setPosition(position.x + 12, position.y);
        nailTopRect.setPosition(position.x + 9, position.y + 14);
        velocity.scl(1 / dt);

        time = time + dt;
        if(time>1 && MOVEMENT<100) {
            time = 0;
            MOVEMENT = MOVEMENT + 1 + rand.nextInt(8);
        }else if(time>3 && MOVEMENT<120){
            time = 0;
            MOVEMENT = MOVEMENT + 1 + rand.nextInt(5);
        }else if(time>4 && MOVEMENT<140){
            time = 0;
            MOVEMENT = MOVEMENT + 1 + rand.nextInt(3);
        }else if(time>5){
            time = 0;
            MOVEMENT = MOVEMENT + 1 + rand.nextInt(2);
        }
    }

    public Texture getNail() {
        return nail;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getNailRightTopRect() {
        return nailTopRect;
    }

    public Rectangle getNailRightAngleRect() {
        return nailAngleRect;
    }

    public void reposition() {
        position.set(rand.nextInt(StickyDemo.WIDTH/4  ) + StickyDemo.WIDTH/4 ,StickyDemo.HEIGHT ,0);
        nailAngleRect.setPosition(position.x + 12, position.y);
        nailTopRect.setPosition(position.x + 9, position.y + 14);
    }

    public void dispose() {
        nail.dispose();
    }

}
