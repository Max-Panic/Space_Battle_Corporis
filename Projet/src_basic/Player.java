import javafx.*;
import java.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * The class that will manage the players, not implemented yet
 */
public class Player {

	private int id;
	private List<Planet> planetsOwned = new ArrayList<Planet>();
	private List<Squadron> squadrons = new ArrayList<Squadron>();
	/**
	 * Percentage of ships the player will launch from his planets 
	 */
	private int shipRate;
	
	public Player()
	{
		this.id = 0;
	}
	
	public void play(Game g)
	{
		Random r = new Random();
		Planet p = this.getPlanetsOwned().get(0);
		for(int i = 1; i<this.getPlanetsOwned().size(); i++)
		{
			if(p.getPower()<this.getPlanetsOwned().get(i).getPower())
			{
				p = this.getPlanetsOwned().get(i);
			}	
		}
		int j = r.nextInt(g.getPlanets().size());
		this.setShipRate(25 - r.nextInt(50 - 25));
		if(!(g.getPlanets().get(j) == p))
		p.launch(g, g.getPlanets().get(j), this);
	}
	public Player(Game g, int ID)
	{
		this.id = ID;
		this.shipRate = 15;
	}
	public int getId() {
		return id;
	}

	public boolean isIA()
	{
		boolean bool = true;
		if(Main.getMode()==0)
			if(this.getId()==1)
				bool = false;
		return bool;
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
	
	public List<Squadron> getSquadrons() {
		return squadrons;
	}

	public void setSquadrons(List<Squadron> squadrons) {
		this.squadrons = squadrons;
	}

	public int getShipRate() {
		return shipRate;
	}
	public void setShipRate(int shipRate) {
		this.shipRate = shipRate;
	}
}
