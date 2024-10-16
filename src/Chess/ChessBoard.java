package Chess;

import Chess.Piece.Base.ChessPiece;
import Chess.Piece.King;
import Chess.Piece.Rook;

public class ChessBoard {
    private final int size = 8;
    public ChessPiece[][] board = new ChessPiece[size][size]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public int getSize() {
        return size;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            ChessPiece currentPiece = board[startLine][startColumn];
            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                if (currentPiece.getClass() == Rook.class || currentPiece.getClass() == King.class && currentPiece.hasMoved())
                    currentPiece.setHasMoved(false);

                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[startLine][startColumn] = null; // set null to previous cell
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            King king = (King) board[0][4];
            Rook rook = (Rook) board[0][0];
            if (king != null && rook != null && !king.hasMoved() && !rook.hasMoved()) {
                if (board[0][1] == null && board[0][2] == null && board[0][3] == null) {
                    if (!king.isUnderAttack(this, 0,4) && !king.isUnderAttack(this, 0,3) && !king.isUnderAttack(this, 0,2)) {
                        board[0][4] = null;
                        board[0][2] = king;
                        board[0][0] = null;
                        board[0][3] = rook;
                        king.setHasMoved(true);
                        rook.setHasMoved(true);
                        nowPlayer = "Black";
                        return true;
                    }
                }
            }
        } else {
            King king = (King) board[7][4];
            Rook rook = (Rook) board[7][0];
            if (king != null && rook != null && !king.hasMoved() && !rook.hasMoved()) {
                if (board[7][1] == null && board[7][2] == null && board[7][3] == null) {
                    if (!king.isUnderAttack(this,7, 4) && !king.isUnderAttack(this,7, 3) && !king.isUnderAttack(this,7, 2)) {
                        board[7][4] = null;
                        board[7][2] = king;
                        board[7][0] = null;
                        board[7][3] = rook;
                        king.setHasMoved(true);
                        rook.setHasMoved(true);
                        nowPlayer = "White";
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            King king = (King) board[0][4];
            Rook rook = (Rook) board[0][7];
            if (king != null && rook != null && !king.hasMoved() && !rook.hasMoved()) {
                if (board[0][5] == null && board[0][6] == null) {
                    if (!king.isUnderAttack(this, 0, 4) && !king.isUnderAttack(this, 0, 5) && !king.isUnderAttack(this, 0, 6)) {
                        board[0][4] = null;
                        board[0][6] = king;
                        board[0][7] = null;
                        board[0][5] = rook;
                        king.setHasMoved(true);
                        rook.setHasMoved(true);
                        nowPlayer = "Black";
                        return true;
                    }
                }
            }
        } else {
            King king = (King) board[7][4];
            Rook rook = (Rook) board[7][7];
            if (king != null && rook != null && !king.hasMoved() && !rook.hasMoved()) {
                if (board[7][5] == null && board[7][6] == null) {
                    if (!king.isUnderAttack(this, 7, 4) && !king.isUnderAttack(this, 7, 5) && !king.isUnderAttack(this, 7, 6)) {
                        board[7][4] = null;
                        board[7][6] = king;
                        board[7][7] = null;
                        board[7][5] = rook;
                        king.setHasMoved(true);
                        rook.setHasMoved(true);
                        nowPlayer = "White";
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
