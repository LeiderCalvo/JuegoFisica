import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Planetas {
	
	private PApplet main;
	
	private PVector pos;
	private PImage img;
	private float masa, gravedad, rango, volumen, r2, r;
	private boolean color;
	
	public Planetas(PApplet main, float x, float y,float masa, PImage img) {
		this.main=main;
		this.img = img;
		pos = new PVector(x, y);
		this.masa = masa;
		
		gravedad = 1;
		volumen = masa*5.5f;
		rango = volumen*4.5f;
		r = rango/2;
		r2 = rango;
		color = false;
	}
	
	public void pintar() {
		main.noStroke();
		if(color) {
			main.fill(255,0,0,30);
		}else {
			main.fill(0,255,255,30);
		}
		main.ellipse(pos.x, pos.y, r, r);
		//main.fill(0,0,0,10);
		main.ellipse(pos.x, pos.y, r2, r2);
		
		//main.fill(255,255,255,80);
		main.image(img, pos.x, pos.y, volumen, volumen);
		//main.ellipse(pos.x, pos.y, volumen, volumen);
		
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
	
	public PVector attract(Personaje m) {
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

	public void setColor(boolean b) {
		color = b;		
	}
}
