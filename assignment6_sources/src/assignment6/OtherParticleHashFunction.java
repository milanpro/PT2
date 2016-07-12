package assignment6;

public class OtherParticleHashFunction implements HashFunction<Particle>{

	@Override
	public int hash(Particle input) {
		if(input == null) throw  new NullPointerException();
		int h,x,y; //h = Hashwert
		x = input.getxPosition(); //xPosition
		y = input.getyPosition(); //yPosition
		h = (x % 317) + (y % 7);
		return h; //Hashwerte
	}

}
