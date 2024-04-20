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

            while (hasHangingTiles()) {
                forceOfGravity();
            }

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
                    if (playingField[i][j].hasThreeInRow() && playingField[i][j].hasThreeInColumn()) {
                        deleteThreeInRow(i, j);
                        deleteThreeInColumn(i, j);
                        count += 2;
                    } else if (playingField[i][j].hasThreeInRow()) {
                        deleteThreeInRow(i, j);
                        count++;
                    } else if (playingField[i][j].hasThreeInColumn()) {
                        deleteThreeInColumn(i, j);
                        count++;
                    }
                }
            }
        }

        return count;
    }

    protected void deleteThreeInRow(int i, int j) {
        replaceTile(playingField[i][j - 1], new MissingTile(TileTypes.MISSING));
        replaceTile(playingField[i][j], new MissingTile(TileTypes.MISSING));
        replaceTile(playingField[i][j + 1], new MissingTile(TileTypes.MISSING));
        fillingOfNeighbors();
    }

    protected void replaceTile(AbstractTile replaceableTile, AbstractTile replacementTile) {
        int rowNum = replaceableTile.getTileCoordinates().getRowNum();
        int columnNum = replaceableTile.getTileCoordinates().getColumnNum();

        playingField[rowNum][columnNum] = replacementTile;
        replaceableTile.replaceBy(replacementTile);
    }

    protected void deleteThreeInColumn(int i, int j) {
        replaceTile(playingField[i + 1][j], new MissingTile(TileTypes.MISSING));
        replaceTile(playingField[i][j], new MissingTile(TileTypes.MISSING));
        replaceTile(playingField[i - 1][j], new MissingTile(TileTypes.MISSING));
        fillingOfNeighbors();
    }

    public void forceOfGravity() {
        int rowsNum = getRowsNum();
        int columnsNum = getColumnsNum();

        for (int i = 2; i < rowsNum + 1; i++) {
            for (int j = 1; j < columnsNum + 1; j++) {
                forceOfGravityForOneTile(i, j);
            }
        }
    }

    public boolean hasHangingTiles() {
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

        playingField[firstTileRowNum][firstTileColumnNum] = secondTile;
        playingField[secondTileRowNum][secondTileColumnNum] = firstTile;
        firstTile.swap(secondTile);
        fillingOfNeighbors();
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
                    playingField[i][j] = tile;
                }
            }
        }

        fillingOfNeighbors();
    }

    public AbstractTile[][] getField() {
        return playingField;
    }
}
