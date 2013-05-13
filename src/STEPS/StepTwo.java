package STEPS;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;


public class StepTwo {
	StepOne créationImagette = new StepOne();
	int dl=0;
	int dh=0;
	
	int [] pixelImagette;
	
	/*************Methode**********/
	public void calculdldh(BufferedImage photo,int indice){
		créationImagette.HLofimage(photo);///
		créationImagette.HLofSubimage();///en a fait ces trois pour avoire Dl,Dh,Dle,Dhe
		créationImagette.differnceHL();///
		
		if((((indice+1)%12)==0)&&((indice+1)!=144)){
			 dl=créationImagette.getDle();
			 dh=créationImagette.getDh();
			// System.out.println("dle&dh");
		}
		else if ((indice>=132)&&((indice+1)!=144)){
			 dl=créationImagette.getDl();
			 dh=créationImagette.getDhe();
			// System.out.println("dl&dhe");
		}
		else if ((indice+1)==144){
			 dl=créationImagette.getDle();
			 dh=créationImagette.getDhe();
			// System.out.println("dle&dhe");
		}
		else{
			 dl=créationImagette.getDl();
			 dh=créationImagette.getDh();
			// System.out.println("dl&dh");
		}
				 
		// System.out.println("dl="+dl+"dh"+dh);

		
	}
	
	/************Methode********************/
	public int [] grabberImagette(BufferedImage [] img, int indice){
		
		pixelImagette=new int[dl*dh];//initialisation du tableau
		PixelGrabber pg = null;
		pg = new PixelGrabber(img [indice],0,0,dl,dh,pixelImagette,0,dl);
		try{ pg.grabPixels();}
		catch (InterruptedException e){ System.out.println(e.getMessage());}
		//System.out.println("pixelImagette[0]="+pixelImagette[0]);
		
	return pixelImagette;
	}
	/*************Method2**********/
	public int intensiteImagette(int valeurOfpixel) {
		// TODO Auto-generated method stub
		int intensite=((valeurOfpixel >> 16) & 0xff);//Pour permettre la mise en niveau de gris de l'image
//		System.out.println("intensité="+intensite);
		return intensite;
		
	}
	/************Method3**********/
	public void intensiteImagette(int [] imageGrabber){
		
		for(int E=0;E<imageGrabber.length;E++){
			imageGrabber[E]=this.intensiteImagette(imageGrabber[E]);
			
		}
		
	}
	/************Method4**********/
	
	public void codage9Class(int [] imageIntensite){
		
		
		for(int P=0;P<imageIntensite.length;P++){
			imageIntensite[P]=(9*imageIntensite[P])/255;//C(x,y)=(9*J(x,y))/255
			if(imageIntensite[P]==9){imageIntensite[P]=8;}
			
		}
		
	}
	/***********Methode**************/

	public int getdl() {
		return dl;
	}

