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
    private float xDelta = 100;
    private float yDelta = 100;
    private Game game;

    //private long lastAniStartTime = 0;
    //private long aniDelay = 1000; // 2 seconds in miliseconds
    
    /* 
    private float xDir = 1f;
    private float yDir = 1f;
    private Random random = new Random();
    */
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

    public void changeXDelta(int value) {
        this.xDelta += value;
        /* 
        if (100 <= this.xDelta && this.xDelta <= 300) {
            repaint();
        } else if (100 >= this.xDelta){
            this.xDelta = 100;
        } else if (this.xDelta >= 300){
            this.xDelta = 300;
        }
        */
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void setRecPosition(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }
       
    public void updateGame() {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        game.render(g);       
        //repaint(); - not needed anymore because of run from "Game"
    }
    /*
    public void updateRect() {
        xDelta += xDir;
        if (xDelta > 400 || xDelta < 0) {
            xDir *= -1;
        }

        yDelta += yDir;
        if (yDelta > 400 || yDelta < 0) {
            yDir *= -1;
        }
    }
    */

    public Game getGame() {
        return game;
    }    
}
