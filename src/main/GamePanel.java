package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class GamePanel extends JPanel{
    
    private MouseInputs mouseInputs = new MouseInputs(this);
    private KeyboardInputs keyboardInputs = new KeyboardInputs(this);
    private Game game;
    
    public GamePanel(Game game) {
        this.game = game;

        setPanelSize();
        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        
    }
    
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("Size:" + GAME_WIDTH + " | " + GAME_HEIGHT);
    }
       
    public void updateGame() {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        game.render(g);       
        //repaint(); - not needed anymore because of run from "Game"
    }
 
    public Game getGame() {
        return game;
    }    
}
