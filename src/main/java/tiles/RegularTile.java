package tiles;

import javax.swing.*;

public class RegularTile extends Tile {

    public RegularTile(TileCoordinates tileCoordinates, TileNeighbors tileNeighbors, RegularTileTypes tileType) {
        super(tileCoordinates, tileNeighbors, tileType.getTileType());
    }

    @Override
    public ImageIcon getTileIcon() {
        TileTypes tileType = getTileType();
        ImageIcon icon;

        switch (tileType) {
            case BLUE -> icon = new ImageIcon("icons/blue.png");
            case GREEN -> icon = new ImageIcon("icons/green.png");
            case GRAY -> icon = new ImageIcon("icons/gray.png");
            case INDIGO -> icon = new ImageIcon("icons/indigo.png");
            case RED -> icon = new ImageIcon("icons/red.png");
            default -> icon = new ImageIcon("icons/yellow.png");
        }

        return icon;
    }
}
