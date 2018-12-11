
public class Planet
{

	private int id;
	private int owner;
	private int radius;
	private int power;
	private int productionRate;
	
	
	
	public int getID()
	{
		return id;
	}
	
	public int getOwner()
	{
		return owner;
	}
	
	public int getRadius()
	{
		return radius;
	}
	
	public int getPower()
	{
		return power;
	}
	
	public int getProductionRate()
	{
		return productionRate;
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public void setOwner(int owner)
	{
		this.owner = owner;
	}
	
	public void setRadius(int radius)
	{
		this.radius = radius;
	}
	
	public void setPower(int power)
	{
		this.power = power;
	}
	
	public void setProductionRate(int productionRate)
	{
		this.productionRate = productionRate;
	}
	
	public Planet(int id, int owner, int radius, int power, int productionRate)
	{
		this.id = id;
		this.owner = owner;
		this.radius = radius;
		this.power = power;
		this.productionRate = productionRate;
	}	
	
	public void produceShip() 
	{
		Spaceship spaceship = new Spaceship(owner);
		try {
			Thread.sleep(spaceship.getProductionTime());
		} catch (InterruptedException e) {
			System.out.println("ça a foiré");
		}
		this.power = this.power + this.productionRate*spaceship.getPower();
	}
	
	public void launch(Planet target)
	{
		//gestion du pourcentage
		int launchRate = 25;
		Squadron squadron = new Squadron(idMax+1, power*attackRate/100;
		
		this.power = power - shipLaunchedNbr;
		target.interact(shipLaunchedNbr);
	}
	
	public void interact(Squadron squadron)
	{
		this.power = this.power - power;
	}
}
