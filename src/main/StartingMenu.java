package main;

import static main.Game.GAME_WIDTH;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class StartingMenu {
    
    int ButtonHeight = 70;
    public Rectangle quitButton = new Rectangle(GAME_WIDTH / 2 - 165, 560, 140, ButtonHeight);
    public Rectangle playButton = new Rectangle(GAME_WIDTH / 2 + 25, 560, 140, ButtonHeight);

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("arial", Font.BOLD, 80);
        g.setFont(fnt0);
        g.setColor(Color.black);
        
        String title = "FRUIT MERGE";
        FontMetrics metrics = g.getFontMetrics(fnt0);
        int x0 = (GAME_WIDTH / 2) - ((metrics.stringWidth(title)) / 2);
        g.drawString(title, x0, 100);

        String controlsTitle = "Controls:";
        String controls1 = "Move right: D";
        String controls2 = "Move left: A";
        String controls3 = "Drop Fruit: Space Bar";

        int fnt2Size = 30;
        Font fnt2 = new Font("arial", Font.BOLD, fnt2Size);
        g.setFont(fnt2);
        metrics = g.getFontMetrics(fnt2);

        int x1 = (GAME_WIDTH / 2) - ((metrics.stringWidth(controlsTitle)) / 2);
        g.drawString(controlsTitle, x1, 250);

        int x2 = (GAME_WIDTH / 2) - ((metrics.stringWidth(controls1)) / 2);
        g.drawString(controls1, x2, 300);

        int x3 = (GAME_WIDTH / 2) - ((metrics.stringWidth(controls2)) / 2);
        g.drawString(controls2, x3, 350);

        int x4 = (GAME_WIDTH / 2) - ((metrics.stringWidth(controls3)) / 2);
        g.drawString(controls3, x4, 400);

        int fnt1Size = 40;
        Font fnt1 = new Font("arial", Font.BOLD, fnt1Size);

        String playButtonText = "Quit";
        metrics = g.getFontMetrics(fnt1);
        g.setFont(fnt1);
        g.drawString(playButtonText, 487, 608);

        g2d.draw(playButton);

        String quitButtonText = "Play";
        g.drawString(quitButtonText,679 , 608);
        
        g2d.draw(quitButton);
    }
}
