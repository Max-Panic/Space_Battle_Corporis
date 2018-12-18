import java.util.*;
import java.*;
import javafx.*;

public class Squadron {

	private int id;
	private int owner;
	private int shipNbr;
	private int target;
	private List<Spaceship> spaceships = new ArrayList<Spaceship>();
	
	public int getOwner()
	{
		return owner;
	}
	
	public int getID()
	{
		return id;
	}
	
	public int getShipNbr()
	{
		return shipNbr;
	}
	
	public int getTarget()
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
	
	public void setShipNbr(int shipNbr)
	{
		this.shipNbr = shipNbr;
	}
	
	public void setTarget(int target)
	{
		this.target = target;
	}
	
	public void setSpaceships(List<Spaceship> spaceships)
	{
		this.spaceships = spaceships;
	}
	
	public Squadron(int id, int owner, int shipNbr, int target)
	{
		this.id = id;
		this.owner = owner;
		this.shipNbr = shipNbr;
		this.target = target;
		//this.spaceships = null;
	}
	public Squadron(int id, int owner, int shipNbr, int target, List<Spaceship> spaceships)
	{
		this.id = id;
		this.owner = owner;
		this.shipNbr = shipNbr;
		this.target = target;
		//this.spaceships = spaceships;
	
	}
	
	public void changeTarget(Planet target)
	{
		//récupérer la demande
		setTarget(target.getID());
	}
}
