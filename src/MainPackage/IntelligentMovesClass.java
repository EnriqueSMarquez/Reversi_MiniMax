package MainPackage;
//////////////////////ENRIQUE MARQUEZ////////////////////
///////////////ASSIGNMENT 2 FOUNDATIONS OF AI//////////////
////THIS CLASS IS IN CHARGED OF GENERATE THE NEXT MOVE////
//////IT CAN BE EITHER RANDOMDLY OR USING MINIMAX//////////
//////////////////UNIVERSITY OF SOUTHAMPTON///////////////
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class IntelligentMovesClass {
	//THIS METHOD RETURNS A RANDOM MOVE, IT REQUIRES THE POSSIBLE MOVES IN AN ARRAY OF OBJECTS
	public MovementObject GetMovementRandom(List<MovementObject> listOfObjects){
		
		Random r = new Random();
		int nextMove;
		MovementObject newMovement;
		nextMove = r.nextInt(listOfObjects.size());
		newMovement = listOfObjects.get(nextMove);
		
		return newMovement;
		
	}
	//THIS METHOD IS IN CHARGED OF DOING MINIMAX ALGORITHM, IT RETURNS THE MOVE THAT WAS PICKED.
	//IT REQUIRES THE DEEPNESS OF THE MINIMAX, THE FILE THAT IS GOING TO BE WRITTEN, AND THE WORKING MATRIX
public MovementObject GetMovementUsingMiniMax(int[][] actualMatrix, int levelOfMinimax, BufferedWriter bw){
	//INITIALIZE VARIABLES
		MovesClass movesClass = new MovesClass();
		List<MovementObject> arrayOfMoves = new ArrayList<MovementObject>();
		List<MovementObject> arrayOfNewMoves = new ArrayList<MovementObject>();
		List<MovementObject> possibleMinPicks = new ArrayList<MovementObject>();
		List<MovementObject> arrayToOpen = new ArrayList<MovementObject>();
		List<MovementObject> minPicks = new ArrayList<MovementObject>();
		int allTheOpenNodes = 0;
		
		int[][] workingMatrix = new int[6][6];
		int deep = 1;
		MovementObject maxProfitObject;
		
		//CLONE THE MATRIX
		for(int i = 0; i < workingMatrix.length; i ++)
			for(int j = 0; j < workingMatrix.length; j ++)
				workingMatrix[i][j] = actualMatrix[i][j];
		arrayToOpen.add(new MovementObject(workingMatrix,0,0));	
		arrayOfMoves.add(new MovementObject(workingMatrix,0,0));	
		//FIRST MOVES
		int carrierForX = 0;
		//MINIMAX
		while(deep < levelOfMinimax){ //WHILE THE DEEPNESS OF THE MINIMAX HAS NOT BEEN ACHIEVED
		for(int x = 0; x < arrayToOpen.size(); x ++){ //MAX LOOP. FOR ALL THE NODES IN ARRAYTOOPEN (FIRSTLY THIS IS THE ROOT NODE, THEN IT IS THE CHOICES OF MIN)
		if(arrayToOpen.get(x) != null){
		arrayOfNewMoves.addAll(movesClass.ValidMovements(arrayToOpen.get(x).howMatrixWouldBe, 1));	 //OPEN THE NEXT NODE
		if(deep == 1){ //SET PARENT IF DEEP = 1
			for(int i = 0; i < arrayOfNewMoves.size(); i++){
				arrayOfNewMoves.get(i).setParent(carrierForX);
				carrierForX ++;
			}
			}else{
				for(int y = 0; y < arrayOfNewMoves.size() ; y ++)
				arrayOfNewMoves.get(y).setParent(arrayToOpen.get(x).getParent()); //IF DEEP IS MORE THAN ONE JUST ASK FOR THE PARENT OF THE PARENT
			}
		for(int j = 0;j < arrayOfNewMoves.size(); j ++){ //MIN LOOP
			
			possibleMinPicks.clear();
			possibleMinPicks.addAll(movesClass.ValidMovements(arrayOfNewMoves.get(j).howMatrixWouldBe, 2)); //GET THE MOVEMENTS OF MIN
		for(int k = 0; k < possibleMinPicks.size(); k ++) //SET THE PARENTS
			possibleMinPicks.get(k).setParent(arrayOfNewMoves.get(j).getParent());
		
			minPicks.add(MinPick(possibleMinPicks)); //WHAT WOULD MIN PICK?
			
		}
		}
		}
		allTheOpenNodes = allTheOpenNodes + possibleMinPicks.size() + arrayOfNewMoves.size(); //VARIABLE FOR FURTHER PLOTTING
		arrayToOpen.clear();
		if(!minPicks.isEmpty()) //IF MIN HAVE A MOVE
		arrayToOpen.addAll(minPicks); //SAVE FOR NEXT ITERATION
		minPicks.clear();
		arrayOfNewMoves.clear();
		deep ++; //INCREASE DEPTH
		

}
		
		MovementObject u = GetMaximunOfMinPicks(arrayToOpen); //GET MAX FINAL PICK
		if(u != null)
		maxProfitObject = movesClass.ValidMovements(workingMatrix, 1).get(u.getParent()); //GET PARENT OF MAX FINAL PICK
		else
		maxProfitObject = GetMovementRandom(movesClass.ValidMovements(workingMatrix, 1));
		
		try {
			bw.write(new MovementObject(actualMatrix,0,0).profit+" "+maxProfitObject.profit + "\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxProfitObject;
		
	}
//THIS METHOD REQUIRES THE POSSIBLE MIN CHOICES AND RETURNS THE FINAL CHOICE
private MovementObject MinPick(List<MovementObject> arrayOfMoves){
	MovementObject objectToReturn = null;
	for(int i = 0; i < arrayOfMoves.size(); i++){
		if (objectToReturn == null || arrayOfMoves.get(i).getProfit() < objectToReturn.getProfit())
			objectToReturn = arrayOfMoves.get(i);
	}
		
	
	return objectToReturn;
}
//THIS METHOD REQUIRES THE POSSIBLE MAX CHOICES AT THE END OF THE TREE AND RETURNS THE FINAL CHOICE
private MovementObject GetMaximunOfMinPicks(List<MovementObject> arrayOfMoves){
	
	MovementObject objectToReturn = null;
	
	for(int i = 0; i < arrayOfMoves.size(); i++){
		if (objectToReturn == null || (arrayOfMoves.get(i) != null && arrayOfMoves.get(i).getProfit() > objectToReturn.getProfit()))
			objectToReturn = arrayOfMoves.get(i);
	}
	
		
	return objectToReturn;
}


	
}
