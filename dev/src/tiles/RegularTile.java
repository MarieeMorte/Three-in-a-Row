package tiles;

public class RegularTile extends Tile {

    public RegularTile(TileCoordinates tileCoordinates, TileNeighbors tileNeighbors, RegularTileTypes tileType) {
        super(tileCoordinates, tileNeighbors, tileType.getTileType());
    }

    // Until the frontend stage has begun, the show() method looks like this for ease of perception
    @Override
    public void show() {
        TileTypes regularTileType = getTileType();
        String formattedText;

        switch (regularTileType) {
            case BLUE -> formattedText = "\u001B[96m" + String.format("%12s", "blue") + "\u001B[0m";
            case GREEN -> formattedText = "\u001B[32m" + String.format("%12s", "green") + "\u001B[0m";
            case GREY -> formattedText = "\u001B[90m" + String.format("%12s", "grey") + "\u001B[0m";
            case INDIGO -> formattedText = "\u001B[34m" + String.format("%12s", "indigo") + "\u001B[0m";
            case RED -> formattedText = "\u001B[31m" + String.format("%12s", "red") + "\u001B[0m";
            default -> formattedText = "\u001B[33m" + String.format("%12s", "yellow") + "\u001B[0m";
        }

        System.out.print(formattedText);
    }
}
