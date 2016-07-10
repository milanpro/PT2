package assignment6;

public class ParticleHashFunction implements HashFunction<Particle>{

	@Override
	public int hash(Particle input) {
		if(input == null) throw  new NullPointerException();
		int h,x,y; //h = Hashwert
		x = input.getxPosition(); //xPosition
		y = input.getyPosition(); //yPosition
		h = x + y;   //Hashfunktion ÄNDERN!!!! Ackermann TODO
		return h; //Hashwerte
	}


}
