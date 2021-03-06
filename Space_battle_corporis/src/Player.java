import javafx.*;
import java.*;
import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name;
	private int id;
	private List<Planet> planetsOwned = new ArrayList<Planet>();
	
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
	
	public Player(String name, int id, List<Planet> planets)
	{
		this.name = name;
		this.id = id;
		this.planetsOwned = planets;
	}
}
