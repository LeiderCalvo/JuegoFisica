import processing.core.PApplet;

public class Main extends PApplet {

	private LogicaJDos log;
	private LogicaJUno logUno;
	private int pantalla, puntaje;
	
	public static void main(String[] args) {
		PApplet.main("Main");
	}

	@Override
	public void setup() {
		log = new LogicaJDos(this);
		logUno = new LogicaJUno(this);
		pantalla=0;
		puntaje=0;
	}

	@Override
	public void settings() {
		size(800, 500);
	}

	@Override
	public void draw() {
		background(255);
		noStroke();
		smooth();
		ellipseMode(CENTER);
		textSize(20);
		
		switch (pantalla) {
		case 0:
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

		case 5:
			fill(0);
			text(puntaje,400,250);
			text("veeeeee Ganaste",200,90);
			break;
			
		case 6:
			fill(0);
			text(puntaje,400,250);
			text("veeeeee perdiste",200,90);
			break;
		}
	}

	@Override
	public void mousePressed() {
	}
	
	@Override
	public void mouseReleased() {
		log.released();
	}
	
	

}
