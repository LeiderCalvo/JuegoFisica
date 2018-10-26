import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class LogicaJDos {
	

	private Main app;
	private Personaje p;
	private ArrayList<Planetas> planetas;

	private PImage fondo;
	private Barra barra;
	private boolean nivelTerminado, gano;
	private int puntaje;

	public LogicaJDos(Main app) {
		this.app = app;

		p = new Personaje(app);
		planetas = new ArrayList<Planetas>();
		barra = new Barra();
		
		fondo = app.loadImage("/data/completo.png");
		nivelTerminado = false;
		gano = false;
		puntaje = 0;
		
		//iniciar la cantidad de planetas necesarios
		for (int i = 0; i < 15; i++) {
			float y = 0;

			if (i % 2 == 0) {
				y = app.random(70, app.height / 4);
			} else {
				y = (app.random((app.height / 4) * 3, app.height - 70));
			}
			planetas.add(new Planetas(app, i * (app.width / 30) + 190, y));
		}
	}
	
	public void pintarMeta() {
		app.imageMode(app.CORNER);
		app.image(fondo, 0, 0);
		app.imageMode(app.CENTER);
		app.fill(200,200,200,40);
		app.rect(700, 0, app.width, app.height);
		app.stroke(255, 0, 0);
		app.strokeWeight(2);
		app.line(750, 0, 750, app.height);
		app.noStroke();
		app.textSize(15);
		app.textLeading(13);
	}

	public void ejecutar() {
		pintarMeta();
		
		p.dibujar();
		app.fill(0,255,0);
		app.text("vel\n" + (float) (p.getVel().mag() * 100) + "", 50, 20);
		app.text("acel\n" + (float) (p.getAcel().mag() * 100) + "", p.getPos().x-20, p.getPos().y+50);
		app.text("masa\n" + p.getMasa() + "", 200, 20);

		for (Planetas pl : planetas) {
			pl.pintar();

			float d = PApplet.dist(pl.getPos().x, pl.getPos().y, p.getPos().x, p.getPos().y);
			
			//Atraer el personaje hacia el planeta que lo tenga dentro de su rango
			if (d <= pl.getRango() / 2) {
				PVector fuerza = pl.attract(p);
				if (fuerza.mag() > 2.13f || fuerza.mag() < -2.13f) {
					// p.setVel(0);
				} else {
					if (p.getCharnging() == false)
						p.aplicarFuerza(fuerza);
				}
			}

			//Aplicar fricciòn si el personaje està dentro del planeta
			if (d <= pl.getVol() / 2) {
				// System.out.println("aplicando friccion");
				PVector friccion = p.getVel().copy();
				friccion.normalize();
				float frictionConstant = -0.08f;
				friccion.mult(frictionConstant);
				p.aplicarFuerza(friccion);
			}

		}
		
		//Aplicar fricciòn si el personaje està en la meta
		if (p.getPos().x > 600) {
			PVector agotamineto = p.getVel().copy();
			agotamineto.normalize();
			float agotaminetoForce = -0.008f;
			agotamineto.mult(agotaminetoForce);
			p.aplicarFuerza(agotamineto);
		}
		
		//dar la fuerza inicial de aceleracion al personaje
		if (app.mousePressed && p.getPos().x < 150) {
			p.aplicarFuerza(new PVector(0.01f, 0));
			p.setCharnging(true);
			barra.paint(0.1f);
		}

		isGanar();

		if (p.getCharnging() == false)
			p.mover();
	}

	private void isGanar() {
		if (p.getPos().x > 150 && p.getVel().mag() <= 0.004f) {
			if (p.getPos().x > 700 && p.getPos().x < app.width) {
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
		float val = Math.abs(750 - p.getPos().x);
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
