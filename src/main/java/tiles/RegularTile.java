package tiles;

import javax.swing.*;
import java.util.Objects;

public class RegularTile extends AbstractTile {

    public RegularTile(TileCoordinates tileCoordinates, TileNeighbors tileNeighbors, RegularTileTypes tileType) {
        super(tileCoordinates, tileNeighbors, tileType.getTileType());
    }

    @Override
    public ImageIcon getUnselectedTileIcon() {
        TileTypes tileType = getTileType();
        ImageIcon icon;

        switch (tileType) {
            case BLUE ->
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/unselectedTiles/blue.png")));
            case GREEN ->
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/unselectedTiles/green.png")));
            case GRAY ->
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/unselectedTiles/gray.png")));
            case INDIGO ->
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/unselectedTiles/indigo.png")));
            case RED ->
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/unselectedTiles/red.png")));
            default ->
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/unselectedTiles/yellow.png")));
        }

        return icon;
    }

    @Override
    public ImageIcon getSelectedTileIcon() {
        TileTypes tileType = getTileType();
        ImageIcon icon;

        switch (tileType) {
            case BLUE ->
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/selectedTiles/blue.png")));
            case GREEN ->
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/selectedTiles/green.png")));
            case GRAY ->
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/selectedTiles/gray.png")));
            case INDIGO ->
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/selectedTiles/indigo.png")));
            case RED ->
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/selectedTiles/red.png")));
            default ->
                    icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/selectedTiles/yellow.png")));
        }

        return icon;
    }
}
