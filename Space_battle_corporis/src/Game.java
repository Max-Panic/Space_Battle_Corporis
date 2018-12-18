import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;

import java.*;
import java.util.ArrayList;
import java.util.List;


public class Game {

	private List<Planet> planets = new ArrayList<Planet>();
	private int squadronIdMax;
	private int planetIdMax;
	private int minDist;
	
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
		
		Planet p = new Planet(this, 1);
		
		//System.out.println(this.getPlanets().get(0).getPosX() + " HAHA posX");
		
		p = new Planet(this, 2);
		
		p = new Planet(this, 50, 50);
		System.out.println(p.isInFrame(800, 600) + " ICI");
		for(int i = 0; i<nbPlanets - 2; i++)
		{
			System.out.print("ok1");
			p = new Planet(this);
		}
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
