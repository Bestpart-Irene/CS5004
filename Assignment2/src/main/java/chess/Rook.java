package chess;

public class Rook extends ChessPiece {

    public Rook(int row, int column, Color color) {
        super(row, column, color);
    }

    @Override
    public boolean canMove(int row, int col) {
        if (!isValidPosition(row, col) || isSamePosition(row, col)) {
            return false;
        }
        return this.row == row || this.column == col;
    }
}
