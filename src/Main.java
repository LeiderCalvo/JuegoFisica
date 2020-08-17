import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

	private PApplet app;
	private LogicaJDos log;
	private LogicaJUno logUno;
	private int pantalla, puntaje;
	private ArrayList<PImage> pantallas;
	private int x,y;
	
	public static void main(String[] args) {
		PApplet.main("Main");
	}

	@Override
	public void setup() {
		log = new LogicaJDos(this);
		logUno = new LogicaJUno(this);
		pantalla=14;
		puntaje=0;
		app = new PApplet();
		x =0;
		y=0;
		pantallas = new ArrayList<PImage>();
		
		/*for(int i=0; i<15; i++) {
			pantallas.add(app.loadImage("../hci2/data/"+i+".png"));
		}*/
	}

	@Override
	public void settings() {
		size(1200, 700);
	}

	@Override
	public void draw() {
		background(255);
		noStroke();
		smooth();
		ellipseMode(CENTER);
		textSize(20);
		
		//app.image(pantallas.get(pantalla), x, y);
		
		switch (pantalla) {
		case 14:
			
			
			logUno.ejecutar();	
			if(logUno.isTermino()) {
				if(logUno.isGano()) {
					System.out.println("Ganò primer nivel");
					puntaje = logUno.getPuntaje();
					pantalla = 5;
				}else {
					System.out.println("perdió primer nivel");
					puntaje = logUno.getPuntaje();
					pantalla = 6;
				}
			}
			break;
			
		case 15:
			log.ejecutar();	
			if(log.isTermino()) {
				if(log.isGano()) {
					System.out.println("Ganò");
					puntaje = log.getPuntaje();
					pantalla = 5;
				}else {
					System.out.println("perdió");
					puntaje = log.getPuntaje();
					pantalla = 6;
				}
			}
			break;

		case 16:
			fill(0);
			text(puntaje,400,250);
			text("veeeeee Ganaste",200,90);
			break;
			
		case 17:
			fill(0);
			text(puntaje,400,250);
			text("veeeeee perdiste",200,90);
			break;
		}
	}

	@Override
	public void mousePressed() {
		/*switch(pantalla) {
	    case 0:
	    	if(app.mouseX >  && app.mouseX< && app.mouseY> && app.mouseY<) {
	    		pantalla++;
	    	}
		break;
		
	    case 1:
	    	if(app.mouseX >  && app.mouseX< && app.mouseY> && app.mouseY<) {
	    		pantalla++;
	    	}
	    	break;
	    	
	    case 2:
	    	if(app.mouseX >  && app.mouseX< && app.mouseY> && app.mouseY<) {
	    		pantalla++;
	    	}
	    	break;
	    	
	    case 3:
	    	if(app.mouseX >  && app.mouseX< && app.mouseY> && app.mouseY<) {
	    		pantalla++;
	    	}
	    	break;
	    case 4:
	    	if(app.mouseX >  && app.mouseX< && app.mouseY> && app.mouseY<) {
	    		pantalla++;
	    	}
	    	break;
	   }*/
	}
	
	@Override
	public void mouseReleased() {
		log.released();
		logUno.released();
	}
	
	

}
