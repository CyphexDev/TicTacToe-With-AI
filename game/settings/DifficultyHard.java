package game.settings;

import game.Board;
import game.Coordinates;
import game.players.AI;

import java.util.ArrayList;
import java.util.List;

public class DifficultyHard extends AI {
    Coordinates aiMove;
    private Coordinates computerMove;

    public DifficultyHard(Settings type, Board board) {
        super(type, board);
    }

    private int miniMax(int depth, boolean isFirstPlayer) {
        int result = getBoard().cells.checkWinner();
        if (result != -111) return result;

        List<Coordinates> pointsAvailable = getAvailableIndexes();
        if (pointsAvailable.isEmpty()) return 0;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < pointsAvailable.size(); ++i) {
            Coordinates pt = pointsAvailable.get(i);
            if (isFirstPlayer) {
                setSpot(pt.getX(), pt.getY(), 'X');
                int currentScore = miniMax(depth + 1, false);
                max = Math.max(currentScore, max);
                if (currentScore >= 0) {
                    if (depth == 0) {
                        computerMove = pt;
                    }
                }
                if (currentScore == 1) {
                    setSpot(pt.getX(), pt.getY(), '_');
                    break;
                }
                if (i == pointsAvailable.size() - 1 && max < 0) {
                    if (depth == 0) {
                        computerMove = pt;
                    }
                }
            } else {
                setSpot(pt.getX(), pt.getY(), 'O');
                int currentScore = miniMax(depth + 1, true);
                min = Math.min(currentScore, min);
                if (min == -1) {
                    setSpot(pt.getX(), pt.getY(), '_');
                    break;
                }
                if (currentScore <= 0) {
                    if (depth == 0) {
                        computerMove = pt;
                    }
                }
                if (currentScore == 1) {
                    setSpot(pt.getX(), pt.getY(), '_');
                    break;
                }
            }
            setSpot(pt.getX(), pt.getY(), '_');
        }
        return isFirstPlayer ? max : min;
    }


    private int returnNextMove(boolean isFirstPlayer) {
        if (getBoard().cells.checkWinner() != -111) return -1;
        miniMax(0, isFirstPlayer);
        return 10;
    }

    private List<Coordinates> getAvailableIndexes() {
        List<Coordinates> coordinates = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Board.board[i][j] == '_') {
                    coordinates.add(new Coordinates(j, i, false));
                }
            }
        }
        return coordinates;
    }

    @Override
    public void getInput(boolean isFirstPlayer) {
        System.out.println(isFirstPlayer ? "Player 1s turn" : "Player 2s turn");
        if (returnNextMove(isFirstPlayer) == 10) {
            if (computerMove != null) {
                int x = computerMove.getX();
                int y = computerMove.getY();
                if (getBoard().cells.isEmpty(y, x)) {
                    setSpot(x, y, isFirstPlayer ? 'X' : 'O');
                }
            }
        }
        getBoard().printBoard();
    }
}

