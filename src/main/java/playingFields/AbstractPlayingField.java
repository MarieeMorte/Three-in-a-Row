package playingfields;

import java.util.Random;
import javax.swing.ImageIcon;
import tiles.AbstractTile;
import tiles.MissingTile;
import tiles.RegularTile;
import tiles.RegularTileTypes;
import tiles.TileCoordinates;
import tiles.TileNeighbors;
import tiles.TileTypes;

public abstract class AbstractPlayingField {
    protected static Random random;
    protected static final int COLORS_NUM = 6;
    protected AbstractTile[][] playingField;

    public AbstractPlayingField() {
        random = new Random();

        int rowsNum = getRowsNum();
        int columnsNum = getColumnsNum();
        playingField = new AbstractTile[rowsNum + 2][columnsNum + 2];

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

    public ImageIcon getUnselectedTileIcon(int rowNum, int columnNum) {
        return playingField[rowNum][columnNum].getUnselectedTileIcon();
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

        return switch (randomNum) {
            case 0 -> RegularTileTypes.BLUE;
            case 1 -> RegularTileTypes.GREEN;
            case 2 -> RegularTileTypes.GRAY;
            case 3 -> RegularTileTypes.INDIGO;
            case 4 -> RegularTileTypes.RED;
            default -> RegularTileTypes.YELLOW;
        };
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
                AbstractTile tile = playingField[i][j];
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
        AbstractTile tile = playingField[i][j];
        if (tile instanceof RegularTile && tile.hasThreeInRow()) {
            replaceTile(playingField[i][j - 1], new MissingTile(TileTypes.MISSING));
            replaceTile(playingField[i][j], new MissingTile(TileTypes.MISSING));
            replaceTile(playingField[i][j + 1], new MissingTile(TileTypes.MISSING));
        }
    }

    protected void replaceTile(AbstractTile replaceableTile, AbstractTile replacementTile) {
        replaceableTile.replaceBy(replacementTile);
        newTile(replacementTile);
    }

    protected void newTile(AbstractTile tile) {
        int rowNum = tile.getTileCoordinates().getRowNum();
        int columnNum = tile.getTileCoordinates().getColumnNum();

        playingField[rowNum + 1][columnNum].getTileNeighbors().setBottom(tile);
        playingField[rowNum][columnNum - 1].getTileNeighbors().setRight(tile);
        playingField[rowNum][columnNum] = tile;
        playingField[rowNum][columnNum + 1].getTileNeighbors().setLeft(tile);
        playingField[rowNum - 1][columnNum].getTileNeighbors().setTop(tile);
    }

    protected void deleteThreeInColumn(int i, int j) {
        AbstractTile tile = playingField[i][j];
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
                if (playingField[i][j].getTileType() != TileTypes.MISSING
                        && playingField[i - 1][j].getTileType() == TileTypes.MISSING) {
                    return true;
                }
            }
        }
        return false;
    }

    protected void forceOfGravityForOneTile(int i, int j) {
        if (playingField[i][j].getTileType() != TileTypes.MISSING
                && playingField[i - 1][j].getTileType() == TileTypes.MISSING) {
            swap(playingField[i][j], playingField[i - 1][j]);
        }
    }

    public void swap(AbstractTile firstTile, AbstractTile secondTile) {
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
                    AbstractTile tile = new RegularTile(new TileCoordinates(i, j),
                            new TileNeighbors(playingField[i + 1][j], playingField[i][j - 1], playingField[i][j + 1],
                                    playingField[i - 1][j]), randomColor());
                    newTile(tile);
                }
            }
        }
    }

    public AbstractTile[][] getField() {
        return playingField;
    }
}
