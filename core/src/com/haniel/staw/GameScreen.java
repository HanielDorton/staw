package com.haniel.staw;


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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.haniel.staw.Cards.Card;
import com.haniel.staw.Cards.Resource;
import com.haniel.staw.Cards.ShipCard;


public class GameScreen implements Screen{
	public final staw game;
	public OrthographicCamera camera;
	public static int assumeX = 1000;
    public static int assumeY = 600;    
	public Stage stage;
	public Skin skin, skin2;
	public Table fleetTable, shipTable, leftTable, rightTable, bottomTable, centerTable, topLeftTable, topRightTable;
	public TextureAtlas atlas, atlas2;
	public TextButton loadFile;
	private String lastAction = "";
	public Button buttonMore, buttonLess, buttonMoreActive, buttonLessActive, buttonMenu, buttonNextShip;
	public Button shipButtonMore, shipButtonLess, shipButtonMoreActive, shipButtonLessActive;
	public List<String> directoryList;
	public ArrayList<Fleet> fleets = new ArrayList<Fleet>();	
	public ArrayList<TextButton> fleetButtons = new ArrayList<TextButton>();
	public ArrayList<TextButton> fleetButtonsActive = new ArrayList<TextButton>();
	public int activeFleet = 5;
	public int activeShip = 50;
	public ArrayList<TextButton> shipButtons = new ArrayList<TextButton>();
	public ArrayList<TextButton> shipButtonsActive = new ArrayList<TextButton>();
	public ArrayList<Card> currentCards = new ArrayList<Card>();
	public ArrayList<Card> focusedCard = new ArrayList<Card>();
	public int startingCard = 0;
	public int numberOfCards = 4;
	public int startingShip = 0;
	public int numberOfShips = 4;
	public int buttonWidth = resizeX(200);
	public int buttonHeight = resizeY(60);
	public int buttonPad = resizeX(20);
	public FileHandle currentDirectory = Gdx.files.absolute("/");
	public boolean playSounds = true;
	public Texture backgroundPanel = Assets.manager.get("backgroundpanel.png", Texture.class);
	public Texture cardBorder = Assets.manager.get("CardBorder.png", Texture.class);
	public Texture cardBorderDisabled = Assets.manager.get("CardDisabled.png", Texture.class);
	public Texture cardBorderDiscarded = Assets.manager.get("CardDiscarded.png", Texture.class);
	public Texture cardBorderDiscardedByOpponent = Assets.manager.get("CardDiscardedStolen.png", Texture.class);
	public Texture cardNoTexture = Assets.manager.get("CardFailedtoLoad.png", Texture.class);
	public Sound doubleBeep = Assets.manager.get("doublebeep.mp3", Sound.class);
	public Sound error = Assets.manager.get("error.wav", Sound.class);
	public Sound highpitch = Assets.manager.get("touchcard.mp3", Sound.class); 
	public Sound noeffect = Assets.manager.get("noeffect.mp3", Sound.class);
	public Sound quickbeep = Assets.manager.get("quickbeep.mp3", Sound.class);
	public Sound openScreen = Assets.manager.get("openscreen.mp3", Sound.class);
	public Music backgroundMusic = Assets.manager.get("tng_bridge_2.mp3", Music.class);
	private Vector2 touchPos = new Vector2(0, 0);
	public boolean gameStarted = false;
	private Rectangle focusedRect;
	public GamePlay gp = new GamePlay(this);
	public DamageDeck damageDeck = new DamageDeck(this);
	public GamePlay gamePlay = new GamePlay(this);
	ArrayList<ArrayList<String>> rounds;
	int currentLog = 0;
	private boolean showGameLog = false;
	public ScrollPane scrollPane;
	public List<String> scrollPaneList;
	private Rectangle shipLogRect = new Rectangle(resizeX(5), resizeY(45), resizeX(500), resizeY(60));
	
