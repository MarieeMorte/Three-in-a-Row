package gameplay;

import playingFields.SimplestPlayingField;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gameplay implements ActionListener {
    private static SimplestPlayingField playingField;
    private static int rowsNum;
    private static int columnsNum;
    private static int score = 0;
    private static int selectedRowNum = -1;
    private static int selectedColumnNum = -1;
    private static JButton[][] playingFieldButtons;
    private static JLabel statusBar;
    private static boolean hasSelectedTile = false;
    private static final int MAGNIFICATION_FACTOR = 100;

    public Gameplay() {
        playingField = new SimplestPlayingField();
        rowsNum = playingField.getRowsNum();
        columnsNum = playingField.getColumnsNum();

        playingFieldButtons = new JButton[rowsNum][columnsNum];
        JButton resetButton = new JButton("RESET");
        JButton exitButton = new JButton("FINISH");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel playingFieldPanel = new JPanel(new GridLayout(rowsNum, columnsNum, 3, 3));

        statusBar = new JLabel("Your score: " + score);
        statusBar.setFont(statusBar.getFont().deriveFont(14.0f));

        JFrame frame = new JFrame("Three-in-a-row");
        ImageIcon icon = new ImageIcon("icons/icon.png");
        frame.setIconImage(icon.getImage());

        for (int i = 0; i < rowsNum; i++) {
            for (int j = 0; j < columnsNum; j++) {
                playingFieldButtons[i][j] = new JButton();

                playingFieldButtons[i][j].setIcon(playingField.getTileIcon(rowsNum - i, j + 1));

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
            case "FINISH" -> System.exit(0);
            case "RESET" -> reset();
            default -> {
                String[] input = e.getActionCommand().split(" ");
                int rowNum = Integer.parseInt(input[0]);
                int columnNum = Integer.parseInt(input[1]);
                swap(rowNum, columnNum);
            }
        }
    }

    public void setStatus(String string) {
        statusBar.setText(string);
    }

    public void reset() {
        playingField = new SimplestPlayingField();
        redrawPlayingField();
    }

    public void redrawPlayingField() {
        for (int i = 0; i < rowsNum; i++) {
            for (int j = 0; j < columnsNum; j++) {
                playingFieldButtons[i][j].setIcon(playingField.getTileIcon(rowsNum - i, j + 1));
            }
        }
    }

    public void swap(int rowNum, int columnNum) {
        if (!hasSelectedTile) {
            hasSelectedTile = true;
            selectedRowNum = rowNum;
            selectedColumnNum = columnNum;
            return;
        }

        if (selectedRowNum == rowNum && selectedColumnNum == columnNum) {
            hasSelectedTile = false;
            selectedRowNum = -1;
            selectedColumnNum = -1;
            return;
        }

        if (Math.abs(selectedRowNum - rowNum) + Math.abs(selectedColumnNum - columnNum) != 1) {
            setStatus("Tiles cannot be swapped, try again!");
            hasSelectedTile = false;
            selectedRowNum = -1;
            selectedColumnNum = -1;
            return;
        }

        playingField.swap(playingField.getField()[rowsNum - selectedRowNum][selectedColumnNum + 1],
                playingField.getField()[rowsNum - rowNum][columnNum + 1]);
        redrawPlayingField();

        if (playingField.hasReadyCombinations()) {
            while (playingField.hasReadyCombinations()) {
                score += playingField.deleteReadyCombinations();
                setStatus("Your score: " + score);
                redrawPlayingField();

                playingField.forceOfGravity();
                redrawPlayingField();

                playingField.secondaryFillingOfPlayingField();
                redrawPlayingField();
            }
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
}
