package com.sticky.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by Gomesito on 3/4/2016.
 */
public class HighScore {
    String highScore;
    FileHandle file;


    public HighScore() {
        if (!Gdx.files.local("HighScore.txt").exists()) {
            file = Gdx.files.local("HighScore.txt");
            file.writeString("0", false);
            highScore = file.readString();

        } else {
            file = Gdx.files.local("HighScore.txt");
            highScore = file.readString();

        }
    }

    public boolean  setHighScore(int score) {
        if(score>Integer.parseInt(highScore)) {
            highScore = Integer.toString(score);
            file.writeString(highScore, false);
            return true;
        }
        else
            return false;
    }


    public String getHighScore() {
        return highScore;
    }
}
