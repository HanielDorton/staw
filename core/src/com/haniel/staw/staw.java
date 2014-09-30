package com.haniel.staw;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//public class staw extends ApplicationAdapter {
public class staw extends Game {
	SpriteBatch batch;
	public BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new LoadingScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
    public void dispose() {
        batch.dispose();
    }
}
