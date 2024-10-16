package Chess.Piece.Base;

import Chess.ChessBoard;

public abstract class ChessPiece {
    private final String color;
    protected boolean hasMoved = false;

    public ChessPiece(String color) {
        this.color = color;
    }

    protected boolean isWithoutBounds(ChessBoard chessBoard, int line, int column, int toLine, int toColumn){
        int sizeBoard = chessBoard.getSize();
        return toLine >= sizeBoard || toLine < 0 || toColumn >= sizeBoard || toColumn < 0 || (line == toLine && column == toColumn);
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public String getColor() {
        return color;
    }

    public abstract char getSymbol();

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    protected abstract boolean isPathClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);
}
