package STEPS;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;


public class StepThree {
	
	
	/*****************Methode1*********************/
	 public int [] arrayToInt(ArrayList<Integer>  imagettesCode[],int numImagette){
		
		 int[] intArray = new int[imagettesCode[numImagette].size()];
		 for (int i = 0; i < imagettesCode[numImagette].size(); i++) {
		 intArray[i] = imagettesCode[numImagette].get(i);
		 }
		 return intArray;
	 }
	 
	 /*****************Methode2*********************///pas encore utiliser cette Méthode!!!
	 public int [] arrayToInt(Vector<Integer>  vec){
			
		 int[] intArray = new int[vec.size()];
		 for (int i = 0; i < vec.size(); i++) {
		 intArray[i] = vec.get(i);
		 }
		 return intArray;
	 }
	 /*****************Methode3*********************/
	 public Vector<Integer> calculNbrOccMasque(int [] TabCode,int w,int h){
		 Vector<Integer> vecOcc = new Vector<Integer>();
		 
		 int i=1,j=-2,x=0,y=0;
		 int MS=0;
		 int occ=0;
		 int[] M1=new int[9],M2=new int[9],TabTeste={-1,-1,-1,-1,-1,-1,-1,-1,-1};
		 int nbrMasque=(w/3)*(h/3);
		 StepTwo zip = new StepTwo();
		 for(int MP=1;MP<=nbrMasque;MP++){
			 
			 if((j+3)>=(w-1)){
				i=i+3;
				j=1; 
			 }
			 else{j=j+3;}
			 
			 M1=zip.masqueIntenesite(i, j, w, TabCode);
			 if(Arrays.equals(M1, TabTeste)!=true){
				 occ=1;
				 x=j;y=i;
				 for(MS=MP+1;MS<=nbrMasque;MS++){
					 
					 if((x+3)>=(w-1)){
						y=y+3;
						x=1; 
					 }
					 else{x=x+3;}
					 
					 M2=zip.masqueIntenesite(y, x, w, TabCode);

					 if(Arrays.equals(M1,M2)){occ++; 
					 						zip.putMasque(TabTeste, TabCode,y, x, w);	
					 						}
					 
				 }//for2
				 vecOcc.add(occ);
							 
			 }//if !equal
		 }//for1
		return vecOcc;
		
		  
	 }
	 /*******************Method4*******************/
	 public Vector<Integer> creationXYZipf(Vector<Integer> vecOcc){
		 
		 /*tri des fréquaeces du vecteur*/
		 boolean haveChange = true;
			while(haveChange){
			    haveChange = false;
				  for(int i=0; i<vecOcc.size()-1; i++){
				    	
				        if(vecOcc.get(i)<vecOcc.get(i+1)){
				            int temp = vecOcc.get(i) ; 
				            vecOcc.set(i, vecOcc.get(i+1));
				            vecOcc.set(i+1, temp);
				            haveChange = true;// ici ca veut dire que j'ai fait un changement (y'a un autre min)
				        }//Fin IF
						
						
				   }//Fin for
			 }//fin de tri
			/*FIN tri des fréquaeces de veceur*/
			/*ajout des rangs au vecteur*/
			int j=0;
			int tailleVecteur=vecOcc.size();
			for(int i=0; i<tailleVecteur; i++){
				vecOcc.add(j, i+1);
				j+=2;
			}
			/*FIn d'ajout des rangs au vecteur*/
			
			
			
	 return vecOcc;
	}
	 /***********************Method5*********************************/
	 public Vector<Double> logVect(Vector<Integer> vecOcc){
		 Vector<Double> vectZip = new Vector<Double>();
		 for(int i=0; i<vecOcc.size();i++){
				vectZip.add(Math.log10(vecOcc.get(i)));
			}
		return vectZip;
	 }
	 /************************************************************************************/
	 /*******************************création Zipf Inverse********************************/
	 /************************************************************************************/
	 /**********************Method6****************************/
	 public Vector<Integer> creationXYZipfInverse(Vector<Integer> vecFrequence){
		 	int fs = 0,FdeFréquence = 0;
			 Vector<Integer> vecZipfinverse = new Vector<Integer>();
			 for(int fp=0;fp<vecFrequence.size();fp++){
						 
						
						 if (vecFrequence.get(fp) != 0)
						 {
							 
						 	 FdeFréquence=1;
							 
							 for(fs=fp+1;fs<vecFrequence.size();fs++)
							 {	 

								 if(vecFrequence.get(fp) == vecFrequence.get(fs))
								 
								 {
									 FdeFréquence++; 
									 vecFrequence.set(fs, 0);	
								 }
								 
							 }//for2
							 vecZipfinverse.add(vecFrequence.get(fp));
							 vecZipfinverse.add(FdeFréquence);
							
										 
						 }//if !equal
						
					 }//for1
	
		 
		return vecZipfinverse; 
		
	 }
	
	 
	 
	 
}
