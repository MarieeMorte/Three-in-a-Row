package playingfields;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
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

    protected AbstractPlayingField() {
        random = new Random();

        int rowsNum = getRowsNum();
        int columnsNum = getColumnsNum();
        playingField = new AbstractTile[rowsNum + 2][columnsNum + 2];

        primaryFillingOfPlayingField();

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

    public AbstractTile[][] getField() {
        return playingField;
    }

    public ImageIcon getUnselectedTileIcon(int rowNum, int columnNum) {
        return playingField[rowNum][columnNum].getUnselectedTileIcon();
    }

    private void primaryFillingOfPlayingField() {
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

        fillingOfNeighbors();
    }

    private RegularTileTypes randomColor() {
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

    private void fillingOfNeighbors() {
        int rowsNum = getRowsNum();
        int columnsNum = getColumnsNum();

        for (int i = 1; i < rowsNum + 1; i++) {
            for (int j = 1; j < columnsNum + 1; j++) {
                playingField[i][j].setTileNeighbors(playingField[i + 1][j], playingField[i + 1][j + 1],
                        playingField[i][j - 1], playingField[i][j + 1], playingField[i - 1][j]);
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

        Set<TileCoordinates> tilesToDelete = new HashSet<>(rowsNum * columnsNum);
        int count = 0;

        for (int i = 1; i < rowsNum + 1; i++) {
            for (int j = 1; j < columnsNum + 1; j++) {
                if (playingField[i][j] instanceof RegularTile && playingField[i][j].hasReadyCombination()) {
                    if (playingField[i][j].hasThreeInRow()) {
                        tilesToDelete.add(playingField[i][j - 1].getTileCoordinates());
                        tilesToDelete.add(playingField[i][j].getTileCoordinates());
                        tilesToDelete.add(playingField[i][j + 1].getTileCoordinates());

                        count++;
                    }

                    if (playingField[i][j].hasThreeInColumn()) {
                        tilesToDelete.add(playingField[i + 1][j].getTileCoordinates());
                        tilesToDelete.add(playingField[i][j].getTileCoordinates());
                        tilesToDelete.add(playingField[i - 1][j].getTileCoordinates());

                        count++;
                    }

                    if (playingField[i][j].hasFourInSquare()) {
                        tilesToDelete.add(playingField[i + 1][j].getTileCoordinates());
                        tilesToDelete.add(playingField[i + 1][j + 1].getTileCoordinates());
                        tilesToDelete.add(playingField[i][j].getTileCoordinates());
                        tilesToDelete.add(playingField[i][j + 1].getTileCoordinates());

                        count++;
                    }
                }
            }
        }

        for (TileCoordinates coordinates : tilesToDelete) {
            replaceTile(playingField[coordinates.getRowNum()][coordinates.getColumnNum()],
                    new MissingTile(TileTypes.MISSING));
        }

        fillingOfNeighbors();

        return count;
    }

    private void replaceTile(AbstractTile replaceableTile, AbstractTile replacementTile) {
        int rowNum = replaceableTile.getTileCoordinates().getRowNum();
        int columnNum = replaceableTile.getTileCoordinates().getColumnNum();

        playingField[rowNum][columnNum] = replacementTile;
        replaceableTile.replaceBy(replacementTile);
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

    private void forceOfGravityForOneTile(int i, int j) {
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

    private void secondaryFillingOfPlayingField() {
        int rowsNum = getRowsNum();
        int columnsNum = getColumnsNum();

        for (int i = 1; i < rowsNum + 1; i++) {
            for (int j = 1; j < columnsNum + 1; j++) {
                replaceMissingTile(i, j);
            }
        }

        fillingOfNeighbors();
    }

    private void replaceMissingTile(int rowNum, int columnNum) {
        if (playingField[rowNum][columnNum].getTileType() == TileTypes.MISSING) {
            AbstractTile tile = new RegularTile(new TileCoordinates(rowNum, columnNum),
                    new TileNeighbors(), randomColor());
            tile.setTileNeighbors(playingField[rowNum + 1][columnNum], playingField[rowNum + 1][columnNum + 1],
                    playingField[rowNum][columnNum - 1], playingField[rowNum][columnNum + 1],
                    playingField[rowNum - 1][columnNum]);
            playingField[rowNum][columnNum] = tile;
        }
    }

    public boolean hasMissingTiles() {
        int rowsNum = getRowsNum();
        int columnsNum = getColumnsNum();

        for (int i = 1; i < columnsNum + 1; i++) {
            if (playingField[rowsNum][i].getTileType() == TileTypes.MISSING) {
                return true;
            }
        }

        return false;
    }

    public void fillingInTopRow() {
        int rowsNum = getRowsNum();
        int columnsNum = getColumnsNum();

        for (int i = 1; i < columnsNum + 1; i++) {
            replaceMissingTile(rowsNum, i);
        }

        fillingOfNeighbors();
    }
}
