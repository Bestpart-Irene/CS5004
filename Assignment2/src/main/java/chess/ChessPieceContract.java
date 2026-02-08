package chess;

public interface ChessPieceContract {
    int getRow();
    int getColumn();
    Color getColor();
    boolean canMove(int row, int col);
    boolean canKill(ChessPiece piece);
}
