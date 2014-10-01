package com.haniel.staw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class ShutdownScreen implements Screen{
	
	final staw game;
	public OrthographicCamera camera;
	private static int assumeX = 1000;
    private static int assumeY = 600; 
	private int start = 0;
	private int finish = 12;
	private float count = .25f;
	private float counting = 0;
	private Texture background = Assets.manager.get("shutdown-0.png", Texture.class);
	private Sound alert = Assets.manager.get("alert23.mp3", Sound.class);
	private boolean alertPlayed = false;
	
	public ShutdownScreen(staw game) {
		this.game = game;
		camera = new OrthographicCamera();
	    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
        game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		counting += Gdx.graphics.getDeltaTime();
		if (counting > count) {
			counting = 0;
			start++;
			if (start == finish) {
				Gdx.app.exit();
			} else {
				background = Assets.manager.get("shutdown-" + start + ".png", Texture.class);
			}
		}
		if (start == 8 && !alertPlayed) {
			alert.play();
			alertPlayed = true;
		}
		game.batch.draw(background, 0 , 0, resizeX(1000), resizeY(600));
		game.batch.end();
		
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		alert.play();
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	private int resizeX(int positionX) {
		float x = Gdx.graphics.getWidth();
		float changeX = x / assumeX;
		return (int) ( positionX * changeX);
		
	}
	
	private int resizeY(int positionY) {
		float y = Gdx.graphics.getHeight();
		float changeY = y / assumeY;
		return (int) ( positionY * changeY);
		
	}

}
