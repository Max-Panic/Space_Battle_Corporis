	import java.io.Serializable;
	import java.util.ArrayList;
	import java.util.List;

public class SaveState implements Serializable 
{
	private int nbPlanets;
	private int nbPlayers;
	private int nbSquadrons;
	private List<Integer> idPlayers = new ArrayList<Integer>();
	private List<Integer> planetsSettings= new ArrayList<Integer>();
	private List<Double> spaceShipsPositions = new ArrayList<Double>();
	private List<Integer> squadronSettings = new ArrayList<Integer>();
	
	private static final long serialVersionUID = -299482035708790407L;
	
	public SaveState(Game g)
	{
		this.nbPlanets = g.getPlanets().size();
		this.nbPlayers = g.getPlayers().size();
		this.nbSquadrons = g.getSquadrons().size();
		
		for(int i = 0; i<g.getPlayers().size(); i++)
		{
			idPlayers.add(g.getPlayers().get(i).getId());
		}
		for(int j = 0; j<g.getPlanets().size(); j++)
		{
			planetsSettings.add(g.getPlanets().get(j).getPosX());
			planetsSettings.add(g.getPlanets().get(j).getPosY());
			planetsSettings.add(g.getPlanets().get(j).getOwner());
			planetsSettings.add(g.getPlanets().get(j).getPower());
			planetsSettings.add(g.getPlanets().get(j).getRadius());
			planetsSettings.add(g.getPlanets().get(j).getId());
		}
		for(int k = 0; k<g.getSquadrons().size(); k++)
		{
			squadronSettings.add(g.getSquadrons().get(k).getOwner());
			squadronSettings.add(g.getSquadrons().get(k).getID());
			squadronSettings.add(g.getSquadrons().get(k).getTarget().getId());
			squadronSettings.add(g.getSquadrons().get(k).getSpaceships().size());
			for(int l = 0; l<g.getSquadrons().get(k).getSpaceships().size(); l++)
			{
				spaceShipsPositions.add(g.getSquadrons().get(k).getSpaceships().get(l).getShape().getCenterX());
				spaceShipsPositions.add(g.getSquadrons().get(k).getSpaceships().get(l).getShape().getCenterY());
			}
			
		}
	}

	public int getNbPlanets() {
		return nbPlanets;
	}

	public void setNbPlanets(int nbPlanets) {
		this.nbPlanets = nbPlanets;
	}

	public int getNbPlayers() {
		return nbPlayers;
	}

	public void setNbPlayers(int nbPlayers) {
		this.nbPlayers = nbPlayers;
	}

	public int getNbSquadrons() {
		return nbSquadrons;
	}

	public void setNbSquadrons(int nbSquadrons) {
		this.nbSquadrons = nbSquadrons;
	}

	public List<Integer> getIdPlayers() {
		return idPlayers;
	}

	public void setIdPlayers(List<Integer> idPlayers) {
		this.idPlayers = idPlayers;
	}

	public List<Double> getSpaceShipsPositions() {
		return spaceShipsPositions;
	}

	public void setSpaceShipsPositions(List<Double> spaceShipsPositions) {
		this.spaceShipsPositions = spaceShipsPositions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Integer> getPlanetsSettings() {
		return planetsSettings;
	}

	public void setPlanetsSettings(List<Integer> planetsSettings) {
		this.planetsSettings = planetsSettings;
	}

	public List<Integer> getSquadronSettings() {
		return squadronSettings;
	}

	public void setSquadronSettings(List<Integer> squadronSettings) {
		this.squadronSettings = squadronSettings;
	}
}


