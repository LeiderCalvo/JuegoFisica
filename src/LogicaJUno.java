import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class LogicaJUno {

	private Main app;
	private Personaje p;

	private PImage fondo, bandera, negativo;
	private Barra barra;
	private boolean nivelTerminado, gano;
	private int puntaje;
	private float meta;
	
	public LogicaJUno(Main app) {
		this.app = app;
		p = new Personaje(app);
		p.setMasa(1);
		p.setPos(new PVector(50,300));
		barra = new Barra();
		
		fondo = app.loadImage("/data/completo.png");
		bandera = app.loadImage("/data/bandera.png");
		negativo = app.loadImage("/data/negativo.png");
		nivelTerminado = false;
		gano = false;
		puntaje = 0;
		
		meta = app.random((app.width/4 )*3, app.width);
	}
	
	public void pintarMeta() {
		app.imageMode(app.CORNER);
		//app.image(negativo, 0, -215);
		app.fill(0);
		app.rect(0, app.height-235 , app.width, 235);
		
		int b = 0;
		//pintar la bandera
		for (int i = 0; i < negativo.height; i++) {
			if(negativo.get((int)meta, i)==-16777216) {
				b = i;
				break;
			}
		}
		
		//app.image(bandera, meta, b - 215 -((bandera.height/6)) , bandera.width/6, bandera.height/6);
		app.image(bandera, meta, app.height-233 -((bandera.height/6)) , bandera.width/6, bandera.height/6);
		app.imageMode(app.CENTER);
	}
	
	public void ejecutar() {
		pintarMeta();
		
		p.dibujar();
		app.fill(0,255,0);
		app.text("vel\n" + (float) (p.getVel().mag() * 100) + "", 50, 20);
		app.text("acel\n" + (float) (p.getAcel().mag() * 100) + "", 200, 20);
		app.text("masa\n" + p.getMasa() + "", p.getPos().x-20, p.getPos().y+50);
		
		//////////////////aplicar friccion cuando toque el negativo
		/*
		if (negativo.get( (int)p.getPos().x, (int) (p.getPos().y+230))== -16777216) {
			PVector agotamineto = p.getVel().copy();
			agotamineto.normalize();
			float agotaminetoForce = -0.008f;
			agotamineto.mult(agotaminetoForce);
			p.aplicarFuerza(agotamineto);
		}*/
		
		if(PApplet.dist(p.getPos().x, p.getPos().y, meta, app.height-233)<20) {
			PVector agotamineto = p.getVel().copy();
			agotamineto.normalize();
			float agotaminetoForce = -0.008f;
			agotamineto.mult(agotaminetoForce);
			p.aplicarFuerza(agotamineto);
			
			gano=true;
			nivelTerminado=true;
		}
		
		PVector agotamineto = p.getVel().copy();
		agotamineto.normalize();
		float agotaminetoForce = -0.00000008f;
		agotamineto.mult(agotaminetoForce);
		p.aplicarFuerza(agotamineto);
		
		p.rebotar();
		
		//aplicar fricciòn siempre dependiendo de si sube o baja posiblemente aplicar una normal cuando toque el negativo
		
		////////////darle la masa inicial al personaje
		if (app.mousePressed && p.getPos().x < 150) {
			p.setMasa(p.getMasa()+0.002f);
			p.setCharnging(true);
			barra.paint(0.1f);
		}

		isGanar();

		if (p.getCharnging() == false) {
			p.mover();
			
			p.aplicarFuerza(attractGravedad(p));
		}
	}
	
	public PVector attractGravedad(Personaje m) {
	    PVector force = new PVector(0,1);   // Calcula la direccion de la fuerza PVector.sub(new PVector(m.getPos().x, app.height-240),m.getPos())
	    float d = PApplet.dist(m.getPos().x, m.getPos().y, m.getPos().x, app.height-220);                              // Distancia entre los objetos
	   // d = PApplet.constrain(d,5.0f,25.0f);                        // Limiting the distance to eliminate "extreme" results for very close or very far objects
	    force.normalize();                                  
	    float potencia = (2 * 6 * m.getMasa()) / (d * d);      // Calcula la magnitud de la potencia
	    force.mult(potencia);                                  // obtener force vector --> magnitud * dir
	    return force;
	}
	
	public PVector attract(Personaje m) {
	    PVector force = new PVector(1,0);      // Calcula la magnitud de la potencia
	    force.mult(1/(m.getMasa()));                                  // obtener force vector --> magnitud * dir
	    return force;
	}
	
	private void isGanar() {
		if (p.getPos().x > 150 && p.getVel().mag() <= 0.004f) {
			//definir si quedo cerca de la bandera
			if (p.getPos().x > meta+25 && p.getPos().x < meta-25) {
				gano = true;
				puntaje = calcular();
				nivelTerminado = true;
			} else {
				gano = false;
				puntaje = calcular();
				nivelTerminado = true;
			}
		}

	}

	private int calcular() {
		//cambiar valores por el random meta
		float val = Math.abs(meta - p.getPos().x);
		if (val > 50)
			return 0;
		return (int) PApplet.map(val, 0, 50, 30, 20);
	}
	
	public void released() {

		p.aplicarFuerza(attract(p)); //aplicando fuerza de avance segun la masa
		p.setCharnging(false);
	}

	public boolean isTermino() {
		return nivelTerminado;
	}

	public boolean isGano() {
		return gano;
	}

	public int getPuntaje() {
		return puntaje;
	}
	
	private class Barra {

		public float ancho;

		public Barra() {
			ancho = 0;
		}

		public void paint(float a) {
			if (ancho < 50)
				ancho += a;
			app.fill(0);
			app.rect(p.getPos().x - 25, p.getPos().y + 30, 50, 10);
			app.fill(app.map(ancho, 0, 25, 0, 255), app.map(ancho, 0, 50, 255, 0), 0);
			app.rect(p.getPos().x - 25, p.getPos().y + 30, ancho, 10);
		}
	}

}
