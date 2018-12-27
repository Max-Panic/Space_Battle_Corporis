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
	

	
	public Squadron(int owner, Planet target, Game g)
	{
		this.id = g.getSquadronIdMax()+1;
		g.setSquadronIdMax(this.id);
		this.owner = owner;
		this.target = target;
		g.getSquadrons().add(this);
	}
	
	public void changeTarget(Planet target)
	{
		//r�cup�rer la demande
		setTarget(target);
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
