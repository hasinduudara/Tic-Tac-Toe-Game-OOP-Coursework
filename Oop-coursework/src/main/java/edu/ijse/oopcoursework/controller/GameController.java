package edu.ijse.oopcoursework.controller;

import edu.ijse.oopcoursework.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

        @FXML
        private AnchorPane ap;

        @FXML
        private Button btn1;

        @FXML
        private Button btn2;

        @FXML
        private Button btn3;

        @FXML
        private Button btn4;

        @FXML
        private Button btn5;

        @FXML
        private Button btn6;

        @FXML
        private Button btn7;

        @FXML
        private Button btn8;

        @FXML
        private Button btn9;

        @FXML
        private GridPane gp;

        @FXML
        private Label namelbl;

        @FXML
        private Button restartbtn;

        @FXML
        private Label winninglbl;

        @FXML
        private Pane pane;

        BoardImpl boardImpl = new BoardImpl();

        public static Button[][] buttons = new Button[3][3];

        public void initialize(URL location, ResourceBundle resources) {
                winninglbl.setVisible(false);
                winninglbl.setStyle("-fx-text-fill: red; font-weight: bold; -fx-font-size: 25px; -fx-alignment: center;");

                buttons[0][0] = btn1;
                buttons[0][1] = btn2;
                buttons[0][2] = btn3;
                buttons[1][0] = btn4;
                buttons[1][1] = btn5;
                buttons[1][2] = btn6;
                buttons[2][0] = btn7;
                buttons[2][1] = btn8;
                buttons[2][2] = btn9;

        }

        @FXML
        void btnOnAction(ActionEvent event) {

                Button clickedButton = (Button) event.getSource();
                String buttonId = clickedButton.getId();

                int row = -1;
                int col = -1;

                //System.out.println("Button clicked: " + buttonId);

                if (clickedButton.getText().isEmpty()) {
                        //System.out.println("Button is Valid");

                        for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < 3; j++) {
                                        if (buttons[i][j] == clickedButton) {
                                                row = i;
                                                col = j;
                                        }
                                }
                        }

                        /*we need to parse the button to the humanplayer move to set the value to the button so we pass it through the constructor*/
                        HumanPlayer humanPlayer = new HumanPlayer(boardImpl, clickedButton);
                        humanPlayer.move(row, col);

                        String pieces1 = BoardImpl.pieces1;

                        if (pieces1.equals("X")) {
                                loadResponse("X");
                        } else if ("O".equals(pieces1)) {
                                loadResponse("O");
                        } else if (pieces1.equals("draw")) {
                                loadResponse("draw");
                        }

                        /*we  do not need to parse the button to the aiplayer move so we do not have to pass it through the constructor*/
                        AIPlayer aiPlayer = new AIPlayer(boardImpl);
                        aiPlayer.move(row, col);

                        String pieces2 = BoardImpl.pieces1;
                        if (pieces2.equals("X")) {
                                loadResponse("X");
                        } else if ("O".equals(pieces2)) {
                                loadResponse("O");
                        } else if (pieces2.equals("draw")) {
                                loadResponse("draw");
                        }
                        // Reapply the border style to the clicked button

                        // clickedButton.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-font-size: 24px; -fx-alignment: center;");
                }else{
                        System.out.println("Invalid move");
                }
        }

        public static Button[][] getButtons() {
                return buttons;
        }


        public void loadResponse(String result){
                disableButtons();

                winninglbl.setVisible(true);
                if (result.equals("X")) {
                        restartbtn.setVisible(true);
                        winninglbl.setText(" You Won");
                }else if (result.equals("O")) {
                        restartbtn.setVisible(true);
                        winninglbl.setText(" You Lost");
                } else if (result.equals("draw")) {
                        restartbtn.setVisible(true);
                        winninglbl.setText(" The Game is a Draw");
                }

        }

        public void disableButtons(){
                for (int i = 0; i < buttons.length; i++) {
                        for (int j = 0; j < buttons.length; j++) {
                                buttons[i][j].setDisable(true);
                        }
                }
        }

        @FXML
        void restartbtnOnAction(ActionEvent event) {
                boardImpl.resetPieces();
                winninglbl.setVisible(false);

                for (int i = 0; i < buttons.length; i++) {
                        for (int j = 0; j < buttons.length; j++) {
                                buttons[i][j].setDisable(false);
                        }
                }

                for (Button[] row : buttons) {
                        for (Button cell : row) {
                                cell.setText("");
                                cell.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-font-size: 24px; -fx-alignment: center;");
                        }
                }
        }

}