package Pong.src;

public class Pong 
{
    // map size
    static final int SIZE[] = {20, 35}; // this is the size of the map

    // ball - variables
    static int x_ball = (int)(SIZE[0] / 2);
    static double velx_ball, vely_ball = 1;
    static int y_ball = (int)SIZE[1] / 2;

    // player 1 - variables
    static final int x_player = 0;
    static double velx_player, vely_player = 1;
    static int y_player = 0;

    // player 2 - variables
    static final int x_player2 = SIZE[1] - 1;
    static double velx_player2, vely_player2 = 1;
    static int y_player2 = 0;

    // instance of the map
    static final char[][] Map = new char[SIZE[0]][SIZE[1]]; // this is map

    public static void main(String[] args) throws Exception 
    {
        while (true) 
        {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            Map[y_ball][x_ball] = 'O'; // draw ball
            Map[y_player][x_player] = '|'; // draw player 1
            Map[y_player2][x_player2] = '|'; // draw player 2

            x_ball += velx_ball;
            y_ball += vely_ball;

            if (x_ball >= SIZE[1] - 1 || x_ball <= 0)
                velx_ball *= -1;
            if (y_ball >= SIZE[0] - 1 || y_ball <= 0)
                vely_ball *= -1;

            ShowMatriz(Map);  
            Update(Map);
        }
    }

    static void Update(char[][] matriz) 
    {
        for (int l = 0; l < matriz.length; l++) 
        {
            for (int c = 0; c < matriz[l].length; c++) 
                matriz[l][c] = ' ';
        }        
    }

    static void ShowMatriz(char[][] matriz) 
    {
        // In this function i go to show the matriz(map)
        for (int l = 0; l < matriz.length; l++) 
        {
            for (int c = 0; c < matriz[l].length; c++)
                System.out.print(matriz[l][c]);
            System.out.println("|");
        }
        System.out.println("------------------------------------");
    }
}
