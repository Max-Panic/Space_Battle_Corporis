import javafx.application.*;
import java.*;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

import java.util.Random;

import javafx.geometry.*;


public class Main extends Application{
	/**
	 * A variable used to launch the game either on the menu or directly in the game
	 */
	private static int screen = 0;
	/**
	 * Main stage
	 */
	private Stage myStage;
	/**
	 * Main group of observable elements(shapes for example)
	 */
	private Group primaryGroup = new Group();
	/**
	 * Main scene
	 */
	private Scene primaryScene;
	/**
	 * The game currently playing
	 */
	private Game primaryGame;
	/**
	 * The x size of the frame
	 */
	public static int xMax = 1300;
	/**
	 * The y size of the frame
	 */
	public static int yMax = 850;
	/**
	 * the percentage of launched ships text node
	 */
	public Text shipRateP1;
	
	/**
	 * Create a new instance of main and a new instance of game
	 * <p>
	 * The game currently contains 8 planet, the first one will attack the second one
	 * </p>
	 */
	public Main() 
	{
		super();
		//Scene scene = new Scene(primaryGroup, 400, 400);
		if(screen == 0)
		{
			this.primaryGame = new Game(this, 8);
			
			
		}
		if(screen == 1)
		{
			this.primaryGame = new Game(this);
			
		}
	}
	
	/**
	 * The function that will contain the main loop of the game
	 * @param myStage The stage the game will be start on
	 */
	public void start(Stage myStage)
	{ 		
		startGame(myStage);
		int winner = 0;
		while(winner == 1)
		{
			if(this.primaryGame.getPlanets().isEmpty() == false)
			{
			}
		}
		
	}
		
	/**
	 * The function that display the game
	 * @param s The stage the game will be start on
	 */
	public void startGame(Stage s)
	{
		this.primaryScene = new Scene(this.primaryGroup, xMax, yMax);
		
		Player player1 = new Player(1);
		this.primaryGame.getStartButton().setOnMouseClicked(e -> 
		{
			System.out.println("click");
			this.primaryGame = new Game(this, 4);
		});
		
		this.shipRateP1 = new Text(10, 20, String.valueOf(player1.getShipRate()));
		getPrimaryGroup().getChildren().add(shipRateP1);
		
		primaryScene.setOnScroll(scroll ->
		{
			int percentage;
			double deltaY = scroll.getDeltaY();
			if(deltaY > 0)
			{
				if(player1.getShipRate() < 100)
				{
					player1.setShipRate(player1.getShipRate()+5);
				}
			}
			else
			{
				if(player1.getShipRate() > 0)
				{
					player1.setShipRate(player1.getShipRate()-5);
				}
			}
			getPrimaryGroup().getChildren().remove(shipRateP1);
			this.shipRateP1 = new Text(10, 20, String.valueOf(player1.getShipRate()));
			getPrimaryGroup().getChildren().add(shipRateP1);
		});
		
		for(int i = 0; i<primaryGame.getPlanets().size(); i++)
		{
			this.primaryGroup.getChildren().add(primaryGame.getPlanets().get(i).getShape());
			this.primaryGame.getPlanets().get(i).getShape().setOnMouseClicked(e -> 
			{
				Planet p1 = getPlanetFromCircle((Circle) e.getSource());
				onClickedPlanet(p1);
				for(int j = 0; j<primaryGame.getPlanets().size(); j++)
				{
					if(primaryGame.getPlanets().get(j) != p1)
					{
						primaryGame.getPlanets().get(j).getShape().setOnMouseClicked(f -> 
						{
							Planet p2 = getPlanetFromCircle((Circle) f.getSource());
							p1.launch(primaryGame, p2, player1);
						});
						
					}
				}
				
				System.out.println("CLICK");
			});
		}
		
		s.setScene(primaryScene);
		
		s.show();
	}
	
	public void onClickedPlanet(Planet p)
	{
		for(int i = 0; i<this.primaryGame.getPlanets().size();i++)
		{
			if(i!=p.getId()-1)
			{
				if(primaryGame.getPlanets().get(i).getOwner() == p.getOwner())
				{
					this.primaryGame.getPlanets().get(i).setNewImage("vert.jpg");
				}
				else
				{
					this.primaryGame.getPlanets().get(i).setNewImage("red.jpg");
				}
				
			}
		}
	}
	/**
	 * The main of the application, it create a new Main and call the start method
	 * @param args Argument array, no arguments needed for the moment
	 */
	public static void main(String[] args) 
	{
		Main game = new Main();
		game.launch(args);
		
		
		
	}
	
	public Planet getPlanetFromCircle(Circle c)
	{
		int i = getPlanetIDFromCircle(c);
		Planet p = getPlanetFromPlanetID(i);
		return p;
	}
	
	public int getPlanetIDFromCircle(Circle c)
	{
		int p = 0;
		for(int i = 0; i<primaryGame.getPlanets().size(); i++)
		{
			if(c.getCenterX()==primaryGame.getPlanets().get(i).getPosX() && c.getCenterY()==primaryGame.getPlanets().get(i).getPosY())
			{
				p = primaryGame.getPlanets().get(i).getId();
				break;
				
				
			}
		}
		return p;
	}
	
	public Planet getPlanetFromPlanetID(int id)
	{
		Planet p = new Planet();
		for(int i = 0; i<primaryGame.getPlanets().size(); i++)
		{
			if(id == primaryGame.getPlanets().get(i).getId())
			{
				p = primaryGame.getPlanets().get(i);
				break;
			}
		}
		return p;
	}
	
	public Stage getStage() {
		return myStage;
	}
	
	public void setStage(Stage stage) {
		this.myStage = stage;
	};
	
	public Stage getMyStage() {
		return myStage;
	}


	public void setMyStage(Stage myStage) {
		this.myStage = myStage;
	}


	public Group getPrimaryGroup() {
		return primaryGroup;
	}


	public void setPrimaryGroup(Group primaryGroup) {
		this.primaryGroup = primaryGroup;
	}


	public Scene getPrimaryScene() {
		return primaryScene;
	}


	public void setPrimaryScene(Scene primaryScene) {
		this.primaryScene = primaryScene;
	}


	public Game getPrimaryGame() {
		return primaryGame;
	}


	public void setPrimaryGame(Game primaryGame) {
		this.primaryGame = primaryGame;
	}


	public static int getxMax() {
		return xMax;
	}


	public static void setxMax(int xMax) {
		Main.xMax = xMax;
	}


	public static int getyMax() {
		return yMax;
	}


	public static void setyMax(int yMax) {
		Main.yMax = yMax;
	}

	public static int getScreen() {
		return screen;
	}

	public static void setScreen(int screen) {
		Main.screen = screen;
	}
}