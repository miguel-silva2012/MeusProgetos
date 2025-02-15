package main;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import resources.render_images;

public class frame extends JFrame
{
    static boolean right, left, down, space;

    public frame()
    {
        setTitle("AstronautGame");
        setSize(700, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setIconImage(render_images.images[3][0]);

        add(new panel());

        new Timer(5, _ -> mmove()).start();

        // Detectar teclas
        addKeyListener(new KeyListener() {
            private boolean keyPressed;

            public void DetectKey(KeyEvent evt, boolean keyPressed)
            {
                switch (evt.getKeyCode()) 
                {
                    case KeyEvent.VK_LEFT -> left = keyPressed;
                    case KeyEvent.VK_RIGHT -> right = keyPressed;

                    case KeyEvent.VK_SPACE -> space = keyPressed;
                    case KeyEvent.VK_DOWN -> down = keyPressed;
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
            public void keyTyped(KeyEvent evt) { } 
        });
    
        setVisible(true);
    }

    public void mmove() 
    {
        if (right) { panel.x_caracter += panel.xspd_caracter; }
        if (left) { panel.x_caracter -= panel.xspd_caracter; }
        
        if (down)
        {
            panel.down_spd += panel.down_spd > 4 ? 0 : .05f;
        }
        else 
        {
            if (panel.down_spd > 1)
            {
                panel.down_spd -= .05f;
            } 
            else
            {
                if (space) 
                {
                    panel.y_caracter -= panel.up_spd * 3;
                }
                else
                {
                    panel.down_spd = 1.f;
                }
            }    
        }

        repaint();
    }

    public static void main(String[] args)
    {
        new frame();
    }
}
