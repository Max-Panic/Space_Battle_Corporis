import java.util.Random;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.animation.PathTransition;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.application.*;
import java.*;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.*;
import javafx.scene.shape.*;
import java.util.Random;
import javafx.scene.shape.Rectangle;
import javafx.geometry.*;

/**
 * 
 * The class managing the planets
 *
 */
public class Planet 
{
	/**
	 * The planet identifier 
	 */
	private int id;
	/**
	 * The ID of the player who own the planet
	 */
	private int owner; 
	/**
	 * The radius of the planet
	 */
	private int radius; 
	/**
	 * The number of ships stored in this planet 
	 */
	private int power = 20; 
	/**
	 * The number of ship that this planet produce for each production cycle
	 */
	private int productionRate; 
	/**
	 * The x coordinate of the planet center
	 */
	private int posX; 
	/**
	 * The y coordinate of the planet center
	 */
	private int posY; 
	/**
	 * The planet's background 
	 */
	private Image image = new Image("Dédé.PNG");
	/**
	 * The planet's shape
	 */
	private Circle shape = new Circle();
	/**
	 * A Circle slightly bigger than shape that is used for trajectory computing
	 */
	private Circle hitZone = new Circle();
	/**
	 * Used for printing images into shapes
	 * @see image
	 * @see shape
	 */
	private ImagePattern imagePattern;
	/**
	 * A list that contains the spaceships launched from this planet
	 * @see Spaceship
	 */
	//private List<Spaceship> spaceships = new ArrayList<Spaceship>(); //tableau contenant les vaisseaux ayant dï¿½collï¿½ de la planï¿½te, ce sera dans le futur gï¿½rï¿½ par un escadron
	/**
	 * A text used to display the planet's power in its center
	 */
	private Text powerTxt = new Text(String.valueOf(this.power));
	
	/**
	 * Create a new instance of Planet, this planet does not spawn and is neutral
	 * @return An instance of Planet
	 */
	public Planet()	  //Constructeur permettant d'instancier une planï¿½te sans la faire apparaï¿½tre	
	{
		this.owner = 0;
	}
	/**
	 * Create a new instance of Planet in the given Game, then spawn this planet. The planet is neutral
	 * @param g The game where the planet will spawn
	 * @return A new instance of Planet
	 */
	public Planet(Game g) 
	{
	
		this.owner = 0;
		this.spawn(g); //appel de la fonction permettant de crï¿½er et faire apparaï¿½tre la planï¿½te
	}
	/**
	 * Create a new instance of Planet for the given owner in the given game, then spawn this planet.
	 * @param g The game the planet belongs to
	 * @param owner The new Planet owner
	 * @return A new instance of Planet 
	 */
	public Planet(Game g, int owner)  
	{
		Random r = new Random(); //instanciation d'un objet "Random" pour la gï¿½nï¿½ration de nombres alï¿½atoires
		this.owner = owner;
		this.image = new Image("Green.jpg");
		this.imagePattern = new ImagePattern(image);
		this.radius = 20 + r.nextInt(30 - 20);	//Gï¿½nï¿½ration d'un entier alï¿½atoire dans l'intervalle [50; 80] pour le rayon de la planï¿½te
		g.getPlayers().add(new Player(g, owner));
		
		if(g.getPlanets().isEmpty())  //Test du nombre de planï¿½tes prï¿½sentes dans la partie pour voir si le test de la position relative aux autres planï¿½tes est nï¿½cessaire
		{
			
			this.productionRate = 5; //assignation provisoire du taux de production de la planï¿½te ï¿½ 1
			
			this.posX = r.nextInt((Main.xMax - 100) - (getRadius()+20)) + (getRadius()+20);	//Gï¿½nï¿½ration d'un entier alï¿½atoire dans l'intervalle [rayonPlanï¿½te+20; TailleFenï¿½treX - 100] pour la position X du centre de la planï¿½te
			this.posY = r.nextInt((Main.yMax - 100) - (getRadius()+20)) + (getRadius()+20);	//-------------------------------------------------------------------; TailleFenï¿½treY - 100]----------------- Y -----------------------
	
			this.getShape().setCenterX(getPosX());	//
			this.getShape().setCenterY(getPosY());	//Dï¿½finition de la taille, de la position
			this.getShape().setRadius(getRadius());	//et de l'image de la planï¿½te 
			this.getShape().setFill(imagePattern);	//
			this.getPowerTxt().setBoundsType(TextBoundsType.VISUAL);
			this.getPowerTxt().setText(String.valueOf(this.power));
			this.getPowerTxt().setX(this.getPosX() - this.getPowerTxt().getBoundsInLocal().getWidth()/2);
			this.getPowerTxt().setY(this.getPosY() + this.getPowerTxt().getBoundsInLocal().getHeight()/2);
			//this.getPowerTxt().setX(getPosX - getPowerTxt().get)
			
			/*this.getHitZone().setCenterX(getPosX());	
			this.getHitZone().setCenterY(getPosY());	
			this.getHitZone().setRadius(getRadius() + 20);*/
			
			
			this.id = g.getPlanetIdMax()+1;			//Assignation de l'ID de la planï¿½te ï¿½ la valeur max des ID des planï¿½tes prï¿½sente dans la partie incrï¿½mentï¿½e de 1
			g.getPlanets().add(this);				//Ajout de la planï¿½te au tableau des planï¿½tes de la partie -> ajout rï¿½el de la planï¿½te au jeu
			g.setPlanetIdMax(this.id);				//Incrï¿½mentation de la valeur de l'id maximale des planï¿½tes dans la partie
		}
		
		else
		{
			this.spawn(g);
		}	
	}
	
