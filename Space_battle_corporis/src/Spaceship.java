


public class Spaceship {

	private int owner;
	private int speed;
	private int productionTime;
	private int power;
	private int squadronID;
	
	/*public int getID()
	{
		return id;
	}*/
	
	public int getOwner()
	{
		return owner;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public int getProductionTime()
	{
		return productionTime;
	}
	
	public int getSquadronID()
	{
		return squadronID;
	}
	
	/*public void setID(int id)
	{
		this.id = id;
	}
	*/
	
	public int getPower()
	{
		return power;
	}
	
	public void setOwner(int owner)
	{
		this.owner = owner;
	}
	
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	
	public void setProductionTime(int productionTime)
	{
		this.productionTime = productionTime;
	}
	
	public void setPower(int power)
	{
		this.power = power;
	}
	public void setSquadronID(int squadronID)
	{
		this.squadronID = squadronID;
	}
	
	public Spaceship(int owner, int squadronID)
	{
		this.owner = owner;
		this.squadronID = squadronID;
	}
	
	public Spaceship(int owner)
	{
		this.owner = owner;
		this.squadronID = 0;
		this.productionTime = 5000;
		this.speed = 5;
		this.power = 1;
	}
}
