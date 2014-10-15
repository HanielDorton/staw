package com.haniel.staw;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import android.content.Context;

//public class staw extends ApplicationAdapter {
public class staw extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	private static Context c;
	
	@SuppressWarnings("static-access")
	public staw(Context c) {
		this.setC(c);
	}
	
	public staw() {
	}

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

	public static Context getC() {
		return c;
	}

	public static void setC(Context c) {
		staw.c = c;
	}
}
