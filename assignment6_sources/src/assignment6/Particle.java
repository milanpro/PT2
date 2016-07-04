package assignment6;

public class Particle {
	
	private int xPosition, yPosition;
	
	public Particle(int x, int y){
		xPosition = x;
		yPosition = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Particle other = (Particle) obj;
		if (xPosition != other.xPosition)
			return false;
		if (yPosition != other.yPosition)
			return false;
		return true;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}
}
