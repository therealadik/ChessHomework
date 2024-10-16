package Chess.Piece;

import Chess.ChessBoard;
import Chess.Piece.Base.ChessPiece;

public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isWithoutBounds(chessBoard, line, toLine, column, toColumn))
            return false;

        if (Math.abs(toLine - line) == Math.abs(toColumn - column)) {
            // Проверка, что на пути нет других фигур
            if (!isPathClear(chessBoard, line, column, toLine, toColumn)) {
                return false;
            }

            // Проверка на возможность съедания фигуры
            if (chessBoard.board[toLine][toColumn] == null ||
                    !chessBoard.board[toLine][toColumn].getColor().equals(this.getColor())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public char getSymbol() {
        return 'B';
    }

    @Override
    protected boolean isPathClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int lineStep = (toLine > line) ? 1 : -1;
        int columnStep = (toColumn > column) ? 1 : -1;
        int i = line + lineStep;
        int j = column + columnStep;
        while (i != toLine && j != toColumn) {
            if (chessBoard.board[i][j] != null) {
                return false;
            }
            i += lineStep;
            j += columnStep;
        }
        return true;
    }
}
