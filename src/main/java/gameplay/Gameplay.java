package gameplay;

import playingFields.SimplestPlayingField;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Gameplay {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Three-in-a-Row game!");
        System.out.println("So far, the game is under development, so it does not have the most convenient gameplay.");
        System.out.println("But we will finalize everything soon. We will be glad if you try to play the game now!\n");
        Thread.sleep(1500);

        System.out.print("If you plan to play, please enter your name: ");
        String name = input.nextLine();
        Thread.sleep(500);

        System.out.printf("\nHello, %s!\n", name);
        Thread.sleep(500);

        SimplestPlayingField playingField = new SimplestPlayingField();
        int rowsNum = playingField.getRowsNum();
        int columnsNum = playingField.getColumnsNum();

        int score = 0;

        boolean flag = true;
        while (flag) {
            System.out.printf("\nYour score: %d.\n", score);
            Thread.sleep(250);

            playingField.show();

            System.out.println("At the moment, there are three actions available to you:");
            System.out.println("1. Create a new playing field, if you can't find a combination;");
            System.out.println("2. Swap two tiles to get a combination;");
            System.out.println("3. End the game.\n");
            Thread.sleep(750);

            System.out.print("Enter one of the commands CREATE, SWAP or END: ");
            String command = input.nextLine();
            Thread.sleep(250);

            switch (command) {
                case "CREATE" -> {
                    playingField = new SimplestPlayingField();
                }
                case "SWAP" -> {
                    try {
                        System.out.print("\nEnter the row where the first tile is located: ");
                        int firstTileRowNum = input.nextInt();
                        input.nextLine();
                        Thread.sleep(250);

                        if (firstTileRowNum < 1 || firstTileRowNum > rowsNum) {
                            System.out.println("\nThe number has gone beyond the game area, try again!");
                            Thread.sleep(250);
                            break;
                        }

                        System.out.print("\nEnter the column where the first tile is located: ");
                        int firstTileColumnNum = input.nextInt();
                        input.nextLine();
                        Thread.sleep(250);

                        if (firstTileColumnNum < 1 || firstTileColumnNum > columnsNum) {
                            System.out.println("\nThe number has gone beyond the game area, try again!");
                            Thread.sleep(250);
                            break;
                        }

                        System.out.print("\nEnter the row where the second tile is located: ");
                        int secondTileRowNum = input.nextInt();
                        input.nextLine();
                        Thread.sleep(250);

                        if (secondTileRowNum < 1 || secondTileRowNum > rowsNum) {
                            System.out.println("\nThe number has gone beyond the game area, try again!");
                            Thread.sleep(250);
                            break;
                        }

                        System.out.print("\nEnter the column where the second tile is located: ");
                        int secondTileColumnNum = input.nextInt();
                        input.nextLine();
                        Thread.sleep(250);

                        if (secondTileColumnNum < 1 || secondTileColumnNum > columnsNum) {
                            System.out.println("\nThe number has gone beyond the game area, try again!");
                            Thread.sleep(250);
                            break;
                        }

                        if (Math.abs(firstTileRowNum - secondTileRowNum) +
                                Math.abs(firstTileColumnNum - secondTileColumnNum) != 1) {
                            System.out.println("\nTiles cannot be swapped, try again!");
                            Thread.sleep(250);
                            break;
                        }

                        playingField.swap(playingField.getField()[firstTileRowNum][firstTileColumnNum],
                                playingField.getField()[secondTileRowNum][secondTileColumnNum]);
                        if (playingField.hasReadyCombinations()) {
                            while (playingField.hasReadyCombinations()) {
                                score += playingField.deleteReadyCombinations();
                                playingField.forceOfGravity();
                                playingField.secondaryFillingOfPlayingField();
                            }
                        } else {
                            playingField.swap(playingField.getField()[firstTileRowNum][firstTileColumnNum],
                                    playingField.getField()[secondTileRowNum][secondTileColumnNum]);
                            System.out.println("\nTiles do not give any combination, try again!");
                            Thread.sleep(250);
                        }

                    } catch (InputMismatchException e) {
                        Thread.sleep(250);
                        System.out.println("\nYou didn't enter a number, try again!");
                        Thread.sleep(250);
                    }
                }
                case "END" -> {
                    input.close();
                    flag = false;

                    System.out.printf("\nYour score: %d.\n", score);
                    Thread.sleep(500);

                    System.out.printf("\nSee you soon, %s!\n", name);
                    Thread.sleep(500);
                }
                default -> {
                    System.out.println("\nYou entered the command incorrectly, try again!");
                    Thread.sleep(500);
                }
            }
        }
    }
}
