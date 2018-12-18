import java.util.Random;

import javafx.scene.shape.Rectangle;
import javafx.application.*;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.*;


public class Planet
{

	private int id;
	private int owner;
	private int radius;
	private int power;
	private int productionRate;
	private int posX; //coordonnées en X du centre de la planète
	private int posY; //coordonnées en Y du centre de la planète
	private Rectangle shape;
	
	public Rectangle getShape() {
		return shape;
	}

	public void setShape(Rectangle shape) {
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
		this.shape = new Rectangle(0, 0);
	}
	
	public Planet(Game g)
	{
		this.id = g.getPlanetIdMax()+1;
		g.setPlanetIdMax(this.id);
		this.owner = 0;
		this.power = 0;
		this.shape = new Rectangle(0, 0);
		this.spawn(g);
	}
	
	public Planet(Game g, int owner)
	{
		this.id = g.getPlanetIdMax()+1;
		this.owner = owner;
		this.power = 0;
		this.shape = new Rectangle(0, 0);
		if(id == 1)
		{
			Random r = new Random();
			this.radius = 40 + r.nextInt(90 - 60);
			while(isInFrame(800, 600) == false)
			{
				this.posX = r.nextInt(800);
				this.posY = r.nextInt(600);
			}
			
			getShape().setX(getPosX());
			getShape().setY(getPosY());
			getShape().setWidth(getRadius()*2);
			getShape().setHeight(getRadius()*2);
			
			g.getPlanets().add(this);
			g.setPlanetIdMax(this.id);
		}
		
		else
		{
			this.id = g.getPlanetIdMax()+1;
			this.owner = owner;
			this.power = 0;
			this.shape = new Rectangle(0, 0);
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
		this.id = 3;
		//this.id = g.getPlanetIdMax()+1;
		//g.setPlanetIdMax(this.id);
		this.owner = 0;
		this.power = 0;
		this.shape = new Rectangle(0, 0);
		this.posX = posX;
		this.posY = posY;
		this.radius = 20;
		
		getShape().setX(getPosX());
		getShape().setY(getPosY());
		getShape().setWidth(getRadius()*2);
		getShape().setHeight(getRadius()*2);
		
		
		
		boolean ok = false;
		for(int i = 0; i<g.getPlanets().size();i++)
		{
			//System.out.println(getDistance(g.getPlanets().get(i)) + " spawn DISTANCE");
		
			if(this.getDistance(g.getPlanets().get(i)) < g.getMinDist())
			{
				ok = false;
				//System.out.println(ok);
				break;
			}
			ok = true;
			//System.out.println(ok);
		}
		g.getPlanets().add(this);
		g.setPlanetIdMax(this.id);
		System.out.println(ok);
	}
	
	public void spawn(Game g)
	{
		Random r = new Random();
		boolean ok = false;
		
		this.radius = 40 + r.nextInt(60 - 40);
		
		while(ok == false)
		{
			
			this.posX = r.nextInt(800);
			this.posY = r.nextInt(600);
			if(isInFrame(800, 600))
			{
				for(int i = 0; i<g.getPlanets().size();i++)
				{
					//System.out.println(getDistance(g.getPlanets().get(i)) + " spawn DISTANCE");
				
					if(this.getDistance(g.getPlanets().get(i)) < g.getMinDist())
					{
						ok = false;
						//System.out.println(ok);
						break;
					}
					ok = true;
					//System.out.println(ok);
				}
			}
		}
		getShape().setX(getPosX());
		getShape().setY(getPosY());
		getShape().setWidth(getRadius()*2);
		getShape().setHeight(getRadius()*2);		
		
		this.productionRate = 1;
				
				//1 + r.nextInt(4);
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
	
	public boolean isInFrame(double xMax, double yMax)  
	{
		boolean inFrame = false;
		System.out.println(radius + " " + id + " " + posY + " " + posY);
		if(posY > radius+10 && posY < yMax-(radius+10))
		{ 
			if(posX > radius + 10 && posX < xMax-(radius+10))
			{ 	
				System.out.println(radius + " " + id + " " + posY + " " + posY);
				inFrame = true;
				return inFrame;
			}
			else return inFrame;
		} 
		else return inFrame;
		

	}

	
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
