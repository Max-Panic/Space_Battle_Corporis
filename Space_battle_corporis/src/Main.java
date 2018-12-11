import javafx.*;
import java.*;

public class Main {

	
	public static void main(String[] args) 
	{
		Planet terre = new Planet(1, 1, 5, 100, 0);
		Planet mars = new Planet(2, 2, 5, 100, 0);
		System.out.println(terre.getPower());
		System.out.println(mars.getPower());
		terre.launch(mars);
		System.out.println(terre.getPower());
		System.out.println(mars.getPower());
	}

}
