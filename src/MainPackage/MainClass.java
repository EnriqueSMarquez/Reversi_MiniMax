package MainPackage;
//////////////////////ENRIQUE MARQUEZ////////////////////
///////////////ASSIGNMENT 2 FOUNDATIONS OF AI//////////////
///////////////THIS CLASS IS THE MAIN THREAD//////////////
//////////////////////////////////////////////////////////
//////////////////UNIVERSITY OF SOUTHAMPTON///////////////
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainClass {
	//BLACK are 1
	//WHITE are 2
	public static void main(String args[]) {
	//INITIALIZE VARIABLES
	MovesClass movesClass = new MovesClass();
	int[][] initialMatrix = new int[6][6];
	int[][] workingMatrix = new int[6][6];
	List<MovementObject> listOfPossibleMoves = new ArrayList<MovementObject>();
	MovementObject newMovement;
	IntelligentMovesClass intelMoves = new IntelligentMovesClass();
	int whoWon = 0;
	int whosPlaying = 1;
	for(int i = 0;i < initialMatrix.length; i++)
		for(int j = 0; j < initialMatrix.length; j++)
			initialMatrix[i][j] = 0; //CREATE INITIAL MATRIX
	
	initialMatrix[2][2] = 1;
	initialMatrix[3][3] = 1;
	initialMatrix[3][2] = 2;
	initialMatrix[2][3] = 2;
	int games = 0;
	float blacksCarrier = 0f;
	File file = new File("toGetMeanFile7Profit.txt");
	FileWriter fw = null;
	BufferedWriter bw = null;
	try {
	fw = new FileWriter(file.getAbsoluteFile());
	bw = new BufferedWriter(fw);
	bw.write("#STARTING FILE \n");
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	
	ViewController viewController = new ViewController(initialMatrix);
	//20 CONSECUTIVE GAMES
	while(games < 20){
    viewController.DisplayNewMatrix(initialMatrix); //DISPLAY INITIAL MATRIX
	workingMatrix = initialMatrix; //INITIALIZE WORKING MATRIX
	int carrier = 0;
	boolean gameOver = false;
	//WHILE THE GAME HAS NOT FINISHED
	while(!gameOver){
		//WAIT FOR BUTTON
		while(!viewController.isNextMove()){
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		viewController.getButton().setText("NEXT"); //CHANGE BUTTON STRING
		listOfPossibleMoves.clear();
		listOfPossibleMoves.addAll(movesClass.ValidMovements(workingMatrix, whosPlaying)); //CHECK IF THERE A POSSIBLE MOVE
	if(listOfPossibleMoves.isEmpty()){ //IF THERE IS NOT A MOVE, INCREMENT CARRIER
		carrier++;
	}else{ //IF THERE IS A MOVE
		if(whosPlaying == 2){ //IF WHITES ARE PLAYING
		
		newMovement = intelMoves.GetMovementRandom(listOfPossibleMoves); //GET RANDOM MOVE
		}
		else{ //IF BLACKS ARE PLAYING GET MINIMAX MOVE
		newMovement = intelMoves.GetMovementUsingMiniMax(workingMatrix,viewController.GetWhichButtonIsSelected(),bw);
		}
		if(newMovement != null){
		workingMatrix = newMovement.howMatrixWouldBe; //NEW WORKING MATRIX
		carrier = 0;}
		else
		carrier ++;
		
	}
	if(listOfPossibleMoves.isEmpty() && carrier == 2){ //IF NOBODY CAN PLAY, THE GAME IS OVER
		gameOver = true;
		}
	
	
	if(whosPlaying == 1) whosPlaying = 2; //CHANGE PLAYER AFTER EVERY ITERATION
	else whosPlaying = 1;
	try {
		Thread.sleep(1);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	viewController.DisplayNewMatrix(workingMatrix); //DISPLAY NEW MATRIX
	
	}
	String str = "NONE";
	whoWon = movesClass.CheckWhoWon(workingMatrix); //CHECK WHO WON AFTER THE GAME HAS FINISHED
	if(whoWon == 0){
		str = "IT WAS A TIE";
		System.out.println(str);
	}else if(whoWon == 1){
		str = "BLACKS WON";
		System.out.println(str);
		blacksCarrier ++;
	}else if(whoWon == 2){
		str = "WHITES WON";
		System.out.println(str);
		
		}
	games ++;
	
	viewController.getButton().setText(str + "  PLAY AGAIN");
	while(!viewController.isNextMove()){
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	try {
		bw.close();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	float ratio = 100f*blacksCarrier/games; //CALCULATE RATIO
	System.out.println(ratio);
	
}
	
}
