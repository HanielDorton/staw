package com.haniel.staw;


import java.io.File;
import java.util.ArrayList;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.XmlReader;
import com.haniel.staw.Cards.Card;
import com.haniel.staw.Cards.ShipCard;


public class GameScreen implements Screen{
	final staw game;
	public OrthographicCamera camera;
	private static int assumeX = 1000;
    private static int assumeY = 600;    
	private Stage stage;
	private Skin skin, skin2;
	private Table fleetTable, shipTable, leftTable, rightTable, bottomTable, centerTable;
	private TextureAtlas atlas, atlas2;
	private TextButton loadFile;
	private Button buttonMore, buttonLess, buttonMoreActive, buttonLessActive;
	List<String> directoryList;
	private ArrayList<Fleet> fleets = new ArrayList<Fleet>();	
	private ArrayList<TextButton> fleetButtons = new ArrayList<TextButton>();
	private ArrayList<TextButton> shipButtons = new ArrayList<TextButton>();
	public ArrayList<Card> currentCards = new ArrayList<Card>();
	private int startingCard = 0;
	private int numberOfCards = 4;
	private int buttonWidth = resizeX(200);
	private int buttonHeight = resizeY(60);
	private int buttonPad = resizeX(20);
	private FileHandle currentDirectory = Gdx.files.absolute("/");
	private Texture backgroundPanel = Assets.manager.get("backgroundpanel.png", Texture.class);


    
	public GameScreen(final staw gam) {
		this.game = gam;
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
		int x = resizeX(70);
		int size = currentCards.size();
		game.batch.draw(backgroundPanel, 0, 0, resizeX(assumeX), resizeY(assumeY));
		for (int i = startingCard; i < startingCard + numberOfCards; i++) {
			if (i < size) {
				if (currentCards.get(i).hasTexture()) {
					game.batch.draw(currentCards.get(i).getTexture(), x, resizeY(106), resizeX(200), resizeY(278));
					x += resizeX(220);
				}
			}
		}
		game.batch.end();
    	stage.act(Gdx.graphics.getDeltaTime());
    	stage.draw();
	}

	@Override
	public void resize(int width, int height) {		
	}

