/**
 * Interface defining the contract for all chess pieces.
 */
public interface ChessPieceContract {
    /**
     * Gets the current row position of the piece.
     * @return the row position (0-7)
     */
    int getRow();
    
    /**
     * Gets the current column position of the piece.
     * @return the column position (0-7)
     */
    int getColumn();
    
    /**
     * Gets the color of the piece.
     * @return the color (BLACK or WHITE)
     */
    Color getColor();
    
    /**
     * Determines if the piece can move to a given cell.
     * @param row the target row (0-7)
     * @param col the target column (0-7)
     * @return true if the move is valid, false otherwise
     */
    boolean canMove(int row, int col);
    
    /**
     * Determines if this piece can kill/capture another piece.
     * @param piece the target piece
     * @return true if this piece can kill the target piece, false otherwise
     */
    boolean canKill(ChessPiece piece);
}