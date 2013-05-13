package PRINCIPALE;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt. Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



import de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel;


//import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel;

import STEPS.StepFive;
import STEPS.StepFour;
import STEPS.StepOne;
import STEPS.StepSix;
import STEPS.StepThree;
import STEPS.StepTwo;
public class ClasseMenu extends JFrame implements ActionListener {
	JMenuBar menubare;
	JMenu fichier,autre,àpropos;

	JMenuItem ouvrir,cZipf,cZipfinverse,nuageDepoints,ImageOriginale,fermer,propos,aide,enregistrer;
	FileDialog dialog;
	Panneaulmage zonelmage;
	Panel arriere;
	BufferedImage buffeurActuel,buffeurCouleur;
	Image image;
	int W, H;
	PrintWriter sortie;
	int [] pixelsCourant;
	int [] pixelSubimage;
	JButton Ouvrir,zipf,zipfInverse,nuage,imageOriginale,Quitter,apropos,Enregistrer;
	double [] entropies2=new double [144];
	double[] laires=new double[144]; 
	static JComboBox<String> macombo;
	static JComboBox<String> classifieur;
	static boolean typeClassification = false;;boolean test = false;

	static Vector<Double>  XYZipfinverseImagettes[] = new Vector[144];
	static Vector<Double>  XYZipfImagettes[] = new Vector[144];
	static Vector<Integer>  XYZipfinverseImagettesInt[] = new Vector[144];
	static Vector<Integer>  XYZipfImagettesInt[] = new Vector[144];
	BufferedImage [] Imagettes; 
	JLabel bienvenue,typeImage,typeDeClassification,courbef,courbeInverse,accueil;
	static String APPRENTISSAGE_PREFIX = "apprentissage";  
	static String Nomfichier= APPRENTISSAGE_PREFIX+"/ApprentissageNiGray2.arff";
	double omega=0.2; double sigma= 0.6;
	JSeparator s1;
	public void ClasseMenu1(){


		this.setLayout(null);


		accueil = new JLabel();
		accueil.setIcon(new ImageIcon("images/12.jpg"));
		accueil.setBounds(410, 30, 900, 600);
		this.add(accueil);
		arriere = new Panel();

		arriere.setBounds(0, 0, 1366, 768);

		this.setIconImage(new ImageIcon("images/icone.jpg").getImage());
		setTitle("Traitement d'Images");
		setSize(1366,768);
		setResizable(false);
		menubare=new JMenuBar();
		fichier=new JMenu("Fichier");

		menubare.add(fichier);

		autre=new JMenu("Autre");
		menubare.add(autre);

		àpropos =new JMenu("Aide");
		menubare.add(àpropos);
		initMenuFichier();//interepteur
		setJMenuBar(menubare);

		getContentPane().setLayout(null);

		arriere=new Panel();
		arriere.setBackground(Color.white);
		arriere.setBounds(0, 0, 1366, 768);
		getContentPane().add(arriere);//ici il a met le panel

		s1 = new JSeparator();s1.setBounds(300, 600, 100, 500);this.add(s1);
		setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){System.exit(0);} 
		});
		setLocationRelativeTo(null);
	}
	public static void main(String[] args) throws Exception {
		try 
		{
			UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel());  } 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		new ClasseMenu().ClasseMenu1();

	}

	/**************color*******************/

	/**************for opening the file of picture**************************/


	public void actionPerformed(ActionEvent event) {

		if (event.getSource().equals(classifieur))
		{
			Object valeur2= classifieur.getSelectedIndex();
			if(valeur2.equals(0)){typeClassification = false;}
			else if (valeur2.equals(1)){typeClassification = true;}
		}
		if (event.getSource().equals(macombo))
		{
			Object valeur= macombo.getSelectedIndex();
			if(valeur.equals(0)){
				Nomfichier= APPRENTISSAGE_PREFIX+"/apprentissageNiGray2.arff";
				omega=0.2; sigma= 0.6;test = false;
			}
			else 
			{
				Nomfichier= APPRENTISSAGE_PREFIX+"/mamo2.arff";
				omega=0.12; sigma= 0.11;test = true;
			}
		}

		if ((event.getSource().equals(fermer))||(event.getSource().equals(Quitter)))
		{
			{

				affiche (JOptionPane.showConfirmDialog(null,"Vous voulez vraiment quitter?","Confirmation",
						JOptionPane.YES_NO_OPTION));
			}


		}

		if (event.getSource().equals(propos))
		{
			JOptionPane.showMessageDialog(null, "l'Application zone détect\n est developpées par \n Benhammadi Mostefa Islem \n pendant le projet de Master2 ");
		}





		if ((event.getSource()==ouvrir)||(event.getSource().equals(Ouvrir))){

			zipf.setEnabled(true);
			zipfInverse.setEnabled(true);
			nuage.setEnabled(true);
			imageOriginale.setEnabled(true);
			Enregistrer.setEnabled(true);
			try {

				this.Ouvrirlmage();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//	this.dispose();
		}
		if((event.getSource()==cZipf)||(event.getSource().equals(zipf)))
		{
			CourbeZipf();
		}

		if((event.getSource()==cZipfinverse)||(event.getSource().equals(zipfInverse)))
		{
			courbeZipfinverse();
		}

		if((event.getSource()==nuageDepoints)||(event.getSource().equals(nuage)))
		{
			nuageDepoints();
		}


		if
		((event.getSource()==ImageOriginale)|| (event.getSource().equals(imageOriginale)))
		{ 
			new FenetreFinal().FenetreInitiale(buffeurCouleur, W, H);
		}
		
		if((event.getSource()==enregistrer )||(event.getSource()==Enregistrer ))
		{
			JFileChooser fileEnregistrerImage = new JFileChooser();
		if (fileEnregistrerImage.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			File fichierEnregistrement = new File(fileEnregistrerImage.getSelectedFile().getAbsolutePath()+ ".JPG");
			zonelmage.enregistrerImage(fichierEnregistrement);

			JOptionPane.showMessageDialog(null, "L'image à été enregistré avec succés");
		}
		}



	}



	public void Ouvrirlmage() throws Exception   {

		try
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Veuillez séléctionner l'limage à traiter");
			fileChooser.showOpenDialog(null);
			this.add(fileChooser);
			fileChooser.setVisible(true);

			///



			fileChooser.setVisible(false);
			File fichier1 = fileChooser.getSelectedFile();
			System.out.println(fichier1.getName());
			buffeurActuel=ImageIO.read(fichier1);
			this.detection(buffeurActuel);
		}
		catch (Exception e) { 
			JOptionPane.showMessageDialog(null, "Attention vous devez choisir un fichier de type image");
			System.out.print(e.getMessage());
		}
		//			   folder = fileChooser.getSelectedFile();


	}

	public void detection(BufferedImage buffeurActuel) throws Exception{



		/************************end for opening the file of picture**********************************/

		/***********initialisation de la fenetre d'affivhage !!***************/
		/**********pour que l'image saffiche sans dimessioner et redimessioner la fenetre************/
		setSize(getInsets().left+getInsets().right+Math.max(0,(buffeurActuel.getWidth())+261+10)
		,getInsets().top+getInsets().bottom+Math.max(0,(buffeurActuel.getHeight())+83));


		Imagettes=new BufferedImage [144];	//ilitialisation de BufferedImage []

		StepOne créationImagette = new StepOne();
		buffeurCouleur=créationImagette.imageEnCouleur(buffeurActuel);//Pour que j'affiche l'image à son état originale
		buffeurActuel=créationImagette.imageEnNiveauGris(buffeurActuel);//je met l'image à son niveau de gris pour pouvoir la traiter 
		créationImagette.HLofimage(buffeurActuel);//donne les dimession de l'image
		créationImagette.HLofSubimage();//donne les dimenssion des imagettes de melieux 
		créationImagette.differnceHL();//elle donne les dimenssion des imagettes des borne et la differnce entre leur dimenssion et la dimenssion des imagettes de milieux

		//		 
		if (test == true) // je teste si l'utilisateur à choisi le traitement pour image couleur ou mammographie
		{
			Imagettes=créationImagette.créateSubimages(buffeurCouleur);//diviser l'image BufferedImage on imagettes BufferedImage[]
		}
		else 
			Imagettes=créationImagette.créateSubimages(buffeurActuel);


		//		 créationImagette.tt();
		pixelsCourant=créationImagette.créationImageafficher();

		H=créationImagette.getHaut();

		W=créationImagette.getLarg();

		//		 image = createImage (new MemoryImageSource( W,H, pixelsCourant, 0 ,W)); 
		arriere.removeAll();

		/****************codage 9 class********************/

		StepTwo codage = new StepTwo();
		ArrayList<Integer>  tabCodage9Classes[] = new ArrayList[144];
		for(int i=0;i<144;i++){
			codage.calculdldh(buffeurActuel,i);
			int dl=codage.getdl();
			int dh=codage.getdh();
			pixelSubimage=new int [dl*dh];
			pixelSubimage=codage.grabberImagette(Imagettes,i);
			codage.intensiteImagette(pixelSubimage);
			codage.codage9Class(pixelSubimage);

			tabCodage9Classes[i] = new ArrayList<Integer>();
			for(int t=0; t<pixelSubimage.length;t++){
				tabCodage9Classes[i].add(pixelSubimage[t]);
			}

		}

		/****************Fin codage 9 class********************/

		/****************codage rangs generaux*****************/


		ArrayList<Integer>  tabCodageRangGeneraux[] = new ArrayList[144];
		for(int i=0;i<144;i++){
			codage.calculdldh(buffeurActuel,i);
			int dl=codage.getdl();
			int dh=codage.getdh();
			pixelSubimage=new int [dl*dh];
			pixelSubimage=codage.grabberImagette(Imagettes,i);
			codage.intensiteImagette(pixelSubimage);

			//codage.codage9Class(pixelSubimage);
			codage.codageRangGeneraux(dl, dh, pixelSubimage);

			tabCodageRangGeneraux[i] = new ArrayList<Integer>();
			for(int t=0; t<pixelSubimage.length;t++){
				tabCodageRangGeneraux[i].add(pixelSubimage[t]);
			}

		}


		/****************Fin codage rangs generaux**************/
		StepThree zipf = new StepThree();
		Vector<Integer> vectOcc = new Vector<Integer>();
		Vector<Double> vectOcc2 = new Vector<Double>();
		/**************Zip F***********************************/

		int I=0;//c'est juste pour le print

		Vector<Integer>  freqImagettes[] = new Vector[144];
		//		 Vector<Double>  XYZipfImagettes[] = new Vector[144];
		for (int nimg = 0 ; nimg < 144; nimg++)
		{
			codage.calculdldh(buffeurActuel,nimg);
			int w=codage.getdl();
			int h=codage.getdh();

			vectOcc=zipf.calculNbrOccMasque((zipf.arrayToInt(tabCodageRangGeneraux, nimg)), w, h);
			//sauvgarder valeur des occ de tout les imagettes pour l'utilise dans l'etape de zipf inverse et zipf
			//						 freqImagettes[nimg] = new Vector<Integer>();
			//						 for(int t=0; t<vectOcc.size();t++){
			//							 freqImagettes[nimg].add(vectOcc.get(t));
			//						 }
			//&
			vectOcc=zipf.creationXYZipf(vectOcc);
			//sauvgardage des vecteur de Zipf entie
			XYZipfImagettesInt[nimg] = new Vector<Integer>();
			for(int t=0; t<vectOcc.size();t++){
				XYZipfImagettesInt[nimg].add(vectOcc.get(t));
			}
			//Fin du sauvgarde du nouveau vecteur aveec tt les rangs
			vectOcc2=zipf.logVect(vectOcc);

			//sauvgardage des vecteur de Zipf
			XYZipfImagettes[nimg] = new Vector<Double>();
			for(int t=0; t<vectOcc2.size();t++){
				XYZipfImagettes[nimg].add(vectOcc2.get(t));
			}
			//Fin du sauvgardage

		}//Fin for nimg


		/**************Fin Zip F***********************************/
		/**************************Zip f inverse*****************/
		//		  Vector<Double>  XYZipfinverseImagettes[] = new Vector[144];


		for (int nimg = 0 ; nimg < 144; nimg++)
		{
			codage.calculdldh(buffeurActuel,nimg);
			int w=codage.getdl();
			int h=codage.getdh();  
			vectOcc=zipf.calculNbrOccMasque((zipf.arrayToInt(tabCodage9Classes, nimg)), w, h);
			vectOcc=zipf.creationXYZipfInverse(vectOcc);
			//sauvgardage des vecteur de Zipfinverse entie
			XYZipfinverseImagettesInt[nimg] = new Vector<Integer>();
			for(int t=0; t<vectOcc.size();t++){
				XYZipfinverseImagettesInt[nimg].add(vectOcc.get(t));
			}
			//Fin du sauvgarde

			vectOcc2=zipf.logVect(vectOcc);

			//			////******///
			XYZipfinverseImagettes[nimg] = new Vector<Double>();
			for(int t=0; t<vectOcc2.size();t++){
				XYZipfinverseImagettes[nimg].add(vectOcc2.get(t));
			}


		}
		/**************************Fin Zip f inverse*****************/

		StepFive nuageDePoint = new StepFive();

		/**************************calcul de l'entropie2 pour  les courbes ZIPFinverse**********************/
		int r=0;
		//	 double [] entropies2=new double [144];
		for(int i=0;i<144;i++){
			int [] IF=new int [XYZipfinverseImagettesInt[i].size()/2];	
			if(IF.length!=1){
				IF=nuageDePoint.calculIf(XYZipfinverseImagettesInt[i]);
				r=nuageDePoint.calculNbreMotifDistinct(IF);
				entropies2[i]=nuageDePoint.entropie2(IF, r);
			} 
			else{entropies2[i]=1;}
		}

		//*teste*//
		//			  for ( int i = 0 ; i < 144; i++)
		//			  {
		//							  
		//				  System.out.println(" l'entropie2 de "+i+"=" +entropies2[i]);
		//			  }
		//*fintest*//
		/**************************Fin de calcul de l'entropie2 pour  les courbes ZIPFinverse**********************/
		/**************************calcul de l'aire pour  les courbes ZIPF**********************/


		// double[] laires=new double[144]; 
		for ( int i = 0 ; i < 144; i++)
		{
			//			  System.out.println(" i="+i);
			laires[i] = nuageDePoint.CalculAire( XYZipfImagettesInt[i]);

			//			  System.out.println(" l'aire de "+i+"=" +aireTest);
		}
		//		  //*teste*//
		//		  for ( int i = 0 ; i < 144; i++)
		//		  {
		//						  
		//			  System.out.println(" l'aire de "+i+"=" +laires[i]);
		//		  }
		//		  //*fintest*//


		/****************indice de chaque imagette******************/
		//		  for ( int i = 0 ; i < 144; i++)
		//		     {
		//	      System.out.println(laires[i]+","+entropies2[i]+","+"'homogene'"+" "+(i+1)+" ");
		//	      
		//		     }//pour  faire l'apprentissage
		//		  
		/***************End***************************************/
		/**************************Fin de calcul de l'aire pour  les courbes ZIPF***************************/


		/****************************************dessin de nuage de points*****************************************/
		//		  FenetreZoom dessinNuage= new FenetreZoom("nuage de point",925,550);
		//		  dessinNuage.setLocationRelativeTo(null);
		//
		//		  dessinNuage.DrawNuageDePoint(entropies2, laires);



		/************************************Fin dessin de nuage de points*****************************************/

		/***************************************Classification*********************************************************/
		StepSix classification = new StepSix();
		//		  	Dialogue type= new Dialogue();
		//		  	String Nf= NomFichier;
		//		  	System.out.println("Nomfichier"+Nomfichier+"omega"+omega+"sigma"+sigma+"type de classfication"+typeClassification);
		//		  	double o = type.getOmega(); double s = type.getSigma();
		String[] classe = classification.classification(laires, entropies2,Nomfichier,omega,sigma,typeClassification);
		//		  	 type.setNomfichier("apprentissageNiGray2.arff");type.setOmega(0.2);type.setSigma(0.6);
		//		  	typeClassification = (false);
		/******teste********/
		//		  	for (int i = 0 ; i < 144; i++)
		//		  	{
		//		  		System.out.println("L'imagette "+(i+1)+" est une imagette "+classe[i]);
		//		  	}
		/*****fin test******/
		/**mise en evidence des zones d'intterret**/
		FenetreFinal end=new FenetreFinal();
		//		  		  	
		//		  	  	end.FenetreResultat(buffeurCouleur, classe);

		H=créationImagette.getHaut();
		W=créationImagette.getLarg();

		image = createImage (new MemoryImageSource( W,H, end.FenetreResultat(buffeurCouleur, classe), 0 ,W));

		arriere.removeAll();

		zonelmage=new Panneaulmage(image,W,H,250); //redimentionner l'image 
		//arriere.setBounds(0, 0, 100, 100);


		//				zonelmage.setBounds(100, 100, 600, 600);

		arriere.add(zonelmage);

		/**fin de la mise en evidence des zones d'intterret**/


		/***************************************Fin Classification ****************************************************/



		this.remove(bienvenue);this.remove(accueil);
	}//Fin Ouvrirlmage

	/*******************Methodes de l'affichage***********************************/
	public void CourbeZipf(){
		StepFour draw = new StepFour("Courbes Zipf",1230,652);
		int x = 0 ,y = 0;


		for ( int i = 0 ; i < 144; i++)
		{
			draw.DrawGraph( XYZipfImagettes[i], "Imagette "+i,x,y);
			if ((i+1)%12 == 0)
			{
				x = 0 ; y = y + 51;
			}
			else  x =x+ 101;




		}

	}

	/*****/
	public void courbeZipfinverse(){
		StepFour drawinverse = new StepFour("Courbes Zipf inverse",1230,652);
		int x = 0 ;int y = 0;
		for ( int i = 0 ; i < 144; i++)
		{
			drawinverse.DrawGraph( XYZipfinverseImagettes[i], "Imagette "+i,x,y);
			if ((i+1)%12 == 0)
			{
				x = 0 ; y = y + 51;
			}
			else  x =x+ 101;
		}
	}
	/**/
	public void nuageDepoints(){
		FenetreZoom dessinNuage= new FenetreZoom("nuage de point",925,550);
		dessinNuage.setLocationRelativeTo(null);

		dessinNuage.DrawNuageDePoint(entropies2, laires);
	}

	/********************************************************/
	public Vector<Double>[] getXYZipfinverseImagettes() {
		//		 System.out.println("PP="+XYZipfinverseImagettes[71].get(0));
		return XYZipfinverseImagettes;
	}
	public static Vector<Double>[] getXYZipfImagettes() {
		return XYZipfImagettes;
	}

	private void initMenuFichier(){


		/******************Menu**********************/

		propos = new JMenuItem("à propos");
		àpropos.add(propos);
		aide = new JMenuItem("?");
		aide.setEnabled(false);
		àpropos.add(aide);



		ouvrir=new JMenuItem("Ouvrir");
		ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.ALT_DOWN_MASK));
		fichier.add(ouvrir);

		enregistrer = new JMenuItem("Enregistrer l'image traitée");
		enregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.ALT_DOWN_MASK));
		fichier.add(enregistrer);enregistrer.addActionListener(this);

		ouvrir.addActionListener(this);
		/**/
		cZipf=new JMenuItem("cZipf");
		cZipf.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.ALT_DOWN_MASK));
		autre.add(cZipf);
		cZipf.addActionListener(this);
		/**/
		cZipfinverse=new JMenuItem("cZipfinverse");
		cZipfinverse.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,InputEvent.ALT_DOWN_MASK));

		autre.add(cZipfinverse);
		cZipfinverse.addActionListener(this);
		/**/
		nuageDepoints=new JMenuItem("nuageDepoints");
		nuageDepoints.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.ALT_DOWN_MASK));
		autre.add(nuageDepoints);
		nuageDepoints.addActionListener(this);
		/**/
		ImageOriginale=new JMenuItem("ImageOriginale");
		ImageOriginale.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.ALT_DOWN_MASK));
		autre.add(ImageOriginale);
		ImageOriginale.addActionListener(this);

		fermer=new JMenuItem("Quitter");
		fermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.ALT_DOWN_MASK));

		fichier.add(fermer);fermer.addActionListener(this);


		/**************************BOUTON********************/

		String [] choix={"image couleur" , "mammographie" };
		macombo=new JComboBox(choix);
		String [] algorithme = {"SVM" , "Arbre de décision"};
		classifieur = new JComboBox(algorithme);

		Ouvrir = new JButton("");

		zipf = new JButton("");
		zipfInverse = new JButton("");
		nuage = new JButton(" nuage");
		imageOriginale = new JButton("Image original");
		Quitter = new JButton("");Quitter.addActionListener(this);
		Enregistrer = new JButton("");Enregistrer.addActionListener(this);
		Enregistrer.setBounds(80, 180, 60 , 60);this.add(Enregistrer);

		Enregistrer.setIcon(new ImageIcon("Images/save.png"));
		nuage.setIcon(new ImageIcon("Images/cloud.png"));

		Enregistrer.setEnabled(false);
		zipf.setEnabled(false);
		zipfInverse.setEnabled(false);
		nuage.setEnabled(false);
		imageOriginale.setEnabled(false);

		propos.addActionListener(this);
		macombo.addActionListener(this);Ouvrir.addActionListener(this);
		macombo.setBounds(10, 80, 150, 20);this.add(macombo);
		classifieur.addActionListener(this);this.add(classifieur);
		classifieur.setBounds(10, 150, 150, 20);

		bienvenue = new JLabel(" Bienvenue dans l'application de détection d'une zone d'intérêt");
		bienvenue.setBounds(440, 10, 348, 20);
		bienvenue.setFont(new java.awt.Font("Comic Sans MS", 1, 11));
		//			this.add(bienvenue);

		typeImage = new JLabel(" Type d'image");
		typeImage.setBounds(10, 50, 100, 20);
		typeImage.setFont(new java.awt.Font("Comic Sans MS", 1, 11));
		this.add(typeImage);

		typeDeClassification = new JLabel(" Type de classification");
		typeDeClassification.setBounds(10, 120,130, 20);
		typeDeClassification.setFont(new java.awt.Font("Comic Sans MS", 1, 11));
		this.add(typeDeClassification);

		courbef = new JLabel("Zipf");
		courbef.setBounds(20, 260,50, 20);
		courbef.setFont(new java.awt.Font("Comic Sans MS", 1, 11));
		this.add(courbef);

		courbeInverse = new JLabel("Zipf Inverse");
		courbeInverse.setBounds(80, 260,70, 20);
		courbeInverse.setFont(new java.awt.Font("Comic Sans MS", 1, 11));
		this.add(courbeInverse);


		Ouvrir.setIcon(new ImageIcon("images/browse.png"));
		Ouvrir.setBounds(10, 180, 60,60);this.add(Ouvrir);
		zipf.setIcon(new ImageIcon("images/zip.png"));
		zipf.setBounds(10, 260, 60 , 60);
		zipf.addActionListener(this);this.add(zipf);
		zipfInverse.setIcon(new ImageIcon("images/zip.png"));
		zipfInverse.setBounds(80, 260, 60 , 60);

		zipfInverse.addActionListener(this);this.add(zipfInverse);
		Quitter.setBounds(50, 360, 80, 70);this.add(Quitter);Quitter.addActionListener(this);
		Quitter.setIcon(new ImageIcon("images/shutdown.png"));

		nuage.setBounds(150, 260, 60, 60);
		nuage.addActionListener(this);this.add(nuage);
		imageOriginale.setBounds(150, 180, 60, 60);
		imageOriginale.addActionListener(this);this.add(imageOriginale);



		Ouvrir.setToolTipText("Séléctionner l'image à ouvrir");
		Enregistrer.setToolTipText("Enregistrer l'image traitée");
		zipf.setToolTipText("Afficher la courbe de Zipf pour cette image");
		zipfInverse.setToolTipText("Afficher la courbe de Zipf inverse pour cette image");
		nuage.setToolTipText("afficher le nuage de point");
		imageOriginale.setToolTipText("afficher l'image à son état d'origine");
		Quitter.setToolTipText("Quitter l'application");


	}	

	private void affiche (int rep){
		switch(rep){
		case JOptionPane.YES_OPTION:
			System.out.println("oui");
			System.exit(0);
			break;
		case JOptionPane.NO_OPTION:
			System.out.println("non");
			break;
		}
	}



}
