package com.nathanielmorihara.traininggameprototype.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nathanielmorihara.traininggameprototype.TrainingGamePrototypeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new TrainingGamePrototypeGame(), config);
	}
}
