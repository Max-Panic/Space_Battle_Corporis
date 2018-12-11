
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
		//Spaceship ship = new Spaceship(this.owner);
		
		
		//gestion du pourcentage
		int launchRate = 25;
		int shipLaunchedNbr = power*launchRate/100;
		Squadron squadron = new Squadron(1, 1, shipLaunchedNbr, target.id);
		this.power = power - shipLaunchedNbr;
		
		/*for(int i = 0; i<shipLaunchedNbr; i++)
		{
		target.interact(ship);
		}*/
	}
	
	public void interact(Spaceship ship)
	{
		//detecter vaisseau
		if(this.owner==ship.getOwner())
			this.power = this.power + ship.getPower();
		else
			this.power = this.power - ship.getPower();
	}
}
