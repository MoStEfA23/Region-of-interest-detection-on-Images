package STEPS;
import java.util.Vector;


public class StepFive {
	
	/*
	 * Air_sous_GZ=0;
for i=1:length(occurence_motifs_1)-1
    Air_sous_GZ=Air_sous_GZ+((occurence_motifs_1(i)+occurence_motifs_1(i+1))*(rang_motifs(i+1)-rang_motifs(i))/2);
end
%================
	 */

	/**********************Method1*******************************/
	public double CalculAire(Vector<Integer> coordoneesZipF)
	{
		double  Air_sous_GZ=0;
		double taille=coordoneesZipF.size()/2;
		if(taille==1){
			Air_sous_GZ = Air_sous_GZ + (coordoneesZipF.get(1))*(coordoneesZipF.get(0));
		}
		else{
		for (int i = 0; i < (taille) ; i+=2)
		{
			Air_sous_GZ = Air_sous_GZ + (coordoneesZipF.get(i+1)+coordoneesZipF.get(i+3))*(coordoneesZipF.get(i+2)-coordoneesZipF.get(i));
			 
		}
		
		}
		Air_sous_GZ = Air_sous_GZ/2;
		return Air_sous_GZ;
		
	}
	/**********************Method2*******************************/
	 public double entropie2(int [] If, int R ){
			double entropie2 = 0,LL1=0,LL2=0;
			 LL2=Math.log(If.length);
			 for(int ind=0;ind<If.length;ind++){
				 LL1=Math.log((double)If[ind]/(double)R);
				 entropie2=entropie2+((double)If[ind]/(double)R)*(LL1/LL2);


			 }
			 entropie2=entropie2*(-1);
			
			return entropie2;
			 
		 }
	 /**********************Method3*******************************/
	 public int calculNbreMotifDistinct(int [] If){
		 int R=0;
		 for(int i=0;i<If.length;i++){R=R+If[i];}
		return R;
	 }
	 /**********************Method4*******************************/
	 public int [] calculIf(Vector<Integer> coordonees){
		 int [] If=new int [coordonees.size()/2];
		 int j=1;
		 for(int i=0;i<If.length;i++){
			 If[i]=coordonees.get(j);
			 j=j+2;
		 }
		return If;
		 
	 }
	 

}
