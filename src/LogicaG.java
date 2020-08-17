import processing.core.PApplet;
import processing.core.PImage;

public class LogicaG {

	private PApplet app;
	private LogicaJDos log;
	private LogicaJUno logUno;
	private PImage p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18;
	private int pantalla, puntaje;
	private int x, y;

	public LogicaG(PApplet app) {
		this.app = app;
		log = new LogicaJDos(app);
		logUno = new LogicaJUno(app);
		p0 = app.loadImage("../hci2/data/0.png");
		p1 = app.loadImage("../hci2/data/1.png");
		p2 = app.loadImage("../hci2/data/2.png");
		p3 = app.loadImage("../hci2/data/3.png");
		p4 = app.loadImage("../hci2/data/4.png");
		p5 = app.loadImage("../hci2/data/5.png");
		p6 = app.loadImage("../hci2/data/6.png");
		p7 = app.loadImage("../hci2/data/7.png");
		p8 = app.loadImage("../hci2/data/8.png");
		p9 = app.loadImage("../hci2/data/9.png");
		p10 = app.loadImage("../hci2/data/10.png");
		p11 = app.loadImage("../hci2/data/11.png");
		p12 = app.loadImage("../hci2/data/12.png");
		p13 = app.loadImage("../hci2/data/13.png");
		p14 = app.loadImage("../hci2/data/14.png");
		p15 = app.loadImage("../hci2/data/15.png");
		p16 = app.loadImage("../hci2/data/16.png");
		p17 = app.loadImage("../hci2/data/ganaste.png");
		p18 = app.loadImage("../hci2/data/perdiste.png");
		pantalla = 0;
		puntaje = 0;
		x = 0;
		y = 0;

	}

	public void ejecutar() {

		// System.out.println("X"+app.mouseX);
		// System.out.println(app.mouseY);
		// log.ejecutar();
		// logUno.ejecutar();
		app.imageMode(app.CORNER);
		switch (pantalla) {

		case 0:
			app.image(p0, x, y);
			break;

		case 1:
			app.image(p1, 0, 0);
			app.fill(255);
			app.textSize(30);
			app.text(puntaje, 120, 125);
			break;

		case 2:
			app.image(p2, x, y);
			break;

		case 3:
			app.image(p3, x, y);
			break;

		case 4:
			app.image(p4, x, y);
			break;

		case 5:
			app.image(p5, x, y);
			break;

		case 6:
			app.image(p6, x, y);
			break;

		case 7:
			app.image(p7, x, y);
			break;

		case 8:
			app.image(p8, x, y);
			break;

		case 9:
			app.image(p9, x, y);
			break;

		case 10:
			app.image(p10, x, y);
			break;

		case 11:
			app.image(p11, x, y);
			break;

		case 12:
			app.image(p12, x, y);
			app.fill(255);
			app.textSize(30);
			app.text("su puntaje fué: "+puntaje, app.width/2 - 150, app.height-150);
			break;

		case 13:
			app.image(p13, x, y);
			app.fill(255);
			app.textSize(30);
			app.text("su puntaje fué: "+puntaje, app.width/2 - 150, app.height-150);
			break;

		case 14:
			app.image(p14, x, y);
			break;

		case 15:
			app.image(p15, x, y);
			break;

		case 16:
			app.image(p16, x, y);
			break;

		case 17:
			app.image(p17, x, y);
			break;

		case 18:
			app.image(p18, x, y);
			break;

		case 19:
			// Juego uno
			logUno.ejecutar();
			if (logUno.isTermino()) {
				if (logUno.isGano()) {
					System.out.println("Ganò primer nivel");
					puntaje = logUno.getPuntaje();
					logUno.reiniciar();
					pantalla = 12;
				} else {
					System.out.println("perdió primer nivel");
					puntaje = logUno.getPuntaje();
					logUno.reiniciar();
					pantalla = 13;
				}
			}

			break;
		case 20:
			// Juego dos
			log.ejecutar();
			if (log.isTermino()) {
				if (log.isGano()) {
					System.out.println("Ganò");
					puntaje = log.getPuntaje();
					log.reiniciar();
					pantalla = 12;
				} else {
					System.out.println("perdió");
					puntaje = log.getPuntaje();
					log.reiniciar();
					pantalla = 13;
				}
				
			}
		}
	}

