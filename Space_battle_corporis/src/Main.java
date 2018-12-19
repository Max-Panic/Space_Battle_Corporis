import javafx.application.*;
import java.*;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.shape.*;
import java.util.Random;
import javafx.scene.shape.Rectangle;
import javafx.geometry.*;


public class Main extends Application{
	
	private Stage myStage;
	private Group primaryGroup = new Group();
	private Scene primaryScene;
	private Game primaryGame;
	public static int xMax = 1900;
	public static int yMax = 1080;
	
	
	public Main() {
		super();
		//Scene scene = new Scene(primaryGroup, 400, 400);
		this.primaryGame = new Game(8);
		
	
		//this.primaryScene = scene;
	}
	
	public Main(Stage myStage, Group primaryGroup, Scene primaryScene) {
		super();
		this.myStage = myStage;
		this.primaryGroup = primaryGroup;
		this.primaryScene = primaryScene;
	}

	
	public void start(Stage myStage)
	{ 	
		
		this.primaryGroup.getChildren().add(primaryGame.getBackground());
		
		for(int i = 0; i<primaryGame.getPlanets().size(); i++)
		{
			
		
			this.primaryGroup.getChildren().add(primaryGame.getPlanets().get(i).getShape());
			this.primaryGame.getPlanets().get(i).getShape().setOnMouseClicked(e -> 
			{
				System.out.println("CLICK SAMA");
			});
		}
		
		Scene scene = new Scene(this.primaryGroup, xMax, yMax);
		
		myStage.setScene(scene);
		
		
		myStage.show();
		
	}

	public Stage getStage() {
		return myStage;
	}
	
	public void setStage(Stage stage) {
		this.myStage = stage;
	};
	
		
	public static void main(String[] args) 
	{
		Main game = new Main();
		game.launch(args);
		
		
	
		
		
		//Planet terre = new Planet(1, 1, 5, 100, 0);
		//Planet mars = new Planet(2, 2, 5, 100, 0);
		/*System.out.println(terre.getPower());
		System.out.println(mars.getPower());
		terre.launch(mars);
		System.out.println(terre.getPower());
		System.out.println(mars.getPower());*/
		
		
		
		/*Game gamu = new Game();
		gamu.start(gamu.getStage());*/
		
	}
	
}
