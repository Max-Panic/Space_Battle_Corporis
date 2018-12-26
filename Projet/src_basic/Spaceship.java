import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
/**
 * 
 * The class managing the spaceships
 *
 */
public class Spaceship {

	/**
	 * The player the spaceship belongs to
	 */
	private int owner;
	/**
	 * The speed of the spaceship
	 */
	private int speed = 150;
	/**
	 * The x coordinate of the spaceship spawning point
	 */
	private int posX;
	/**
	 * The y coordinate of the spaceship spawning point
	 */
	private int posY;
	/**
	 * The planet that spawned the ship
	 */
	private Planet spawner;
	/**
	 * The target of the ship
	 */
	private Planet target = new Planet();
	/**
	 * The x radius of the ship
	 */
	private static double radiusX = 10;
	/**
	 * The y radius of the ship
	 */
	private static double radiusY = 5;
	/**
	 * The time the ship takes to produce
	 */
	private int productionTime;
	/**
	 * The attack power of the ship
	 */
	private int power;
	/**
	 * The ID of the squadron the ship belongs to
	 */
	private int squadronID;
	/**
	 * The appearance of the ship
	 */
	private Image image = new Image("space.jpg");
	/**
	 * The shape of the ship in the game
	 */
	private Ellipse shape = new Ellipse();
	/**
	 * The path the ship is traveling
	 */
	private Polyline trajectory = new Polyline();
	/**
	 * Used for the ship animation
	 * @see PathTransition
	 */
	private PathTransition pathTransition = new PathTransition();
	/**
	 * Used for printing image into shapes of the ship
	 * @see image
	 * @see shape
	 */
	private ImagePattern imagePattern = new ImagePattern(image);
	/**
	 * Create a new instance of Spaceship in the given game for the given planet 
	 * <p>
	 * And spawn it around the given planet
	 * 
	 * @param g The game the new spaceship belongs to
	 * @param p The planet that produced this ship
	 * @return A new instance of Spaceship
	 * 
	 * @see spawn()
	 */
	public Spaceship(Game g, Planet p)
	{
		this.owner = p.getOwner();
		this.squadronID = 0;
		this.productionTime = 1;
		this.power = 1;
		this.target = g.getPlanets().get(0);
		
		spawn(g, p);
		
	}
	/**
	 * Spawn the spaceship in the given game around the given planet
	 * @param g The game the spaceship belongs to
	 * @param p The planet that produced the spaceship
	 */
	public void spawn(Game g, Planet p)
	{
		Random r = new Random();
		boolean ok = false;
		
		while(ok==false)
		{	
			this.posX = (p.getPosX() - p.getRadius() - 35) + r.nextInt((p.getPosX() + p.getRadius() + 35) - (p.getPosX() - p.getRadius() - 35));
			this.posY = (p.getPosY() - p.getRadius() - 35) + r.nextInt((p.getPosY() + p.getRadius() + 35) - (p.getPosY() - p.getRadius() - 35));
			this.getShape().setCenterX(posX);
			this.getShape().setCenterY(posY);
			
			
			if(p.getSpaceships().isEmpty())
			{
				if(this.getDistance(p) - this.radiusX - 5 > p.getRadius()+5)
				{
					ok = true;
				}
			}
			else
			{
				
				if(this.getDistance(p) - this.radiusX - 5 > p.getRadius() + 5)
				{
					ok = true;
					/*for(int i = 0; i<p.getSpaceships().size(); i++)
					{
						if (Spaceship.colli)
						{						
							ok = true;
						}
						else
						{	
							break;	
						}
					}*/
				}	
			}
		}
		this.getShape().setRadiusX(getRadiusX());
		this.getShape().setRadiusY(getRadiusY());
		this.getShape().setFill(imagePattern);
		p.getSpaceships().add(this);
		g.getMain().getPrimaryGroup().getChildren().add(this.getShape());
	}
		
