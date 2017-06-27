package pl.damiandziura;

import com.badlogic.gdx.Game;
import pl.damiandziura.screens.GameScreen;

public class ClickerGame extends Game {
	public final static int DEFAULT_WIDTH = 1280;
	public final static int DEFAULT_HEIGHT = 720;
	public final static int CRIT_CHANCE_PRECENT = 10;
	public final static int AMOUNT_OF_UNIQUE_STAGES = 12;
	public final static int AMOUNT_OF_UNIQUE_MONSTERS = 29;

	@Override
	public void create () {
		this.setScreen(new GameScreen(this));
	}
}
