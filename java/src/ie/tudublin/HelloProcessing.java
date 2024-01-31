package ie.tudublin;

import processing.core.PApplet;

public class HelloProcessing extends PApplet
{

	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		
	}

	float x1, y1, x2, y2;
	float x1dir, x2dir, y1dir, y2dir;
	float c = 0;
	
	public void draw()
	{	
		background(255,10,140);
		fill(0, 255, 0);
		circle(250,255,255);
		fill(255, 255, 255);
		triangle(250, 50, 100, 350, 400, 350);
		fill(255, 0, 255);
		ellipse(250,220,200,80);//cx,cy
		fill(0, 0, 0);
		circle(250,220,50);
		/*background(255,0,0);
		stroke(0,255,0);
		line (10,10,100,100);//x1,y1,x2,y2
		fill(GRAY);
		ellipse(200,90,80,200);//cx,cy
		circle(200,300,80);
		fill(90,78,88);
		rect(400,380,50,100);//x,y,w,h
		triangle(10,200,200,200,300,300);*/

	}
}
