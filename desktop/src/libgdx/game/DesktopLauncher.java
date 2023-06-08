package libgdx.game;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Graphics.DisplayMode primaryMode = Lwjgl3ApplicationConfiguration.getDisplayMode();
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setFullscreenMode(primaryMode);
		config.setForegroundFPS(60);
		config.setTitle("Kursovaya");
		new Lwjgl3Application(new Starter(), config);
	}
}
