package playingFields;

import tiles.*;

import javax.swing.*;
import java.util.Random;

public abstract class PlayingField {
    protected static Random random;
    protected static final int COLORS_NUM = 6;
    protected Tile[][] playingField;

    public PlayingField() {
        random = new Random();

        int rowsNum = getRowsNum();
        int columnsNum = getColumnsNum();
        playingField = new Tile[rowsNum + 2][columnsNum + 2];

        primaryFillingOfPlayingField();
        fillingOfNeighbors();

        while (hasReadyCombinations()) {
            deleteReadyCombinations();
            forceOfGravity();
            secondaryFillingOfPlayingField();
        }
    }

    public abstract int getRowsNum();

    public abstract int getColumnsNum();

    public ImageIcon getTileIcon(int rowNum, int columnNum) {
        return playingField[rowNum][columnNum].getTileIcon();
    }

    protected void primaryFillingOfPlayingField() {
        int rowsNum = getRowsNum();
        int columnsNum = getColumnsNum();

        for (int i = 0; i < rowsNum + 2; i++) {
            for (int j = 0; j < columnsNum + 2; j++) {
                if (i == 0 || i == rowsNum + 1 || j == 0 || j == columnsNum + 1) {
                    playingField[i][j] = new MissingTile(new TileCoordinates(i, j), new TileNeighbors(),
                            TileTypes.MISSING);
                } else {
                    playingField[i][j] = new RegularTile(new TileCoordinates(i, j), new TileNeighbors(),
                            randomColor());
                }
            }
        }
    }

    protected RegularTileTypes randomColor() {
        int randomNum = random.nextInt(COLORS_NUM);
        RegularTileTypes tileType;

        switch (randomNum) {
            case (0) -> tileType = RegularTileTypes.BLUE;
            case (1) -> tileType = RegularTileTypes.GREEN;
            case (2) -> tileType = RegularTileTypes.GRAY;
            case (3) -> tileType = RegularTileTypes.INDIGO;
            case (4) -> tileType = RegularTileTypes.RED;
            default -> tileType = RegularTileTypes.YELLOW;
        }

        return tileType;
    }

    protected void fillingOfNeighbors() {
        int rowsNum = getRowsNum();
        int columnsNum = getColumnsNum();

        for (int i = 1; i < rowsNum + 1; i++) {
            for (int j = 1; j < columnsNum + 1; j++) {
                playingField[i][j].setTileNeighbors(playingField[i + 1][j], playingField[i][j - 1],
                        playingField[i][j + 1], playingField[i - 1][j]);
            }
        }
    }

    public boolean hasReadyCombinations() {
        int rowsNum = getRowsNum();
        int columnsNum = getColumnsNum();

        for (int i = 1; i < rowsNum + 1; i++) {
            for (int j = 1; j < columnsNum + 1; j++) {
                Tile tile = playingField[i][j];
                if (tile instanceof RegularTile && tile.hasReadyCombination()) {
                    return true;
                }
            }
        }
        return false;
    }

    public int deleteReadyCombinations() {
        int rowsNum = getRowsNum();
        int columnsNum = getColumnsNum();

        int count = 0;

        for (int i = 1; i < rowsNum + 1; i++) {
            for (int j = 1; j < columnsNum + 1; j++) {
                if (playingField[i][j] instanceof RegularTile && playingField[i][j].hasReadyCombination()) {
                    count++;
                    deleteThreeInRow(i, j);
                    deleteThreeInColumn(i, j);
                }
            }
        }

        return count;
    }

    protected void deleteThreeInRow(int i, int j) {
        Tile tile = playingField[i][j];
        if (tile instanceof RegularTile && tile.hasThreeInRow()) {
            replaceTile(playingField[i][j - 1], new MissingTile(TileTypes.MISSING));
            replaceTile(playingField[i][j], new MissingTile(TileTypes.MISSING));
            replaceTile(playingField[i][j + 1], new MissingTile(TileTypes.MISSING));
        }
    }

    protected void replaceTile(Tile replaceableTile, Tile replacementTile) {
        replaceableTile.replaceBy(replacementTile);
        newTile(replacementTile);
    }

