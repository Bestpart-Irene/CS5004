package chess;

public class Queen extends ChessPiece {

    public Queen(int row, int column, Color color) {
        super(row, column, color);
    }

    @Override
    public boolean canMove(int row, int col) {
        if (!isValidPosition(row, col) || isSamePosition(row, col)) {
            return false;
        }
        int rowDiff = Math.abs(row - this.row);
        int colDiff = Math.abs(col - this.column);
        boolean diagonal = rowDiff == colDiff;
        boolean horizontal = this.row == row;
        boolean vertical = this.column == col;
        return diagonal || horizontal || vertical;
    }
}
