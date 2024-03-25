package playingFields;

public class SimplestPlayingField extends PlayingField {

    @Override
    public int getRowsNum() {
        return 8;
    }

    @Override
    public int getColumnsNum() {
        return 8;
    }

    // Until the frontend stage has begun, the show() method looks like this for ease of perception
    @Override
    public void show() throws InterruptedException {
        int rowsNum = getRowsNum();
        int columnsNum = getColumnsNum();

        System.out.println();
        System.out.printf("%13s%12s%12s%12s%12s%12s%12s%12s%n", "1", "2", "3", "4", "5", "6", "7", "8");
        for (int i = rowsNum; i > 0; i--) {
            System.out.print(i);
            for (int j = 1; j < columnsNum + 1; j++) {
                playingField[i][j].show();
            }
            System.out.println();
        }
        System.out.println();
        Thread.sleep(750);
    }
}
