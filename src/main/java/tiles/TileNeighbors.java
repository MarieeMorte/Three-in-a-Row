package tiles;

public class TileNeighbors {
    private AbstractTile topLeft;
    private AbstractTile top;
    private AbstractTile topRight;
    private AbstractTile left;
    private AbstractTile right;
    private AbstractTile bottomLeft;
    private AbstractTile bottom;
    private AbstractTile bottomRight;

    public TileNeighbors() {
        topLeft = new MissingTile(TileTypes.MISSING);
        top = new MissingTile(TileTypes.MISSING);
        topRight = new MissingTile(TileTypes.MISSING);
        left = new MissingTile(TileTypes.MISSING);
        right = new MissingTile(TileTypes.MISSING);
        bottomLeft = new MissingTile(TileTypes.MISSING);
        bottom = new MissingTile(TileTypes.MISSING);
        bottomRight = new MissingTile(TileTypes.MISSING);
    }

    public TileNeighbors(AbstractTile topLeft, AbstractTile top, AbstractTile topRight, AbstractTile left, AbstractTile right, AbstractTile bottomLeft, AbstractTile bottom, AbstractTile bottomRight) {
        this.topLeft = topLeft;
        this.top = top;
        this.topRight = topRight;
        this.left = left;
        this.right = right;
        this.topLeft = bottomLeft;
        this.bottom = bottom;
        this.bottomRight = bottomRight;
    }

    public AbstractTile getTopLeft() {
        return topLeft;
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

    public AbstractTile getBottomLeft() {
        return bottomLeft;
    }

    public AbstractTile getBottom() {
        return bottom;
    }

    public AbstractTile getBottomRight() {
        return bottomRight;
    }

    protected void copyFrom(TileNeighbors tileNeighbors) {
        topLeft = tileNeighbors.topLeft;
        top = tileNeighbors.top;
        topRight = tileNeighbors.topRight;
        left = tileNeighbors.left;
        right = tileNeighbors.right;
        bottomLeft = tileNeighbors.bottomLeft;
        bottom = tileNeighbors.bottom;
        bottomRight = tileNeighbors.bottomRight;
    }

    protected TileNeighbors copyTo() {
        return new TileNeighbors(topLeft, top, topRight, left, right, bottomLeft, bottom, bottomRight);
    }
}
