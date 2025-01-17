package tiles;

import java.util.Objects;
import javax.swing.ImageIcon;

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
        return new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/icons/unselectedTiles/missing.png")));
    }

    @Override
    public ImageIcon getSelectedTileIcon() {
        return new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/icons/unselectedTiles/missing.png")));
    }
}
