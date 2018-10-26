import processing.core.PImage;
import processing.core.PVector;

public class Personaje {

	private Main app;

	private PVector pos, vel, acel;
	private float masa;
	private PImage img;

	private boolean charging;

	public Personaje(Main app) {
		this.app = app;
		img = app.loadImage("/data/carrito.png");
		pos = new PVector(50, app.height / 2);
		vel = new PVector();
		acel = new PVector();
		masa = app.random(1,2);
		charging = true;
	}

	public void dibujar() {
		if(charging) {
			app.fill(0,255,0);
		}else {
			app.fill(0);			
		}
		//app.ellipse(pos.x, pos.y, masa * 25, masa * 25);
		app.imageMode(app.CENTER);
		app.image(img, pos.x, pos.y, img.width/masa, img.height/masa);
	}

	public void mover() {
		vel.add(acel);
		pos.add(vel);
		acel.mult(0);
	}

	public void aplicarFuerza(PVector fuerza) {
		PVector f = PVector.div(fuerza, masa);
		// System.out.println(" "+f.mag());
		acel.add(f);
	}

	public void rebotar() {
		if (pos.x > app.width) {
			pos.x = app.width;
			vel.x *= -1;
		}

		if (pos.x < 0) {
			pos.x = 0;
			vel.x *= -1;
		}

		if (pos.y > app.height) {
			pos.y = app.height;
			vel.y *= -1;
		}

		if (pos.y < 0) {
			pos.y = 0;
			vel.y *= -1;
		}
	}

	public PVector getVel() {
		return vel;
	}
	
	public PVector getAcel() {
		return acel;
	}

	public PVector getPos() {
		return pos;
	}

	public float getMasa() {
		return masa;
	}

	public void setVel(int i) {
		vel.mult(i);
	}

	public void setCharnging(boolean b) {
		charging = b;
	}
	
	public boolean getCharnging() {
		return charging;
	}

}
