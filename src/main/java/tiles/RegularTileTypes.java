package tiles;

public enum RegularTileTypes {
    BLUE(TileTypes.BLUE),
    GREEN(TileTypes.GREEN),
    GRAY(TileTypes.GRAY),
    INDIGO(TileTypes.INDIGO),
    RED(TileTypes.RED),
    YELLOW(TileTypes.YELLOW);

    private final TileTypes tileType;

    RegularTileTypes(TileTypes tileType) {
        this.tileType = tileType;
    }

    public TileTypes getTileType() {
        return this.tileType;
    }
}