	public void cambioPantalla() {

		switch (pantalla) {
		case 0:
			// Pantalla introduccion
			if (app.mouseX > 762 && app.mouseX < 1038 && app.mouseY > 543 && app.mouseY < 611) {
				System.out.println("click");
				pantalla = 1;
				return;
			}
			break;

		case 1:
			// Pantalla del mapa general
			// Voy a la pantalla de distancia de parada
			if (app.mouseX > 226 && app.mouseX < 382 && app.mouseY > 354 && app.mouseY < 479) {
				pantalla = 2;
				return;
			}
			// Voy a la pantalla de atracion gravitacional
			if (app.mouseX > 945 && app.mouseX < 1102 && app.mouseY > 229 && app.mouseY < 380) {
				pantalla = 8;
				return;
			}
			break;

		case 2:
			// Pantalla distancia de parada
			// Volver al home
			if (app.mouseX > 821 && app.mouseX < 885 && app.mouseY > 89 && app.mouseY < 148) {
				pantalla = 1;
				return;
			}
			// Volver atras
			if (app.mouseX > 936 && app.mouseX < 997 && app.mouseY > 86 && app.mouseY < 90) {
				pantalla = 1;
				return;
			}
			// Ir al siguiente
			if (app.mouseX > 1034 && app.mouseX < 1098 && app.mouseY > 88 && app.mouseY < 147) {
				pantalla = 3;
				return;
			}
			break;
			
		case 12:
			if (app.mouseX > 470 && app.mouseX < 730 && app.mouseY > 570 && app.mouseY < 630) {
				pantalla = 1;
				return;
			}
			break;
			
		case 13:
			if (app.mouseX > 609 && app.mouseX < 880 && app.mouseY > 600 && app.mouseY < 660) {
				pantalla = 1;
				return;
			}
			break;
		}

		// pantalla atraccion gravitacional
		if (pantalla == 8) {
			// Volver al home
			if (app.mouseX > 822 && app.mouseX < 833 && app.mouseY > 91 && app.mouseY < 145) {
				pantalla = 1;
				return;
			}
			// Volver atras
			if (app.mouseX > 932 && app.mouseX < 999 && app.mouseY > 90 && app.mouseY < 147) {
				pantalla = 1;
				return;
			}
			// Ir al siguiente
			if (app.mouseX > 1033 && app.mouseX < 1098 && app.mouseY > 92 && app.mouseY < 148) {
				pantalla = 15;
				return;
			}
		}

		// Pantalla distancia
		if (pantalla == 3) {
			// Volver al home
			if (app.mouseX > 824 && app.mouseX < 855 && app.mouseY > 91 && app.mouseY < 147) {
				pantalla = 1;
				return;
			}
			// Volver a la pantalla anterior(distancia de parada)
			if (app.mouseX > 934 && app.mouseX < 1000 && app.mouseY > 90 && app.mouseY < 147) {
				pantalla = 2;
				return;
			}
			// Ir a la pantalla siguiente(velocidad)
			if (app.mouseX > 1036 && app.mouseX < 1100 && app.mouseY > 89 && app.mouseY < 146) {
				pantalla = 4;
				return;
			}
		}
		// Pantalla velocidad
		if (pantalla == 4) {
			// Volver al home
			if (app.mouseX > 825 && app.mouseX < 884 && app.mouseY > 91 && app.mouseY < 148) {
				pantalla = 1;
				return;
			}
			// Volver a la pantalla anterior(distancia)
			if (app.mouseX > 936 && app.mouseX < 995 && app.mouseY > 91 && app.mouseY < 148) {
				pantalla = 3;
				return;
			}
			// Ir a la pantalla siguiente(friccion)
			if (app.mouseX > 1035 && app.mouseX < 1099 && app.mouseY > 94 && app.mouseY < 147) {
				pantalla = 10;
				return;
			}
		}
		// Pantalla friccion
		if (pantalla == 10) {
			// Volver al home
			if (app.mouseX > 824 && app.mouseX < 886 && app.mouseY > 92 && app.mouseY < 147) {
				pantalla = 1;
				return;
			}
			// Volver a la pantalla anterior(velocidad)
			if (app.mouseX > 934 && app.mouseX < 999 && app.mouseY > 91 && app.mouseY < 148) {
				pantalla = 4;
				return;
			}
			// Ir a la pantalla siguiente(coeficiente de rozamiento)
			if (app.mouseX > 1036 && app.mouseX < 1100 && app.mouseY > 91 && app.mouseY < 148) {
				pantalla = 5;
				return;
			}
		}
		// Pantalla coeficiente de friccion
		if (pantalla == 5) {
			// Volver al home
			if (app.mouseX > 825 && app.mouseX < 887 && app.mouseY > 92 && app.mouseY < 148) {
				pantalla = 1;
				return;
			}
			// Volver a la pantalla anterior(coeficiente de friccion)
			if (app.mouseX > 934 && app.mouseX < 999 && app.mouseY > 92 && app.mouseY < 148) {
				pantalla = 10;
				return;
			}
			// Ir a la pantalla siguiente(gravedad)
			if (app.mouseX > 1035 && app.mouseX < 1101 && app.mouseY > 90 && app.mouseY < 146) {
				pantalla = 6;
				return;
			}
		}
		// Pantalla gravedad
		if (pantalla == 6) {
			// Volver al home
			if (app.mouseX > 822 && app.mouseX < 888 && app.mouseY > 88 && app.mouseY < 146) {
				pantalla = 1;
				return;
			}
			// Volver a la pantalla anterior(gravedad)
			if (app.mouseX > 937 && app.mouseX < 1000 && app.mouseY > 91 && app.mouseY < 144) {
				pantalla = 5;
				return;
			}
			// Ir a la pantalla siguiente(intro juego)
			if (app.mouseX > 1036 && app.mouseX < 1098 && app.mouseY > 92 && app.mouseY < 147) {
				pantalla = 7;
				return;
			}
		}
		// Pantalla intro juego
		if (pantalla == 7) {
			// Volver al home
			if (app.mouseX > 822 && app.mouseX < 888 && app.mouseY > 88 && app.mouseY < 146) {
				pantalla = 1;
				return;
			}
			// Volver a la pantalla anterior(gravedad)
			if (app.mouseX > 937 && app.mouseX < 1000 && app.mouseY > 91 && app.mouseY < 144) {
				pantalla = 6;
				return;
			}
			// Ir a al juego de distanciaa
			if (app.mouseX > 774 && app.mouseX < 1058 && app.mouseY > 539 && app.mouseY < 611) {
				pantalla = 19;
				return;
			}

		}

		// Pantalla gravedad atraccion
		if (pantalla == 15) {
			// Volver al home
			if (app.mouseX > 822 && app.mouseX < 881 && app.mouseY > 93 && app.mouseY < 148) {
				pantalla = 1;
				return;
			}
			// Volver a la pantalla anterior(gravedad)
			if (app.mouseX > 932 && app.mouseX < 997 && app.mouseY > 90 && app.mouseY < 151) {
				pantalla = 8;
				return;
			}
			// Ir a la pantalla siguiente(masa)
			if (app.mouseX > 1035 && app.mouseX < 1095 && app.mouseY > 93 && app.mouseY < 150) {
				pantalla = 9;
				return;
			}
		}

		// Pantalla masa
		if (pantalla == 9) {
			// Volver al home
			if (app.mouseX > 824 && app.mouseX < 883 && app.mouseY > 90 && app.mouseY < 148) {
				pantalla = 1;
				return;
			}
			// Volver a la pantalla anterior(gravedad)
			if (app.mouseX > 934 && app.mouseX < 999 && app.mouseY > 90 && app.mouseY < 150) {
				pantalla = 15;
				return;
			}
			// Ir a la pantalla siguiente(distancia)
			if (app.mouseX > 1034 && app.mouseX < 1096 && app.mouseY > 92 && app.mouseY < 151) {
				pantalla = 16;
				return;
			}
		}
		// Pantalla distancia atraccion
		if (pantalla == 16) {
			// Volver al home
			if (app.mouseX > 819 && app.mouseX < 886 && app.mouseY > 91 && app.mouseY < 149) {
				pantalla = 1;
				return;
			}
			// Volver a la pantalla anterior(distancia)
			if (app.mouseX > 928 && app.mouseX < 996 && app.mouseY > 92 && app.mouseY < 148) {
				pantalla = 9;
				return;
			}
			// Ir a la pantalla siguiente(juego)
			if (app.mouseX > 1035 && app.mouseX < 1096 && app.mouseY > 94 && app.mouseY < 149) {
				pantalla = 11;
				return;
			}
		}
		// Pantalla intro juegodos
		if (pantalla == 11) {
			// Volver al home
			if (app.mouseX > 824 && app.mouseX < 885 && app.mouseY > 92 && app.mouseY < 149) {
				pantalla = 1;
				return;
			}
			// Volver a la pantalla anterior(distancia)
			if (app.mouseX > 932 && app.mouseX < 999 && app.mouseY > 92 && app.mouseY < 147) {
				pantalla = 9;
				return;
			}
			// Ir a la pantalla siguiente(juego)
			//if (app.mouseX > 1781 && app.mouseX < 1059 && app.mouseY > 574 && app.mouseY < 642) {
				if (app.mouseX > 774 && app.mouseX < 1058 && app.mouseY > 539 && app.mouseY < 611) {
				System.out.println("dksjnfksdjfnskdjnfskdjnfksjd");
				pantalla = 20;
				return;
			}
		}
	}

	public void mouseSuelto() {
		if(pantalla==20)log.released();
		if(pantalla==19)logUno.released();
	}
}
