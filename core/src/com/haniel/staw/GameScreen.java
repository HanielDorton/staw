package com.haniel.staw;


import java.io.File;
import java.util.ArrayList;








import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
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
	public Skin skin, skin2;
	private Table fleetTable, shipTable, leftTable, rightTable, bottomTable, centerTable;
	private TextureAtlas atlas, atlas2;
	private TextButton loadFile;
	private Button buttonMore, buttonLess, buttonMoreActive, buttonLessActive, buttonExit;
	public List<String> directoryList;
	private ArrayList<Fleet> fleets = new ArrayList<Fleet>();	
	private ArrayList<TextButton> fleetButtons = new ArrayList<TextButton>();
	private ArrayList<TextButton> fleetButtonsActive = new ArrayList<TextButton>();
	private int activeFleet = 5;
	private ArrayList<TextButton> shipButtons = new ArrayList<TextButton>();
	public ArrayList<Card> currentCards = new ArrayList<Card>();
	private int startingCard = 0;
	private int numberOfCards = 4;
	private int buttonWidth = resizeX(200);
	private int buttonHeight = resizeY(60);
	private int buttonPad = resizeX(20);
	public FileHandle currentDirectory = Gdx.files.absolute("/");
	private Texture backgroundPanel = Assets.manager.get("backgroundpanel.png", Texture.class);
	private Texture cardBorder = Assets.manager.get("CardBorder.png", Texture.class); 
	private Sound doubleBeep = Assets.manager.get("doublebeep.mp3", Sound.class);
	private Sound error = Assets.manager.get("error.wav", Sound.class);
	//private Sound highpitch = Assets.manager.get("highpitch.wav", Sound.class); // use for clicking on cards?
	private Sound noeffect = Assets.manager.get("noeffect.mp3", Sound.class);
	private Sound quickbeep = Assets.manager.get("quickbeep.mp3", Sound.class);
	private Sound openScreen = Assets.manager.get("openscreen.mp3", Sound.class);
	private Music backgroundMusic = Assets.manager.get("tng_bridge_2.mp3", Music.class);


    
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
		int x = resizeX(80);
		int size = currentCards.size();
		game.batch.draw(backgroundPanel, 0, 0, resizeX(assumeX), resizeY(assumeY));
		for (int i = startingCard; i < startingCard + numberOfCards; i++) {
			if (i < size) {
				if (currentCards.get(i).hasTexture()) {
					game.batch.draw(currentCards.get(i).getTexture(), x, resizeY(106), resizeX(200), resizeY(278));
					game.batch.draw(cardBorder, x, resizeY(106), resizeX(200), resizeY(278));
					x += resizeX(210);
				}
				else {
					game.font.draw(game.batch, currentCards.get(i).getName(), x, resizeY(200));
					x += resizeX(210);
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
		openScreen.play();
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
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
		rightTable.setBounds(resizeX(940), resizeY(-56), resizeX(40), stage.getHeight());
		leftTable.setBounds(resizeX(10), resizeY(-56), resizeX(60), stage.getHeight());
		bottomTable.setBounds(0, resizeY(45), stage.getWidth(), resizeY(40));
		centerTable.setBounds(resizeX(150), resizeY(100), resizeX(700), resizeY(100));
		Gdx.input.setInputProcessor(stage);
		
		final GameScreen g = this;
		
		for (int i = 0; i < 4; i++) {
			final int current = i;
			loadFile = new TextButton("Load Fleet", skin);
			loadFile.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {
					centerTable.clear();
					new LoadFileMenu(g, centerTable, current);
					doubleBeep.play();
					clearBoard();
					leftTable.clear();
					rightTable.clear();
					leftTable.add(buttonLess).width(buttonHeight ).height(buttonWidth - resizeY(5));
					rightTable.add(buttonMore).width(buttonHeight).height(buttonWidth- resizeY(5));
				}
			});
			fleetButtons.add(loadFile);
			fleetButtonsActive.add(loadFile);
		}
		
		buttonMore = new TextButton("+", skin);
		buttonMore.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				noeffect.play();
			}
		});
		buttonLess = new TextButton("-", skin);
		buttonLess.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				noeffect.play();
			}
		});
		buttonMoreActive = new TextButton("+", skin2);
		buttonMoreActive.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				quickbeep.play();
				startingCard += numberOfCards;
				leftTable.clear();
				leftTable.add(buttonLessActive).width(buttonHeight).height(buttonWidth - resizeY(5));
				if (startingCard + numberOfCards >= currentCards.size()) {
					rightTable.clear();
					rightTable.add(buttonMore).width(buttonHeight).height(buttonWidth - resizeY(5));
					
				}
			}
		});
		buttonLessActive = new TextButton("-", skin2);
		buttonLessActive.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				quickbeep.play();
				startingCard -= numberOfCards;
				if (startingCard == 0) {
					leftTable.clear();
					leftTable.add(buttonLess).width(buttonHeight).height(buttonWidth - resizeY(5));
				}
				if (startingCard + numberOfCards < currentCards.size()) {
					rightTable.clear();
					rightTable.add(buttonMoreActive).width(buttonHeight).height(buttonWidth - resizeY(5));
				}
			}
		});
		buttonExit = new TextButton("Abort", skin);
		buttonExit.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				backgroundMusic.stop();
				quickbeep.play();
				game.setScreen(new ShutdownScreen(game));
			}
		});
		
		bottomTable.add(buttonExit).width(buttonWidth).height(resizeY(40));
		leftTable.add(buttonLess).width(buttonHeight).height(buttonWidth - resizeY(5));
		rightTable.add(buttonMore).width(buttonHeight).height(buttonWidth - resizeY(5));
		recreateFleetTable();
		
	}

	private void recreateFleetTable() {
		for (int i = 0; i < fleetButtons.size(); i++) {
			if (i != activeFleet ) fleetTable.add(fleetButtons.get(i)).width(buttonWidth).height(buttonHeight).padRight(buttonPad);
			else fleetTable.add(fleetButtonsActive.get(i)).width(buttonWidth).height(buttonHeight).padRight(buttonPad);
		}		
	}

	protected void loadFleet(String text, final int fleetButton) {
		try {
			FileHandle handle = Gdx.files.absolute(text);
			XmlReader xml = new XmlReader();
			xml.parse(handle);
			final Fleet fleet = new Fleet(this, text);
			fleets.add(fleet);
			File f = new File(text);

			fleetButtons.remove(fleetButton);

			final TextButton button = new TextButton((f.getName()).replace(".xml", ""), skin);
			button.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {
					displayFleet(fleet);
					activeFleet = fleetButton;
					fleetTable.reset();
					recreateFleetTable();
					doubleBeep.play();
				}
			});
			fleetButtons.add(fleetButton, button);
			fleetButtonsActive.remove(fleetButton);
			final TextButton button2 = new TextButton((f.getName()).replace(".xml", ""), skin2);
			fleetButtonsActive.add(fleetButton, button2);
			activeFleet = fleetButton;
			displayFleet(fleet);
			fleetTable.reset();
			recreateFleetTable();
			doubleBeep.play();
			centerTable.clear();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("error1");
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
					quickbeep.play();
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
		startingCard = 0;
		leftTable.add(buttonLess).width(buttonHeight).height(buttonWidth);
		if (currentCards.size() > numberOfCards) rightTable.add(buttonMoreActive).width(buttonHeight).height(buttonWidth - resizeY(5));
		else rightTable.add(buttonMore).width(buttonHeight).height(buttonWidth - resizeY(5));
				
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
		error.play();
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
		leftTable.add(buttonLess).width(buttonHeight).height(buttonWidth - resizeY(5));
		rightTable.add(buttonMore).width(buttonHeight).height(buttonWidth - resizeY(5));
		startingCard = 0;
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
