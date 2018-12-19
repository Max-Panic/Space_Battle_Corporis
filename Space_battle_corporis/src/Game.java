import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.*;
import java.util.ArrayList;
import java.util.List;


public class Game {

	private List<Planet> planets = new ArrayList<Planet>();
	private int squadronIdMax;
	private int planetIdMax;
	private int minDist;
	private Image imgBackground = new Image("space.jpg");
	private ImagePattern imagePattern = new ImagePattern(imgBackground);
	private Rectangle background = new Rectangle();
	
	public int getMinDist() {
		return minDist;
	}

	public void setMinDist(int minDist) {
		this.minDist = minDist;
	}

	public Game()
	{
		this.squadronIdMax = 0;;
		this.planetIdMax = 0;
		planets.clear();
	}
	
	public Game(int nbPlanets) //il y aura forcément au moins deux planètes
	{
		this.squadronIdMax = 0;
		this.planetIdMax = 0;
		planets.clear();
		this.minDist = 250;
		
		getBackground().setX(0);
		getBackground().setY(0);
		getBackground().setWidth(Main.xMax);
		getBackground().setHeight(Main.yMax);
		getBackground().setFill(imagePattern);
		
		for(int i=0; i<1; i++)
		{
		Planet p = new Planet(this, 1);
		}
		
		
		Planet p = new Planet(this, 2);
		
		//Planet p = new Planet(this, 0, 0);
		for(int i = 0; i<nbPlanets - 2; i++)
		{
			p = new Planet(this);
		}
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
	
	
}
