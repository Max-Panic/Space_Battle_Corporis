import javafx.animation.PathTransition;
import javafx.application.*;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The class managing the game elements
 * <p>
 * it will also manage the safeguard in the future
 * </p>
 */
public class Game 
{
	/**
	 * The Main the game belongs to
	 * @see Main
	 */
	private Main main; 
	/**
	 * The list of the planets present in the game
	 * @see Planet
	 */
	private List<Planet> planets = new ArrayList<Planet>();
	/**
	 * The list of the players present in the game
	 */
	private List<Player> players = new ArrayList<Player>();
	/**
	 * The list of the squadrons present in the game
	 */
	private List<Squadron> squadrons = new ArrayList<Squadron>();
	/**
	 * The squadron's max ID, not implemented yet
	 * @see Squadron
	 */
	private int squadronIdMax;	
	/**
	 * The planets's max ID
	 * @see Planet
	 */
	private int planetIdMax; 	
	/**
	 * The minimum distance between the planets
	 */
	private int minDist; 	
	/**
	 * The background's image
	 */
	private Image imgBackground = new Image("Uni.PNG");
	/**
	 * The start button's image
	 */
	private Image imgStartButton = new Image("start.png"); 
	/**
	 * The background's shape (a rectangle that fit the frame shape)
	 */
	private Rectangle background = new Rectangle(); 
	/**
	 * The start button's shape
	 */
	private Rectangle startButton = new Rectangle();
	/**
	 * Used for printing images into shapes
	 * @see image
	 * @see shape
	 */
	private ImagePattern imagePattern;

	/**
	 * Create a new Game in the given main, launch the game on the menu (not implemented yet)
	 * @param main The Main the new Game belongs to
	 * @return A new instance a Game
	 */
	public Game(Main main) //contructeur de base servant ï¿½ lancer une partie dans un main donnï¿½, lance la partie sur le menu principal
	{
		this.main = main;
		this.squadronIdMax = 0;;
		this.planetIdMax = 0;
		planets.clear();
		
		this.imagePattern = new ImagePattern(this.imgBackground);
		getBackground().setX(0);
		getBackground().setY(0);
		getBackground().setWidth(Main.xMax);
		getBackground().setHeight(Main.yMax);
		getBackground().setFill(imagePattern);
		 
		this.imagePattern = new ImagePattern(this.imgStartButton);
		getStartButton().setX((Main.xMax/2) - (Main.xMax*15/100)/2);
		getStartButton().setY((Main.yMax/2) - (Main.yMax*10/100)/2);
		getStartButton().setWidth(Main.xMax*15/100);
		getStartButton().setHeight(Main.yMax*10/100);
		getStartButton().setFill(imagePattern);
		
		main.getPrimaryGroup().getChildren().addAll(background, startButton);
		
	}
	
	/**
	 * Create a new Game in the given main for the given number of planets
	 * <p>
	 * It start the game with the given number of planet, and launch an attack from the first to second created planet (for an example of the current state of this game)
	 * </p>
	 * @param main
	 * @param nbPlanets 
	 * @return A new instance a Game
	 */
	public Game(Main main, int nbPlanets) //il y aura forcément au moins deux planï¿½tes
	{
		this.main = main;
		this.squadronIdMax = 0;
		this.planetIdMax = 0;
		planets.clear();
		this.minDist = 250;
		
		this.imagePattern = new ImagePattern(this.imgBackground);	//
		getBackground().setX(0);									//
		getBackground().setY(0);									//
		getBackground().setWidth(Main.xMax);						//Affichage du background
		getBackground().setHeight(Main.yMax);						//
		getBackground().setFill(imagePattern);						//
		main.getPrimaryGroup().getChildren().add(background);		//
				
		Planet p = new Planet(this, 1);
		Planet target = new Planet(this, 2);	
	
		
		for(int i = 0; i<nbPlanets - 2; i++)
		{
			p = new Planet(this);
		}
		
		
		
	}
	
	
	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Image getImgBackground() {
		return imgBackground;
	}

	public void setImgBackground(Image imgBackground) {
		this.imgBackground = imgBackground;
	}

	public ImagePattern getImagePattern() {
		return imagePattern;
	}

	public void setImagePattern(ImagePattern imagePattern) {
		this.imagePattern = imagePattern;
	}

	public Rectangle getBackground() {
		return background;
	}

	public void setBackground(Rectangle background) {
		this.background = background;
	}

	public List<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}

	public int getPlanetIdMax() {
		return planetIdMax;
	}

	public void setPlanetIdMax(int planetIdMax) {
		this.planetIdMax = planetIdMax;
	}
	
	public int getSquadronIdMax() {
		return squadronIdMax;
	}

	public void setSquadronIdMax(int squadronIdMax) {
		this.squadronIdMax = squadronIdMax;
	}
	
	public int getMinDist() {
		return minDist;
	}

	public void setMinDist(int minDist) {
		this.minDist = minDist;
	}

	public Rectangle getStartButton() {
		return startButton;
	}

	public void setStartButton(Rectangle startButton) {
		this.startButton = startButton;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Image getImgStartButton() {
		return imgStartButton;
	}

	public void setImgStartButton(Image imgStartButton) {
		this.imgStartButton = imgStartButton;
	}

	public List<Squadron> getSquadrons() {
		return squadrons;
	}

	public void setSquadrons(List<Squadron> squadrons) {
		this.squadrons = squadrons;
	}
	
}
