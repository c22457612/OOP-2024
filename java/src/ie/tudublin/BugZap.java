package ie.tudublin;

import processing.core.PApplet;

import ddf.minim.*;

public class BugZap extends PApplet {
	float playerX;
	float playerY;
	float playerWidth;
	float laserX;
	float laserY;
	float bugX;
	float bugY;
	float bugWidth;
	float min=0;
	float max=10;
	float randomNum;
	int score=0;
	float bugYIncrement=1;
	float bugXIncrement=150;
	boolean spaceKeyPressed = false;
	boolean gameOver=false;
	boolean splashScreen=true;
	Minim minim;
	AudioPlayer laserSound;


	public void settings() {
		size(1000, 1000);
	}

	public void setup() {
	playerX=width/2;
	playerY=height-400;
	playerWidth=90;
	laserX=playerX; // we want laser to appear at the player position
	laserY=playerY;
	bugX=random(100,900);
	bugY=height/100;
	bugWidth=70;
	minim = new Minim(this);
   	laserSound = minim.loadFile("data/laser_sound.wav"); 
	}

	
	public void drawPlayer(float x,float y,float w){
		line(x-w, y, x+w, y);
	}

	public void drawBug(float x,float y,float w)
	{
		if (bugY>300)
		{
			bugYIncrement=2;
			System.out.println("bug is fast");
			//System.out.println(bugY); debugging
		}
		else
		{
			bugYIncrement=1;
			System.out.println("bug is slow");
		}
		if (bugX>950.0)// bug gone too far right
		{
			System.out.println("limit bug");
			bugX=bugX-bugXIncrement;
		}
		else if (bugX<150.0)
		{
			System.out.println("limit bug"); //bug gone too far left
			bugX=bugX+bugXIncrement;
		}
		else if (bugY<playerY)
		{
			stroke(0,255,0);
			//line(x-w, y, x+w, y);
			
			circle(x, y, 50);
			fill(255, 255, 255);
			triangle(x, y - 40,x - 30, y + 20,x + 30, y + 20);
			fill(255, 0, 255);
			ellipse(x, y - 10, 60, 30);
			fill(0, 0, 0);
			ellipse(x, y - 10, 20, 20);

			bugY=bugY+bugYIncrement; // always move down
			randomNum=random(min,max);
			if (random(1)>0.5)
			{
				bugX=x+random(min,max);
			}
			else
			{
				bugX=x-random(min,max);
			}
			
		}
		else
		{
			System.out.println("lose");
			gameOver=true;
			//exit();
		}

		
		
	}


	public void keyPressed()
	{
		if (playerX>=900.0) //make sure we dont go off screen
		{
			System.out.println("limit");
			if (keyCode == LEFT)
			{
				System.out.println("Left arrow pressed");
				System.out.println(playerX);
				playerX=playerX-15;
			}
		}
		else if (playerX<=100.0) //make sure we dont go off screen
		{
			System.out.println("limit");
			if (keyCode == RIGHT)
			{
				System.out.println("Right arrow pressed");
				System.out.println(playerX);
				playerX=playerX+15;
			}
		}
		else //else act as normal
		{
			if (keyCode == LEFT)
			{
				System.out.println("Left arrow pressed");
				System.out.println(playerX);
				playerX=playerX-15;
			}
			if (keyCode == RIGHT)
			{
				System.out.println("Right arrow pressed");
				System.out.println(playerX);
				playerX=playerX+15;
			}
			if (key == ' ')
			{
				System.out.println("SPACE key pressed");
				spaceKeyPressed = true;
				drawLaser(playerX,playerY);
				laserSound.rewind(); // Rewind the sound file to the beginning
      			laserSound.play();
			}
			else
			{
				spaceKeyPressed = false;
			}
		}

		
	}

	public void draw() {
		if (splashScreen==true)
		{
			background(0);
			textAlign(CENTER, CENTER);
			textSize(50);
			fill(255);
			text("Bug Zap", 500, 300);
			textSize(20);
			text("Press SPACE to start", 500, 350);

			// Check for SPACE key press to start the game
			if (keyPressed && key == ' ') {
				splashScreen = false;
			}
		}
		else
		{
			background(0);
			textSize(17);
			fill(255);
			text("Score:",25,15);
			text(score,60,15);
			stroke(0,0,255);
			drawPlayer(playerX,playerY,playerWidth);
			drawBug(bugX,bugY,bugWidth);
		}
		

		if (gameOver==true) //game over screen
		{
			background(0);
			stroke(0,0,0);
			textAlign(CENTER, CENTER);
			textSize(50);
			fill(255, 0, 0);
			text("Game Over", width / 2, height / 2);
			textSize(30);
			fill(255);
			text("Your Score: " + score, width / 2, height / 2 + 50);
		}
	}

	public void drawLaser(float x, float y)
	{
		stroke(255,0,0);
		strokeWeight(3);
		line(x,y,x,0); // players x1,y1,then same players x for x2 then we go up to the top of screen which is 0
		stroke(0, 0, 255);
		strokeWeight(1);
		if (x>bugX-bugWidth && x<bugX+bugWidth)
		{
			bugLasered();
		}
	}

	public void bugLasered()
	{
		bugX=random(100,900);
		bugY=height/100;
		if (bugWidth>5) //make sure bug doesnt get as small as 5 or less
		{
			bugWidth=bugWidth-5;// bug evolves to be smaller 
			// note this code works if bug is a line not ready for the new bug design i made just yet
		}
		
		score++;
	}

	public void stop() {
		laserSound.close();
		minim.stop();
		super.stop();
	 }
	
}
