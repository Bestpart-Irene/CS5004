package chess;

public class Bishop extends ChessPiece {

    public Bishop(int row, int column, Color color) {
        super(row, column, color);
    }

    @Override
    public boolean canMove(int row, int col) {
        if (!isValidPosition(row, col) || isSamePosition(row, col)) {
            return false;
        }
        int rowDiff = Math.abs(row - this.row);
        int colDiff = Math.abs(col - this.column);
        return rowDiff == colDiff;
    }
}
