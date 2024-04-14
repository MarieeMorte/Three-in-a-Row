package tiles;

import javax.swing.*;

public class RegularTile extends Tile {

    public RegularTile(TileCoordinates tileCoordinates, TileNeighbors tileNeighbors, RegularTileTypes tileType) {
        super(tileCoordinates, tileNeighbors, tileType.getTileType());
    }

    @Override
    public ImageIcon getUnselectedTileIcon() {
        TileTypes tileType = getTileType();
        ImageIcon icon;

        switch (tileType) {
            case BLUE -> icon = new ImageIcon("icons/unselectedTiles/blue.png");
            case GREEN -> icon = new ImageIcon("icons/unselectedTiles/green.png");
            case GRAY -> icon = new ImageIcon("icons/unselectedTiles/gray.png");
            case INDIGO -> icon = new ImageIcon("icons/unselectedTiles/indigo.png");
            case RED -> icon = new ImageIcon("icons/unselectedTiles/red.png");
            default -> icon = new ImageIcon("icons/unselectedTiles/yellow.png");
        }

        return icon;
    }

    @Override
    public ImageIcon getSelectedTileIcon() {
        TileTypes tileType = getTileType();
        ImageIcon icon;

        switch (tileType) {
            case BLUE -> icon = new ImageIcon("icons/selectedTiles/blue.png");
            case GREEN -> icon = new ImageIcon("icons/selectedTiles/green.png");
            case GRAY -> icon = new ImageIcon("icons/selectedTiles/gray.png");
            case INDIGO -> icon = new ImageIcon("icons/selectedTiles/indigo.png");
            case RED -> icon = new ImageIcon("icons/selectedTiles/red.png");
            default -> icon = new ImageIcon("icons/selectedTiles/yellow.png");
        }

        return icon;
    }
}
