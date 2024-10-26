package edu.ijse.oopcoursework.service;

import javafx.scene.control.Button;
import edu.ijse.oopcoursework.controller.GameController;

public class AIPlayer extends Player {

    private final BoardImpl boardImpl;

    public AIPlayer(BoardImpl boardImpl) {
        super(boardImpl);
        this.boardImpl = boardImpl;
    }


    public void move(int row, int col) {
        Piece piece = Piece.O; // AI's piece
        Button[][] aiButtons = GameController.getButtons();

        int[] bestMove = chooseBestMove();

        if (bestMove != null) {
            int aiRow = bestMove[0];
            int aiCol = bestMove[1];

            /* check the button of the selected move is empty */
            if (aiButtons[aiRow][aiCol].getText().isEmpty()) {
                aiButtons[aiRow][aiCol].setText("O");
                //aiButtons[aiRow][aiCol].setStyle("-fx-text-fill: blue;");
                aiButtons[aiRow][aiCol].setStyle("-fx-text-fill: blue; -fx-font-size: 50px; -fx-alignment: center;");
                aiButtons[aiRow][aiCol].setDisable(true);

                boardImpl.updateMove(aiRow, aiCol, piece);
            } else {
                System.out.println("Invalid Button");
            }
        }
    }


    public int[] chooseBestMove() {
        int topScore = Integer.MIN_VALUE;
        int[] bestMove = null;/* we use this to save the best  move row and col*/

        /*  find how much the board is full and which places are empty (present state of the board) */
        Piece[][] currentBoard = boardImpl.getPieces();

        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[i].length; j++) {
                /* */
                if (currentBoard[i][j] == Piece.EMPTY) {
                    currentBoard[i][j] = Piece.O;
//                    System.out.println("Ai is thinking");
//                    boardImpl.printBoard();

                        // Use minimax to evaluate this move
                    int score = minimax(currentBoard, 0, false, false);

                        // Undo the move to restore the original state
                    currentBoard[i][j] = Piece.EMPTY;

                        // Update the best move if a higher score is found
                    if (score > topScore) {
                        topScore = score;
                        bestMove = new int[]{i, j}; /* we can only put two values at one to an array if we create a new one here */
                    }
                }
            }
        }
        return bestMove;
    }


        // Minimax algorithm to evaluate the best move
    public int minimax(Piece[][] currentBoard, int depth, boolean isMaximizing, boolean realMove) {
        Piece winner = boardImpl.checkWinner(realMove); // Check if there's a winner

            // Base case: Return a score based on the outcome of the game
        if (winner == Piece.O) return 10 - depth; // AI wins
        if (winner == Piece.X) return depth - 10; // Opponent wins
        if (boardImpl.isBoardFull()) return 0; // Draw

        if (isMaximizing) { // Maximizing for AI
            int bestScore = Integer.MIN_VALUE; // Initialize to the lowest score

                // Evaluate all possible moves for the AI
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (currentBoard[i][j] == Piece.EMPTY) {
                        currentBoard[i][j] = Piece.O; // Simulate AI's move

//                        System.out.println("Ai is thinking");
//                        boardImpl.printBoard();

                            // Recursively call minimax for the opponent's turn
                        int score = minimax(currentBoard, depth + 1, false, false);

                        currentBoard[i][j] = Piece.EMPTY; // Undo the move

                        bestScore = Math.max(score, bestScore); // Track the maximum score
                    }
                }
            }
            return bestScore;
        } else { // Minimizing for the opponent
            int bestScore = Integer.MAX_VALUE; // Initialize to the highest score

                // Evaluate all possible moves for the opponent
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (currentBoard[i][j] == Piece.EMPTY) {
                        currentBoard[i][j] = Piece.X; // Simulate opponent's move

//                        System.out.println("Ai is thinking");
//                        boardImpl.printBoard();

                            // Recursively call minimax for the AI's turn
                        int score = minimax(currentBoard, depth + 1, true, false);

                        currentBoard[i][j] = Piece.EMPTY; // Undo the move

                        bestScore = Math.min(score, bestScore); // Track the minimum score
                    }
                }
            }
            return bestScore;
        }
    }
}