import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class LogicaJDos {

	private PApplet app;
	private Personaje p;
	private ArrayList<Planetas> planetas;

	private PImage fondo;
	private Barra barra;
	private boolean nivelTerminado, gano;
	private int puntaje;

	public LogicaJDos(PApplet app) {
		this.app = app;

		p = new Personaje(app);
		planetas = new ArrayList<Planetas>();
		barra = new Barra();

		fondo = app.loadImage("/data/completo.png");
		nivelTerminado = false;
		gano = false;
		puntaje = 0;

		PImage pUno = app.loadImage("/data/p1.png");
		PImage pDos = app.loadImage("/data/p2.png");
		PImage pTres = app.loadImage("/data/p3.png");

		// iniciar la cantidad de planetas necesarios
		for (int i = 0; i < 17; i++) {
			float y = 0;
			float random = app.random(1, 20);

			if (i % 2 == 0) {
				y = app.random(150, app.height / 3);
			} else {
				y = (app.random((app.height / 3) * 2, app.height - 150));
			}

			if (random < 20 / 3) {
				planetas.add(new Planetas(app, i * (app.width / 24) + 200, y, random, pTres));
			} else if (random > (20 / 3) * 2) {
				planetas.add(new Planetas(app, i * (app.width / 24) + 200, y, random, pDos));
			} else {
				planetas.add(new Planetas(app, i * (app.width / 24) + 200, y, random, pUno));
			}

		}
	}

	public void pintarMeta() {
		app.imageMode(app.CORNER);
		app.image(fondo, 0, 0);
		app.imageMode(app.CENTER);
		app.fill(200, 200, 200, 40);
		app.rect(1100, 0, app.width, app.height);
		app.stroke(255, 0, 0);
		app.strokeWeight(2);
		app.line(1150, 0, 1150, app.height);
		app.noStroke();
		app.textSize(15);
		app.textLeading(13);
	}

	public void ejecutar() {
		pintarMeta();

		p.dibujar();
		app.fill(0, 255, 0);
		app.text("vel\n" + (float) (p.getVel().mag() * 100) + "", p.getPos().x - 20, p.getPos().y + 50);
		app.text("acel\n" + (float) (p.getAcel().mag() * 100) + "", 50, 20 );
		app.text("masa\n" + p.getMasa() + "", 200, 20);

		for (Planetas pl : planetas) {
			pl.pintar();

			float d = PApplet.dist(pl.getPos().x, pl.getPos().y, p.getPos().x, p.getPos().y);

			// Atraer el personaje hacia el planeta que lo tenga dentro de su rango
			if (d <= pl.getRango() / 2) {
				PVector fuerza = pl.attract(p);
				pl.setColor(true);
				flecha(pl.getPos().copy(), pl.getVol(), d);
				if (fuerza.mag() > 2.13f || fuerza.mag() < -2.13f) {
					// p.setVel(0);
				} else {
					if (p.getCharnging() == false) {
						p.aplicarFuerza(fuerza);
					}
				}
			}else {
				pl.setColor(false);
			}

			// Aplicar fricciòn si el personaje està dentro del planeta
			if (d <= pl.getVol() / 2) {
				// System.out.println("aplicando friccion");
				PVector friccion = p.getVel().copy();
				friccion.normalize();
				float frictionConstant = -0.08f;
				friccion.mult(frictionConstant);
				p.aplicarFuerza(friccion);
			}

		}

		// Aplicar fricciòn si el personaje està en la meta
		if (p.getPos().x > 1100) {
			PVector agotamineto = p.getVel().copy();
			agotamineto.normalize();
			float agotaminetoForce = -0.008f;
			agotamineto.mult(agotaminetoForce);
			p.aplicarFuerza(agotamineto);
		}

		// dar la fuerza inicial de aceleracion al personaje
		if (app.mousePressed && p.getPos().x < 150) {
			p.aplicarFuerza(new PVector(0.01f, 0));
			p.setCharnging(true);
			barra.paint(0.1f);
			
			app.fill(255,255,255,90);
			app.textSize(160);
			app.textAlign(app.CENTER);
			app.text("acel\n" + (int) (p.getAcel().mag() * 100) + "", app.width/2, app.height/2 );
		}
		
		if(p.getPos().x < 150 && p.getCharnging() == false) {
			app.fill(255,255,255,50);
			app.textSize(60);
			app.textAlign(app.CENTER);
			app.text("manten presionado el click\npara acelerar", app.width/2, app.height/2 );
		}

		isGanar();

		if (p.getCharnging() == false)
			p.mover();
	}
	
	public void flecha(PVector este, float mas, float d) {
		PVector posUno = p.getPos().copy();
		
		PVector res = este.sub(posUno);
		res.normalize();
		res.mult(mas);
		res.limit(d);
		app.stroke(255,0,0,30);
		app.strokeWeight(4);
		app.line(p.getPos().x, p.getPos().y, p.getPos().x+res.x, p.getPos().y+res.y);
		app.ellipse(p.getPos().x+res.x, p.getPos().y+res.y, 10, 10);
	};

	private void isGanar() {
		if (p.getPos().x > 150 && p.getVel().mag() <= 0.004f) {
			if (p.getPos().x > 1100 && p.getPos().x < app.width) {
				gano = true;
				puntaje = calcular();
				nivelTerminado = true;
			} else {
				gano = false;
				puntaje = calcular();
				nivelTerminado = true;
			}
		}

		if (p.getPos().x > app.width + 90 || p.getPos().y > app.height + 90 || p.getPos().y < -90) {
			gano = false;
			nivelTerminado = true;
		}
	}

	private int calcular() {
		float val = Math.abs(1150 - p.getPos().x);
		if (val > 50)
			return 0;
		return (int) PApplet.map(val, 0, 50, 30, 20);
	}

	public void released() {
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

	public void reiniciar() {
		p = new Personaje(app);
		barra = new Barra();
		nivelTerminado = false;
		gano = false;
		puntaje = 0;
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
