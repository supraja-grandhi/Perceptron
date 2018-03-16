import java.util.*;
import java.lang.*;
import java.text.*;

class perceptron{
	public static void main(String args[]){
		int maxIterations=100;
		int maxSamples=100;
		int threshold=0;
		int iteration=0,output;
		double Error=0,localError=0,learnRate=0.1;
		neuron a[] = new neuron[100];
		for(int i=0;i<maxSamples;i++){
			a[i]=new neuron();
		}
		for(int i=0;i<maxSamples/2;i++){
			a[i].x=5+Math.random()*5; 
			a[i].y=4+Math.random()*4; 
			a[i].z=2+Math.random()*7;  
			a[i].output=1; 
		}
		for(int i=50;i<maxSamples;i++){
			a[i].x=-1+Math.random()*4; 
			a[i].y=-4+Math.random()*6; 
			a[i].z=-3+Math.random()*8; 
			a[i].output=0; 
		}
		double weights[] = new double[4];
		for(int i=0;i<4;i++){
			weights[i]=Math.random()*1;
			System.out.print(round(weights[i])+"  ");
		}
		do{
			Error = 0;
			for(int i=0;i<maxSamples;i++){
				output = calculateOutput(threshold,weights,a[i].x,a[i].y,a[i].z);
				localError = a[i].output - output;
				
				weights[0]+= learnRate*localError*a[i].x;
				weights[1]+= learnRate*localError*a[i].y;
				weights[2]+= learnRate*localError*a[i].z;
				weights[3]+= learnRate*localError;
				Error += Math.pow(localError,2);
			}
			System.out.println("Iteration : "+iteration+"  |||  RMSE : "+round(Math.sqrt(Error/maxSamples)));
			iteration++;
		}while(iteration<=maxIterations&&Error!=0);
		
		for(int i=0;i<10;i++){
			double x = -10+Math.random()*20;
			double y = -10+Math.random()*20;
			double z = -10+Math.random()*20;
			
			output=calculateOutput(threshold,weights,x,y,z);
			System.out.println("New random point ");
			System.out.println("x : "+round(x)+"  y : "+round(y)+"  z : "+round(z)+"  output : "+output);
			System.out.println("");
			System.out.println("");
		}
	}
	
	public static int calculateOutput(int threshold,double weights[],double x,double y,double z){
		double sum = x*weights[0]+y*weights[1]+z*weights[2]+weights[3];
		if(sum>=threshold) return 1;
		return 0;
	}
	
	public static double round(double n){
		DecimalFormat df = new DecimalFormat("#.####");
		String s = df.format(n);
		return Double.parseDouble(s);
	}
}

class neuron{
	double x,y,z;
	int output;
	public neuron(){
		this.x=0;
		this.y=0;
		this.z=0;
		this.output=0;
	}
}