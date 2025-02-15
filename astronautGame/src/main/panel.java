package main;
import javax.swing.*;

import resources.render_images;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class panel extends JPanel implements ActionListener
{
    static int x_caracter, y_caracter = 0;

    static float xspd_caracter = 2.f;
    static float yspd_caracter = 2.f;

    static float down_spd = 1.f;
    static float up_spd = 1.f;

    static int colected_coins = 0;

    static int hp_caracter = 10;

    final int list_positions[][] = {
        {60, 50}, 
        {60, 500}, 
        {600, 50}, 
        {600, 500}, 
        {320, 270},
        {300, 300}
    }; 

    final int list_positions_size = list_positions.length;

    boolean colissions[] = new boolean[list_positions_size];

    public panel()
    {
        new Timer(5, this).start();
    }

    public boolean collisionDetect(int x1, int y1, int x2, int y2, int HEIGHT, int WIDTH)
    {
        return (
            x1 < x2 + WIDTH &&
            x1 + WIDTH > x2 &&
            y1 < y2 + HEIGHT &&
            y1 + HEIGHT > y2
        );
    }

    public void makeString(Font font, Color color, int x, int y, String text, Graphics g)
    {
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);        
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.BLACK);
       
        g.drawImage(render_images.images[2][0], 0, 0, getWidth(), getHeight(), this);  

        for (int i = 0; i < list_positions.length; i++)
        {
            int position_coin_x = list_positions[i][0];
            int position_coin_y = list_positions[i][1];
            
            if (collisionDetect(x_caracter, y_caracter, position_coin_x, position_coin_y, 25, 25))
            {
                if (!colissions[i])
                {
                    colissions[i] = true;
                    colected_coins++;                    
                }
            } 
            else
            {
                if (!colissions[i])
                {
                    g.drawImage(render_images.images[1][0], position_coin_x, position_coin_y, 25, 25, this);                  
                }
            }
        }      

        if (!frame.space)
        {
            g.drawImage(frame.left ? render_images.images[0][1] : render_images.images[0][0], x_caracter, y_caracter, 50, 50, this);
        }
        else
        {
            g.drawImage(frame.left ? render_images.images[0][3] : render_images.images[0][2], x_caracter, y_caracter, 50, 50, this);
        }

        makeString(new Font("Pixel Operator 8", Font.PLAIN, 20), Color.BLACK, 5, 32, "Moedas: " + colected_coins, g);
        makeString(new Font("Pixel Operator 8", Font.PLAIN, 20), Color.WHITE, 0, 27, "Moedas: " + colected_coins, g);

        makeString(new Font("Pixel Operator 8", Font.PLAIN, 20), Color.BLACK, 200, 32, "vida: " + hp_caracter, g);
        makeString(new Font("Pixel Operator 8", Font.PLAIN, 20), Color.WHITE, 195, 27, "vida: " + hp_caracter, g);
    }

    @Override
    public void actionPerformed(ActionEvent c)
    {
        y_caracter += down_spd;
        repaint();
    }
}
