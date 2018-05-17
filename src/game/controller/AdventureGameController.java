package game.controller;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import game.model.TileBorder;

public class AdventureGameController extends BasicGame {
	// Initialize all of the data members

	// Player
	int PX = 270, PY = 270, EX = 0, EY = 0, EX1 = 930, EY1 = 50, EX2 = 500, EY2 = 500;

	// Initialize Border
	TileBorder tileBorder = new TileBorder();

	// Create DeathScreen
	Image DeathScreen, StartScreen;
	boolean drawDeathScreen;
	boolean drawStartScreen = true;
	boolean isTouching = false;
	boolean isTouching1 = false;
	boolean isTouching2 = false;

	// Makes the window and connects it to the runner class
	public void makeWindow() {
		try {
			AppGameContainer app = new AppGameContainer(new AdventureGameController());
			app.setDisplayMode(1000, 600, false);
			app.setTargetFrameRate(80);
			app.setShowFPS(false);
			app.start();
		} catch (Exception e) {

		}
	}

	// Put the Game title here(Thats all)
	public AdventureGameController() {
		super("Adventure");
	}

	// Draw Images Here or Animations
	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {

		if (drawStartScreen == true) {
			StartScreen.draw(0, 0);
		}
		if (drawStartScreen == false) {
			// Creates the backGround color of the opening room
			arg1.setColor(new Color(0, 100, 200));
			// Creates the background object
			arg1.fillRect(0, 0, 1000, 600);

			// Creates Color of player as a box
			arg1.setColor(Color.white);
			// Creates player as a box [ fillRect(PX = player x position, PY = player y
			// position, 30 = length, 30 = width) ]
			arg1.fillRect(PX, PY, 30, 30);

			// Creates Enemy1
			arg1.setColor(Color.red);
			arg1.fillRect(EX, EY, 50, 50);

			// Creates Enemy2
			arg1.setColor(Color.red);
			arg1.fillRect(EX1, EY1, 50, 50);

			// Create Enemy3
			arg1.setColor(Color.red);
			arg1.fillRect(EX2, EY2, 50, 50);

			if (drawDeathScreen == true) {
				DeathScreen.draw(0, 0);
			}

			// creates top border
			tileBorder.border(arg1, 0, 0, 1000, 10);
			// creates left border
			tileBorder.border(arg1, 0, 0, 10, 600);
			// creates right border
			tileBorder.border(arg1, 990, 0, 10, 600);
			// creates bottom border
			tileBorder.border(arg1, 0, 590, 1000, 10);
		}
	}

	// Initialize Images
	@Override
	public void init(GameContainer arg0) throws SlickException {
		DeathScreen = new Image("res/DeathScreen.png");
		StartScreen = new Image("res/StartScreen.png");
	}

	// Logic, Update, Actual Code (physics and gravity etc...)
	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// gets input from keyboard
		Input input = arg0.getInput();

		if(drawStartScreen == true)
		{
			if(input.isKeyDown(Input.KEY_ENTER))
			{
				drawStartScreen=false;
			}
		}
		
		if(drawStartScreen==false)
		{
		// Moves enemy
		moveX(PX);
		moveY(PY);

		// Moves enemy1
		moveX1(PX);
		moveY1(PY);

		//Moves enemy2
		moveX2(PX);
		moveY2(PY);
		
		

		// Makes hitBox for Enemy
		if (PX + 25 > EX && PX < EX + 50 && PY + 25 > EY && PY < EY + 50) {
			isTouching = true;
		} else {
			isTouching = false;
		}

		// Makes hitBox for Enemy1
		if (PX + 25 > EX1 && PX < EX1 + 50 && PY + 25 > EY1 && PY < EY1 + 50) {
			isTouching1 = true;
		} else {
			isTouching1 = false;
		}
		
		//Makes hitBox for Enemy2
		if (PX + 25 > EX2 && PX < EX2 + 50 && PY + 25 > EY2 && PY < EY2 + 50) {
			isTouching2 = true;
		} else {
			isTouching2 = false;
		}
		
		// Moves Player
		if (drawDeathScreen != true) {
			if (input.isKeyDown(Input.KEY_LEFT)) {
				PX -= 7;
			}
			// moves player right
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				PX += 7;
			}
			// moves player up
			if (input.isKeyDown(Input.KEY_UP)) {
				PY -= 7;
			}
			// moves player down
			if (input.isKeyDown(Input.KEY_DOWN)) {
				PY += 7;
			}
			
		
		}
		
		// creates borders
		PX = tileBorder.borderHitBoxX(PX);
		PY = tileBorder.borderHitBoxY(PY);

		// If player touches Enemy
		if (isTouching) {
			drawDeathScreen = true;
		}
		//If player touches Enemy1
		if (isTouching1) {
			drawDeathScreen = true;
		}
		//If player touches Enemy2
		if (isTouching2) {
			drawDeathScreen = true;
		}
		
		if (drawDeathScreen == true) {
			EX = 0;
			EY = 0;
			EX1 = 940;
			EY1 = 50;
			EX2 = 500;
			EY2 = 500;
			if (input.isKeyDown(Input.KEY_SPACE)) {
				drawDeathScreen = false;
				PX = 270;
				PY = 270;

			}
		}
		}

	}

	// Moves Enemy
	public int moveX(int PX) {
		if (EX > PX) {
			EX = EX - 1;
		}
		if (EX < PX) {
			EX = EX + 1;
		}
		return EX;
	}

	public int moveY(int PY) {
		if (EY > PY) {
			EY = EY - 1;
		}
		if (EY < PY) {
			EY = EY + 1;
		}
		return EY;
	}

	// Moves Enemy1
	public int moveX1(int PX) {
		if (EX1 > PX) {
			EX1 = EX1 - 2;
		}
		if (EX1 < PX) {
			EX1 = EX1 + 2;
		}
		return EX1;
	}

	public int moveY1(int PY) {
		if (EY1 > PY) {
			EY1 = EY1 - 2;
		}
		if (EY1 < PY) {
			EY1 = EY1 + 2;
		}
		return EY1;
	}

	// Move Enemy2
	public int moveX2(int PX) {
		if (EX2 > PX) {
			EX2 = EX2 - 3;
		}
		if (EX2 < PX) {
			EX2 = EX2 + 3;
		}
		return EX2;
	}

	public int moveY2(int PY) {
		if (EY2 > PY) {
			EY2 = EY2 - 3;
		}
		if (EY2 < PY) {
			EY2 = EY2 + 3;
		}
		return EY2;
	}
}