	@Override
	public void show() {
		atlas = new TextureAtlas("uiskin.atlas");
		atlas2 = new TextureAtlas("uiskin2.atlas");
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
		skin2 = new Skin(Gdx.files.internal("uiskin.json"), atlas2);
		directoryList = new List<String>(skin);
		stage = new Stage();
		fleetTable = new Table(skin2);
		shipTable = new Table(skin);
		rightTable = new Table(skin);
		leftTable = new Table(skin);
		bottomTable = new Table(skin);
		centerTable = new Table(skin);
		stage.addActor(fleetTable);
		stage.addActor(shipTable);
		stage.addActor(rightTable);
		stage.addActor(leftTable);
		stage.addActor(bottomTable);
		stage.addActor(centerTable);
		shipTable.setBounds(0, resizeY(415), stage.getWidth(), resizeY(60));
		fleetTable.setBounds(0, resizeY(495), stage.getWidth(), resizeY(60));
		rightTable.setBounds(resizeX(950), resizeY(-56), resizeX(40), stage.getHeight());
		leftTable.setBounds(-1, resizeY(-56), resizeX(60), stage.getHeight());
		bottomTable.setBounds(0, 0, stage.getWidth(), resizeY(80));
		centerTable.setBounds(resizeX(300), resizeY(100), resizeX(400), resizeY(500));
		Gdx.input.setInputProcessor(stage);
						
		loadFile = new TextButton("Add Fleet", skin);
		loadFile.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				centerTable.clear();
				makeFileChooser();
			}
		});
		
		buttonMore = new TextButton("+", skin);
		buttonMore.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {

			}
		});
		buttonLess = new TextButton("-", skin);
		buttonLess.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {

			}
		});
		buttonMoreActive = new TextButton("+", skin2);
		buttonMoreActive.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				startingCard += numberOfCards;
				leftTable.clear();
				leftTable.add(buttonLessActive).width(buttonHeight - resizeX(22) ).height(buttonWidth + resizeY(8));
				if (startingCard + numberOfCards > currentCards.size()) {
					rightTable.clear();
					rightTable.add(buttonMore).width(buttonHeight- resizeX(22)).height(buttonWidth + resizeY(9));
					
				}
			}
		});
		buttonLessActive = new TextButton("-", skin2);
		buttonLessActive.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				startingCard -= numberOfCards;
				if (startingCard == 0) {
					leftTable.clear();
					leftTable.add(buttonLess).width(buttonHeight- resizeX(22)).height(buttonWidth + resizeY(9));
				}
				if (startingCard + numberOfCards < currentCards.size()) {
					rightTable.clear();
					rightTable.add(buttonMore).width(buttonHeight- resizeX(22)).height(buttonWidth + resizeY(9));
				}
			}
		});
		fleetButtons.add(loadFile);
		leftTable.add(buttonLess).width(buttonHeight - resizeX(22) ).height(buttonWidth + resizeY(8));
		rightTable.add(buttonMore).width(buttonHeight- resizeX(22)).height(buttonWidth + resizeY(9));
		recreateTable();
		
	}

	private void recreateTable() {
		if (fleetButtons.size() > 4) fleetButtons.remove(0);
		for (TextButton w : fleetButtons) {
			fleetTable.add(w).width(buttonWidth).height(buttonHeight).padRight(buttonPad);
		}		
	}

	protected void loadFleet(String text) {
		try {
			FileHandle handle = Gdx.files.absolute(text);
			XmlReader xml = new XmlReader();
			xml.parse(handle);
			final Fleet fleet = new Fleet(this, text);
			fleets.add(fleet);
			File f = new File(text);
			final TextButton button = new TextButton((f.getName()).replace(".xml", ""), skin2);
			button.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {
					displayFleet(fleet);	
					for (TextButton a : fleetButtons) {
						if (a != button) {
						}
					}
					
				}
			});
			fleetButtons.add(button);
			fleetTable.reset();
			recreateTable();
			centerTable.clear();
		}
		catch (Exception e) {
			addError("Unable to Parse File", "Belay that Order");
		}
	}
	
	public void displayFleet(Fleet fleet) {
		//separate fleet by ships and make a display button for each one
		clearBoard();
		shipTable.clear();
		shipButtons.clear();
		for (final Card ship : fleet.getShips()) {
			final TextButton button = new TextButton(ship.getName(), skin);
			button.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {
					displayShip((ShipCard) ship);
					for (TextButton a : shipButtons) {
						a.setSkin(skin);
					}
					button.setSkin(skin2);
				}
			});
			shipButtons.add(button);
			shipTable.add(button).width(buttonWidth).height(buttonHeight).padRight(buttonPad);
		}
	}
	
	public void displayShip(ShipCard ship) {
		clearBoard();
		ship.displayShip(this);
		leftTable.clear();
		rightTable.clear();
		leftTable.add(buttonLess).width(buttonHeight - resizeX(22) ).height(buttonWidth + resizeY(8));
		if (currentCards.size() > numberOfCards) rightTable.add(buttonMoreActive).width(buttonHeight- resizeX(22)).height(buttonWidth + resizeY(9));
		else rightTable.add(buttonMore).width(buttonHeight- resizeX(22)).height(buttonWidth + resizeY(9));
				
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	public void addError(String errorString, String okString) {
		Dialog d = new Dialog(errorString, skin);
		d.button(okString);
		bottomTable.add(d).width(buttonWidth * 2).height(buttonHeight * 2);
	}
	
	public void addCards(Card card) {
			currentCards.add(card);
	}
	

	public void clearBoard() {
		currentCards.clear();
		leftTable.clear();
		rightTable.clear();
		leftTable.add(buttonLess).width(buttonHeight - resizeX(22) ).height(buttonWidth + resizeY(8));
		rightTable.add(buttonMore).width(buttonHeight- resizeX(22)).height(buttonWidth + resizeY(9));
		startingCard = 0;
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
	
	private void makeFileChooser() {		

		centerTable.setBounds(resizeX(300), resizeY(100), resizeX(400), resizeY(500));
		
		final TextField currentDirectoryText = new TextField(currentDirectory.path(), skin);
		
		final TextButton exitButton = new TextButton("Exit", skin);
		exitButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				centerTable.clear();
			}
		});
		
		TextButton backDirectory = new TextButton("Move Up", skin);
		backDirectory.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				currentDirectory = currentDirectory.parent();
				loadFiles(currentDirectory);
				currentDirectoryText.setText(currentDirectory.path());
			}
		});
		
		TextButton openFile = new TextButton("openFile", skin);
		openFile.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				FileHandle newFile = Gdx.files.absolute(currentDirectory + directoryList.getSelected() + "/");
				if (newFile.isDirectory()) {
					currentDirectory = newFile;
					loadFiles(currentDirectory);
					currentDirectoryText.setText(newFile.path());
					return;
				} else {
					if (newFile.exists()) {
	            		loadFleet(newFile.toString());
	            		return;
	            	}
				}
				newFile = Gdx.files.absolute(currentDirectory +"/" +  directoryList.getSelected() + "/");
				if (newFile.isDirectory()) {
					currentDirectory = newFile;
					loadFiles(currentDirectory);
					currentDirectoryText.setText(newFile.path());
					return;
				} else {
					if (newFile.exists()) {
	            		loadFleet(newFile.toString());
	            		return;
	            	}
				}
			}
		});
		
		loadFiles(currentDirectory);
		
		ScrollPane scrollPane = new ScrollPane(directoryList, skin);
		centerTable.add(scrollPane).width(resizeX(200)).height(resizeY(300));
		centerTable.add(currentDirectoryText).width(buttonWidth).height(buttonHeight);
		centerTable.add(exitButton).width(buttonWidth).height(buttonHeight);
		centerTable.add(backDirectory).width(buttonWidth).height(buttonHeight);
		centerTable.add(openFile).width(buttonWidth).height(buttonHeight);
		
	}
	
	public void loadFiles(FileHandle file) {
		FileHandle[] files = file.list();
		ArrayList<String> stringFiles = new ArrayList<String>();
		for (FileHandle f : files) {
			stringFiles.add(f.name());
		}
		String[] filesArray = new String[ stringFiles.size() ];
		stringFiles.toArray(filesArray);
		directoryList.setItems(filesArray);
	}

}
