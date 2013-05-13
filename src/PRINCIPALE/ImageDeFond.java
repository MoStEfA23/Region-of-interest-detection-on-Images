package PRINCIPALE;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class ImageDeFond extends JPanel implements MouseListener  {
 

   private Image img;
  static int teste=0;

    public ImageDeFond(Image img,String titre) {
      this.img = img;
      this.addMouseListener(this);
      //chaque iteration un nom
      if(titre.equals("Courbes Zipf")){teste=0; System.out.println("0");}//pour que le nom des fenetre retse entre 0 et 1 et l'affiche de zoom sera reglo
      if (titre.equals("Courbes Zipf inverse")){teste=1;System.out.println("1");}
      String test=String.valueOf(teste);

      this.setName(test);
      
      setSize(1230,652);
  } 
    
    public int getNumeroImagette(int x,int y){
		int position=0,Px=0,Py=0;
		Px=(12*x)/1211;
		Py=(12*y)/611;
		position=(Py*12)+Px;
		
		
	
		return position;
	}
    
    @Override
    public void mousePressed(MouseEvent ev) {
  	  Vector<Double> grapheDetaille[] = new Vector[144];
  	  String Xaxis=null,Yaxis=null,titre=null;
     // TODO Auto-generated method stub
  	  FenetreZoom grapheDetaillé=new FenetreZoom("Courbe détaillé de l'imagette",610,340); 
  	  ClasseMenu m=new ClasseMenu();
  	  
//  	  System.out.println("get="+this.getName());
  	  int X=ev.getX(),Y=ev.getY();
  	  if(this.getName().equals("0")){
  		  grapheDetaille= m.getXYZipfImagettes();
  		  titre="Courbe de Zipf détaille pour une imagette";
  		  Xaxis="log(rang)";
  		Yaxis="log(Fréquance)";
      }
  	  else{grapheDetaille= m.getXYZipfinverseImagettes();
  	titre="Courbe de Zipfinvers détaille pour une imagette";
  	Xaxis="log(Fréquence)";
		Yaxis="log(Nombre de motifs)";

  	  }
  	  
  	  
        grapheDetaillé.DrawGraphe(grapheDetaille[this.getNumeroImagette(X,Y)], titre,Xaxis,Yaxis);
    }
    
    public void paintComponent(Graphics g) {
      g.drawImage(img, 0, 0, null);
    }

  @Override
  public void mouseClicked(MouseEvent arg0) {
   // TODO Auto-generated method stub
   
  }

  @Override
  public void mouseEntered(MouseEvent ev) {
   // TODO Auto-generated method stub
   
  }

  @Override
  public void mouseExited(MouseEvent arg0) {
   // TODO Auto-generated method stub
   
  }

   @Override
  public void mouseReleased(MouseEvent arg0) {
   // TODO Auto-generated method stub
   
  }}

