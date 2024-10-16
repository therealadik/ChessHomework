package Chess.Piece;

import Chess.ChessBoard;
import Chess.Piece.Base.ChessPiece;

public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isWithoutBounds(chessBoard, line, column, toLine, toColumn))
            return false;

        int lineDiff = Math.abs(toLine - line);
        int columnDiff = Math.abs(toColumn - column);

        if ((lineDiff == 2 && columnDiff == 1) || (lineDiff == 1 && columnDiff == 2)) {
            if (chessBoard.board[toLine][toColumn] == null ||
                    !chessBoard.board[toLine][toColumn].getColor().equals(this.getColor())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public char getSymbol() {
        return 'H';
    }

    @Override
    protected boolean isPathClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return false;
    }
}
