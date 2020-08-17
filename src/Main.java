import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

	private PApplet app;
	//private LogicaJDos log;
	//private LogicaJUno logUno;
	private LogicaG logic;
	private PImage inicio;
	private boolean esta;
	//private int pantalla, puntaje;
	//private ArrayList<PImage> pantallas;
	//private PImage p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14;
	//private int x,y;
	
	public static void main(String[] args) {
		PApplet.main("Main");
	}

	@Override
	public void settings() {
		size(1200, 700);
	}
	
	@Override
	public void setup() {
		
		app = new PApplet();
		logic = new LogicaG(this);

		inicio = loadImage("../data/89.png");
		esta = false;
		//log = new LogicaJDos(this);
		//logUno = new LogicaJUno(this);
		//pantalla=0;
		//puntaje=0;
		//y=0;
		
		//p1 = app.loadImage("../hci2/data/0.png");
		/*pantallas = new ArrayList<PImage>();
		
		for(int i=0; i<14; i++) {
			pantallas.add(app.loadImage("../hci2/data/"+i+".png"));
		}*/
	}

	

	@Override
	public void draw() {
		background(255);
		noStroke();
		smooth();
		ellipseMode(CENTER);
		textSize(20);
		
		if(esta) {
			logic.ejecutar();
		}else {
			image(inicio, 0, 0);
		}
		//app.image(p1,x, y);
		//app.image(pantallas.get(pantalla), x, y);
		
		/*switch (pantalla) {
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
		}*/
	}

	@Override
	public void mousePressed() {
		if(!esta)esta = true;
		if(esta)logic.cambioPantalla();
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
		//log.released();
		//logUno.released();
		if(esta)logic.mouseSuelto();
	}
	
	

}
