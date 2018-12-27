import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.*;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
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
	public static int xMax = 800;
	/**
	 * The y size of the frame
	 */
	public static int yMax = 600;
	/**
	 * The percentage of launched ships text node
	 */
	public Text shipRateP1;
	/**
	 * The end display
	 */
	public Text endScreen;
	
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
			this.primaryGame = new Game(this, 4);
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
	
		Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );
        
        final long timeStart = System.currentTimeMillis();
        
        KeyFrame kf = new KeyFrame(
            Duration.seconds(0.017),                // 60 FPS
            new EventHandler<ActionEvent>()
            {
            	int cpt = 0;
                public void handle(ActionEvent ae)
                {
                    double t = (System.currentTimeMillis() - timeStart) / 1000.0; 
                    for(int i = 0; i<primaryGame.getSquadrons().size(); i++)
                    {
                        for (int j = 0; j<primaryGame.getSquadrons().get(i).getSpaceships().size(); j++)
                        {	
                            if (primaryGame.getSquadrons().get(i).getSpaceships().get(j).interactifcollide(primaryGame.getSquadrons().get(i).getSpaceships().get(j).getTarget(),primaryGame))
                            {
                                getPrimaryGroup().getChildren().remove(primaryGame.getSquadrons().get(i).getSpaceships().get(j).getShape());
                                primaryGame.getSquadrons().get(i).getSpaceships().remove(primaryGame.getSquadrons().get(i).getSpaceships().get(j));

                            }
                        }
                    }
                    if (primaryGame.getPlayers().size()==1 )
                    {
                        if (primaryGame.getPlayers().get(0).getId() == 1) 
                        {
                            primaryGame.getMain().setEndScreen(new Text(10, 40, "Victory"));
                            getPrimaryGroup().getChildren().add(primaryGame.getMain().getEndScreen());
                        }
                        else
                        {
                            primaryGame.getMain().setEndScreen(new Text(10, 40, "Defeat"));
                            getPrimaryGroup().getChildren().add(primaryGame.getMain().getEndScreen());
                        }
                    }
	                if (!(cpt == 0))
	                    for(int k = 0; k<primaryGame.getPlanets().size(); k++)
	                    {
	                    	if (cpt%(Spaceship.productionTime*60)==0) {
	                    		primaryGame.getPlanets().get(k).produceShips(getPrimaryGame());
	                    	}
	                    }
                cpt++;
                }
            });
        
        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();
        
        this.setMyStage(myStage);
		myStage.show();
			/*if(this.primaryGame.getPlanets().isEmpty() == false)
			{
			}
		}*/
		
	}
		
	/**
	 * The function that display the game
	 * @param s The stage the game will be start on
	 */
	public void startGame(Stage s)
	{
		this.primaryScene = new Scene(this.primaryGroup, xMax, yMax);
		
		this.primaryGame.getStartButton().setOnMouseClicked(e -> 
		{
			System.out.println("click");
			this.primaryGame = new Game(this, 4);
		});
		
		this.shipRateP1 = new Text(10, 20, String.valueOf(primaryGame.getPlayers().get(0).getShipRate()));
		getPrimaryGroup().getChildren().add(shipRateP1);
		
		primaryScene.setOnScroll(scroll ->
		{
			int percentage;
			double deltaY = scroll.getDeltaY();
			if(deltaY > 0)
			{
				if(primaryGame.getPlayers().get(0).getShipRate() < 100)
				{
					primaryGame.getPlayers().get(0).setShipRate(primaryGame.getPlayers().get(0).getShipRate()+5);
				}
			}
			else
			{
				if(primaryGame.getPlayers().get(0).getShipRate() > 0)
				{
					primaryGame.getPlayers().get(0).setShipRate(primaryGame.getPlayers().get(0).getShipRate()-5);
				}
			}
			getPrimaryGroup().getChildren().remove(shipRateP1);
			this.shipRateP1 = new Text(10, 20, String.valueOf(primaryGame.getPlayers().get(0).getShipRate()));
			getPrimaryGroup().getChildren().add(shipRateP1);
		});
		
		for(int i = 0; i<primaryGame.getPlanets().size(); i++)
		{
			this.primaryGroup.getChildren().addAll(primaryGame.getPlanets().get(i).getShape(),primaryGame.getPlanets().get(i).getPowerTxt());
			this.primaryGame.getPlanets().get(i).getShape().setOnMouseClicked(e -> 
			{
				Planet p1 = getPlanetFromCircle((Circle) e.getSource());
				//onClickedPlanet(p1);
				for(int j = 0; j<primaryGame.getPlanets().size(); j++)
				{
					if(primaryGame.getPlanets().get(j) != p1)
					{
						primaryGame.getPlanets().get(j).getShape().setOnMouseClicked(f -> 
						{
							Planet p2 = getPlanetFromCircle((Circle) f.getSource());
							p1.launch(primaryGame, p2, primaryGame.getPlayers().get(0));
						});
						
					}
				}
				
				System.out.println("CLICK");
			});
		}
		
		s.setScene(primaryScene);
		this.setMyStage(s);
		myStage.show();
		
	}
	
	/*public void onClickedPlanet(Planet p)
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
	}*/
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
			if(c.getCenterX()==primaryGame.getPlanets().get(i).getPosX() && c.getCenterY() == primaryGame.getPlanets().get(i).getPosY())
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

	public Text getEndScreen() {
		return endScreen;
	}

	public void setEndScreen(Text endScreen) {
		this.endScreen = endScreen;
	}
}
