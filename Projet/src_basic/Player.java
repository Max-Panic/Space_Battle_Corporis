import javafx.*;
import java.*;
import java.util.ArrayList;
import java.util.List;
/**
 * The class that will manage the players, not implemented yet
 */
public class Player {

	private String name;
	private int id;
	private List<Planet> planetsOwned = new ArrayList<Planet>();
	/**
	 * Percentage of ships the player will launch from his planets 
	 */
	private int shipRate;
	
	public Player(int ID)
	{
		this.name = "Player1";
		this.id = ID;
		this.shipRate = 15;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Planet> getPlanetsOwned() {
		return planetsOwned;
	}

	public void setPlanetsOwned(List<Planet> planetsOwned) {
		this.planetsOwned = planetsOwned;
	}

	public Player(String name, int id, List<Planet> planets)
	{
		this.name = name;
		this.id = id;
		this.planetsOwned = planets;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getID()
	{
		return id;
	}
	
	public List<Planet> getPlanets()
	{
		return planetsOwned;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public void setPlanets(List<Planet> planets)
	{
		this.planetsOwned = planets;
	}
	public int getShipRate() {
		return shipRate;
	}
	public void setShipRate(int shipRate) {
		this.shipRate = shipRate;
	}
}
