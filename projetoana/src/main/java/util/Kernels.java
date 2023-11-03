package util;

import java.util.ArrayList;
import java.util.List;

public class Kernels {
	
	public List<List<float[][]>> listaDeMatrizes(){
		List<List<float[][]>> listaListaKernels = new ArrayList<List<float[][]>>();
		
		List<float[][]> listaMatrizes1 = new ArrayList<float[][]>();
		
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		
		listaListaKernels.add(listaMatrizes1);
		
		List<float[][]> listaMatrizes2 = new ArrayList<float[][]>();
		
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		
		listaListaKernels.add(listaMatrizes2);
		
		
		List<float[][]> listaMatrizes3 = new ArrayList<float[][]>();
		
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
	
		listaListaKernels.add(listaMatrizes3);
	
		List<float[][]> listaMatrizes4 = new ArrayList<float[][]>();
		
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		listaMatrizes1.add(Matriz1);
		
		listaListaKernels.add(listaMatrizes4);
		
		
		return listaListaKernels;
	}

	float[][] Matriz1 = { 
	{(float) -0.036459379    , 	(float) -0.024899706 , 	(float) 0.208547384	 , (float) 0.097460344},
	{(float) 0.040239234	 , (float) 0.138883799	 , (float) 0.082744226	 , (float) 0.082329273},
	{(float) 0.138256073	 , (float) -0.115525216	 , (float) -0.032481454	 , (float) -0.154996023},
	{(float) 0.003860548	 , (float) -0.038160041	 , (float) -0.048569169	 , (float) 0.006127015} };
	
}