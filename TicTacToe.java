import java.util.Scanner;

public class TicTacToe {
    
    // Constants for board size and player symbols
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY_CELL = '-';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    
    // Game board represented as a 2D character array
    private char[][] board;
    
    // Current player ('X' or 'O')
    private char currentPlayer;
    
    // Scanner for reading user input
    private Scanner scanner;
    
    /**
     * Constructor - Initializes the game with an empty board and sets Player X to start.
     */
    public TicTacToe() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        scanner = new Scanner(System.in);
        initializeBoard();
        currentPlayer = PLAYER_X; // X always starts first
    }
    
    /**
     * Initializes or resets the game board by filling all cells with empty markers.
     */
    private void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = EMPTY_CELL;
            }
        }
    }
    
    /**
     * Displays the current state of the game board in a formatted grid.
     */
    private void displayBoard() {
        System.out.println();
        System.out.println("  0   1   2  "); // Column headers
        System.out.println("-------------");
        
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print(row + "|"); // Row number
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(" " + board[row][col] + " |");
            }
            System.out.println();
            System.out.println("-------------");
        }
        System.out.println();
    }
    
    /**
     * Displays the game instructions and welcome message.
     */
    private void displayInstructions() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║           WELCOME TO TIC TAC TOE!                        ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║  How to Play:                                            ║");
        System.out.println("║  • Player 1 uses 'X' and Player 2 uses 'O'               ║");
        System.out.println("║  • Enter your move as: row column (e.g., 0 1)            ║");
        System.out.println("║  • Row and column numbers range from 0 to 2              ║");
        System.out.println("║  • Get three in a row (horizontal/vertical/diagonal)     ║");
        System.out.println("║    to win!                                               ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║  Board Positions:                                        ║");
        System.out.println("║     0   1   2                                            ║");
        System.out.println("║   ┌───┬───┬───┐                                          ║");
        System.out.println("║  0│0,0│0,1│0,2│                                          ║");
        System.out.println("║   ├───┼───┼───┤                                          ║");
        System.out.println("║  1│1,0│1,1│1,2│                                          ║");
        System.out.println("║   ├───┼───┼───┤                                          ║");
        System.out.println("║  2│2,0│2,1│2,2│                                          ║");
        System.out.println("║   └───┴───┴───┘                                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
    }
    
    /**
     * Validates whether the specified position is within the board bounds.
     * 
     * @param row The row index to validate
     * @param col The column index to validate
     * @return true if the position is valid, false otherwise
     */
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }
    
    /**
     * Checks if the specified cell is empty and available for a move.
     * 
     * @param row The row index to check
     * @param col The column index to check
     * @return true if the cell is empty, false otherwise
     */
    private boolean isCellEmpty(int row, int col) {
        return board[row][col] == EMPTY_CELL;
    }
    
    /**
     * Attempts to place the current player's symbol at the specified position.
     * 
     * @param row The row index for the move
     * @param col The column index for the move
     * @return true if the move was successful, false if the cell is occupied
     */
    private boolean makeMove(int row, int col) {
        if (isCellEmpty(row, col)) {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }
    
    /**
     * Switches the current player from X to O or O to X.
     */
    private void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }
    
    /**
     * Checks if the current player has won the game.
     * Examines all rows, columns, and diagonals for three matching symbols.
     * 
     * @return true if the current player has won, false otherwise
     */
    private boolean checkWinner() {
        // Check all rows for a win
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][0] == currentPlayer && 
                board[row][1] == currentPlayer && 
                board[row][2] == currentPlayer) {
                return true;
            }
        }
        
        // Check all columns for a win
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[0][col] == currentPlayer && 
                board[1][col] == currentPlayer && 
                board[2][col] == currentPlayer) {
                return true;
            }
        }
        
        // Check main diagonal (top-left to bottom-right)
        if (board[0][0] == currentPlayer && 
            board[1][1] == currentPlayer && 
            board[2][2] == currentPlayer) {
            return true;
        }
        
        // Check anti-diagonal (top-right to bottom-left)
        if (board[0][2] == currentPlayer && 
            board[1][1] == currentPlayer && 
            board[2][0] == currentPlayer) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Checks if the game has ended in a draw (all cells filled, no winner).
     * 
     * @return true if the board is full, false otherwise
     */
    private boolean checkDraw() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == EMPTY_CELL) {
                    return false; // Found an empty cell, game can continue
                }
            }
        }
        return true; // No empty cells found, it's a draw
    }
    
    /**
     * Gets and validates the player's move input.
     * Continues prompting until a valid move is entered.
     * 
     * @return An array containing [row, col] of the valid move
     */
    private int[] getPlayerMove() {
        int row = -1;
        int col = -1;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print("Player " + currentPlayer + ", enter your move (row column): ");
            
            try {
                // Read the row and column from user input
                String input = scanner.nextLine().trim();
                String[] parts = input.split("\\s+");
                
                if (parts.length != 2) {
                    System.out.println("⚠ Invalid input! Please enter two numbers separated by space (e.g., 0 1)");
                    continue;
                }
                
                row = Integer.parseInt(parts[0]);
                col = Integer.parseInt(parts[1]);
                
                // Validate the position is within bounds
                if (!isValidPosition(row, col)) {
                    System.out.println("⚠ Invalid position! Row and column must be between 0 and 2.");
                    continue;
                }
                
                // Check if the cell is already occupied
                if (!isCellEmpty(row, col)) {
                    System.out.println("⚠ That cell is already occupied! Choose another position.");
                    continue;
                }
                
                validInput = true;
                
            } catch (NumberFormatException e) {
                System.out.println("⚠ Invalid input! Please enter numbers only (e.g., 0 1)");
            }
        }
        
        return new int[]{row, col};
    }
    
    /**
     * Asks players if they want to play another game.
     * 
     * @return true if players want to play again, false otherwise
     */
    private boolean askPlayAgain() {
        System.out.print("Would you like to play again? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }
    
    /**
     * Resets the game for a new round.
     */
    private void resetGame() {
        initializeBoard();
        currentPlayer = PLAYER_X; // X always starts first
        System.out.println("\n🎮 Starting a new game!\n");
    }
    
    /**
     * Main game loop that handles the flow of the game.
     * Continues until a player wins, the game ends in a draw,
     * or players choose not to play again.
     */
    public void play() {
        displayInstructions();
        
        boolean playAgain = true;
        
        while (playAgain) {
            boolean gameOver = false;
            
            // Display initial empty board
            displayBoard();
            
            // Main game loop
            while (!gameOver) {
                // Get valid move from current player
                int[] move = getPlayerMove();
                int row = move[0];
                int col = move[1];
                
                // Make the move
                makeMove(row, col);
                
                // Display updated board
                displayBoard();
                
                // Check for winner
                if (checkWinner()) {
                    System.out.println("🎉 Congratulations! Player " + currentPlayer + " wins! 🎉");
                    gameOver = true;
                }
                // Check for draw
                else if (checkDraw()) {
                    System.out.println("🤝 It's a draw! Well played both!");
                    gameOver = true;
                }
                // Switch to other player if game continues
                else {
                    switchPlayer();
                }
            }
            
            // Ask if players want to play again
            playAgain = askPlayAgain();
            
            if (playAgain) {
                resetGame();
            }
        }
        
        // Game ended
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║        Thanks for playing Tic Tac Toe! Goodbye!          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Main method - Entry point of the application.
     * Creates a new TicTacToe game instance and starts the game.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}
