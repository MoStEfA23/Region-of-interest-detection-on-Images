package PRINCIPALE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.WritableRaster;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import STEPS.StepOne;

public class FenetreFinal extends JFrame {
	BufferedImage [] Imagettes; 
	int [] pixelsResultat;
	Image image;
	Panel arriere;
	Panneaulmage zoneimage;

	public int [] FenetreResultat(BufferedImage buffeurActuel,String [] classe)
	{
	
	
 Imagettes=new BufferedImage [144];	//ilitialisation de BufferedImage []
 
 StepOne créationImagette = new StepOne();
 

 créationImagette.HLofimage(buffeurActuel);//donne les dimession de l'image
 créationImagette.HLofSubimage();//donne les dimenssion des imagettes de melieux 
 créationImagette.differnceHL();//elle donne les dimenssion des imagettes des borne et la differnce entre leur dimenssion et la dimenssion des imagettes de melieux
 
 Imagettes=créationImagette.créateSubimages(buffeurActuel);//diviser l'image BufferedImage on imagettes BufferedImage[]
 
 
 
 créationImagette.tt();
 pixelsResultat=créationImagette.créationImageFinal(Imagettes,classe);
		

 return pixelsResultat;
	
	}
/*********************************************************************************/
	public void FenetreInitiale(BufferedImage Image, int W, int H ){

		setTitle("image originale");
		 setSize(getInsets().left+getInsets().right+Math.max(0,W+5)
				    ,getInsets().top+getInsets().bottom+Math.max(0,H+25));
		setResizable(false);
		setVisible(true);
	    setLocationRelativeTo(null);
	    arriere=new Panel();
	    arriere.setLayout(new BorderLayout());
		arriere.setBackground(Color.BLUE);
		getContentPane().add("Center",arriere);
	
		 image = Image;
		 arriere.removeAll();
		
			zoneimage=new Panneaulmage(image,W,H,0); //redimentionner l'image 
			//arriere.setBounds(0, 0, 100, 100);
			arriere.add("Center",zoneimage);
			
		} 
	
	
		
}
