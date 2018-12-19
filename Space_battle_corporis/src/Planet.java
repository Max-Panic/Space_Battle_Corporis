import java.util.Random;

import javafx.scene.shape.Rectangle;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.application.*;
import java.*;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.shape.*;
import java.util.Random;
import javafx.scene.shape.Rectangle;
import javafx.geometry.*;


public class Planet
{

	private int id;
	private int owner;
	private int radius;
	private int power;
	private int productionRate;
	private int posX; //coordonnées en X du centre de la planète
	private int posY; //coordonnées en Y du centre de la planète
	private ImageView imageView;
	private Image image = new Image("Dédé.PNG");
	private ImagePattern imagePattern = new ImagePattern(image);
	private Circle shape;
	
	
	public Circle getShape() {
		return shape;
		
	}

	public void setShape(Circle shape) {
		this.shape = shape;
	}

	public int getID()
	{
		return id;
	}
	
	public int getOwner()
	{
		return owner;
	}
	
	public int getRadius()
	{
		return radius;
	}
	
	public int getPower()
	{
		return power;
	}
	
	public int getProductionRate()
	{
		return productionRate;
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public void setOwner(int owner)
	{
		this.owner = owner;
	}
	
	public void setRadius(int radius)
	{
		this.radius = radius;
	}
	
	public void setPower(int power)
	{
		this.power = power;
	}
	
	public void setProductionRate(int productionRate)
	{
		this.productionRate = productionRate;
	}
	
	public Planet()
	{
	
		this.owner = 0;
		this.power = 0;
		this.imageView = new ImageView(image);
	}
	
	public Planet(Game g)
	{
	
		this.owner = 0;
		this.power = 0;
		this.imageView = new ImageView(image);
		this.spawn(g);
	}
	
	public Planet(Game g, int owner)
	{
	
		this.id = g.getPlanetIdMax()+1;
		this.owner = owner;
		this.power = 0;
		this.radius = 50;
		this.imageView = new ImageView(image);
		
		if(id == 1)
		{
			Random r = new Random();
			
			this.radius = 20 + r.nextInt(70 - 30);
			
			this.shape = new Circle();
			
			this.posX = r.nextInt((Main.xMax - 100) - (getRadius()+100) + 1) + (getRadius() + 20);
			this.posY = r.nextInt((Main.yMax - 100) - (getRadius()+100) + 1) + (getRadius() + 20);
			//}
			
			this.getShape().setCenterX(getPosX());
			this.getShape().setCenterY(getPosY());
			this.getShape().setRadius(getRadius());
			this.getShape().setFill(imagePattern);
		
			g.getPlanets().add(this);
			g.setPlanetIdMax(this.id);
		}
		
		else
		{
			this.id = g.getPlanetIdMax()+1;
			this.owner = owner;
			this.power = 0;
			this.imageView = new ImageView(image);
			this.spawn(g);
		}	
		
	}
	public Planet(int id, int owner, int radius, int power, int productionRate)
	{
		this.id = id;
		this.owner = owner;
		this.radius = radius;
		this.power = power;
		this.productionRate = productionRate;
		
		
	}	
	
	public Planet(Game g, int posX, int posY)
	{
		this.id = g.getPlanetIdMax()+1;
		//g.setPlanetIdMax(this.id);
		this.owner = 0;
		this.power = 0;
		this.imageView = new ImageView(image);
		this.posX = posX;
		this.posY = posY;
		this.radius = 40;
		
		boolean ok = false;
		for(int i = 0; i<g.getPlanets().size();i++)
		{
		
			if(this.getDistance(g.getPlanets().get(i)) < g.getMinDist())
			{
				ok = false;
				break;
			}
			ok = true;
		}
		g.getPlanets().add(this);
		g.setPlanetIdMax(this.id);
		
		this.shape = new Circle();
		
		this.getShape().setCenterX(getPosX());
		this.getShape().setCenterY(getPosY());
		this.getShape().setRadius(getRadius());
		this.getShape().setFill(imagePattern);
		
		/*getImageView().setX(getPosX() - radius);
		getImageView().setY(getPosY() - radius);
		getShape().setFitWidth(getRadius()*2);
		getShape().setFitHeight(getRadius()*2);*/
		
	}
	
	public void spawn(Game g)
	{
		Random r = new Random();
		boolean ok = false;
		this.radius = 50;
		this.radius = 30 + r.nextInt(70 - 30);
		
		while(ok == false)
		{
			this.posX = r.nextInt((Main.xMax - 100) - (getRadius()+100) + 1) + (getRadius() + 20);
			this.posY = r.nextInt((Main.yMax - 100) - (getRadius()+100) + 1) + (getRadius() + 20);
			/*if(isInFrame(800, 600))
			{*/
			if(posX > (radius*2+20) || posX < (Main.xMax - radius*2+20) || posY > (radius*2 + 20) || posY < (Main.yMax - radius*2 +20))
			{
				for(int i = 0; i<g.getPlanets().size();i++)
				{
					
				
					if(this.getDistance(g.getPlanets().get(i)) < g.getMinDist())
					{
						ok = false;
						break;
					}
					ok = true;
				}
				
			}
			else
			ok = false;
		}

		this.shape = new Circle();
		this.getShape().setCenterX(getPosX());
		this.getShape().setCenterY(getPosY());
		this.getShape().setRadius(getRadius());
		this.getShape().setFill(imagePattern);
		
		/*getShape().setX(getPosX() + radius);
		getShape().setY(getPosY() + radius);
		getShape().setFitWidth(getRadius()*2);      
		getShape().setFitHeight(getRadius()*2);		*/
		
		this.productionRate = 1;
				
				//1 + r.nextInt(4);
		this.id = g.getPlanetIdMax()+1;
		g.getPlanets().add(this);
		g.setPlanetIdMax(this.id);
		
	}
	
	public double getDistance(Planet p)
	{
		 return Math.sqrt(sqr(this.posY - p.getPosY()) + sqr(this.posX - p.getPosX()));
	}
	
	public double getDistance(double x1, double y1, double x2, double y2)
	{
		 return Math.sqrt(sqr(y2 - y1) + sqr(x2 - x1));
	}
	
	/*public boolean isInFrame()  
	{
		boolean inFrame = false;
		System.out.println(radius + " " + id + " " + posY + " " + posY);
		if(posY > radius+40 && posY < Main.yMax-(radius+40))
		{ 
			if(posX > radius + 40 && posX < Main.xMax-(radius+40))
			{ 	
				System.out.println(radius + " " + id + " " + posX + " " + posY);
				inFrame = true;
				return inFrame;
			}
			else return inFrame;
		} 
		else return inFrame;
	}*/

	
	public double sqr(double d)
	{
		return d*d;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void produceShip() 
	{
		Spaceship spaceship = new Spaceship(owner);
		try {
			Thread.sleep(spaceship.getProductionTime());
		} catch (InterruptedException e) {
			System.out.println("ça a foiré");
		}
		this.power = this.power + this.productionRate*spaceship.getPower();
	}
	
	public void launch(Planet target)
	{
		//Spaceship ship = new Spaceship(this.owner);
		
		
		//gestion du pourcentage
		int launchRate = 25;
		int shipLaunchedNbr = power*launchRate/100;
		Squadron squadron = new Squadron(1, 1, shipLaunchedNbr, target.id);
		this.power = power - shipLaunchedNbr;
		
		/*for(int i = 0; i<shipLaunchedNbr; i++)
		{
		target.interact(ship);
		}*/
	}
	
	public void interact(Spaceship ship)
	{
		//detecter vaisseau
		if(this.owner==ship.getOwner())
			this.power = this.power + ship.getPower();
		else
			this.power = this.power - ship.getPower();
	}
}
