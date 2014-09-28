package com.haniel.staw;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.haniel.staw.Cards.Card;
import com.haniel.staw.Cards.ShipCard;


public class GameScreen implements Screen{
	final staw game;
	public OrthographicCamera camera;
	private static int screenWidth = 1000;
    private static int screenHeight = 600;
    
	private Stage stage;
	private Skin skin;
	//private Skin skin2;
	private Table fleetTable, shipTable, leftTable, rightTable, bottomTable;
	private TextureAtlas atlas;
	//private TextureAtlas atlas2;
	private TextField loadFile;
	private Button buttonMore, buttonLess;
	//private ListFileChooser chooser;
	private List<Fleet> fleets = new ArrayList<Fleet>();	
	private List<Actor> actors = new ArrayList<Actor>();
	public List<Card> currentCards = new ArrayList<Card>();
	private int startingCard = 0;
	private int numberOfCards = 4;
	private int buttonWidth = 200;
	private int buttonHeight = 40;
    
	public GameScreen(final staw gam) {
		this.game = gam;
		camera = new OrthographicCamera();
	    camera.setToOrtho(false, screenWidth, screenHeight);		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
        game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		int x = 62;
		int size = currentCards.size();
		for (int i = startingCard; i < startingCard + numberOfCards; i++) {
			if (i < size) {
				game.batch.draw(currentCards.get(i).getTexture(), x, 150, 200, 300);
				x += 225;
			}
		}
		game.batch.end();
    	stage.act(Gdx.graphics.getDeltaTime());
    	stage.draw();
	}
	public int getWidth() {
    	return screenWidth;
    }

    public int getHeight() {
    	return screenHeight;
    }


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		atlas = new TextureAtlas("uiskin.atlas");
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
		//atlas2 = new TextureAtlas("uiskin2.atlas");
		//skin2 = new Skin(Gdx.files.internal("uiskin2.json"), atlas2);
		stage = new Stage();
		fleetTable = new Table(skin);
		shipTable = new Table(skin);
		rightTable = new Table(skin);
		leftTable = new Table(skin);
		bottomTable = new Table(skin);
		stage.addActor(fleetTable);
		stage.addActor(shipTable);
		stage.addActor(rightTable);
		stage.addActor(leftTable);
		stage.addActor(bottomTable);
		shipTable.setBounds(0, 515, stage.getWidth(), 40);
		fleetTable.setBounds(0, 560, stage.getWidth(), 40);
		
		rightTable.setBounds(960, 0, 40, stage.getHeight());
		leftTable.setBounds(0, 0, 40, stage.getHeight());
		bottomTable.setBounds(0, 0, stage.getWidth(), 40);
		Gdx.input.setInputProcessor(stage);
		/*
		chooser = new ListFileChooser(skin2, new Listener() {

			@Override
			public void choose(FileHandle file) {
				System.out.println("chose " + file);
			}

			@Override
			public void choose(Array<FileHandle> files) {
				System.out.println("chose");
				for(int i = 0; i < files.size; i++)
					System.out.println("\t" + files.get(i).name());
			}

			@Override
			public void cancel() {
				System.out.println("cancelled");
			}
		});
		
		chooser.setDirectoriesChoosable(true);
		chooser.setShowHidden(true);
		
		Window window = new Window("choose a file", skin);
		window.setPosition(stage.getWidth() / 2 - chooser.getWidth() / 2, stage.getHeight() / 2 - chooser.getHeight() / 2);
		window.setResizable(true);
		window.add(chooser).expand().fill();
		*/
		
		loadFile = new TextField("/home/haniel/Fed.xml", skin);
		loadFile.addListener(new InputListener() {
			public boolean keyUp(InputEvent event, int keycode) {
	            if (keycode == Input.Keys.ENTER) {
	            	if (!(Gdx.files.absolute(loadFile.getText()).exists())) {
						addError("File does not exist", "Belay that Order");
	            	} else{
	            		loadFleet(loadFile.getText());
	            	}
	               
	            }
	            return false;
	        }

		});
		buttonMore = new TextButton("+", skin);
		buttonMore.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				startingCard += numberOfCards;
				leftTable.add(buttonLess).width(buttonHeight).height(buttonWidth);
				if (startingCard + numberOfCards > currentCards.size()) {
					//buttonMore.remove();
					rightTable.clear();
				}
			}
		});
		buttonLess = new TextButton("-", skin);
		buttonLess.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				startingCard -= numberOfCards;
				if (startingCard == 0) {
					//buttonLess.remove();
					leftTable.clear();
				}
				if (startingCard + numberOfCards < currentCards.size()) {
					rightTable.add(buttonMore).width(buttonHeight).height(buttonWidth);;
				}
			}
		});
		//actors.add(window);
		actors.add(loadFile);
		recreateTable();
		
	}

	private void recreateTable() {
		if (actors.size() > 4) actors.remove(0);
		for (Actor w : actors) {
			fleetTable.add(w).width(buttonWidth).height(buttonHeight).padRight(20);
		}		
	}

	protected void loadFleet(String text) {
		final Fleet fleet = new Fleet(this, text);
		fleets.add(fleet);
		File f = new File(text);
		TextButton button = new TextButton((f.getName()).replace(".xml", ""), skin);
		button.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				displayFleet(fleet);	
			}
		});
		actors.add(button);
		fleetTable.reset();
		recreateTable();
	}
	
	public void displayFleet(Fleet fleet) {
		//separate fleet by ships and make a display button for each one
		clearBoard();
		shipTable.clear();
		for (final Card ship : fleet.getShips()) {
			TextButton button = new TextButton(ship.getName(), skin);
			button.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {
					displayShip((ShipCard) ship);	
				}
			});
			shipTable.add(button).width(buttonWidth).height(buttonHeight).padRight(20);
		}
	}
	
	public void displayShip(ShipCard ship) {
		clearBoard();
		ship.displayShip(this);
		if (currentCards.size() > numberOfCards) rightTable.add(buttonMore).width(buttonHeight).height(buttonWidth);;
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
	
	public void addError(String errorString, String okString) {
		Dialog d = new Dialog(errorString, skin);
		d.button(okString);
		bottomTable.add(d).width(buttonWidth).height(buttonHeight);
	}
	
	public void addCards(Card card) {
			currentCards.add(card);
	}
	

	public void clearBoard() {
		currentCards.clear();
		leftTable.clear();
		rightTable.clear();
		startingCard = 0;
	}

}
