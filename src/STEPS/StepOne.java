package STEPS;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ByteLookupTable;
import java.awt.image.LookupOp;
import java.awt.image.PixelGrabber;

import PRINCIPALE.FenetreFinal;


public class StepOne {
	int larg, haut;
	int Dl=0,Dh=0,Dle=0,Dhe=0;
	int i=0,difference=0,difference2=0;
	int t = 0;int tt=0;
	BufferedImage [] subImages; 
	int [] pixelsCourant;
	
	

	 public BufferedImage imageEnCouleur(BufferedImage picture)
	 {
	  BufferedImage imageGris = new BufferedImage(picture.getWidth(), picture.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
	  Graphics2D surfaceImg = imageGris.createGraphics();
	  surfaceImg.drawImage(picture, null, null);       
	  picture = imageGris;
	  return picture;
	 }
	
	
	/////////////////////////////////*
	/************Methode0********/
	 public BufferedImage imageEnNiveauGris(BufferedImage picture)
	 {
	  BufferedImage imageGris = new BufferedImage(picture.getWidth(), picture.getHeight(), BufferedImage.TYPE_USHORT_GRAY);
	  Graphics2D surfaceImg = imageGris.createGraphics();
	  surfaceImg.drawImage(picture, null, null);       
	  picture = imageGris;
	  return picture;
	 }
	
	
	/*******Methode1*************/
	 public void HLofimage(BufferedImage picture){
		subImages=new BufferedImage [144];// lazzam manensach n'itilialisi la taille ta3 tableau sinn pointeur 0 
		larg=(picture.getWidth());// la largeur de toute l'image
		haut=(picture.getHeight());// la hauteur de toute l'image
	}
	
	
	////*************Methode2***************/
	
	public void HLofSubimage(){
		Dl=(int) (Math.round((double)larg/12));// il faut faire (double) sinon come résultat il donne 32.0 a la place de 32.91 ex
		Dh=(int) (Math.round((double)haut/12));
	}
	////************Methode3****************/
	
	public void differnceHL(){
		Dle=larg-(Dl*11);
		difference=Dle-Dl;//????
		/*********************************/
		Dhe=haut-(Dh*11);
		difference2=Dhe-Dh;//????
	}
	////*************Methode4****************/
	
	public BufferedImage [] créateSubimages(BufferedImage picture){
		
		int y=0,x=0,i=0;
		
		while(y<haut){
			x=0;
			while(x<larg){
				if(((i+1)%12)==0){
					if(i==143){ subImages[i]=picture.getSubimage(x, y, Dle, Dhe);x=x+Dle;y=y+Dhe;}
					else {subImages[i]=picture.getSubimage(x, y, Dle, Dh);i++;x=x+Dle;y=y+Dh;}
				}
				else if(i>=132){
					subImages[i]=picture.getSubimage(x, y, Dl, Dhe);i++;x=x+Dl;
				}
				
				else {subImages[i]=picture.getSubimage(x, y, Dl, Dh);i++;x=x+Dl;}
				
			}
		}

		return subImages;
	}
	///////********Methode5***************/
	public void tt(){
		
		 if(Dl<Dh){
			tt=((Dh-Dl)*11);
			tt=(((Dh+1)*Dl*12))+tt; //je multipler la differnce*Dh parceque la differnce ce repete dans chaque ligne 
		}
		else if(Dh<Dl){
			tt=-((Dl-Dh)*11);
			tt=(((Dh+1)*Dl*12))+tt;
		}
		else {
			tt=(((Dh+1)*Dl*12));
		}
		
	}
	///////***********Methode6*************/
	
	
	public int[] créationImageafficher(){
		
		/********************/
		 haut=haut+11;
			larg=larg+11;
			pixelsCourant=new int[larg*haut];//initialisation du tableau
			PixelGrabber pg = null;
		/**************/
		for (int k =0; k < 144;k++)
		{
			if(((k+1)%12)==0){
				if(k==143){pg = new PixelGrabber(subImages[k],0,0,Dle,Dhe,pixelsCourant,t,larg);}
				else {pg = new PixelGrabber(subImages[k],0,0,Dle,Dh,pixelsCourant,t,larg);}
			}
			else if(k>=132){
				pg = new PixelGrabber(subImages[k],0,0,Dl,Dhe,pixelsCourant,t,larg);
			}
			
			else {pg = new PixelGrabber(subImages[k],0,0,Dl,Dh,pixelsCourant,t,larg);}
			
			if(((k+1)%12)==0){t=t+tt+(difference*Dh)+difference;//+((difference*Dh)+difference)
			//System.out.println("t="+t);
			}
			else{t = t + (Dl+1);}//t c elle qui nous aide que les imagette soi en ordre dans pixelsCourant
			
			 try{ pg.grabPixels();}
				catch (InterruptedException e){ System.out.println(e.getMessage());}
			 
			
		}


		return pixelsCourant;
		
	}
	///************Methode7&8******************/

	public int getLarg() {
		return larg;
	}

	public int getHaut() {
		return haut;
	}
	/*******************************************/


	public int getDl() {
		//System.out.println("Dl="+Dl+" Dh="+Dh);
		return Dl;
	}
	public int getDh() {
		//System.out.println("Dl="+Dl+" Dh="+Dh);
		return Dh;
	}


	public int getDle() {
		return Dle;
	}


	public int getDhe() {
		return Dhe;
	}
	
	
	
	/********************Methode3***************************/
	public int[] créationImageFinal(BufferedImage [] Imagettes,String [] classe){
		
		//	this.imageEnNiveauGris(picture);
			//this.HLofimage(picture);
			 //this.HLofSubimage();
			 //this.differnceHL();
			 //this.créateSubimages(picture);
			// this.tt();
		
			/********************/
			 haut=haut+11;
				larg=larg+11;
				pixelsCourant=new int[larg*haut];//initialisation du tableau
				PixelGrabber pg = null;
			/**************/
			for (int k =0; k < 144;k++)
			{
				if(classe[k].equals("complexe")){
					Imagettes[k]=this.imageBinaire(Imagettes[k]);
				}

				if(((k+1)%12)==0){
					if(k==143){pg = new PixelGrabber(Imagettes[k],0,0,Dle,Dhe,pixelsCourant,t,larg);}
					else {pg = new PixelGrabber(Imagettes[k],0,0,Dle,Dh,pixelsCourant,t,larg);}
				}
				else if(k>=132){
					pg = new PixelGrabber(Imagettes[k],0,0,Dl,Dhe,pixelsCourant,t,larg);
				}
				
				else {pg = new PixelGrabber(Imagettes[k],0,0,Dl,Dh,pixelsCourant,t,larg);}
				
				if(((k+1)%12)==0){t=t+tt+(difference*Dh)+difference;//+((difference*Dh)+difference)
				//System.out.println("t="+t);
				}
				else{t = t + (Dl+1);}//t c elle qui nous aide que les imagette soi en ordre dans pixelsCourant
				
				 try{ pg.grabPixels();}
					catch (InterruptedException e){ System.out.println(e.getMessage());}
				 
				
			}
			/*t=(subImages[11].getWidth());//j'ai fait ça pour teste si vraiment la taille des dernier imagette different
			System.out.println("t="+t);
			t=(subImages[12].getWidth());
			System.out.println("t="+t);*/ 
		
		
			return pixelsCourant;
			
		}
	/*****************Methode2***************************/
	  private BufferedImage imageBinaire(BufferedImage source) {
      BufferedImage image = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
      byte[] normal = new byte[256];
      byte[] zéro = new byte[256];
      for (int i=0; i<256; i++) {
         normal[i] = (byte)i;
         zéro[i] = 0;
      }
      byte[][] rouge = { normal, zéro, zéro};
      ByteLookupTable table = new ByteLookupTable(0, rouge);
      LookupOp inversion = new LookupOp(table, null);
      inversion.filter(source, image);
      return image;
   }

	/******************************************************/
	
	

}
