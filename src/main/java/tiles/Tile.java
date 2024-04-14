package tiles;

import javax.swing.*;

public abstract class Tile {
    private TileCoordinates tileCoordinates;
    private TileNeighbors tileNeighbors;
    private final TileTypes tileType;

    public Tile(TileTypes tileType) {
        this.tileType = tileType;
    }

    public Tile(TileCoordinates tileCoordinates, TileNeighbors tileNeighbors, TileTypes tileType) {
        this.tileCoordinates = tileCoordinates;
        this.tileNeighbors = tileNeighbors;
        this.tileType = tileType;
    }

    public abstract ImageIcon getUnselectedTileIcon();

    public abstract ImageIcon getSelectedTileIcon();

    public TileCoordinates getTileCoordinates() {
        return tileCoordinates;
    }

    public void setTileNeighbors(Tile top, Tile left, Tile right, Tile bottom) {
        tileNeighbors = new TileNeighbors(top, left, right, bottom);
    }

    public TileNeighbors getTileNeighbors() {
        return tileNeighbors;
    }

    public TileTypes getTileType() {
        return tileType;
    }

    public boolean hasReadyCombination() {
        return hasThreeInRow() || hasThreeInColumn();
    }

    public boolean hasThreeInRow() {
        return this.tileType == this.tileNeighbors.getLeft().getTileType() &&
                this.tileType == this.tileNeighbors.getRight().getTileType();
    }

    public boolean hasThreeInColumn() {
        return this.tileType == this.tileNeighbors.getTop().getTileType() &&
                this.tileType == this.tileNeighbors.getBottom().getTileType();
    }

    public void replaceBy(Tile replacementTile) {
        replacementTile.tileCoordinates = tileCoordinates.copyTo();
        replacementTile.tileNeighbors = tileNeighbors.copyTo();
    }

    public void swap(Tile tile) {
        TileCoordinates tempTileCoordinates = this.tileCoordinates.copyTo();
        this.tileCoordinates.copyFrom(tile.tileCoordinates);
        tile.tileCoordinates.copyFrom(tempTileCoordinates);

        TileNeighbors tempTileNeighbors = this.tileNeighbors.copyTo();
        this.tileNeighbors.copyFrom(tile.tileNeighbors);
        tile.tileNeighbors.copyFrom(tempTileNeighbors);
    }
}
