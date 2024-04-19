package playingfields;

public class SimplestPlayingField extends AbstractPlayingField {
    public SimplestPlayingField() {
        super();
    }

    @Override
    public int getRowsNum() {
        return 8;
    }

    @Override
    public int getColumnsNum() {
        return 8;
    }
}
