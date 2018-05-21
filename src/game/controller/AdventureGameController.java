package game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

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
	int PX = 270, PY = 270, EX3 = 60, EY3 = 560;
	int bounceXV = 5;
	int bounceYV = 5;
	int bounceXV1 = 6;
	int bounceYV1 = 6;
	int EX = (int) (Math.random() * 980);
	int EY = (int) (Math.random() * 540);
	int EX1 = (int) (Math.random() * 980);
	int EY1 = (int) (Math.random() * 540);
	int EX2 = (int) (Math.random() * 980);
	int EY2 = (int) (Math.random() * 540);
	int EX4 = (int) (Math.random() * 980);
	int EY4 = (int) (Math.random() * 540);
	int EX5 = (int) (Math.random() * 980);
	int EY5 = (int) (Math.random() * 540);
	//Timer
	int secondsPassed = 0;
	int HighScore;
	// Initialize Border
	TileBorder tileBorder = new TileBorder();

	// Create DeathScreen
	Image DeathScreen, StartScreen;
	boolean drawDeathScreen;
	// Creates the start screen
	boolean drawStartScreen = true;
	// initialize all of the is touching variables for each enemy
	boolean isTouching = false;
	boolean isTouching1 = false;
	boolean isTouching2 = false;
	boolean isTouching3 = false;
	boolean isTouching4 = false;
	boolean isTouching5 = false;

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
		super("Adventure game");
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

			// Create Enemy4
			arg1.setColor(Color.red);
			arg1.fillRect(EX3, EY3, 50, 50);
			
			//create Enemy5
			arg1.setColor(Color.red);
			arg1.fillRect(EX4, EY4, 50, 50);
			
			//create Enemy6
			arg1.setColor(Color.red);
			arg1.fillRect(EX5, EY5, 50, 50);

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

			arg1.setColor(Color.white);
			arg1.drawString("Timer: "+secondsPassed  + "", 10, 10);
			arg1.setColor(Color.white);
			arg1.drawString("Previous High Score: " + HighScore + "", 10, 30);
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

		if (drawStartScreen == true) {
			if (input.isKeyDown(Input.KEY_ENTER)) {
				drawStartScreen = false;
			}
		}

		if (drawStartScreen == false) {
			
			Timer();
			
			
			// Moves enemy
			moveX(PX);
			moveY(PY);

			// Moves enemy1
			moveX1(PX);
			moveY1(PY);

			// Moves enemy2
			moveX2(PX);
			moveY2(PY);

			// moves enemy3
			moveX3(PX);
			moveY3(PY);
			
			// Moves Enemy4
			moveX4(PX);
			moveY4(PY);
			
			// Moves Enemy5
				moveX5(PX);
				moveY5(PY);

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

			// Makes hitBox for Enemy2
			if (PX + 25 > EX2 && PX < EX2 + 50 && PY + 25 > EY2 && PY < EY2 + 50) {
				isTouching2 = true;
			} else {
				isTouching2 = false;
			}
			// Makes hitBox for Enemy3
			if (PX + 25 > EX3 && PX < EX3 + 50 && PY + 25 > EY3 && PY < EY3 + 50) {
				isTouching3 = true;
			} else {
				isTouching3 = false;
			}
			//Makes hitBox for Enemy4
			if (PX + 25 > EX4 && PX < EX4 + 50 && PY + 25 > EY4 && PY < EY4 + 50) {
				isTouching4 = true;
			} else {
				isTouching4 = false;
			}
			//Makes hitBox for Enemy4
			if (PX + 25 > EX5 && PX < EX5 + 50 && PY + 25 > EY5 && PY < EY5 + 50) {
				isTouching5 = true;
			} else {
				isTouching5 = false;
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
			// If player touches Enemy1
			if (isTouching1) {
				drawDeathScreen = true;
			}
			// If player touches Enemy2
			if (isTouching2) {
				drawDeathScreen = true;
			}
			//If player touches Enemy3
			if (isTouching3) {
				drawDeathScreen = true;
			}
			//If player touches Enemy4
			if (isTouching4) {
				drawDeathScreen = true;
			}
			//If player touches Enemy5
			if (isTouching5) {
				drawDeathScreen = true;
			}

			if (drawDeathScreen == true) {
				EX = (int) (Math.random() * 980);
				EY = (int) (Math.random() * 540);
				EX1 = (int) (Math.random() * 980);
				EY1 = (int) (Math.random() * 540);
				EX2 = (int) (Math.random() * 980);
				EY2 = (int) (Math.random() * 540);
				EX4 = (int) (Math.random() * 980);
				EY4 = (int) (Math.random() * 540);
				EX5 = (int) (Math.random() * 980);
				EY5 = (int) (Math.random() * 540);
				EX3 = 60;
				EY3 = 560;
				if(secondsPassed > HighScore)
				{
					HighScore = secondsPassed;
				}
				secondsPassed = 0;
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

	// Move Enemy3
	public int moveX3(int PX) {
		if (EX3 > PX) {
			EX3 = EX3 - 4;
		}
		if (EX3 < PX) {
			EX3 = EX3 + 4;
		}
		return EX3;
	}

	public int moveY3(int PY) {
		if (EY3 > PY) {
			EY3 = EY3 - 4;
		}
		if (EY3 < PY) {
			EY3 = EY3 + 4;
		}
		return EY3;
	}
	//Move Enemy4
	public int moveX4(int PX)
	{
		if(EX4 > 950 || EX4 < 10)
		{
			bounceXV *= -1;
		}
		if(drawDeathScreen == false)
		{
			EX4 = EX4 + bounceXV;
		}
		return EX4;
	}
	public int moveY4(int PY)
	{
		if(EY4 > 550|| EY4 < 10)
		{
			bounceYV *= -1;
		}
		if(drawDeathScreen == false)
		{
			EY4 = EY4 + bounceYV;
		}
		return EY4;
	}
	//Move Enemy5
		public int moveX5(int PX)
		{
			if(EX5 > 950 || EX5 < 10)
			{
				bounceXV1 *= -1;
			}
			if(drawDeathScreen == false)
			{
				EX5 = EX5 + bounceXV1;
			}
			return EX5;
		}
		public int moveY5(int PY)
		{
			if(EY5 > 550|| EY5 < 10)
			{
				bounceYV1 *= -1;
			}
			if(drawDeathScreen == false)
			{
				EY5 = EY5 + bounceYV1;
			}
			return EY5;
		}
		
		
		//Timer
		public void Timer()
		{
			if(!drawStartScreen && !drawDeathScreen)
			{
				Timer timer = new Timer(2000, new ActionListener() {
					public void actionPerformed(ActionEvent E)
					{
						secondsPassed++;
						try {
						Thread.sleep(1000);
						}catch(Exception E1)
						{
							E1.printStackTrace();
						}
					}
				});
				timer.setRepeats(false);
				timer.start();
			}
		}
}
