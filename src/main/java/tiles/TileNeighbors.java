package tiles;

public class TileNeighbors {
    private AbstractTile top;
    private AbstractTile topRight;
    private AbstractTile left;
    private AbstractTile right;
    private AbstractTile bottom;

    public TileNeighbors() {
        top = new MissingTile(TileTypes.MISSING);
        topRight = new MissingTile(TileTypes.MISSING);
        left = new MissingTile(TileTypes.MISSING);
        right = new MissingTile(TileTypes.MISSING);
        bottom = new MissingTile(TileTypes.MISSING);
    }

    public TileNeighbors(
            AbstractTile top, AbstractTile topRight, AbstractTile left, AbstractTile right, AbstractTile bottom) {
        this.top = top;
        this.topRight = topRight;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }

    public AbstractTile getTop() {
        return top;
    }

    public AbstractTile getTopRight() {
        return topRight;
    }

    public AbstractTile getLeft() {
        return left;
    }

    public AbstractTile getRight() {
        return right;
    }

    public AbstractTile getBottom() {
        return bottom;
    }

    public void copyFrom(TileNeighbors tileNeighbors) {
        top = tileNeighbors.top;
        topRight = tileNeighbors.topRight;
        left = tileNeighbors.left;
        right = tileNeighbors.right;
        bottom = tileNeighbors.bottom;
    }

    protected TileNeighbors copyTo() {
        return new TileNeighbors(top, topRight, left, right, bottom);
    }
}
