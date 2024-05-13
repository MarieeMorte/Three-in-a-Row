package tiles;

import java.util.Objects;
import javax.swing.ImageIcon;

public class RegularTile extends AbstractTile {
    public RegularTile(RegularTileTypes regularTileType) {
        super(regularTileType.getTileType());
    }

    public RegularTile(
            TileCoordinates tileCoordinates, TileNeighbors tileNeighbors, RegularTileTypes regularTileType) {
        super(tileCoordinates, tileNeighbors, regularTileType.getTileType());
    }

    @Override
    public ImageIcon getUnselectedTileIcon() {
        TileTypes tileType = getTileType();

        return switch (tileType) {
            case BLUE -> new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/icons/unselectedTiles/blue.png")));
            case GREEN -> new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/icons/unselectedTiles/green.png")));
            case GRAY -> new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/icons/unselectedTiles/gray.png")));
            case INDIGO -> new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/icons/unselectedTiles/indigo.png")));
            case RED -> new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/icons/unselectedTiles/red.png")));
            default -> new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/icons/unselectedTiles/yellow.png")));
        };
    }

    @Override
    public ImageIcon getSelectedTileIcon() {
        TileTypes tileType = getTileType();

        return switch (tileType) {
            case BLUE -> new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/icons/selectedTiles/blue.png")));
            case GREEN -> new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/icons/selectedTiles/green.png")));
            case GRAY -> new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/icons/selectedTiles/gray.png")));
            case INDIGO -> new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/icons/selectedTiles/indigo.png")));
            case RED -> new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/icons/selectedTiles/red.png")));
            default -> new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/icons/selectedTiles/yellow.png")));
        };
    }
}