	public GameScreen(final staw gam) {
		this.game = gam;
		camera = new OrthographicCamera();
	    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    setupRoundsList();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
        game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		int x = resizeX(90);
		int size = currentCards.size();
		focusedRect = new Rectangle(resizeX(105), resizeY(95), resizeX(222), resizeY(315));
		game.batch.draw(backgroundPanel, 0, 0, resizeX(assumeX), resizeY(assumeY));
    	
    	if (Gdx.input.isTouched()) {    		
		    touchPos.set(Gdx.input.getX(), Math.abs(600 - Gdx.input.getY()));
    	}
    	
    	game.font.draw(game.batch, lastAction, resizeX(10), resizeY(75));
    	
		for (int i = startingCard; i < startingCard + numberOfCards; i++) {
			if (i < size) {
				currentCards.get(i).getRect().setPosition(x, resizeY(106));
				if (currentCards.get(i).hasTexture()) game.batch.draw(currentCards.get(i).getTexture(), x, resizeY(106), resizeX(200), resizeY(278));
				else {
					game.batch.draw(cardNoTexture, x, resizeY(106), resizeX(200), resizeY(278));
					game.font.draw(game.batch, currentCards.get(i).getName(), x+resizeX(25), resizeY(280));
				}
				if (currentCards.get(i).disabled) game.batch.draw(cardBorderDisabled, x, resizeY(106), resizeX(200), resizeY(278));
				else if (currentCards.get(i).discardedByUse) game.batch.draw(cardBorderDiscarded, x, resizeY(106), resizeX(200), resizeY(278)); 
				else if (currentCards.get(i).discardedByOpponent) game.batch.draw(cardBorderDiscardedByOpponent, x, resizeY(106), resizeX(200), resizeY(278));
				else  game.batch.draw(cardBorder, x, resizeY(106), resizeX(200), resizeY(278));
				x += resizeX(205);

				if (Gdx.input.isTouched()) {
					if (currentCards.get(i).getRect().contains(touchPos)) {
						//System.out.println(touchPos.x + "" +touchPos.y);
						//System.out.println("Touched: " + i);
						
						currentCards.get(i).focusCard();
						break;
					} 
				}
			}
		}
		if (focusedCard.size() > 0) {
			if (focusedCard.get(0).hasTexture()) game.batch.draw(focusedCard.get(0).getTexture(), resizeX(105), resizeY(95), resizeX(222), resizeY(315));
			else game.batch.draw(cardNoTexture, resizeX(105), resizeY(95), resizeX(222), resizeY(315));
			if (focusedCard.get(0).discardedByUse) game.batch.draw(cardBorderDiscarded, resizeX(105), resizeY(95), resizeX(222), resizeY(315));
			else if (focusedCard.get(0).discardedByOpponent) game.batch.draw(cardBorderDiscardedByOpponent, resizeX(105), resizeY(95), resizeX(222), resizeY(315));
			else if (focusedCard.get(0).disabled) game.batch.draw(cardBorderDisabled, resizeX(105), resizeY(95), resizeX(222), resizeY(315));
			else game.batch.draw(cardBorder, resizeX(105), resizeY(95), resizeX(222), resizeY(315));
			focusedCard.get(0).focusCardDetails();	
			if (Gdx.input.isTouched()) {
				if (!focusedCard.get(0).onFocusedScreen) {
					if (focusedRect.contains(touchPos)) {
						if (playSounds) highpitch.play();
						centerTable.clear();
						focusedCard.get(0).focusCard();
					}
				}
			}
		}
    	if (Gdx.input.isTouched()) {
		    if (shipLogRect.contains(touchPos)) {
		    	currentLog = rounds.size()-1;
		    	showGameLog(); 
		    }
    	}
		game.batch.end();
    	stage.act(Gdx.graphics.getDeltaTime());
    	stage.draw();
   	
	}

	@Override
	public void resize(int width, int height) {		
	}
	
	public void setupRoundsList() {
		rounds = new ArrayList<ArrayList<String>>();
		ArrayList<String> l = new ArrayList<String>();
		rounds.add(l);
		addAction("Pre-Game Setup");
	}

