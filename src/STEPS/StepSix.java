package STEPS;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.RBFNetwork;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.supportVector.Kernel;
import weka.classifiers.functions.supportVector.Puk;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


public class StepSix {
	

	
	@SuppressWarnings("null")
	public String[] classification(double aire[] , double entropie2[], String Nf,double omega,double sigma,boolean classifieur) throws Exception
	{
		FileReader reader = new FileReader(Nf);
		Instances apprentissage = new Instances(reader);
		String[] resultatClassification = new String[144];
		apprentissage.setClassIndex(apprentissage.numAttributes() - 1);
		
		  if (classifieur == true)
		 {
			 Classifier cModel;
			 cModel=J48();
			 		cModel.buildClassifier(apprentissage);
				 this.prediction(aire, entropie2);
				 
				 Instances unlabeled = DataSource.read("apprentissage/prediction.arff");
				 unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
				 Instances labeled = new Instances(unlabeled);
				 // label instances
				 for (int i = 0; i < unlabeled.numInstances(); i++) {
					 double clsLabel = cModel.classifyInstance(unlabeled.instance(i));
					 labeled.instance(i).setClassValue(clsLabel);
//					 System.out.println(" "+labeled.instance(i).toString(2));//test pour le contenu de Résultat
					 resultatClassification[i] = labeled.instance(i).toString(2);
//					 System.out.println("num"+unlabeled.numInstances());
			 
				 													}
		 }
		  else if (classifieur == false)
		{
			
			SMO cModel;
		 
		 
		 cModel = SVM( omega, sigma, apprentissage);
			cModel.buildClassifier(apprentissage);
			 this.prediction(aire, entropie2);
			 
			 Instances unlabeled = DataSource.read("apprentissage/prediction.arff");
			 unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
			 Instances labeled = new Instances(unlabeled);
			 // label instances
			 for (int i = 0; i < unlabeled.numInstances(); i++) {
				 double clsLabel = cModel.classifyInstance(unlabeled.instance(i));
				 labeled.instance(i).setClassValue(clsLabel);
				 resultatClassification[i] = labeled.instance(i).toString(2);				 

			 													}
		}
			

		return  resultatClassification;		
}
	

	public void prediction(double aire[],double entropie2[]) throws IOException
	{
		FileReader reader = new FileReader("apprentissage/prediction.arff");
		
		 BufferedWriter writer = new BufferedWriter(new FileWriter("apprentissage/prediction.arff"));
		 writer.write("@relation ’apprentissage’\n" +
		         		"@attribute ’laire’ NUMERIC\n" +
		         		"@attribute ’entropie2’ NUMERIC\n" +
		         		"@attribute ’class’ {'homogene','complexe'}\n" +
		         		"@data\n");
		 
		 for ( int i = 0 ; i < 144 ;i++)
		 {
			 writer.write(aire[i]+","+entropie2[i]+",?\n");
		 }

		 writer.flush();
		 writer.close();
		 }


	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public SMO SVM(double omega,double sigma,Instances app) throws Exception
	{
		  Puk noyeau ;
			
				noyeau = new Puk(app, 250007, omega, sigma);
		        SMO cModel1 = new SMO();
		        cModel1.setKernel(noyeau);
			  
		  return cModel1;
		  
	}
	
	/****************/
	
	public Classifier J48(){
		   Classifier Model = new J48();
		  return Model;
		 }

}










 
 
 