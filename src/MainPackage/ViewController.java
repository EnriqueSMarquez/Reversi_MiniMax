package MainPackage;
//////////////////////ENRIQUE MARQUEZ////////////////////
///////////////ASSIGNMENT 2 FOUNDATIONS OF AI//////////////
////THIS CLASS IS IN CHARGED OF THE FRAME AND THE BUTTONS////
//////IT CAN BE EITHER RANDOMDLY OR USING MINIMAX//////////
//////////////////UNIVERSITY OF SOUTHAMPTON///////////////
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ViewController extends JFrame{
	
	private static final long serialVersionUID = 1L;
	//INITILIZE ALL THE VARIABLES AND THE ELEMENTS OF THE FRAME
	JPanel panel1 = new JPanel();
	ImageIcon blankIcon = new ImageIcon("res/blank.png");
	ImageIcon blackIcon = new ImageIcon("res/black.png");
	ImageIcon whiteIcon = new ImageIcon("res/white.png");
	int[][] workingMatrix = new int[6][6];
	JLabel[][] labelsInBoard = new JLabel[6][6];
	JPanel mousePanel = new JPanel();
	JRadioButton radioButton3 = new JRadioButton("DEPTH 3");
	JRadioButton radioButton5 = new JRadioButton("DEPTH 5");
	JRadioButton radioButton7 = new JRadioButton("DEPTH 7");
	ButtonGroup groupOfRadioButtons = new ButtonGroup();
	List<MovementObject> arrayOfMoves = new ArrayList<MovementObject>();
	boolean mouseVariable = false;
	boolean isItAMove = false;
	int whosPlaying;
	MovementObject moveThatWasPicked = new MovementObject(new Point(10000,0));
	JButton button = new JButton();
	boolean nextMove;
	
	public ViewController() {
		
	}
	
	public ViewController(int[][] initialMatrix) {
		
		super("FOUNDATIONS OF AI SECOND ASSIGNMENT");
		workingMatrix = initialMatrix;
		FrameInfo();
		LabelsInfo();
		ButtonInfo();
		RadioButtonsInfo();
		PanelInfo();
		DisplayNewMatrix(workingMatrix);
		
		
	}
	//INITIALIZE RADIO BUTTONS
	private void RadioButtonsInfo(){
		groupOfRadioButtons.add(radioButton3);
		groupOfRadioButtons.add(radioButton5);
		groupOfRadioButtons.add(radioButton7);
		radioButton3.setVisible(true);
		radioButton3.setLocation(10, 580);
		radioButton3.setSize(100, 50);
		radioButton5.setVisible(true);
		radioButton5.setLocation(10, 610);
		radioButton5.setSize(100, 50);
		radioButton7.setVisible(true);
		radioButton7.setLocation(10, 640);
		radioButton7.setSize(100, 50);
		radioButton3.setSelected(true);
		radioButton3.setForeground(Color.WHITE);
		radioButton5.setForeground(Color.WHITE);
		radioButton7.setForeground(Color.WHITE);
		
		
	}
	
	public JRadioButton getRadioButton3() {
		return radioButton3;
	}

	public JRadioButton getRadioButton5() {
		return radioButton5;
	}

	public JRadioButton getRadioButton7() {
		return radioButton7;
	}
	//INITILIZE BUTTON INFO INCLUDING ACTION PERFORMER
	private void ButtonInfo() {
		
		button.setVisible(true);
		button.setLocation(150,650);
		button.setText("START GAME");
		button.setSize(350, 40);
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				nextMove = true;
				
					nextMove = true;
			
				try {
				       Thread.sleep(10);
				    } catch(InterruptedException e1) {
				    }
				nextMove = false;
			}
			
			
		});
		
	}



	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public boolean isNextMove() {
		return nextMove;
	}
//PANEL INFORMATION
	public void PanelInfo(){
		panel1.add(radioButton3);
		panel1.add(radioButton5);
		panel1.add(radioButton7);
		panel1.setLayout(null);
		panel1.setBackground(new Color(200,10,80));
		for(int i = 0; i < labelsInBoard.length;i++){
			for(int j = 0; j < labelsInBoard.length;j++){
				panel1.add(labelsInBoard[i][j]);

			}
		}	
		panel1.add(button);
	}
	
	//FRAME INFORMATION
	public void FrameInfo(){
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,800);
		setLayout(new BorderLayout());
		add(panel1, BorderLayout.CENTER);
		setResizable(false);
		
	}
	//LABELS INFORMATION
	public void LabelsInfo(){
		int carrierX = 0;
		int carrierY = 0;
		for(int i = 0; i < labelsInBoard.length;i++){
			for(int j = 0; j < labelsInBoard.length; j++){
			labelsInBoard[i][j] = new JLabel(blankIcon);
			labelsInBoard[i][j].setSize(100,100);
			labelsInBoard[i][j].setLocation(carrierX,carrierY);
			carrierX += 100;
			}
		carrierX = 0;
		carrierY +=100;
		}
		
		
		
		}
	//THIS METHOD DISPLAYS A NEW MATRIX IN THE FRAME
	public void DisplayNewMatrix(int[][] matrixToDisplay){
		
		for(int i = 0; i < matrixToDisplay.length; i++){
			for(int j = 0; j < matrixToDisplay.length; j ++){
			
				if(matrixToDisplay[i][j] == 1)
					labelsInBoard[i][j].setIcon(blackIcon);
				else if(matrixToDisplay[i][j] == 2)
					labelsInBoard[i][j].setIcon(whiteIcon);
				else if(matrixToDisplay[i][j] == 0)
					labelsInBoard[i][j].setIcon(blankIcon);
			}
		}
		
	}

	public void setArrayOfMoves(List<MovementObject> arrayOfMoves) {
		this.arrayOfMoves = arrayOfMoves;
	}

	
	public void setWhosPlaying(int whosPlaying) {
		this.whosPlaying = whosPlaying;
	}

	public MovementObject getMoveThatWasPicked() {
		return moveThatWasPicked;
	}
	
	public int GetWhichButtonIsSelected(){
		int returningInt = 0;
		if(radioButton3.isSelected())
			returningInt = 2;
		else if(radioButton5.isSelected())
			returningInt = 3;
		else if(radioButton7.isSelected())
			returningInt = 4;
		
		return returningInt;
	}

	
	
	

}