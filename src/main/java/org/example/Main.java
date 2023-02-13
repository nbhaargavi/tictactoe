package org.example;
import java.util.Scanner;
import java.util.logging.*;
public class Main {
    static Logger l = Logger.getLogger("kitty");
    static void draw(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int finalI = i;
                int finalJ = j;
                l.log(Level.INFO,() ->""+(board[finalI][finalJ]));
            }
            l.info("");
        }
    }
    public static char won(char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] !='-') {
                return board[i][0];
            }
        }
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j]&& board[0][j] !='-') {
                return board[0][j];
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]&& board[0][0] !='-' ) {
            return board[0][0];
        }
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2]&& board[2][0] !='-') {
            return board[2][0];
        }
        return '-';
    }
    public static boolean tied(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
    static boolean check(int c, int r){
        return (r > 2 || c > 2 || r < 0 || c < 0);
    }
    public static void main(String[] args) {
        int a = 0;
        do {
            Logger l = Logger.getLogger("kitty");
            Scanner sc = new Scanner(System.in);
            l.info("Enter player 1 name");
            String p1 = sc.nextLine();
            l.info("Enter player 2 name");
            String p2 = sc.nextLine();
            int stop = 1;
            char[][] board = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = '-';
                }
            }
            boolean isp1 = true;
            boolean gameended = false;

            while (!gameended && stop == 1) {

                char symbol;
                if (isp1) {
                    symbol = 'x';
                } else {
                    symbol = 'o';
                }
                if (isp1) {
                    l.log(Level.INFO,() -> p1 + " turn");
                } else {
                    l.log(Level.INFO,() -> p2 + " turn");
                }
                l.info("Enter the row position");
                int r = sc.nextInt();
                l.info("Enter the column position");
                int c = sc.nextInt();
                if (check(c,r)) {
                    l.info("Invalid input");
                    continue;
                } else {
                    board[r][c] = symbol;
                }
                char win = won(board);
                boolean tie = tied(board);
                if (win == 'x') {
                    l.log(Level.INFO,() ->p1 + "have won");
                    gameended = true;
                    stop = 0;
                } else if (win == 'o') {
                    l.log(Level.INFO,() ->p2 + " have won");
                    gameended = true;
                    stop = 0;
                } else if (tie) {
                    l.info("Match tied ");
                    gameended = true;
                    stop = 0;
                } else {
                    isp1 = !isp1;
                }
                draw(board);
            }
            l.info("Press 1 for continue playing ");
            a = sc.nextInt();
        }while (a == 1);
    }
}

