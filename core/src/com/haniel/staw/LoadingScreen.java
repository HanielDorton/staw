package com.haniel.staw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


public class LoadingScreen implements Screen{
	
	final staw game;
	private Stage stage;
	private Skin skin;
	private Table table;
	private Label title;
	private TextureAtlas atlas;
	OrthographicCamera camera;
	private String message;
	
	private List<String> messages = new ArrayList<String>();
		
	public LoadingScreen(final staw gam){
		this.game = gam;
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.end();
    	stage.act(Gdx.graphics.getDeltaTime());
    	stage.draw();
        Assets.update();
        if(Assets.manager.getProgress()==1) {
        	stage.dispose();
			game.setScreen(new GameScreen(game));
        }
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Assets.manager.clear(); 
		Assets.queueLoading();
		message = getMessage();
		atlas = new TextureAtlas("uiskin.atlas");
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
		stage = new Stage();
		table = new Table(skin);
		stage.addActor(table);
		table.setBounds(0, 0, stage.getWidth(), stage.getHeight());
		Gdx.input.setInputProcessor(stage);
		title =new Label(message, skin);
		table.add(title).padBottom(30);
		
	}

	private String getMessage() {
		messages.add("Load the damn torpedoes");
		messages.add("Acquiring Target Lock");
		messages.add("Make it so");
		messages.add("Helm Warp One, Engage");
		messages.add("Engage");
		messages.add("I'll be in my ready room");
		Random rand = new Random();
		message = messages.get(rand.nextInt(messages.size() - 1));
		return message;
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
		stage.dispose();
		
	}

}