	public int getdh() {
		return dh;
	}
	/***************************************************************************************************************/
	/********************************Méthodes de codage des rangs géneraux******************************************/
	/****************************************************/
	 public void codageRangGeneraux(int w,int h ,int [] imageIntensite ){
		 int i=1;
		 int j=-2;
		 int[] masque=new int[9];
		 int nbrMasques=(w/3)*(h/3);
		// System.out.println("nbrMasques="+nbrMasques);
		 for(int y=1;y<=nbrMasques;y++){
			 
			 if((j+3)>=(w-1)){
				i=i+3;
				j=1; 
			 }
			 else{j=j+3;}
			 //System.out.println("i="+i+" j="+j);
			 masque=this.masqueIntenesite(i, j, w, imageIntensite);
			 masque=this.rangGenerauxMasque(masque);
			 this.putMasque(masque, imageIntensite, i, j, w);
			 
		 }
		 
		 if((w%3)!=0){this.edgeRightZero(w, h, imageIntensite);}
		 if((h%3)!=0){this.edgeDownZero(w, h, imageIntensite);}
		 
	 } 
	 /******************************************************/
	 public int [] masqueIntenesite(int i,int j,int w,int [] imageIntensite){
		 
		 int[] masque=new int[9];
		 int i1=i-1;int i2=i+1;
		 int j1=j-1;int j2=j+1;
		 
		 masque[0]=imageIntensite[i1*w+j1];masque[1]=imageIntensite[i1*w+j];masque[2]=imageIntensite[i1*w+j2];
		 masque[3]=imageIntensite[i*w+j1]; masque[4]=imageIntensite[i*w+j]; masque[5]=imageIntensite[i*w+j2];
		 masque[6]=imageIntensite[i2*w+j1];masque[7]=imageIntensite[i2*w+j];masque[8]=imageIntensite[i2*w+j2];
		return masque;
	 }
	 
	 
	 /**********************************************************/
	 public int[] rangGenerauxMasque(int [] masqueIntenesite){
		 /******/

			// TODO Auto-generated method stub
			   int rang[] =  new int[9];
			   int [] masqueCode = new int [9];
			   
			   for (int i = 0;i < 9;i++)
			   {
				   masqueCode[i] = masqueIntenesite[i];
				 //  System.out.println("Mio"+m[i]);
			   }
			
			boolean haveChange = true;
			while(haveChange){
			    haveChange = false;
			    for(int i=0; i<masqueIntenesite.length-1; i++){
			    	
			        if(masqueIntenesite[i]>masqueIntenesite[i+1]){
			            int temp = masqueIntenesite[i] ;     
			            masqueIntenesite[i] = masqueIntenesite[i+1];
			            masqueIntenesite[i+1] = temp;
			            haveChange = true;// ici ca veut dire que j'ai fait un changement (y'a un autre min)
			   
			            
			         							}
			        
			    									}
			   
							 }//fin de tré

			 /*initialisation de tableaux des rang*/
			for(int i = 0;i <= 8;i++)
			{
					rang[i] = i+1;// Je met les rangs de 1 à 8 dans un tableau
					//System.out.println("voilà rangggg"+rang[i]);
			}
			//fin d'initialisation
		
			int k = 0;
			//System.out.println("voilà ran"+rang[k]);
			while (k < 8)   // je met le meme rang au cas ou il y'a la meme valeur dans le masque
			{
				if (masqueIntenesite[k+1] == masqueIntenesite[k])
					rang[k+1] = rang[k];
				else 
					rang[k+1] = rang[k]+1;
				
				k++;
			//System.out.println("voilà ran"+rang[k]);
			//System.out.println("voilà ran"+rang[k]+" pour mon"+masqueIntenesite[k]);
				
			}  
			// Je change chaque valeur du masque dans le tableau masqueIntenesite par son rang equivalent qui se trovue dans le tableau rang
			
				for (int i = 0; i <= 8; i++)
				{

					for(int tt = 0; tt <=8;tt++)
					{
						if(masqueCode[i] == masqueIntenesite[tt])
						{
							masqueCode[i] = rang[tt];
							tt = 9;
							
						}
					//	System.out.println("m["+i+"]="+masqueCode[i]);
						
					}
				}

		 /******/
		 return masqueCode;
	 }
	 
	 /*********************************************************/
	 public void putMasque(int [] masqueCode,int [] imageIntensite,int i,int j,int w){
		 
		 int i1=i-1;int i2=i+1;
		 int j1=j-1;int j2=j+1;
		 
		imageIntensite[i1*w+j1]=masqueCode[0];imageIntensite[i1*w+j]=masqueCode[1];imageIntensite[i1*w+j2]=masqueCode[2];
		imageIntensite[i*w+j1]=masqueCode[3]; imageIntensite[i*w+j]=masqueCode[4]; imageIntensite[i*w+j2]=masqueCode[5];
		imageIntensite[i2*w+j1]=masqueCode[6];imageIntensite[i2*w+j]=masqueCode[7];imageIntensite[i2*w+j2]=masqueCode[8];
	 }
	
	 /***********************************************/
	 public void edgeRightZero(int w,int h,int[] imagetteCode){
		 
		int debut=(3*(w/3));
					for(int i=0;i<h;i++){
						for(int j=debut;j<w;j++){
							imagetteCode[(i*w)+j]=0;
						}
					}
		 
	 }
	 
public void edgeDownZero(int w,int h,int[] imagetteCode){
	
	int debut=(3*(h/3));
			for(int i=debut;i<h;i++){
				for(int j=0;j<w;j++){
					imagetteCode[(i*w)+j]=0;
				}
			}
		 
	 }
	 
	
}
