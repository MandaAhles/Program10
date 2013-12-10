
public class Notes {
/**
 * very sim to Lab12
 * LAYOUT:
 * a board layout (11, 1?)
 * (CENTER, grid layout(8, 8)) and the control buttons on side (EAST, grid layout (2, 1))
 * for grid part of the board: Square Class (extends JButton)
 * create a board:
 * Square board= new Square[8][8]; this is 2 dimention array
 * to fill the board with squares: (like TicTacToe)
 * -create buttons and add an action listener to the button.
 * for(int i=0; i<8; i++)//if i is board [3][j], can change all i's to red.
 * {
 * 	for(int j=0; j<8; j++)
 * 	{
 * 		board[i][j] = new Square();
 * 		add(board[i][j]);
 * 		//this allows access to each individual button. we need to know information about this square 
 * 		//(ie: horiz, vert, diag, make "unsafe")
 * 	}
 * }
 * 0's, 1's, 2's. Queen square=2, red squares=1, and open spaces=0. Job is to change the squares to 0, 1, or 2.
 * 
 * extra credits: changing the buttons to show "safeness" when click and hold the buttons; flash the win/loss
 * DO THE BASICS FIRST, then if there is time, can do the ec.
 * 
 * maybe this will work. How do I save this to gitHub initially?
 * 
 * 
 * from lecture 12/8: 
 * 
 * By analyzing the the window at different sizes, can read the layouts chosen:
 * 
 * JFrame is boarderLayout: gameBoard is Center, controls are East;
 * gameBoard is gridLayout(8, 8); "it's made up of 8x8 grid pattern that
 * are always the same size and shape;
 * control panel (would be a good one for: flowLayout): gridLayout: one column, many rows,
 * when sizes are adjusted, they mostly stay the same size.
 * startOver button: suggests that it is within a panel, and has a flow layout (doens't go to edges)
 * center alignment of labels is default, and the lastResult label is specified as left Alignment
 * Wins/Losses lines are in a flow layout, centered.
 * 
 * 
 * useful code:
 * for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++){
				if(board[i][j].getState()==0){
					
				}
			}
		}
 * 
 */
}
