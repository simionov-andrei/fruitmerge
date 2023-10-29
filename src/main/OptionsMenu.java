package main;

import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class OptionsMenu {

    public void render(Graphics g) {

        Rectangle optionsBackground = new Rectangle(GAME_WIDTH / 2 - 400, GAME_HEIGHT / 2 - 250, 800, 500);
        Rectangle quitButton = new Rectangle(GAME_WIDTH / 2 - 305, 490, 280, 70);
        Rectangle continueButton = new Rectangle(GAME_WIDTH / 2 + 25, 490, 280, 70);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.white);
        g2d.fill(optionsBackground);

        Font fnt0 = new Font("arial", Font.BOLD, 80);
        g.setFont(fnt0);
        g.setColor(Color.black);
        
        String title = "OPTIONS";
        FontMetrics metrics = g.getFontMetrics(fnt0);
        int x0 = (GAME_WIDTH / 2) - ((metrics.stringWidth(title)) / 2);
        g.drawString(title, x0, 175);
        
        int fnt1Size = 40;

        Font fnt1 = new Font("arial", Font.BOLD, fnt1Size);
        metrics = g.getFontMetrics(fnt1);
        g.setFont(fnt1);
        g.drawString("Quit", 415, 540);

        g2d.draw(quitButton);

        g.drawString("Continue",700 , 540);
        
        g2d.draw(continueButton);

    }
}
