package com.sticky.game.sprites;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Gomesito on 17/3/2016.
 */
public class Wall {

    private Texture middleWall;

    public static  int midWidth ;

    public Wall() {
        middleWall = new Texture("middleWall.jpg");
        midWidth =  middleWall.getWidth();
    }


    public Texture getMiddleWall() {
        return middleWall;
    }

    public int getWallWidth(){
        return middleWall.getWidth();
    }

    public void dispose() {
        middleWall.dispose();
    }
}
