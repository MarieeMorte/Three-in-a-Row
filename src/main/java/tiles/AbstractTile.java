package tiles;

import javax.swing.ImageIcon;

public abstract class AbstractTile {
    private TileCoordinates tileCoordinates;
    private TileNeighbors tileNeighbors;
    private final TileTypes tileType;

    protected AbstractTile(TileTypes tileType) {
        this.tileType = tileType;
    }

    protected AbstractTile(TileCoordinates tileCoordinates, TileNeighbors tileNeighbors, TileTypes tileType) {
        this.tileCoordinates = tileCoordinates;
        this.tileNeighbors = tileNeighbors;
        this.tileType = tileType;
    }

    public abstract ImageIcon getUnselectedTileIcon();

    public abstract ImageIcon getSelectedTileIcon();

    public TileCoordinates getTileCoordinates() {
        return tileCoordinates;
    }

    public void setTileNeighbors(AbstractTile top, AbstractTile left, AbstractTile right, AbstractTile bottom) {
        tileNeighbors = new TileNeighbors(top, left, right, bottom);
    }

    public TileTypes getTileType() {
        return tileType;
    }

    public boolean hasReadyCombination() {
        return hasThreeInRow() || hasThreeInColumn();
    }

    public boolean hasThreeInRow() {
        return this.tileType == this.tileNeighbors.getLeft().getTileType()
                && this.tileType == this.tileNeighbors.getRight().getTileType();
    }

    public boolean hasThreeInColumn() {
        return this.tileType == this.tileNeighbors.getTop().getTileType()
                && this.tileType == this.tileNeighbors.getBottom().getTileType();
    }

    public void replaceBy(AbstractTile replacementTile) {
        replacementTile.tileCoordinates = tileCoordinates.copyTo();
        replacementTile.tileNeighbors = tileNeighbors.copyTo();
    }

    public void swap(AbstractTile tile) {
        TileCoordinates tempTileCoordinates = this.tileCoordinates.copyTo();
        this.tileCoordinates.copyFrom(tile.tileCoordinates);
        tile.tileCoordinates.copyFrom(tempTileCoordinates);

        TileNeighbors tempTileNeighbors = this.tileNeighbors.copyTo();
        this.tileNeighbors.copyFrom(tile.tileNeighbors);
        tile.tileNeighbors.copyFrom(tempTileNeighbors);
    }
}
