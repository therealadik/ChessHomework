package Chess.Piece;

import Chess.ChessBoard;
import Chess.Piece.Base.ChessPiece;

public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (isWithoutBounds(chessBoard, line, column, toLine, toColumn) || Math.abs(toColumn-column) > 0)
            return false;

        int moveDiff = Math.abs(toLine - line);

        if (!hasMoved){
            return moveDiff <= 2;
        }

        return moveDiff == 1;
    }

    @Override
    public char getSymbol() {
        return 'P';
    }

    @Override
    protected boolean isPathClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return false;
    }
}
