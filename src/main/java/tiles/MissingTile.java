package tiles;

import javax.swing.*;

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

    @Override
    public ImageIcon getTileIcon() {
        return new ImageIcon("icons/miss.png");
    }
}