    protected void newTile(Tile tile) {
        int rowNum = tile.getTileCoordinates().getRowNum();
        int columnNum = tile.getTileCoordinates().getColumnNum();

        playingField[rowNum + 1][columnNum].getTileNeighbors().setBottom(tile);
        playingField[rowNum][columnNum - 1].getTileNeighbors().setRight(tile);
        playingField[rowNum][columnNum] = tile;
        playingField[rowNum][columnNum + 1].getTileNeighbors().setLeft(tile);
        playingField[rowNum - 1][columnNum].getTileNeighbors().setTop(tile);
    }

    protected void deleteThreeInColumn(int i, int j) {
        Tile tile = playingField[i][j];
        if (tile instanceof RegularTile && tile.hasThreeInColumn()) {
            replaceTile(playingField[i + 1][j], new MissingTile(TileTypes.MISSING));
            replaceTile(playingField[i][j], new MissingTile(TileTypes.MISSING));
            replaceTile(playingField[i - 1][j], new MissingTile(TileTypes.MISSING));
        }
    }

    public void forceOfGravity() {
        int rowsNum = getRowsNum();
        int columnsNum = getColumnsNum();

        while (hasHangingTiles()) {
            for (int i = 2; i < rowsNum + 1; i++) {
                for (int j = 1; j < columnsNum + 1; j++) {
                    forceOfGravityForOneTile(i, j);
                }
            }
        }
    }

    protected boolean hasHangingTiles() {
        int rowsNum = getRowsNum();
        int columnsNum = getColumnsNum();

        for (int i = 2; i < rowsNum + 1; i++) {
            for (int j = 1; j < columnsNum + 1; j++) {
                if (playingField[i][j].getTileType() != TileTypes.MISSING &&
                        playingField[i - 1][j].getTileType() == TileTypes.MISSING) {
                    return true;
                }
            }
        }
        return false;
    }

    protected void forceOfGravityForOneTile(int i, int j) {
        if (playingField[i][j].getTileType() != TileTypes.MISSING &&
                playingField[i - 1][j].getTileType() == TileTypes.MISSING) {
            swap(playingField[i][j], playingField[i - 1][j]);
        }
    }

    public void swap(Tile firstTile, Tile secondTile) {
        int firstTileRowNum = firstTile.getTileCoordinates().getRowNum();
        int firstTileColumnNum = firstTile.getTileCoordinates().getColumnNum();

        int secondTileRowNum = secondTile.getTileCoordinates().getRowNum();
        int secondTileColumnNum = secondTile.getTileCoordinates().getColumnNum();

        firstTile.swap(secondTile);
        newTile(firstTile);
        newTile(secondTile);

        if (firstTileRowNum - secondTileRowNum == 1 && firstTileColumnNum == secondTileColumnNum) {
            firstTile.getTileNeighbors().setBottom(playingField[secondTileRowNum - 1][secondTileColumnNum]);
            secondTile.getTileNeighbors().setBottom(firstTile);
        } else if (firstTileRowNum == secondTileRowNum && firstTileColumnNum - secondTileColumnNum == 1) {
            firstTile.getTileNeighbors().setRight(playingField[secondTileRowNum][secondTileColumnNum + 1]);
            secondTile.getTileNeighbors().setRight(firstTile);
        } else if (firstTileRowNum == secondTileRowNum && secondTileColumnNum - firstTileColumnNum == 1) {
            firstTile.getTileNeighbors().setLeft(playingField[secondTileRowNum][secondTileColumnNum - 1]);
            secondTile.getTileNeighbors().setLeft(firstTile);
        } else if (secondTileRowNum - firstTileRowNum == 1 && firstTileColumnNum - secondTileColumnNum == 0) {
            firstTile.getTileNeighbors().setTop(playingField[secondTileRowNum + 1][secondTileColumnNum]);
            secondTile.getTileNeighbors().setTop(firstTile);
        }
    }

    public void secondaryFillingOfPlayingField() {
        int rowsNum = getRowsNum();
        int columnsNum = getColumnsNum();

        for (int i = 1; i < rowsNum + 1; i++) {
            for (int j = 1; j < columnsNum + 1; j++) {
                if (playingField[i][j].getTileType() == TileTypes.MISSING) {
                    Tile tile = new RegularTile(new TileCoordinates(i, j),
                            new TileNeighbors(playingField[i + 1][j], playingField[i][j - 1], playingField[i][j + 1],
                                    playingField[i - 1][j]), randomColor());
                    newTile(tile);
                }
            }
        }
    }

    public Tile[][] getField() {
        return playingField;
    }
}
