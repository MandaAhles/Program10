/**
 * Comp Sci 251
 * sc. 805
 * created by Amanda McTavish
 * updated 12/15
 */

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class EightQueens extends JFrame{
	public static void main(String[] args){
		
			try {
			// Set cross-platform Java L&F (also called "Metal") 
				UIManager.setLookAndFeel(
						UIManager.getCrossPlatformLookAndFeelClassName());
			}catch (Exception e) {
				System.out.println("Cross Platform transformation couldn't take place.");
				return;
			}
			new EightQueens();
	}

	private Square[][] board;
	private int queenCount=0;
	private int nWins=0;
	private int nLosses=0;
	//any additional swing components must be here so can be used by rest of prog.
	JPanel control = new JPanel(new GridLayout(11, 3));
	
	JPanel gameBoard= new JPanel(new GridLayout(8, 8)); 
	//^ not sure need this with Square class board, new name in any case.
	JButton startOver;
	JRadioButton alwaysShow;
	JRadioButton showMousePress;
	
	JTextField lastResult;
	JTextField wins;
	JTextField losses;

	public EightQueens(){
		setSize(700, 550);
		setLayout(new BorderLayout(3,1));
		setTitle("Eight Queens Puzzle");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createContents();
		setVisible(true);
	}
	
	public void createContents(){
		//instantiate stuff
		JLabel controlLabel= new JLabel("Controls");
		JLabel chooseVisibilityLabel= new JLabel("Show Unsafe Spaces: ");
		
		JLabel lResultLabel= new JLabel("Last Result: ");
		JLabel winsLabel= new JLabel("Wins: ");
		JLabel lossLabel= new JLabel("Losses: ");
		
		alwaysShow= new JRadioButton("Always", true);
		showMousePress= new JRadioButton("When Mouse Pressed");
		ButtonGroup group= new ButtonGroup();
		group.add(alwaysShow);
		group.add(showMousePress);
		
		startOver=new JButton("Start Over");
		lastResult= new JTextField(0);
		lastResult.setVisible(false);//don't want to see the actual box, just result
		wins= new JTextField(0);
		losses= new JTextField(0);
		
		//add any listeners
		startOver.addActionListener(new Listener());
		
		board= new Square[8][8];
		for (int i = 0; i < 8; i++) {
			board[i] = new Square[8]; 
				for (int j = 0; j < 8; j++){
					board[i][j] = new Square();
					gameBoard.add(board[i][j]);				
				}
		}
		
		//add components to controls
		control.add(controlLabel);
		control.add(chooseVisibilityLabel);
		control.add(alwaysShow);
		control.add(showMousePress);
		control.add(startOver);
		control.add(lResultLabel, LEFT_ALIGNMENT);
		control.add(lastResult);
		control.add(winsLabel);
		control.add(wins);
		control.add(lossLabel);
		control.add(losses);
		
		
		//add it all together within JFrame
		add(gameBoard, BorderLayout.CENTER);
		add(control, BorderLayout.EAST);

	}//end createContents
	
	private void markSquares(){
		//search for squares in "queen state" (state=2);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++){
				if(board[i][j].getState()==2){
					try{
						Image img=ImageIO.read(getClass().getResource("queen-icon.jpg"));
						board[i][j].setIcon(new ImageIcon(img));
					}catch(IOException e){
						System.out.println("File not found");
						return;
					}
					markSquaresForOneQueen(i, j);//i=qRow, j=qCol
				}
			}
		}
	}
	
	private void markSquaresForOneQueen(int qRow, int qCol){
		//marks "unsafe" squares
		//state of square clicked: state=2
		for(int i=0; i<8; i++){//vertical and horizontal 
			for(int j=0; j<8; j++){
				if(i==qRow && j!=qCol){
					board[i][j].setState(1);
					board[i][j].markUnsafe();
				}
				if(j==qCol && i!=qRow){
					board[i][j].setState(1);
					board[i][j].markUnsafe();
				}
			}
		}
		//NE and SW diagonal
		for (int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if ((i!=qRow || j!=qCol)){//not the queen square
					if ((i+j)==(qRow+qCol)){
						board[i][j].setState(1);
						board[i][j].markUnsafe();
					}
				}
			}
		}
		//NW, i and j are <qRow and qCol
		for(int i=qRow, j=qCol, k=1; i>-1; i--, j--){
			if((i==(qRow-k)) && (j==(qCol-k) && ((qRow-k)>=0) && ((qCol-k)>=0))){
				board[i][j].setState(1);
				board[i][j].markUnsafe();
				k++;
			}
		}
		
		//SE, i and j are >qRow and qCol
		for(int i=qRow, j=qCol, k=1; i<8; i++, j++){
			if((i==(qRow+k)) && (j==(qCol+k) && ((qRow+k)<8) && ((qCol+k)<8))){
				board[i][j].setState(1);
				board[i][j].markUnsafe();
				k++;
			}
		}
		
	}//end markForOneQueenSquares
	
	private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			boolean giveUp=false;
			
			if (e.getSource()==startOver){
				
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++){
						int qState=board[i][j].getState();
						if(qState==0){
							//at least one square is open still, so giving up.
							giveUp=true;
						}
					}
				}
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++){
						board[i][j].clear();
						validate();
					}
				}
				
				
				if(giveUp==true){
					nLosses++;
					losses.setText(""+ nLosses);
					lastResult.setVisible(true);
					lastResult.setBackground(null);
					lastResult.setBorder(null);
					lastResult.setText("Loss");
				}
				else{
					nWins++;
					wins.setText(""+nWins);
					lastResult.setVisible(true);
					lastResult.setBackground(null);
					lastResult.setBorder(null);
					lastResult.setText("Win!");
				}
			}
			
			else{//from square:
				//which square
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++){
						if(board[i][j]==e.getSource()){//mark it as Queen
							board[i][j].setState(2);
						}
					}
				}
				markSquares();
			}
				
			
		}
	}//ends listener class
	
	private class Square extends JButton {
		int state;//0 for safe, 1 for unsafe, 2 for queen.
		public Square() {
			super.setContentAreaFilled(false);
			// add Listener here
			addActionListener(new Listener());
			state=0;//starts out as 0 for Safe.
			
		}
		public int getState(){
			return state;
		}
		public void setState(int state){
			this.state=state;
		}
		public void markUnsafe(){
			//set all background colors of state=1 to RED
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++){
					if (board[i][j].getState()==1){
						board[i][j].setBackground(Color.RED);
					}
				}
			}
		}
		public void clear(){
			//reset all squares to state=0;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++){
					board[i][j].setState(0);
					board[i][j].setBackground(null);
					board[i][j].setIcon(null);
				}
			}
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight()); 
			super.paintComponent(g);
		} 
	}//ends Square class
}//ends EightQueens class
