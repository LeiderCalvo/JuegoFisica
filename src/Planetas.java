import processing.core.PApplet;
import processing.core.PVector;

public class Planetas {
	
	private Main main;
	
	private PVector pos;
	private float masa, gravedad, rango, volumen, r2, r;
	
	public Planetas(Main main, float x, float y) {
		this.main=main;
		
		pos = new PVector(x, y);
		masa = main.random(20);
		
		gravedad = 1;
		volumen = masa*5.5f;
		rango = volumen*4.5f;
		r = rango/2;
		r2 = rango;
	}
	
	public void pintar() {
		main.fill(255,0,0,30);
		main.ellipse(pos.x, pos.y, r, r);
		//main.fill(0,0,0,10);
		main.ellipse(pos.x, pos.y, r2, r2);
		
		main.fill(255,255,255,80);
		main.ellipse(pos.x, pos.y, volumen, volumen);
		
		if(r2 >= rango || r2 < 0) {
			r2=rango-1;
		}else{
			r2--;
		}
		
		if(r >= rango || r < 0) {
			r=rango-1;
		}else{
			r--;
		}
	}
	
	PVector attract(Personaje m) {
	    PVector force = PVector.sub(pos,m.getPos());   // Calcula la direccion de la fuerza
	    float d = force.mag();                              // Distancia entre los objetos
	   // d = PApplet.constrain(d,5.0f,25.0f);                        // Limiting the distance to eliminate "extreme" results for very close or very far objects
	    force.normalize();                                  
	    float potencia = (gravedad * masa * m.getMasa()) / (d * d);      // Calcula la magnitud de la potencia
	    force.mult(potencia);                                  // obtener force vector --> magnitud * dir
	    return force;
	  }

	public PVector getPos() {
		return pos;
	}

	public float getVol() {
		return volumen;
	}
	
	public void setPos(PVector pos) {
		this.pos = pos;
	}
	
	public float getRango() {
		return rango;
	}
}