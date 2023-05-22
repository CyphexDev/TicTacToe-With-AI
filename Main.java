import game.Board;
import game.settings.Settings;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String playerTwo;
        String playerOne;
        while (true) {
            System.out.print("\nPlayer1:\n1.) user\n2.) easy\n3.) medium\n4.) hard\nOption: ");
            int p1Opt = scanner.nextInt();
            playerOne = getPlayer(p1Opt);
            if (playerOne == null) {
                System.out.println("Choose again!");
                continue;
            }
            System.out.print("\nPlayer2:\n1.) user\n2.) easy\n3.) medium\n4.) hard\nOption: ");
            int p2Opt = scanner.nextInt();

            playerTwo = getPlayer(p2Opt);
            if (playerTwo == null) {
                System.out.println("Choose again!");
                continue;
            }

            Board board = new Board(playerOne, playerTwo);
            board.startGame();
            System.out.println();
        }
    }
    private static String getPlayer(int pos) {
        switch(pos){
            case 1: {
                return "user";
            }
            case 2: {
                return "easy";
            }
            case 3: {
                return "medium";
            }
            case 4: {
                return "hard";
            }
            default: {
                return null;
            }
        }
    }
}