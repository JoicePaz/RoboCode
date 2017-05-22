package Uni;
import robocode.*;
import java.awt.Color;


/**
 * RoboUni - a robot by (Leonardo, Joice, Isadora, Larissa, Juliana)
 */
public class RoboUni extends Robot // o robô criado herda da classe Robot
{
	public void run() 
	{
	  //Definem as cores do robô	
      setBodyColor(Color.blue);
	  setGunColor(Color.black);
	  setRadarColor(Color.red);
	  setScanColor(Color.red);
	  setBulletColor(Color.white);
 
	  while(true)
		{	
	     turnRadarRight(360); //vira a 360 graus
	     ahead(100); //anda 100 unidades
	     turnGunRight(360); //vira o canhão 360
	     back(100);//volta 100 unidades
       	 
	  }
   }
   
   public void onScannedRobot(ScannedRobotEvent e) { //Detecta os outros robôs
      double max = 100; //define a potência em 100 inicialmente
	 	
	  //Faz um controle da energia que é gasta no que diz. 
	  //respeito à potência do tiro.
	  //getBearing entrega um valor em graus para a direçao que o robô precisa pra
	  	//andar até atingir um alvo.
		
      if(e.getEnergy() < max){
         max = e.getEnergy();
         miraCanhao(e.getBearing(), max, getEnergy());
      }else if(e.getEnergy() >= max){
         max = e.getEnergy();
         miraCanhao(e.getBearing(), max, getEnergy());
      }else if(getOthers() == 1){
         max = e.getEnergy();
         miraCanhao(e.getBearing(), max, getEnergy());
      }
    }
	

    //quando o seu robo colide com outro robo
    public void onHitRobot(HitRobotEvent e) {
		fire(getGunHeading());
	   	
    }
	
    //Quando seu robô leva um tiro
    public void onHitByBullet(HitByBulletEvent e) { 
		back(100);//volta 100 unidades
		turnRight(20);
	
    }
	
    //Fornece as coordenadas para o ajuste do canhão.
    public void miraCanhao(double PosIni, double energiaIni, double minhaEnergia) {
       double Distancia = PosIni;
	   double Coordenadas = getHeading() + PosIni - getGunHeading();
	   double PontoQuarenta = (energiaIni / 4) + .1;
	   
	   if (!(Coordenadas > -180 && Coordenadas <= 180)) {
	      while (Coordenadas <= -180) {
		     Coordenadas += 360;
		  }
		  while (Coordenadas > 180) {
		     Coordenadas -= 360;
		  }
	   }
	   turnGunRight(Coordenadas);
		
	   if (Distancia > 200 || minhaEnergia < 15 || energiaIni > minhaEnergia){
          fire(1);
       } else if (Distancia > 50 ) {
          fire(2);
       } else {
          fire(PontoQuarenta);
       }
   }
   
   //É chamado quando o robô bate na parede,
   public void onHitWall(HitWallEvent e) {
      turnLeft(90);
      ahead(200);
   }
   //Dança da vitória
   public void onWin(WinEvent e) {	
      turnRight(72000);
	  back(50);
	  ahead(50);
   }
   
   public void tiroFatal(double PosIni, double energiaIni, double minhaEnergia) {
	  double Coordenadas = getHeading() + PosIni - getGunHeading();
	  double PontoQuarenta = (energiaIni / 4) + .1;
	  if (!(Coordenadas > -180 && Coordenadas <= 180)) {
	     while (Coordenadas <= -180) {
	        Coordenadas += 360;
		 }
		 while (Coordenadas > 180) {
	        Coordenadas -= 360;
	     }
	  }
	  turnGunRight(Coordenadas);
	  fire(PontoQuarenta);
       
   }
		
}
