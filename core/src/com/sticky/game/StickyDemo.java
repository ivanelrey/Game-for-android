package com.sticky.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sticky.game.states.GameStateManager;
import com.sticky.game.states.MenuState;
import com.sticky.game.states.State;

public class StickyDemo extends ApplicationAdapter {
	public static final int WIDTH =1280;
	public static final int HEIGHT = 720;

	public static final String TITLE = "Sticky Ball";
	private GameStateManager gsm;
	private SpriteBatch batch;

	AdHandler handler;
	public static boolean toggle;

	private Music music;

	public StickyDemo(AdHandler handler){
		this.handler = handler;
	}
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("beat.mp3"));
		music.setLooping(true);
		music.setVolume(0.01f);
		//music.play();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		//handler.showAds(toggle);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}
}
