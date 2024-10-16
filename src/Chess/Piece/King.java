package Chess.Piece;

import Chess.ChessBoard;
import Chess.Piece.Base.ChessPiece;

public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isWithoutBounds(chessBoard,line,column,toLine,toColumn))
            return false;

        int lineDiff = Math.abs(toLine - line);
        int columnDiff = Math.abs(toColumn - column);

        if (lineDiff <= 1 && columnDiff <= 1) {
            if (chessBoard.board[toLine][toColumn] == null ||
                    !chessBoard.board[toLine][toColumn].getColor().equals(this.getColor())) {
                return true;
            }
        }
        return false;
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        int size = chessBoard.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ChessPiece piece = chessBoard.board[i][j];
                if (piece != null && !piece.getColor().equals(getColor())) {
                    if (piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    @Override
    public char getSymbol() {
        return 'K';
    }

    @Override
    protected boolean isPathClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return true;
    }
}
