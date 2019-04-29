/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TTT;

import java.util.Scanner;
/**
 *
 * @author yeric
 */
public class TTTcustom {

        // Name-constants to represent the seeds and cell contents
        public static final int EMPTY = 0;
        public static final int CROSS = 1;
        public static final int NOUGHT = 2;

        // Name-constants to represent the various states of the game
        public static final int PLAYING = 0;
        public static final int DRAW = 1;
        public static final int CROSS_WON = 2;
        public static final int NOUGHT_WON = 3;
        
        public static int currentState;  // the current state of the game
                                    // (PLAYING, DRAW, CROSS_WON, NOUGHT_WON)
        public static int currentPlayer; // the current player (CROSS or NOUGHT)
        public static int currntRow, currentCol; // current seed's row and column
        
        public static Scanner inp = new Scanner(System.in);
        public static Scanner keyboard = new Scanner(System.in);
        public static int in = keyboard.nextInt();
        public static int[][] board = new int[in][in];
   
	public static void main( String[] args )
	{
                
                for ( int r=0; r<in; r++ )
			for ( int c=0; c<in; c++ )
                            	board[r][c] = EMPTY;
                        
                System.out.println("\t"+"------------");
		for ( int r=0; r<in; r++ )
		{
			System.out.print("\t |");
			for ( int c=0; c<in; c++ )
			{
				System.out.print( board[r][c] + "|" );
			}
                        System.out.println();
			System.out.println("\t"+"------------");
		}
                
                currentState = PLAYING; // ready to play
                currentPlayer = CROSS;  // cross plays first
      
                do {
                    playerMove(currentPlayer); // update currentRow and currentCol
                    updateGame(currentPlayer, currntRow, currentCol); // update currentState
                    printBoard();
                    // Print message if game-over
                    if (currentState == CROSS_WON) {
                       System.out.println("'X' won! Bye!");
                    } else if (currentState == NOUGHT_WON) {
                       System.out.println("'O' won! Bye!");
                    } else if (currentState == DRAW) {
                       System.out.println("It's a Draw! Bye!");
                    }
                    // Switch player
                    currentPlayer = (currentPlayer == CROSS) ? NOUGHT : CROSS;
                } while (currentState == PLAYING); // repeat if not game-over

	}
        
        public static void playerMove(int theSeed) {
            boolean validInput = false;  // for input validation
            do {
               if (theSeed == CROSS) {
                  System.out.print("Player 'X', enter your move row[]: ");
                  int row = (inp.nextInt() - 1);
                  System.out.print("Player 'X', enter your move column[] : ");
                  int col = (inp.nextInt() - 1);
                  if (row >= 0 && row < in && col >= 0 && col < in && board[row][col] == EMPTY) {
                  currntRow = row;
                  currentCol = col;
                  board[currntRow][currentCol] = theSeed;  // update game-board content
                  validInput = true;  // input okay, exit loop
               } else {
                  System.out.println("This move at (" + (row + 1) + "," + (col + 1)
                        + ") is not valid. Try again...");
               }

               } else {
                  System.out.print("Player 'O', enter your move (row[]: ");
                  int row = (inp.nextInt() - 1);
                  System.out.print("Player 'O', enter your move (column[] : ");
                  int col = (inp.nextInt() - 1);
                  if (row >= 0 && row < in && col >= 0 && col < in && board[row][col] == EMPTY) {
                  currntRow = row;
                  currentCol = col;
                  board[currntRow][currentCol] = theSeed;  // update game-board content
                  validInput = true;  // input okay, exit loop
                  } else {
                  System.out.println("This move at (" + (row + 1) + "," + (col + 1)
                        + ") is not valid. Try again...");
                 }
               }

            } while (!validInput);  // repeat until input is valid
        }

     /** Update the "currentState" after the player with "theSeed" has placed on
         (currentRow, currentCol). */
     public static void updateGame(int theSeed, int currentRow, int currentCol) {
            if (in == 3 ){
                if (hasWon(theSeed, currentRow, currentCol)) {  // check if winning move
                    currentState = (theSeed == CROSS) ? CROSS_WON : NOUGHT_WON;
                } else if (isDraw()) {  // check for draw
                    currentState = DRAW;
                }
            } else {
                if (hasWon2(theSeed, currentRow, currentCol)) {  // check if winning move
                    currentState = (theSeed == CROSS) ? CROSS_WON : NOUGHT_WON;
                } else if (isDraw()) {  // check for draw
                    currentState = DRAW;
                }
            }
                
        // Otherwise, no change to currentState (still PLAYING).
     }

     /** Return true if it is a draw (no more empty cell) */
     // TODO: Shall declare draw if no player can "possibly" win
     public static boolean isDraw() {
        for (int row = 0; row < in; ++row) {
           for (int col = 0; col < in; ++col) {
              if (board[row][col] == EMPTY) {
                 return false;  // an empty cell found, not draw, exit
              }
           }
        }
        return true;  // no empty cell, it's a draw
     }

     /** Return true if the player with "theSeed" has won after placing at
         (currentRow, currentCol) */
     public static boolean hasWon(int theSeed, int currentRow, int currentCol) {
        return (board[currentRow][0] == theSeed         // 3-in-the-row
                     && board[currentRow][1] == theSeed
                     && board[currentRow][2] == theSeed
                || board[0][currentCol] == theSeed      // 3-in-the-column
                     && board[1][currentCol] == theSeed
                     && board[2][currentCol] == theSeed
                || currentRow == currentCol            // 3-in-the-diagonal
                     && board[0][0] == theSeed
                     && board[1][1] == theSeed
                     && board[2][2] == theSeed
                || currentRow + currentCol == 2  // 3-in-the-opposite-diagonal
                     && board[0][2] == theSeed
                     && board[1][1] == theSeed
                     && board[2][0] == theSeed);
     }
     
