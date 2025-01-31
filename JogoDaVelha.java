package JogoDavelha.src;
import java.util.Scanner;

// Classe pricipal
public class JogoDaVelha {
    static Scanner sc = new Scanner(System.in);

    public static int Get_int(String text) {
        System.out.print(text);
        int i = sc.nextInt();
        System.out.println();  
        return i; 
    }

    public static void main(String[] args) throws Exception {
        // Variaveis
        // Strings(Arrays)
        final char Board[][] = 
        {{' ', ' ', ' '}, 
         {' ', ' ', ' '},
         {' ', ' ', ' '}};
        final char Array[] = {'O', 'X'};
        
        // Bool
        boolean o_win = false;
        boolean x_win = false;

        // Int
        int rounds = 0;
        int x;
        int y;

        // Programa principal
        while (true) {
            if (rounds > Array.length - 1) { rounds = 0; }

            for (char[] l : Board)
                System.out.println("|" + l[0] + "|" + l[1] + "|" + l[2] + "|");

            System.out.println("Vez do " + Array[rounds]);

            x = Get_int("Posição X(lateral): ");
            y = Get_int("Posição Y(horizontal): ");
            x--; y--;     

            if (x < Board[0].length && y < Board.length) {             
                if (Board[y][x] == ' ')
                    Board[y][x] = Array[rounds];

                // Indentificar quem ganhou
                for (int i = 0; i <= 2; i++) {
                    if (Board[i][0] == 'O' && Board[i][1] == 'O' && Board[i][2] == 'O') {
                        o_win = true;
                    } else if (Board[i][0] == 'X' && Board[i][1] == 'X' && Board[i][2] == 'X') {
                        x_win = true;
                    } else if (Board[0][i] == 'O' && Board[1][i] == 'O' && Board[2][i] == 'O') {
                        o_win = true;
                    } else if (Board[0][i] == 'X' && Board[1][i] == 'X' && Board[2][i] == 'X') {
                        x_win = true;
                    } 
                }
                if ((Board[0][2] == 'O' && Board[1][1] == 'O' && Board[2][0] == 'O') || 
                (Board[0][0] == 'O' && Board[1][1] == 'O' && Board[2][2] == 'O')) {
                    o_win = true;
                } else if ((Board[0][2] == 'X' && Board[1][1] == 'X' && Board[2][0] == 'X') || 
                (Board[0][0] == 'X' && Board[1][1] == 'X' && Board[2][2] == 'X')) {
                    x_win = true;
                }

                System.out.print(x_win ? "X ganhou!\n" : (o_win ? "O ganhou!\n" : ""));    
                if (x_win || o_win) 
                    break;
            } else {
                System.out.println("Coloque " + Array[rounds] + " no tabuleiro!");
                continue;
            }
            rounds++; 
        }
    }
}