	@Override
	public void show() {
		if (playSounds) openScreen.play();
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
		atlas = new TextureAtlas("uiskin.atlas");
		atlas2 = new TextureAtlas("uiskin2.atlas");
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
		skin2 = new Skin(Gdx.files.internal("uiskin.json"), atlas2);
		directoryList = new List<String>(skin);
		stage = new Stage();
		scrollPaneList = new List<String>(skin);
		fleetTable = new Table(skin2);
		shipTable = new Table(skin);
		rightTable = new Table(skin);
		leftTable = new Table(skin);
		bottomTable = new Table(skin);
		centerTable = new Table(skin);
		topLeftTable = new Table(skin);
		topRightTable = new Table(skin);
		stage.addActor(fleetTable);
		stage.addActor(shipTable);
		stage.addActor(rightTable);
		stage.addActor(leftTable);
		stage.addActor(bottomTable);
		stage.addActor(centerTable);
		stage.addActor(topLeftTable);
		stage.addActor(topRightTable);
		shipTable.setBounds(resizeX(110), resizeY(415), resizeX(800), resizeY(60));
		fleetTable.setBounds(resizeX(110), resizeY(495), resizeX(800), resizeY(60));
		rightTable.setBounds(resizeX(940), resizeY(-56), resizeX(40), stage.getHeight());
		leftTable.setBounds(resizeX(10), resizeY(-56), resizeX(60), stage.getHeight());
		bottomTable.setBounds(resizeX(640), resizeY(45), resizeX(160), resizeY(40));
		centerTable.setBounds(resizeX(200), resizeY(106), resizeX(600), resizeY(278));
		topRightTable.setBounds(resizeX(950), resizeY(415), resizeX(40), resizeY(60));
		topLeftTable.setBounds(0, resizeY(415), resizeX(60), resizeY(60));
		Gdx.input.setInputProcessor(stage);
		
		
		createFleetTable();
		
		buttonMore = new TextButton("+", skin);
		buttonMore.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (playSounds) noeffect.play();
			}
		});
		buttonLess = new TextButton("-", skin);
		buttonLess.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (playSounds) noeffect.play();
			}
		});
		
		shipButtonMore = new TextButton("+", skin);
		shipButtonMore.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (playSounds) noeffect.play();
			}
		});
		shipButtonLess = new TextButton("-", skin);
		shipButtonLess.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (playSounds) noeffect.play();
			}
		});
		
		buttonMoreActive = new TextButton("+", skin2);
		buttonMoreActive.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (playSounds) quickbeep.play();
				startingCard += numberOfCards;
				if (showGameLog) {
					currentLog++;
					showGameLog();
				}	
				resetSideButtons();
			}
		});
		buttonLessActive = new TextButton("-", skin2);
		buttonLessActive.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (playSounds) quickbeep.play();
				startingCard -= numberOfCards;
				if (showGameLog) {
					currentLog--;
					showGameLog();
				}	
				resetSideButtons();
			}
		});
		shipButtonMoreActive = new TextButton("+", skin2);
		shipButtonMoreActive.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (playSounds) quickbeep.play();
				startingShip += numberOfShips;
				resetSideButtons();
			}
		});
		shipButtonLessActive = new TextButton("-", skin2);
		shipButtonLessActive.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (playSounds) quickbeep.play();
				startingShip -= numberOfShips;
				resetSideButtons();
			}
		});		
		final GameScreen g = this;
		buttonMenu = new TextButton("Menu", skin);
		buttonMenu.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				centerTable.clear();
				new SettingsMenu(g, centerTable);
				if (playSounds) doubleBeep.play();
				resetCardsandSideButtons();				
			}
		});
		buttonNextShip = new TextButton("Start Game", skin);
		buttonNextShip.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if (fleets.size() > 0) {
					if (playSounds) doubleBeep.play();
					gamePlay.next();
				} else {
					if (g.playSounds) g.error.play();
					g.addAction("Error: Unable to start game with no ships loaded");
				}
			}
		});
		bottomTable.add(buttonNextShip).width(buttonWidth).height(resizeX(40)).padRight(buttonPad);
		bottomTable.add(buttonMenu).width(buttonWidth).height(resizeX(40));
		resetSideButtons();
	}

	public void redrawFleetTable() {
		for (int i = 0; i < fleetButtons.size(); i++) {
			if (i != activeFleet ) fleetTable.add(fleetButtons.get(i)).width(buttonWidth).height(buttonHeight).padRight(buttonPad);
			else fleetTable.add(fleetButtonsActive.get(i)).width(buttonWidth).height(buttonHeight).padRight(buttonPad);
		}		
	}
	
	public void createFleetTable() {
		fleetTable.clear();
		shipTable.clear();
		fleets.clear();
		fleetButtons.clear();
		fleetButtonsActive.clear();
		shipButtons.clear();
		shipButtonsActive.clear();
		final GameScreen g = this;
		for (int i = 0; i < 4; i++) {
			final int current = i;
			loadFile = new TextButton("Load Fleet", skin);
			loadFile.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {
					centerTable.clear();
					shipTable.clear();
					shipButtons.clear();
					shipButtonsActive.clear();
					new LoadFileMenu(g, centerTable, current);
					if (playSounds) doubleBeep.play();
					resetCardsandSideButtons();

				}
			});
			fleetButtons.add(loadFile);
			fleetButtonsActive.add(loadFile);
		}
		fleetTable.reset();
		redrawFleetTable();
		resetCardsandSideButtons();
		
	}

	public void displayFleet(final Fleet fleet) {
		resetCardsandSideButtons();
		shipTable.clear();
		shipButtons.clear();
		shipButtonsActive.clear();
		//for (final Card ship : fleet.getShips()) {
		for (int i = 0; i < fleet.getShips().size(); i++) {
			final int current = i;
			final TextButton button = new TextButton(fleet.getShips().get(i).getName(), skin);
			button.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {
					showGameLog = false;
					focusedCard.clear();
					activeShip = current;
					centerTable.clear();
					if (fleet.getShips().get(current) instanceof ShipCard) displayShip((ShipCard) fleet.getShips().get(current));
					else displayResource((Resource) fleet.getShips().get(current));
					if (playSounds) quickbeep.play();
				}
			});
			shipButtons.add(button);
			final TextButton buttonActive = new TextButton(fleet.getShips().get(i).getName(), skin2);
			buttonActive.addListener(new ChangeListener() {
				public void changed(ChangeEvent event, Actor actor) {
					showGameLog = false;
					focusedCard.clear();
					activeShip = current;
					centerTable.clear();
					if (fleet.getShips().get(current) instanceof ShipCard) displayShip((ShipCard) fleet.getShips().get(current));
					else displayResource((Resource) fleet.getShips().get(current));
					if (playSounds) quickbeep.play();
				}
			});
			shipButtonsActive.add(buttonActive);
		}
		resetSideButtons();
	}
	
	public void displayShip(ShipCard ship) {
		currentCards.clear();
		ship.displayShip(this);
		startingCard = 0;
		resetSideButtons();				
	}
	
	public void displayResource(Resource r) {
		currentCards.clear();
		r.displayResource(this);
		startingCard = 0;
		resetSideButtons();				
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
	
	public void addAction(String s) {
		lastAction = s;
		rounds.get(rounds.size()-1).add(s);
	}

	public void resetCardsandSideButtons() {
		showGameLog = false;
		currentCards.clear();
		startingCard = 0;
		startingShip = 0;
		activeShip = 50;
		focusedCard.clear();
		resetSideButtons();
	}
	
	public void resetSideButtons() {
		leftTable.clear();
		rightTable.clear();
		if (showGameLog) {
			if (currentLog == 0) leftTable.add(buttonLess).width(buttonHeight).height(buttonWidth - resizeY(5));
			else leftTable.add(buttonLessActive).width(buttonHeight).height(buttonWidth - resizeY(5));
			if (rounds.size() -1 > currentLog) rightTable.add(buttonMoreActive).width(buttonHeight).height(buttonWidth - resizeY(5));
			else rightTable.add(buttonMore).width(buttonHeight).height(buttonWidth - resizeY(5));
		}
		else {
			if (startingCard == 0) leftTable.add(buttonLess).width(buttonHeight).height(buttonWidth - resizeY(5));
			else leftTable.add(buttonLessActive).width(buttonHeight).height(buttonWidth - resizeY(5));
			if (currentCards.size() > startingCard + numberOfCards) rightTable.add(buttonMoreActive).width(buttonHeight).height(buttonWidth - resizeY(5));
			else rightTable.add(buttonMore).width(buttonHeight).height(buttonWidth - resizeY(5));
		}
			
		
		topRightTable.clear();
		topLeftTable.clear();
		if (startingShip == 0) topLeftTable.add(shipButtonLess).width(buttonHeight-resizeX(10)).height(buttonHeight);
		else topLeftTable.add(shipButtonLessActive).width(buttonHeight-resizeX(10)).height(buttonHeight);
		if (shipButtons.size() > startingShip + numberOfShips) topRightTable.add(shipButtonMoreActive).width(buttonHeight-resizeX(10)).height(buttonHeight);
		else topRightTable.add(shipButtonMore).width(buttonHeight-resizeX(10)).height(buttonHeight);
		
		shipTable.clear();
		for (int i = startingShip; i < startingShip + numberOfShips; i++) {
			if (i < shipButtons.size()) {
				if (i == activeShip) shipTable.add(shipButtonsActive.get(i)).width(buttonWidth).height(buttonHeight).padRight(buttonPad);
				else shipTable.add(shipButtons.get(i)).width(buttonWidth).height(buttonHeight).padRight(buttonPad);
			}
		}	
		
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
	
	public void resetBottomMenu() {
		bottomTable.clear();
		bottomTable.add(buttonNextShip).width(buttonWidth).height(resizeX(40)).padRight(buttonPad);
		bottomTable.add(buttonMenu).width(buttonWidth).height(resizeX(40));
	}
	
	public void showGameLog() {
		resetCardsandSideButtons();
		ArrayList<String> temp = new ArrayList<String>(rounds.get(currentLog));
		if (currentLog == rounds.size()-1) {
			temp.add("----------------------------------------------");
			if (gamePlay.sortedShips.size() > 0) {
				gamePlay.resortShips();
				temp.add("Ships Remaining:");
			}
			for (Card ship: gamePlay.sortedShips) {
				temp.add(ship.f.name +" - " + ship.name);
			}
		}
		centerTable.clear();
		//currentLog = rounds.size()-1;
		showGameLog = true;
		resetSideButtons();
		String[] stringLog = new String[ temp.size() ];
		temp.toArray(stringLog);
		scrollPaneList.setItems(stringLog);
		scrollPane = new ScrollPane(scrollPaneList, skin);
		centerTable.add(scrollPane).width(resizeX(600)).height(resizeY(278));
	}

}