     public static boolean hasWon2(int theSeed, int currentRow, int currentCol) {
        if (board[currentRow][currentCol] == theSeed      
                     && board[currentRow][currentCol-1] == theSeed
                     && board[currentRow][currentCol-2] == theSeed
                     && board[currentRow][currentCol-3] == theSeed)
        {
            return true;
        }
                else if
                        (board[currentRow][currentCol] == theSeed      
                     && board[currentRow][currentCol-1] == theSeed
                     && board[currentRow][currentCol-2] == theSeed
                     && board[currentRow][currentCol+1] == theSeed)
        {
            return true;
        }
                else if
                        (board[currentRow][currentCol] == theSeed      
                     && board[currentRow][currentCol-1] == theSeed
                     && board[currentRow][currentCol+1] == theSeed
                     && board[currentRow][currentCol+2] == theSeed)
        {
            return true;
        }
                else if
                        (board[currentRow][currentCol] == theSeed      
                     && board[currentRow][currentCol+1] == theSeed
                     && board[currentRow][currentCol+2] == theSeed
                     && board[currentRow][currentCol+3] == theSeed)
        {
            return true;
        }
                else if
                        (board[currentRow][currentCol] == theSeed     
                     && board[currentRow+1][currentCol] == theSeed
                     && board[currentRow+2][currentCol] == theSeed
                     && board[currentRow+3][currentCol] == theSeed)
        {
            return true;
        }
                else if
                        (board[currentRow][currentCol] == theSeed     
                     && board[currentRow+1][currentCol] == theSeed
                     && board[currentRow+2][currentCol] == theSeed
                     && board[currentRow-1][currentCol] == theSeed)
        {
            return true;
        }
                else if
                        (board[currentRow][currentCol] == theSeed     
                     && board[currentRow+1][currentCol] == theSeed
                     && board[currentRow-2][currentCol] == theSeed
                     && board[currentRow-1][currentCol] == theSeed)
        {
            return true;
        }
                else if
                        (board[currentRow][currentCol] == theSeed     
                     && board[currentRow-1][currentCol] == theSeed
                     && board[currentRow-2][currentCol] == theSeed
                     && board[currentRow-3][currentCol] == theSeed)
        {
            return true;
        }
                else if
                        (currentRow == currentCol            
                     && board[currentRow-1][currentCol-1] == theSeed
                     && board[currentRow-2][currentCol-2] == theSeed
                     && board[currentRow-3][currentCol-3] == theSeed)
        {
            return true;
        }
                else if
                        (currentRow == currentCol            
                     && board[currentRow-1][currentCol-1] == theSeed
                     && board[currentRow-2][currentCol-2] == theSeed
                     && board[currentRow+1][currentCol+1] == theSeed)
        {
            return true;
        }
                else if
                        (currentRow == currentCol            
                     && board[currentRow-1][currentCol-1] == theSeed
                     && board[currentRow+2][currentCol+2] == theSeed
                     && board[currentRow+1][currentCol+1] == theSeed)
        {
            return true;
        }
                else if
                        (currentRow == currentCol            
                     && board[currentRow+1][currentCol+1] == theSeed
                     && board[currentRow+2][currentCol+2] == theSeed
                     && board[currentRow+3][currentCol+3] == theSeed)
        {
            return true;
        }
                else if
                        (currentRow + currentCol == (in+1) 
                     && board[currentRow+1][currentCol-1] == theSeed
                     && board[currentRow+2][currentCol-2] == theSeed
                     && board[currentRow-1][currentCol+1] == theSeed)
        {
            return true;
        }
                else if
                        (currentRow + currentCol == (in+1) 
                     && board[currentRow+1][currentCol-1] == theSeed
                     && board[currentRow-2][currentCol+2] == theSeed
                     && board[currentRow-1][currentCol+1] == theSeed)
        {
            return true;
        }
                else if
                        (currentRow + currentCol == (in+1) 
                     && board[currentRow-1][currentCol+1] == theSeed
                     && board[currentRow-2][currentCol+2] == theSeed
                     && board[currentRow-3][currentCol+3] == theSeed)
        {
            return true;
        }
                else if
                        (currentRow + currentCol == (in+1) 
                     && board[currentRow+1][currentCol-1] == theSeed
                     && board[currentRow+2][currentCol-2] == theSeed
                     && board[currentRow+3][currentCol-3] == theSeed)
        {
            return true;
        }
                else
                {}
        return false;          
     }

     /** Print the game board */
     public static void printBoard() {
        for (int row = 0; row < in; ++row) {
           for (int col = 0; col < in; ++col) {
              printCell(board[row][col]); // print each of the cells
              if (col != in - 1) {
                 System.out.print("|");   // print vertical partition
              }
           }
           System.out.println();
           if (row != in - 1) {
              System.out.println("-----------"); // print horizontal partition
           }
        }
        System.out.println();
     }

     /** Print a cell with the specified "content" */
     public static void printCell(int content) {
        switch (content) {
           case EMPTY:  System.out.print("   "); break;
           case NOUGHT: System.out.print(" O "); break;
           case CROSS:  System.out.print(" X "); break;
        }
     }

	
    
}
