package tiles;

import javax.swing.*;

public class MissingTile extends AbstractTile {

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
    public ImageIcon getUnselectedTileIcon() {
        return new ImageIcon("src/main/resources/icons/unselectedTiles/missing.png");
    }

    @Override
    public ImageIcon getSelectedTileIcon() {
        return new ImageIcon("src/main/resources/icons/unselectedTiles/missing.png");
    }
}