	/**
	 * Create a new instance of Planet at the given position in the given game, then spawn this planet. it has no use in the game but is useful for development
	 * @param g The game the planet belongs to
	 * @param posX The x coordinate of the planet
	 * @param posY The y coordinate of the planet
	 * @return A new instance of Planet 
	 * @see Game
	 */
	public Planet(Game g, int posX, int posY)  
	{
		this.id = g.getPlanetIdMax()+1;
		this.owner = 0;
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
		
		this.getShape().setCenterX(getPosX());
		this.getShape().setCenterY(getPosY());
		this.getShape().setRadius(getRadius());
		this.getShape().setFill(imagePattern);
	}
	/**
	 * The method introducing the planets in the given game
	 * 
	 * @param g The game the planet belongs to
	 */
	public void spawn(Game g) 
	{
		Random r = new Random();
		if(getOwner()==0)
		this.image = new Image("white.jpg");
		else this.image = new Image("red.jpg");
		this.imagePattern = new ImagePattern(image);
		boolean ok = false;
		this.power = 100;
		this.radius = 50 + r.nextInt(80 - 50);
		
		while(ok == false) //Boucle de vï¿½rification de la position de la planï¿½te par rapport aux bords de la fenï¿½tre et aux autres planï¿½tes
		{
			this.posX = r.nextInt((Main.xMax - 100) - (getRadius()+20) + 1) + (getRadius() + 20);
			this.posY = r.nextInt((Main.yMax - 100) - (getRadius()+20) + 1) + (getRadius() + 20);
			
			if(posX > (radius*2+20) || posX < (Main.xMax - radius*2+20) || posY > (radius*2 + 20) || posY < (Main.yMax - radius*2 +20)) //Test de la position de la planï¿½te par rapport aux bords de l'ï¿½cran
			{
				for(int i = 0; i<g.getPlanets().size();i++) //boucle parcourant le tableau des planï¿½tes
				{	
					if(this.getDistance(g.getPlanets().get(i)) < g.getMinDist()) //Test de la position de la planï¿½te par rapport aux autres planï¿½tes
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

		this.getShape().setCenterX(getPosX());	//
		this.getShape().setCenterY(getPosY());	//Dï¿½finition de la taille, de la position	
		this.getShape().setRadius(getRadius());	//et de l'image de la planï¿½te 
		this.getShape().setFill(imagePattern);	//
		this.getPowerTxt().setBoundsType(TextBoundsType.VISUAL);
		this.getPowerTxt().setText(String.valueOf(this.power));
		this.getPowerTxt().setX(this.getPosX() - this.getPowerTxt().getBoundsInLocal().getWidth()/2);
		this.getPowerTxt().setY(this.getPosY() + this.getPowerTxt().getBoundsInLocal().getHeight()/2);
		
		this.productionRate = 5;
		
		/*this.getHitZone().setCenterX(getPosX());	
		this.getHitZone().setCenterY(getPosY());	
		this.getHitZone().setRadius(getRadius() + 20);*/
		
		this.id = g.getPlanetIdMax()+1;	//Assignation de l'ID de la planï¿½te ï¿½ la valeur max des ID des planï¿½tes prï¿½sente dans la partie incrï¿½mentï¿½e de 1
		g.getPlanets().add(this);
		if(owner>0)
		g.getPlayers().get(owner-1).getPlanetsOwned().add(this);
		
		g.setPlanetIdMax(this.id);		//Incrï¿½mentation de la valeur de l'id maximale des planï¿½tes dans la partie
		
	}
	/**
	 * A function computing the distance between this planet and another planet
	 * 
	 * @param p The other planet
	 * @return The distance between the center of this planet and p center
	 */
	public double getDistance(Planet p) 
	{
		 return Math.sqrt(sqr(this.posY - p.getPosY()) + sqr(this.posX - p.getPosX()));
	}
	/**
	 * A function computing the distance between two points
	 * @param x1 The x coordinate of the first point
	 * @param y1 The y coordinate of the first point
	 * @param x2 The x coordinate of the second point
	 * @param y2 The y coordinate of the second point
	 * @return
	 */
	public double getDistance(double x1, double y1, double x2, double y2)
	{
		 return Math.sqrt(sqr(y2 - y1) + sqr(x2 - x1));
	}	
	/**
	 * A function squaring a number
	 * @param d The number that will be squared
	 * @return The squared number
	 */
	public double sqr(double d) 
	{
		return d*d;
	}
	
	/**
	 * A method that produce ships for this planet, not implemented yet
	 * @param g The game the planet belongs to
	 */
	
	public void produceShips(Game g) 
	{
		
		try {
			Thread.sleep(Spaceship.productionTime);
		} catch (InterruptedException e) {
			System.out.println("erreur");
		}
		this.power = this.power + this.productionRate; //<- prise en compte du taux de production de la planï¿½te pour le nombre de vaisseaux produits
		updatePower(g);
	}
	
	/**
	 * A method that allow this planet to attack the given target by launching ships at it
	 * @param g The game this planet belongs to
	 * @param target The target of the attack
	 */
	public void launch(Game g, Planet target, Player player)	
	{
		
		int launchRate = player.getShipRate(); 
		int shipLaunchedNbr = power*launchRate/100;
		Squadron squadron = new Squadron(this.owner, target, g);
		setPower(power - shipLaunchedNbr); 
		
		for(int i = 0; i<=shipLaunchedNbr; i++) //apparition d'un vaisseau pour chaque vaisseau lancï¿½, gestion des vagues de lancement ï¿½ implï¿½menter ici
		{
			if(i>0)
			{
			Spaceship s = new Spaceship(g, this, squadron); 
			s.goTo(g); 
			}
		}
	}
	
	/*
	 * 
	 */
	
	public void updatePower(Game g)
	{
		g.getMain().getPrimaryGroup().getChildren().remove(this.getPowerTxt());
		this.getPowerTxt().setText(String.valueOf(this.getPower()));
		this.getPowerTxt().setX(this.getPosX() - this.getPowerTxt().getBoundsInLocal().getWidth()/2);
		this.getPowerTxt().setY(this.getPosY() + this.getPowerTxt().getBoundsInLocal().getHeight()/2);
		g.getMain().getPrimaryGroup().getChildren().add(this.getPowerTxt());
	}
	
	/**
	 * A function that allow the planet to interact with the given ship, not implemented yet
	 * 
	 * @param ship The ship which just crashed on the planet 
	 */
	public void interact(Spaceship ship, Game g) 
    {
		  System.out.println(power);
	        if(this.owner==ship.getOwner())
	        {
	            this.power = this.power + 1; 
	        	updatePower(g);
	        }
	        else
	        {
	            this.power = this.power - ship.getPower();    
	            updatePower(g);
	            if(this.power<=0)
	            {
	            	if(this.getOwner()>0)
	            	{
	            		for(int i = 1; i<g.getPlayers().size(); i++)
	            		{
	            			if(g.getPlayers().get(i).getId() == this.getOwner())
	            			{
	            				g.getPlayers().get(i).getPlanetsOwned().remove(this);
	         	            	g.getPlayers().remove(i);
	            			}
	            		}
	            	}
		            this.owner = ship.getOwner(); 
		            for(int i = 1; i<g.getPlayers().size(); i++)
            		{
            			if(g.getPlayers().get(i).getId() == this.getOwner())
            			{
            				g.getPlayers().get(owner-1).getPlanetsOwned().add(this);
            			}
            		}
		            
		            if(this.owner == 1)
		            {
		            	this.setNewImage("Green.jpg");
		            }
		            else
		            {
		            	this.setNewImage("red.jpg");
		            }
	            	
	            } 
	        }    
    }
	
	
	/**
	 * Change the planet appearance with the given image path
	 * @param s Path to the new image
	 */
	public void setNewImage(String s)
	{
		this.image = new Image(s);
		this.imagePattern = new ImagePattern(image);
		this.getShape().setFill(imagePattern);
	}
	
/*	public void setNewPlanetPower()
	{
		this.getStack().getChildren().remove(this.getPowerTxt());
		this.getPowerTxt().setText(String.valueOf(this.getPower()));
		this.getStack().getChildren().add(this.getPowerTxt());
	}*/
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getOwner()
	{
		return owner;
	}
	
	public void setOwner(int owner)
	{
		this.owner = owner;
	}
	
	public int getRadius()
	{
		return radius;
	}
	
	public void setRadius(int radius)
	{
		this.radius = radius;
	}
	
	public int getPower()
	{
		return power;
	}
	
	public void setPower(int power)
	{
		this.power = power;
		
	}
	
	public int getProductionRate()
	{
		return productionRate;
	}
	
	void setProductionRate(int productionRate)
	{
		this.productionRate = productionRate;
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
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public ImagePattern getImagePattern() {
		return imagePattern;
	}
	
	public void setImagePattern(ImagePattern imagePattern) {
		this.imagePattern = imagePattern;
	}
	
	public Circle getShape() {
		return shape;
	}
	
	public void setShape(Circle shape) {
		this.shape = shape;
	}
	
	public Circle getHitZone() {
		return hitZone;
	}
	public void setHitZone(Circle hitZone) {
		this.hitZone = hitZone;
	}
	public Text getPowerTxt() {
		return powerTxt;
	}
	public void setPowerTxt(Text powerTxt) {
		this.powerTxt = powerTxt;
	}

	/*public Circle getPathHitbox() {
		return pathHitbox;
	}

	public void setPathHitbox(Circle pathHitbox) {
		this.pathHitbox = pathHitbox;
	}*/

}
