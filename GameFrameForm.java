
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

public class GameFrameForm extends JFrame{

    String[] gameNameOptions = { "Removal", "21" };
    private final Game game;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JLabel turnLabel;
    private JTextArea resultArea;
    private JSpinner spinner1;
    private JTextField playerTwoField;
    private JTextField playerOneField;
    private JButton changeTurnButton;
    private JLabel turnCounter;
    private JButton enterButton;
    private JTextArea winnersText;
    private JComboBox gameNameBox = new JComboBox(gameNameOptions);
    private boolean endTurn = true;

    public GameFrameForm() {
        setMinimumSize(new Dimension(600, 600));
        setContentPane(panel1);
        pack();
        game = new Game();
        boolean playerCheck = game.checkPlayer();
        String gameName = "";

        gameNameBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                final String gameName = gameNameBox.getSelectedItem().toString();

            }
        });


        if (Objects.equals(gameName, "Removal")) {
            changeTurnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(Game.firstPlayer);
                    if (endTurn) {
                        if (game.turnNumber == 0) {
                            resultArea.setText("The player who takes the last tile loses.");
                            changeTurnButton.setText("Change Turn");
                            game.playerOne = playerOneField.getText();
                            game.playerTwo = playerTwoField.getText();
                            Player.nameInputs(game.playerOne, game.playerTwo);
                            game.startGame();
                            resultArea.append("There are currently " + Game.pileSize + " tiles.");
                        }
                        boolean playerCheck = game.checkPlayer();
                        game.maxTileChoice = Pile.getMaxLegalMove(Game.pileSize);
                        if (playerCheck && game.maxTileChoice > 1) {
                            game.promptHumanMove();
                            resultArea.append("\n" + game.response);
                            endTurn = false;
                        } else if (!playerCheck && game.maxTileChoice > 1) {
                            game.doComputerMove();
                            resultArea.append("\n" + game.response);
                            endTurn = true;
                        } else {
                            game.finishGame();
                            resultArea.append("\n" + game.response);
                            changeTurnButton.setText("Press to replay!");
                            winnersText.append("\n" + game.winner);
                            return;
                        }

                        turnCounter.setText("Turn " + (game.turnNumber++));
                        turnLabel.setText(Player.getName(game.turnNumber + 1, Game.firstPlayer) + "'s turn.");
                    } else {
                        resultArea.append("\n Please choose a number of tiles to take.");
                    }
                    System.out.println("Change Turn Button Pressed");

                }
            });
            enterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean playerCheck = game.checkPlayer();
                    if (game.maxTileChoice > 0 && !endTurn) {
                        int choice = (int) spinner1.getValue();
                        System.out.println("Choice = " + choice);
                        game.doHumanMove(choice);
                        if (game.invalidMove) {
                            game.errorResult();
                            resultArea.append("\n " + game.response);
                            return;
                        }
                        resultArea.append("\n" + game.response);
                        endTurn = true;
                    } else if (endTurn) {
                        resultArea.append("\n Please Press Change Turn.");
                    }
                    System.out.println(Player.getName(game.turnNumber + 1, Game.firstPlayer) + "'s turn");
                    System.out.println("Enter button pressed");
                }
            });
        } else if (Objects.equals(gameName, "21")){
            changeTurnButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (endTurn){
                        if (game.turnNumber == 0){
                            resultArea.setText("The player who takes the last tile wins.");
                            changeTurnButton.setText("Change Turn");
                            game.playerOne = playerOneField.getText();
                            game.playerTwo = playerTwoField.getText();
                            Player.nameInputs(game.playerOne, game.playerTwo);
                            game.startGame21();
                            resultArea.append("There are currently " + Game.pileSize + " tiles.");
                        }

                    }

                }
            });

            enterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }
        gameNameBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });
    }

}
