import java.util.*;
import java.*;
import javafx.*;
/**
 * The class that will manage the squadrons, not implemented yet
 */
public class Squadron {

	private int id;
	private int owner;
	private Planet target;
	private List<Spaceship> spaceships = new ArrayList<Spaceship>();
	
	/**
	 * Create a new instance of Squadron with no effect
	 */
	public Squadron()
	{
		this.id = 0;
	}

	public Squadron(Game g, int owner, int ID,  int targetId, int nbSpaceShips, List<Double> spaceShipPositions)
	{
		this.id = ID;
		this.owner = owner;
		this.target = Main.getPlanetFromPlanetID(g, targetId);
		Spaceship s;
		for(int i = 0; i<nbSpaceShips*2; i = i+2)
		{
			s = new Spaceship(g, spaceShipPositions.get(i/2), spaceShipPositions.get(i/2+1), target);
			s.goTo(g);
		}
	}
	
	public Squadron(int owner, Planet target, Game g)
	{
		this.id = g.getSquadronIdMax()+1;
		g.setSquadronIdMax(this.id);
		this.owner = owner;
		this.target = target;
		g.getSquadrons().add(this);
	}
	
	public void changeTarget(Planet target, Game g)
	{
		setTarget(target);
		for(int i = 0; i<this.getSpaceships().size(); i++)
		{
			this.getSpaceships().get(i).setTarget(target);
			this.getSpaceships().get(i).goTo(g);
		}
	}
	
	public int getOwner()
	{
		return owner;
	}
	
	public int getID()
	{
		return id;
	}
	
	public Planet getTarget()
	{
		return target;
	}
	
	public List<Spaceship> getSpaceships()
	{
		return spaceships;
	}
	
	public void setOwner(int owner)
	{
		this.owner = owner;
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public void setTarget(Planet target)
	{
		this.target = target;
	}
	
	public void setSpaceships(List<Spaceship> spaceships)
	{
		this.spaceships = spaceships;
	}
}
