package game.model;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class TileBorder 
{
	public void border(Graphics arg1, int StartX, int StartY, int BorderX, int BorderY )
	{
		//Sets rectangle color
		arg1.setColor(Color.black);
		//creates border
		arg1.fillRect(StartX, StartY, BorderX, BorderY);
	}
	//Set hit box to left and right
	public int borderHitBoxX(int PX)
	{
		if(PX < 10)
		{
			PX = 10;
		}
		if(PX > 960)
		{
			PX = 960;
		}
	
		return PX;
	}
	//set border to top and bottom
	public int borderHitBoxY(int PY)
	{
		//Top
		if(PY < 10)
		{
			PY = 10;
		}
		//Bottom
		if(PY > 560)
		{
			PY = 560;
		}
		return PY;
	}
}
