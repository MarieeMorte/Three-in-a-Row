package tiles;

public class TileNeighbors {
    private Tile top;
    private Tile left;
    private Tile right;
    private Tile bottom;

    public TileNeighbors() {
        top = new MissingTile(TileTypes.MISSING);
        left = new MissingTile(TileTypes.MISSING);
        right = new MissingTile(TileTypes.MISSING);
        bottom = new MissingTile(TileTypes.MISSING);
    }

    public TileNeighbors(Tile top, Tile left, Tile right, Tile bottom) {
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }

    public void setTop(Tile tile) {
        top = tile;
    }

    public Tile getTop() {
        return top;
    }

    public void setLeft(Tile tile) {
        left = tile;
    }

    public Tile getLeft() {
        return left;
    }

    public void setRight(Tile tile) {
        right = tile;
    }

    public Tile getRight() {
        return right;
    }

    public void setBottom(Tile tile) {
        bottom = tile;
    }

    public Tile getBottom() {
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
