package tiles;

public class TileNeighbors {
    private AbstractTile top;
    private AbstractTile left;
    private AbstractTile right;
    private AbstractTile bottom;

    public TileNeighbors() {
        top = new MissingTile(TileTypes.MISSING);
        left = new MissingTile(TileTypes.MISSING);
        right = new MissingTile(TileTypes.MISSING);
        bottom = new MissingTile(TileTypes.MISSING);
    }

    public TileNeighbors(AbstractTile top, AbstractTile left, AbstractTile right, AbstractTile bottom) {
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }

    public void setTop(AbstractTile tile) {
        top = tile;
    }

    public AbstractTile getTop() {
        return top;
    }

    public void setLeft(AbstractTile tile) {
        left = tile;
    }

    public AbstractTile getLeft() {
        return left;
    }

    public void setRight(AbstractTile tile) {
        right = tile;
    }

    public AbstractTile getRight() {
        return right;
    }

    public void setBottom(AbstractTile tile) {
        bottom = tile;
    }

    public AbstractTile getBottom() {
        return bottom;
    }

    public void copyFrom(TileNeighbors tileNeighbors) {
        top = tileNeighbors.top;
        left = tileNeighbors.left;
        right = tileNeighbors.right;
        bottom = tileNeighbors.bottom;
    }

    public TileNeighbors copyTo() {
        return new TileNeighbors(top, left, right, bottom);
    }
}