	/**
	 * Make this ship travel to the given planet in the given game
	 * <p>
	 * Does not evade planets for the moment
	 * </p>
	 * @param target The destination of the ship (a planet)
	 * @param g The game the ship belongs to
	 */
	public void goTo(Planet target, Game g)
	{
		/*Polyline l = new Polyline();
		List<Double> points = new ArrayList<Double>();			//
		points.add((double)this.getPosX());						//
		points.add((double) this.getPosY());					//Temporairement : Trajet direct du vaisseau au centre de target
		points.add((double)target.getPosX());					//
		points.add((double)target.getPosY());					//
		l.getPoints().addAll(points);							//
		this.trajectory = l;	*/								//
		
		
		this.setTrajectory(target, g);
		g.getMain().getPrimaryGroup().getChildren().add(this.trajectory);
		getPathTransition().setNode(this.getShape());
		getPathTransition().setDuration(Duration.seconds(getDistance(target)/this.getSpeed()));
		getPathTransition().setPath(this.getTrajectory());
		getPathTransition().setCycleCount(1);
		getPathTransition().play();
	}
	/**
	 * Set the trajectory to the given planet for the ship 
	 * @param target The destination of the path
	 * @param g The game the ship belongs to
	 */
public void setTrajectory(Planet target, Game g) {
		
		this.trajectory = new Polyline();
		Point2D start = new Point2D(this.getPosX(), this.getPosY());
		Point2D end = new Point2D(target.getPosX(), target.getPosY());
		List<Point2D> points = buildPath(start, end, g, target);
		
		for(int i = 0; i<points.size(); i++)
		{		
			//System.out.println(i);
			this.trajectory.getPoints().add(points.get(i).getX());
			this.trajectory.getPoints().add(points.get(i).getY());
		}
	}	
	
/**
 * Create a path that go from the start point to the center of the spaceship target
 * <p>
 * recursive function, not correctly implemented yet
 * </p>
 * @param start The starting point of the path
 * @param end The end point of the path
 * @param g The game the ship belongs to
 * @param target The final destination of the path (a planet)
 * @return the path (a list of points)
 */
	public List<Point2D> buildPath(Point2D start, Point2D end, Game g, Planet target)
	{
		List<Point2D> points = new ArrayList<Point2D>();
		points.add(start); 
		
		Line l = new Line(start.getX(), start.getY(), end.getX(), end.getY());
		
		for(int i = 0; i<g.getPlanets().size(); i++)
			{
			boolean isTarget;
			if(target.getId() == g.getPlanets().get(i).getId()) isTarget = true; else isTarget = false;
				if(!isTarget && Spaceship.collide(g.getPlanets().get(i).getShape(), l))
				{					
					Point2D inter = getIntermediate(g.getPlanets().get(i), end);		
					points = (buildPath(start, inter, g, target));
					points.addAll(buildPath(inter, end, g, target));
				}
				else if (i == g.getPlanets().size() - 1)
				{
					points.add(end);
				}
			}	
		return points;
	}
	/**
	 * Create an intermediate point between two points away from the given planet for the given destination 
	 * @param obstacle the planet the ship must evade
	 * @param dest the destination of the ship
	 * @return An intermediate point
	 */
	public Point2D getIntermediate(Planet obstacle, Point2D dest)
	{
		Point2D src = new Point2D(obstacle.getPosX(), obstacle.getPosY()); 
		Random r = new Random();
		int gap = (obstacle.getRadius()+50) + r.nextInt((obstacle.getRadius()+70) - (obstacle.getRadius()+50));
		double VecX = -(dest.getY() - src.getY());
		double VecY = (dest.getX() - src.getX());
		
		
		double norm = Math.sqrt(VecX*VecX + VecY*VecY);
		VecX = VecX/norm;										//normalisation du vecteur
		VecY = VecY/norm;				
		
		Point2D point = new Point2D(src.getX() + VecX*gap,  src.getY() + VecY*gap);
		
		if(getDistance(point, src) > obstacle.getRadius() + 10)
		{	
		return point;
		}
		else
		{
			return point;
		}
		
	}
	/**
	 * Check if a shape intersects another shape
	 * @param shape1 The first shape
	 * @param shape2 The second shape
	 * @return True if shape1 intersects shape2, false if not
	 */
	public static boolean collide(Shape shape1, Shape shape2)
	{
		return !Shape.intersect(shape1, shape2).getBoundsInLocal().isEmpty();
	}
	
	public double getDistance(Spaceship p)
	{
		 return Math.sqrt(sqr(this.posY - p.getPosY()) + sqr(this.posX - p.getPosX()));
	}
	
	public double getDistance(Planet p)
	{
		 return Math.sqrt(sqr(this.posY - p.getPosY()) + sqr(this.posX - p.getPosX()));
	}
	
	public double getDistance(Point2D src, Planet p)
	{
		return Math.sqrt(sqr(src.getX() - p.getPosX()) + sqr(src.getY() - p.getPosY()));
	}

	public double getDistance(Point2D src, Point2D dest)
	{
		return Math.sqrt(sqr(src.getX() - dest.getX()) + sqr(src.getY() - dest.getY()));
	}
	
	public double sqr(double d)
	{
		return d*d;
	}

	public Polyline getTrajectory() {
		return trajectory;
	}

	public int getOwner()
	{
		return owner;
	}
	
	public ImagePattern getImage() {
		return imagePattern;
	}

	public void setImage(ImagePattern image) {
		this.imagePattern = image;
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
	
	public void setTrajectory(Polyline trajectory) {
		this.trajectory = trajectory;
	}
	
	public PathTransition getPathTransition() {
		return pathTransition;
	}

	public void setPathTransition(PathTransition pathTransition) {
		this.pathTransition = pathTransition;
	}
	
	public ImagePattern getImagePattern() {
		return imagePattern;
	}

	public void setImagePattern(ImagePattern imagePattern) {
		this.imagePattern = imagePattern;
	}

	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public Ellipse getShape() {
		return shape;
	}

	public void setShape(Ellipse shape) {
		this.shape = shape;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public Planet getTarget() {
		return target;
	}

	public void setTarget(Planet target) {
		this.target = target;
	}
	
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
	
	public double getRadiusX() {
		return radiusX;
	}

	public void setRadiusX(double radiusX) {
		this.radiusX = radiusX;
	}

	public double getRadiusY() {
		return radiusY;
	}

	public void setRadiusY(double radiusY) {
		this.radiusY = radiusY;
	}
}
