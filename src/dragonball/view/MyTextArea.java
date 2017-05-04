package dragonball.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;

public class MyTextArea extends JTextArea {
    private Image backgroundImage;

    public MyTextArea() {
        super();
        setOpaque(false);
    }

    public void setBackgroundImage(Image image) {
        this.backgroundImage = image;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this);
        }

        super.paintComponent(g);
    }
}