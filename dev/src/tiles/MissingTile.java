package tiles;

// Missing tile
public class MissingTile extends Tile {

    public MissingTile(TileTypes tileType) {
        super(tileType);

        if (tileType != TileTypes.MISSING) {
            throw new IllegalArgumentException("Invalid tile type!");
        }
    }

    public MissingTile(TileCoordinates tileCoordinates, TileNeighbors tileNeighbors, TileTypes tileType) {
        super(tileCoordinates, tileNeighbors, tileType);

        if (tileType != TileTypes.MISSING) {
            throw new IllegalArgumentException("Invalid tile type!");
        }
    }

    // Until the frontend stage has begun, the show() method looks like this for ease of perception
    @Override
    public void show() {
        System.out.printf("%12s", "");
    }
}
