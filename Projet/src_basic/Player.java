import javafx.*;
import java.*;
import java.util.ArrayList;
import java.util.List;
/**
 * The class that will manage the players, not implemented yet
 */
public class Player {

	private int id;
	private List<Planet> planetsOwned = new ArrayList<Planet>();
	/**
	 * Percentage of ships the player will launch from his planets 
	 */
	private int shipRate;
	
	public Player(Game g, int ID)
	{
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
	
	public int getShipRate() {
		return shipRate;
	}
	public void setShipRate(int shipRate) {
		this.shipRate = shipRate;
	}
}
