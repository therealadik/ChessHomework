package Chess.Piece;

import Chess.ChessBoard;
import Chess.Piece.Base.ChessPiece;

public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isWithoutBounds(chessBoard, line, column, toLine, toColumn))
            return false;

        if (line == toLine || column == toColumn || Math.abs(toLine - line) == Math.abs(toColumn - column)) {
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
        return 'Q';
    }

    @Override
    protected boolean isPathClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line == toLine || column == toColumn) { // Движение как ладья
            int step = (line == toLine) ? (toColumn > column ? 1 : -1) : (toLine > line ? 1 : -1);
            if (line == toLine) {
                for (int i = column + step; i != toColumn; i += step) {
                    if (chessBoard.board[line][i] != null) return false;
                }
            } else {
                for (int i = line + step; i != toLine; i += step) {
                    if (chessBoard.board[i][column] != null) return false;
                }
            }
        } else { // Движение как слон
            int lineStep = (toLine > line) ? 1 : -1;
            int columnStep = (toColumn > column) ? 1 : -1;
            int i = line + lineStep;
            int j = column + columnStep;
            while (i != toLine && j != toColumn) {
                if (chessBoard.board[i][j] != null) return false;
                i += lineStep;
                j += columnStep;
            }
        }
        return true;
    }
}
