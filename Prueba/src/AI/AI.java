package AI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import interfaz.VentanaPrincipal;
import modelo.Ball;
import modelo.Personaje;

public class AI implements Runnable{

	private VentanaPrincipal vp;
	private Personaje AI1;
	private Ball targetBall;
	Rectangle AI, target;
	int xDirection,yDirection;
	
	boolean resting = false;
	boolean shouldSetRandDir=true;
	
	Image octorog;
	
	public AI(Personaje AI1, Ball targetBall, VentanaPrincipal vp)
	{
		
		this.vp=vp;
		this.AI1=AI1;
		this.targetBall=targetBall;
		
	}
	
//	public void draw(Graphics g)
//	{
//		g.setColor(Color.blue);
//		if(AI!=null)
//		{
//			g.fillRect(AI.x, AI.y, AI.width, AI.height);
//		}
//	}
	
	//CHANGES RANDOM DIRECTION
	
	
	public int chooseRandomDirection()
	{
		Random r = new Random();
		int[] randomDirection = new int[3];
		randomDirection[0]=0;
		randomDirection[1]=1;
		randomDirection[2]=-1;
		int randChoice = r.nextInt(3);
		return randomDirection[randChoice];
	}
	
	
	//find a path to that dirtection
	
	public void findPathToTarget()
	{
		System.out.println("llega al target");
		System.out.println("la x del personaje es "+AI1.getPosicionX());
		System.out.println("la x de la bola es "+targetBall.getPosicionX());

		if(AI1.getPosicionX()<targetBall.getPosicionX())
		{
			System.out.println("deberia moverme a la dereha");
			//vp.moverJugador(2);
			setXDirection(1);
		}
		if(AI1.getPosicionX()>targetBall.getPosicionX())
		{
			System.out.println("deberia moverme a la izquierda");
			//vp.moverJugador(1);
			setXDirection(1);
		}
		if(AI1.getPosicionY()<targetBall.getPosicionY())
		{
			//vp.moverJugador(3);
			setYDirection(1);
		}
		if(AI1.getPosicionY()>targetBall.getPosicionY())
		{
			//vp.moverJugador(4);
			setYDirection(-1);
		}
	}
	
	//MOVE IN THAT DIRECTION
	
	public void detectEdges()
	{
		System.out.println("los edges");
		if(AI1.getPosicionX()<=0)
		{
			//vp.moverJugador(2);
			setXDirection(1);
		}
		if (AI1.getPosicionX()<=600-AI1.ANCHO) {
			//vp.moverJugador(1);
			setXDirection(-1);
		}
		if(AI1.getPosicionY()<=25)
		{
			//vp.moverJugador(3);
			setYDirection(1);
		}
		if (AI1.getPosicionY()>400-AI1.ALTO) {
			//vp.moverJugador(4);
			setYDirection(-1);
		}
	}
	
	public void setXDirection(int dir)
	{
		xDirection = dir;
	}
	
	public void setYDirection(int dir)
	{
		yDirection = dir;
	}
	
	public void move()
	{
		if(xDirection==1)
		{
			vp.moverJugador(2);
		}
		if(xDirection==-1)
		{
			vp.moverJugador(1);
		}
		if(yDirection==1)
		{
			vp.moverJugador(3);
		}
		if(xDirection==-1)
		{
			vp.moverJugador(4);
		}
		AI.x+=xDirection;
		AI.y+=yDirection;
	}
	
	//IN RUN METHOD, MOVE IN THAT DIRECTION AND THEN WAIT
	
	
	
	@Override
	public void run() {
		try {
			System.out.println(" y este es un hilo");
			while(true)
			{
				
				if (!resting) {
//					if(shouldSetRandDir)
//					{
//						setXDirection(chooseRandomDirection());
//						setYDirection(chooseRandomDirection());
//						shouldSetRandDir=false;
//					}
					long start = System.currentTimeMillis();
					long end = start + 60000;
					
					while(System.currentTimeMillis()<end)
					{
						System.out.println("llegamos a la parte interna");
						findPathToTarget();
						move();
						detectEdges();
						Thread.sleep(6);
					}
					resting = true;
				} 
				else {
					Thread.sleep(2000);
					//shouldSetRandDir=true;
					resting = false;
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		
	}

}
