/**
 * Comp Sci 251
 * sc. 805
 * created by Amanda McTavish
 * updated 12/8
 * 
 * notes: board is null.
 */

import javax.swing.*;



import java.awt.*;
import java.awt.event.*;

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
		setSize(700, 550);//these are the numbers from lectures' example.
		setLayout(new BorderLayout(3,1));//not sure what the border size should be.
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
		
		//radio buttons instantiated here, group together.
		alwaysShow= new JRadioButton("Always", true);
		showMousePress= new JRadioButton("When Mouse Pressed");
		ButtonGroup group= new ButtonGroup();
		group.add(alwaysShow);
		group.add(showMousePress);
		
		//add listeners for Radio Buttons???
		//alwaysShow.addActionListener((ActionListener) this);
		//showMousePress.addActionListener((ActionListener) this);
		
		startOver=new JButton("Start Over");
		lastResult= new JTextField(0);
		lastResult.setVisible(false);//don't want to see the actual box, just result
		wins= new JTextField(0);
		losses= new JTextField(0);
		
		//add any listeners needed???
		startOver.addActionListener(new Listener());
		//add components to panels:
		//fill gameBoard panel with squares= board
		board= new Square[8][8];
		for (int i = 0; i < 8; i++) {
			board[i] = new Square[8]; 
				for (int j = 0; j < 8; j++){
					board[i][j] = new Square();
					//board[i][j].addActionListener(new Listener());
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
		
		/*  put it all together. 
		 * listener for startOver button, board buttons, radio buttons?
		 */
		
	}//end createContents
	private void markSquares(){
		//search for squares in "queen state" (state=2);
		//calls markSquaresForOneQueen()
	}
	private void markSquaresForOneQueen(){
		//marks "unsafe" squares there (6 loops) by calling Square.markUnsafe()
		//complex 6-loop here:
		//state of square clicked: state=2 (2 loops)
		//state of squares diag to NE, SE, SW, and NW state=1 (4 loops)

	}
	private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			/*
			 * if get.Source is from startOver button, clear/refresh screen,
			 * inc win of loss (how will know? all buttons are "unsafe (1)"
			 * or the queen (2)?)
			 * if all buttons are "unsafe" or "queen", update wins++
			 * else update losses++ (gave up)
			 * then:
			 * clear board;
			 * display "last result" in text field
			 * 
			 * else if (get.Source is from any button in the board: update board.)
			 * (< easier said than done...) 
			 * call markSquares and markSquaresForOneQueen methods?
			 */
			
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
		public void markUnsafe(){
			//set all background colors of state=1 to RED
				
		}
		public void clear(){
			//reset all squares to state=0;
			for (int i = 0; i < 8; i++) {
				//board[i] = new Square[8]; 
					for (int j = 0; j < 8; j++){
						//board[i][j] = new Square();
						board[i][j].state=0;
						//state=0;
					}
			}
			//reset background to null
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight()); 
			super.paintComponent(g);
		} 
	}//ends Square class
}//ends EightQueens class
