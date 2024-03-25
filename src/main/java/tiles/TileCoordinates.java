package tiles;

public class TileCoordinates {
    private int rowNum;
    private int columnNum;

    public TileCoordinates(int rowNum, int columnNum) {
        if (rowNum < 0) {
            throw new IllegalArgumentException("The row number cannot be negative!");
        }
        this.rowNum = rowNum;

        if (columnNum < 0) {
            throw new IllegalArgumentException("The column number cannot be negative!");
        }
        this.columnNum = columnNum;
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void copyFrom(TileCoordinates tileCoordinates) {
        rowNum = tileCoordinates.getRowNum();
        columnNum = tileCoordinates.getColumnNum();
    }

    public TileCoordinates copyTo() {
        return new TileCoordinates(rowNum, columnNum);
    }
}
