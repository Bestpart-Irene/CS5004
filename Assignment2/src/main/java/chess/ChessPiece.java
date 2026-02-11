package chess;

public abstract class ChessPiece implements chess.ChessPieceContract {
    protected int row;
    protected int column;
    protected Color color;

    public ChessPiece(int row, int column, chess.Color color) {
        if (row < 0 || row > 7 || column < 0 || column > 7) {
            throw new IllegalArgumentException("Position must be within 0-7 for both row and column");
        }
        this.row = row;
        this.column = column;
        this.color = color;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public Color getColor() {
        return color;
    }

    protected boolean isValidPosition(int row, int col) {
        return row >= 0 && row <= 7 && col >= 0 && col <= 7;
    }

    protected boolean isSamePosition(int row, int col) {
        return this.row == row && this.column == col;
    }

    @Override
    public boolean canKill(ChessPiece piece) {
        if (piece == null || piece.getColor() == this.color) {
            return false;
        }
        return canMove(piece.getRow(), piece.getColumn());
    }
}
