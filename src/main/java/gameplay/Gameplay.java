package gameplay;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import playingfields.SimplestPlayingField;

public final class Gameplay implements ActionListener {
    static SimplestPlayingField playingField;
    private static int rowsNum;
    private static int columnsNum;
    private static int score = 0;
    private static int selectedRowNum = -1;
    private static int selectedColumnNum = -1;
    private static JButton[][] playingFieldButtons;
    private static JLabel statusBar;
    private static boolean hasSelectedTile = false;
    private static final int MAGNIFICATION_FACTOR = 100;
    private static final int DELAY = 500;

    private Gameplay() {
        playingField = new SimplestPlayingField();
        rowsNum = playingField.getRowsNum();
        columnsNum = playingField.getColumnsNum();

        playingFieldButtons = new JButton[rowsNum][columnsNum];
        JButton resetButton = new JButton("CREATE NEW PLAYING FIELD");
        JButton exitButton = new JButton("END GAME");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel playingFieldPanel = new JPanel(new GridLayout(rowsNum, columnsNum, 3, 3));

        statusBar = new JLabel("Welcome to the Three-in-a-Row game!");
        statusBar.setFont(statusBar.getFont().deriveFont(14.0f));

        JFrame frame = new JFrame("Three-in-a-Row");
        ImageIcon icon = new ImageIcon(
                Objects.requireNonNull(
                        getClass().getResource("/icons/applicationIcon/applicationIcon.png")));
        frame.setIconImage(icon.getImage());

        for (int i = 0; i < rowsNum; i++) {
            for (int j = 0; j < columnsNum; j++) {
                playingFieldButtons[i][j] = new JButton();

                playingFieldButtons[i][j].setIcon(
                        playingField.getUnselectedTileIcon(rowsNum - i, j + 1));

                playingFieldButtons[i][j].setFont(new Font("Monospaced", Font.PLAIN, 14));
                playingFieldButtons[i][j].setActionCommand(i + " " + j);
                playingFieldButtons[i][j].addActionListener(this);
                playingFieldButtons[i][j].setOpaque(true);
                playingFieldButtons[i][j].setBorderPainted(false);

                playingFieldPanel.add(playingFieldButtons[i][j]);
            }
        }

        resetButton.addActionListener(this);
        exitButton.addActionListener(this);

        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(statusBar, BorderLayout.SOUTH);
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(playingFieldPanel, BorderLayout.CENTER);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int frameWidth = MAGNIFICATION_FACTOR * columnsNum;
        int frameHeight = MAGNIFICATION_FACTOR * rowsNum;
        if (frameWidth > screenWidth) {
            frameWidth = screenWidth;
            frameHeight = (int) (screenWidth * ((double) rowsNum / columnsNum));
        }
        if (frameHeight > screenHeight) {
            frameWidth = (int) (screenHeight * ((double) columnsNum / rowsNum));
            frameHeight = screenHeight;
        }

        frame.setSize(frameWidth, frameHeight);
        frame.setLocation(screenWidth / 2 - frameWidth / 2, screenHeight / 2 - frameHeight / 2);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setStatus("Your score: " + score);
        switch (e.getActionCommand()) {
            case "CREATE NEW PLAYING FIELD" -> reset();
            case "END GAME" -> {
                setStatus("See you soon!");
                System.exit(0);
            }
            default -> {
                String[] input = e.getActionCommand().split(" ");
                int rowNum = Integer.parseInt(input[0]);
                int columnNum = Integer.parseInt(input[1]);
                swap(rowNum, columnNum);
            }
        }
    }

    private static void setStatus(String string) {
        statusBar.setText(string);
    }

    private void reset() {
        playingField = new SimplestPlayingField();
        redrawPlayingField();
    }

    private static void redrawPlayingField() {
        for (int i = 0; i < rowsNum; i++) {
            for (int j = 0; j < columnsNum; j++) {
                playingFieldButtons[i][j].setIcon(playingField.getUnselectedTileIcon(rowsNum - i, j + 1));
            }
        }
    }

    private void swap(int rowNum, int columnNum) {
        if (!hasSelectedTile) {
            hasSelectedTile = true;
            playingFieldButtons[rowNum][columnNum].setIcon(
                    playingField.getField()[rowsNum - rowNum][columnNum + 1].getSelectedTileIcon());
            selectedRowNum = rowNum;
            selectedColumnNum = columnNum;
            return;
        }

        if (selectedRowNum == rowNum && selectedColumnNum == columnNum) {
            hasSelectedTile = false;
            playingFieldButtons[rowNum][columnNum].setIcon(
                    playingField.getField()[rowsNum - rowNum][columnNum + 1].getUnselectedTileIcon());
            selectedRowNum = -1;
            selectedColumnNum = -1;
            return;
        }

        if (Math.abs(selectedRowNum - rowNum) + Math.abs(selectedColumnNum - columnNum) != 1) {
            setStatus("Tiles cannot be swapped, try again!");
            hasSelectedTile = false;
            playingFieldButtons[selectedRowNum][selectedColumnNum].setIcon(
                    playingField.getField()[rowsNum - rowNum][columnNum + 1].getUnselectedTileIcon());
            selectedRowNum = -1;
            selectedColumnNum = -1;
            return;
        }

        playingField.swap(playingField.getField()[rowsNum - selectedRowNum][selectedColumnNum + 1],
                playingField.getField()[rowsNum - rowNum][columnNum + 1]);
        if (playingField.hasReadyCombinations()) {
            GameplaySwingWorker gameSwingWorker = new GameplaySwingWorker();
            gameSwingWorker.execute();
        } else {
            playingField.swap(playingField.getField()[rowsNum - selectedRowNum][selectedColumnNum + 1],
                    playingField.getField()[rowsNum - rowNum][columnNum + 1]);
            setStatus("Tiles don't give any combination, try again!");
            redrawPlayingField();
        }

        hasSelectedTile = false;
        selectedRowNum = -1;
        selectedColumnNum = -1;
    }

    public static void main(String[] args) {
        new Gameplay();
    }

    private static class GameplaySwingWorker extends SwingWorker<Void, Void> {
        GameplaySwingWorker() {
            super();
        }

        @Override
        public Void doInBackground() throws Exception {
            redrawPlayingField();
            Thread.sleep(DELAY);

            balance();

            while (playingField.hasMissingTiles()) {
                playingField.fillingInTopRow();
                redrawPlayingField();
                Thread.sleep(DELAY);

                while (playingField.hasHangingTiles()) {
                    playingField.forceOfGravity();
                    redrawPlayingField();
                    Thread.sleep(DELAY);
                }

                balance();
            }

            redrawPlayingField();
            return null;
        }

        private void balance() throws InterruptedException {
            while (playingField.hasReadyCombinations()) {
                score += playingField.deleteReadyCombinations();
                setStatus("Your score: " + score);
                redrawPlayingField();
                Thread.sleep(DELAY);

                while (playingField.hasHangingTiles()) {
                    playingField.forceOfGravity();
                    redrawPlayingField();
                    Thread.sleep(DELAY);
                }
            }
        }
    }
}
