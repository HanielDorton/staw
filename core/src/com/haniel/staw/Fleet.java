package com.haniel.staw;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.haniel.staw.Cards.Card;
import com.haniel.staw.Cards.ShipCard;


public class Fleet {
	
	private GameScreen gameScreen;
	public List<Card> ships = new ArrayList<Card>();
	
	public Fleet(GameScreen g, String file) {
		this.gameScreen = g;
		loadFleet(file);
	}

	private void loadFleet(String file2) {
		FileHandle handle = Gdx.files.absolute(file2);
		XmlReader xml = new XmlReader();
			try {
				Element root = xml.parse(handle);
				Array<Element> child = root.getChildrenByName("Ships");
				for (Element fleet : child) {
					for (int i = 0; i< fleet.getChildCount(); i++) {
						ships.add(new ShipCard(fleet.getChild(i)));	
					}
					
				}
				/* Get Resources -- TBD
				Array<Element> res = root.getChildrenByName("Resource");
				for (Element r : res) {
					System.out.println(r);
				}
				*/
						
				
			} catch (Exception e) {
				System.out.println(e);
				gameScreen.addError("Unable to Parse File", "Belay that Order");
			}
	}
	public List<Card> getShips() {
		return ships;
	}
}
