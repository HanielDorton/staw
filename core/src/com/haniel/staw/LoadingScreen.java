package com.haniel.staw;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
	private Texture background, whiteDot, redDot;
	private static int assumeX = 1000;
    private static int assumeY = 600; 
    private ArrayList<Texture> loadingDots = new ArrayList<Texture>();	
    private int startX;
    private float progress = .19f;
    private float progressBump = .19f;
			
	public LoadingScreen(final staw gam){
		this.game = gam;
		camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0, resizeX(assumeX), resizeY(assumeY));
        startX = 270;
        for (Texture dot : loadingDots) {
        	game.batch.draw(dot, resizeX(startX), resizeY(150), resizeX(51), resizeY(21));
        	startX += 100;
        }
        game.batch.end();
    	stage.act(Gdx.graphics.getDeltaTime());
    	stage.draw();
        Assets.update();
        if(Assets.manager.getProgress()==1) {
        	stage.dispose();
			game.setScreen(new GameScreen(game));
        }
        if (Assets.manager.getProgress() > progress) {
        	progress += progressBump;
        	loadingDots.remove(4);
        	loadingDots.add(0, redDot);
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
		atlas = new TextureAtlas("uiskin.atlas");
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
		stage = new Stage();
		table = new Table(skin);
		stage.addActor(table);
		table.setBounds(0, 0, stage.getWidth(), stage.getHeight());
		Gdx.input.setInputProcessor(stage);
		title =new Label(message, skin);
		table.add(title).padBottom(30);
		background = new Texture(Gdx.files.internal("loadingPanel.png"));
		whiteDot = new Texture(Gdx.files.internal("loadingbitwhite.png"));
		redDot = new Texture(Gdx.files.internal("loadingbitred.png"));
		for (int i = 0; i < 5; i++) {
			loadingDots.add(whiteDot);
		}
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
		background.dispose();;
		redDot.dispose();
		whiteDot.dispose();
		
	}
	public int resizeX(int positionX) {
		float x = Gdx.graphics.getWidth();
		float changeX = x / assumeX;
		return (int) ( positionX * changeX);		
	}
	
	public int resizeY(int positionY) {
		float y = Gdx.graphics.getHeight();
		float changeY = y / assumeY;
		return (int) ( positionY * changeY);		
	}
	

}
