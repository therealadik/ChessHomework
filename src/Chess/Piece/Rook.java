package Chess.Piece;

import Chess.ChessBoard;
import Chess.Piece.Base.ChessPiece;

public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isWithoutBounds(chessBoard, line, column, toLine, toColumn)){
            return false;
        }

        if (line == toLine || column == toColumn) {
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
        return 'R';
    }

    @Override
    protected boolean isPathClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line == toLine) { // Движение по горизонтали
            int step = (toColumn > column) ? 1 : -1;
            for (int i = column + step; i != toColumn; i += step) {
                if (chessBoard.board[line][i] != null) {
                    return false;
                }
            }
        } else { // Движение по вертикали
            int step = (toLine > line) ? 1 : -1;
            for (int i = line + step; i != toLine; i += step) {
                if (chessBoard.board[i][column] != null) {
                    return false;
                }
            }
        }
        return true;
    }
}
