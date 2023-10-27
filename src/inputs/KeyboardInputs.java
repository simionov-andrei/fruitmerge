package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

public class KeyboardInputs implements KeyListener{

    private GamePanel gamePanel;
    
    public KeyboardInputs (GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            /* 
            case KeyEvent.VK_W:
            gamePanel.getGame().getPlayer().setUp(false);
            break;
            case KeyEvent.VK_S:
            gamePanel.getGame().getPlayer().setDown(false);
            break;
            */
            case KeyEvent.VK_A:
            gamePanel.getGame().getBush().setLeft(false);
            break;
            case KeyEvent.VK_D:
            gamePanel.getGame().getBush().setRight(false);
            break;
            case KeyEvent.VK_SPACE:
            gamePanel.getGame().getPlayer().setJump(false);
            break;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            /* 
            case KeyEvent.VK_W:
            gamePanel.getGame().getPlayer().setUp(true);
            break;
            case KeyEvent.VK_S:
            gamePanel.getGame().getPlayer().setDown(true);
            break;
            */
            case KeyEvent.VK_A:
            gamePanel.getGame().getBush().setLeft(true);
            break;
            case KeyEvent.VK_D:
            gamePanel.getGame().getBush().setRight(true);
            break;
            case KeyEvent.VK_SPACE:
            gamePanel.getGame().getPlayer().setJump(true);
            break;
        }
    }

}
