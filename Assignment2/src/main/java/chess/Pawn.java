package chess;

public class Pawn extends ChessPiece {

    public Pawn(int row, int column, Color color) {
        super(row, column, color);
        if (color == Color.WHITE && row == 0) {
            throw new IllegalArgumentException("White pawns cannot be created in row 0");
        }
        if (color == Color.BLACK && row == 7) {
            throw new IllegalArgumentException("Black pawns cannot be created in row 7");
        }
    }

    @Override
    public boolean canMove(int row, int col) {
        if (!isValidPosition(row, col) || isSamePosition(row, col)) {
            return false;
        }
        if (col != this.column) {
            return false;
        }
        if (color == Color.WHITE) {
            if (row == this.row + 1) {
                return true;
            }
            if (this.row == 1 && row == this.row + 2) {
                return true;
            }
        } else {
            if (row == this.row - 1) {
                return true;
            }
            if (this.row == 6 && row == this.row - 2) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canKill(ChessPiece piece) {
        if (piece == null || piece.getColor() == this.color) {
            return false;
        }
        int targetRow = piece.getRow();
        int targetCol = piece.getColumn();
        int colDiff = Math.abs(targetCol - this.column);
        if (colDiff != 1) {
            return false;
        }
        if (color == Color.WHITE) {
            return targetRow == this.row + 1;
        } else {
            return targetRow == this.row - 1;
        }
    }
}
