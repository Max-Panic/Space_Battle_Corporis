import java.util.*;
import java.*;
import javafx.*;
import javafx.geometry.Point2D;
import javafx.scene.text.Text;
/**
 * The class that manages the squadrons
 */
public class Squadron {

	private int id;
	private int owner;
	private Planet target;
	private List<Spaceship> spaceships = new ArrayList<Spaceship>();
	private Text squadronTxt;
	
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
			for(int j = 0; j<g.getPlanets().size(); i++)
			{
				if(!Spaceship.collide(s.getShape(), g.getPlanets().get(i).getShape()))
				{
					if(j==g.getPlanets().size()+1)
					{
						s.goTo(g);
					}
				}
				else
				{
					Point2D start = s.getIntermediate(g.getPlanets().get(i), new Point2D(s.getTarget().getShape().getCenterX(),s.getTarget().getShape().getCenterY()));
					s.getShape().setCenterX(start.getX());
					s.getShape().setCenterX(start.getY());
					s.goTo(g);
				}
			}
		}
	}
	
	public Squadron(int owner, Planet target, Game g)
    {
        this.id = g.getSquadronIdMax()+1;
        g.setSquadronIdMax(this.id);
        this.owner = owner;
        this.target = target;
        g.getSquadrons().add(this);
        System.out.println(owner);
        for(int i=0; i<g.getPlayers().size(); i++)
        {
            if(g.getPlayers().get(i).getId() == this.owner )
            {
                    g.getPlayers().get(i).getSquadrons().add(this);
                    if(g.getPlayers().get(i).getId() == 1)
                    {
                        updateSquadrons(g, g.getPlayers().get(i));
                        break;
                    }
            }
            
        }
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
	
	public void updateSquadrons(Game g, Player p)
	{
		for(int j = 0; j<p.getSquadrons().size(); j++)
        {
			g.getMain().getPrimaryGroup().getChildren().remove(squadronTxt);
            this.squadronTxt = new Text(30+20*j,20,String.valueOf(j+1));
            g.getMain().getPrimaryGroup().getChildren().add(squadronTxt);
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
