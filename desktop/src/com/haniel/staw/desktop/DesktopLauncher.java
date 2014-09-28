package com.haniel.staw.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.haniel.staw.staw;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    config.title = "Star Trek Attack Wing";
	    config.width = 1000;
	    config.height = 600;
		new LwjglApplication(new staw(), config);
	}
}
