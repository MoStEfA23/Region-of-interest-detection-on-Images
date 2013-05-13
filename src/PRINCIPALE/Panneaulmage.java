package PRINCIPALE;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Panneaulmage extends JPanel{
	private Image image ; 
	private int larg,haut;
	int deb = 0 ;
public Panneaulmage (Image ima,int larg,int haut,int deb){

	
	image=ima;

	this.deb = deb;
	this.larg=larg;
	this.haut=haut;
	setVisible(true);
	}
public void paintComponent(Graphics g) {
	this.setLayout(null);
	
	setSize(larg,haut); 
	g.fillRect(0,0,larg,haut);
	g.drawImage(image,0,0,larg,haut,this);
	
	this.setBounds(deb,0,larg,haut);
	
//	this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(
//            10.0f, BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND, 
//            1.0f, new float[] {3.0f, 1.0f}, 6.0f
//        )));
	
	this.setBorder(BorderFactory.createLineBorder(Color.orange, 7));
	}


public void enregistrerImage(File fichierImage)
{
	String format ="JPG";
	BufferedImage image = getImagePanneau();
	try {
		ImageIO.write(image, format, fichierImage);
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public BufferedImage getImagePanneau()
{      // récupérer une image du panneau
	int width  = this.getWidth();
	int height = this.getHeight();
	BufferedImage image = new BufferedImage(width, height,  BufferedImage.TYPE_INT_RGB);
	Graphics2D g = image.createGraphics();

	this.paintAll(g);
	g.dispose();
	return image;
	
}


}