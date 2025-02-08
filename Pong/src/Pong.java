// BUNDA :)
package Projetos.Pong.src;

/**
 * PONG
 * @version 2.0
 * @author Miguel-Silva
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Classe principal
public class Pong extends JFrame
{
    static final int SIZE[] = {700, 550}; // Tamanho da tela
    private boolean up, down, W, S;

    public Pong()
    {
        setTitle("PONG");
        setSize(SIZE[0], SIZE[1]);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setFocusable(true);

        setLocationRelativeTo(null);

        add(new SquarePanel());
    
        setVisible(true);
        
        Timer timer = new Timer(10, _ -> mmove());
        timer.start();
        
        // Movimentar players com as teclas do teclado
        addKeyListener(new KeyListener() 
        {
            private boolean keyPressed;

            public void DetectKey(KeyEvent evt, boolean keyPressed)
            {
                int keyCode = evt.getKeyCode();

                switch (keyCode) 
                {
                    case KeyEvent.VK_UP -> up = keyPressed;
                    case KeyEvent.VK_DOWN -> down = keyPressed;
                    case KeyEvent.VK_W -> W = keyPressed;
                    case KeyEvent.VK_S -> S = keyPressed;
                }                
            }

            @Override
            public void keyPressed(KeyEvent evt) 
            {
                keyPressed = true;

                DetectKey(evt, keyPressed);
            }
        
            @Override
            public void keyReleased(KeyEvent evt) 
            {
                keyPressed = false;

                DetectKey(evt, keyPressed);
            }
        
            @Override
            public void keyTyped(KeyEvent evt) {}
        });
    }

    public void mmove() 
    {
        if (up) SquarePanel.y_player2 -= SquarePanel.spdy_player2;
        if (down) SquarePanel.y_player2 -= (SquarePanel.spdy_player2 * -1);

        if (W) SquarePanel.y_player1 -= SquarePanel.spdy_player1;
        if (S) SquarePanel.y_player1 -= (SquarePanel.spdy_player1 * -1);

        repaint(); // Atualiza a tela
    }

    public static void main(String[] args)
    {
        new Pong();
    }
}

class SquarePanel extends JPanel implements ActionListener
{
    // Informações da bola(Variáveis)
    public final int[] ballSize = {6, 6};
    public int xball = Pong.SIZE[0] / 2;
    public int spdxball = 4;
    public int yball = Pong.SIZE[1] / 2;
    public int spdyball = 4;

    // Informações da raquete 1
    public final int[] size_player1 = {7, 20};
    public int x_player1 = 0;
    public int points_player1 = 0; 

    static int y_player1 = 250;
    static int spdy_player1 = 5;

    // Informações da raquete 2
    public final int[] size_player2 = {7, 20};
    public int x_player2 = Pong.SIZE[0] - size_player2[1] - 2;
    public int points_player2 = 0;

    static int y_player2 = 50;
    static int spdy_player2 = 5;

    public final Timer timer;

    public SquarePanel()
    {
        timer = new Timer(10, this);
        timer.start();
    }

    // Método para desenhar os objetos
    public void drawRect(int x, int y, int WIDTH, int HEIGHT, Graphics g, Color color) 
    {
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);        
    }

    // Componente de desenho
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        drawRect(xball, yball, ballSize[0], ballSize[1], g, Color.WHITE); // Desenha a bola

        drawRect(x_player1, y_player1, size_player1[0], size_player1[1], g, Color.BLUE); // Desenha a raquete 1

        drawRect(x_player2, y_player2, size_player2[0], size_player2[1], g, Color.YELLOW); // Desenha a raquete 2
    }

    // Método para detectar a colisão
    public boolean collisionDetect(int x1, int y1, int x2, int y2, int HEIGHT, int WIDTH)
    {
        return (
            x1 < x2 + WIDTH &&
            x1 + WIDTH > x2 &&
            y1 < y2 + HEIGHT &&
            y1 + HEIGHT > y2
        );
    }

    public void reset()
    {
        this.xball = Pong.SIZE[0] / 2;
        this.yball = Pong.SIZE[1] / 2;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        // Mover e detectar a colisão da bola com as raquetes
        xball += spdxball;
        yball += spdyball;

        // Colisão da bola com as paredes
        if (yball > getHeight() - ballSize[0] || yball <= 0) spdyball *= -1;

        if (xball > getWidth() - ballSize[0])
        {
            points_player1++;
            reset();
            System.out.printf("Player 1 ganhou: %d pontos!\n", points_player1);
        }
        else if (xball <= 0)
        {
            points_player2++;
            reset();
            System.out.printf("Player 2 ganhou: %d pontos!\n", points_player2);
        }

        // Colisão da bola com as raquetes
        if (collisionDetect(xball, yball, x_player1, y_player1, size_player1[1], size_player1[0])) spdxball *= -1;
        if (collisionDetect(xball, yball, x_player2, y_player2, size_player2[1], size_player1[0])) spdxball *= -1;
            
        repaint();
    }
}