
public class Spaceport {

	private int posX;
	private int posY;
	private boolean isEmpty;
	
	
	
	public Spaceport(Planet p) {
		this.posX = 0;
		this.posY = 0;
		this.isEmpty = true;
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
	public boolean isEmpty() {
		return isEmpty;
	}
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
}
