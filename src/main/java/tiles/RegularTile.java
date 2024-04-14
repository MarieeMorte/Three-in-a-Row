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
            case BLUE -> icon = new ImageIcon("src/main/resources/icons/unselectedTiles/blue.png");
            case GREEN -> icon = new ImageIcon("src/main/resources/icons/unselectedTiles/green.png");
            case GRAY -> icon = new ImageIcon("src/main/resources/icons/unselectedTiles/gray.png");
            case INDIGO -> icon = new ImageIcon("src/main/resources/icons/unselectedTiles/indigo.png");
            case RED -> icon = new ImageIcon("src/main/resources/icons/unselectedTiles/red.png");
            default -> icon = new ImageIcon("src/main/resources/icons/unselectedTiles/yellow.png");
        }

        return icon;
    }

    @Override
    public ImageIcon getSelectedTileIcon() {
        TileTypes tileType = getTileType();
        ImageIcon icon;

        switch (tileType) {
            case BLUE -> icon = new ImageIcon("src/main/resources/icons/selectedTiles/blue.png");
            case GREEN -> icon = new ImageIcon("src/main/resources/icons/selectedTiles/green.png");
            case GRAY -> icon = new ImageIcon("src/main/resources/icons/selectedTiles/gray.png");
            case INDIGO -> icon = new ImageIcon("src/main/resources/icons/selectedTiles/indigo.png");
            case RED -> icon = new ImageIcon("src/main/resources/icons/selectedTiles/red.png");
            default -> icon = new ImageIcon("src/main/resources/icons/selectedTiles/yellow.png");
        }

        return icon;
    }
}
