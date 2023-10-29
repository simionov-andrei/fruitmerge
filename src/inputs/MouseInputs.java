package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import main.GamePanel;
import javax.swing.JFrame;
import main.Game;
import main.PlayMenuMusic;
import main.Game.STATE;
import main.PlayBackgroundMusic;
import levels.Playing;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;
    private PlayBackgroundMusic backgroundMusicPlayer;
    private PlayMenuMusic menuMusicPlayer;

    public MouseInputs(GamePanel gamePanel) {
        
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        // Start Menu Play Button
        if(Game.State == STATE.MENU && mx >= Game.GAME_WIDTH / 2 + 25 && mx <= Game.GAME_WIDTH / 2 + 165) {

            if(my >= 560 && my <= 630) {

                // Pressed Play Button
                Game.State = Game.STATE.GAME;
                
                if (Game.State == Game.STATE.GAME) {
                    PlayMenuMusic.stopMenuMusic();
                    PlayBackgroundMusic.playBackgroundMusic("C:/University/Q1/Programming/fruitmerge/res/backgroundMusic.wav");
                } 
            }
        }

        //public Rectangle optionsButton = new Rectangle(1168, 18, 60, 60);

        // Game Options Button
        if(Game.State == STATE.GAME && mx >= 1168 && mx <= 1228) {

            if (my >= 18 && my <= 78) {

                // Pressed Options Button
                Game.State = Game.STATE.OPTIONS;

                if (Game.State != Game.STATE.GAME) {
                    PlayBackgroundMusic.stopBackgroundMusic();
                    PlayMenuMusic.playMenuMusic("C:/University/Q1/Programming/fruitmerge/res/menuMusic.wav");
                } 
            }
        }

        // Start Menu Quit Button
        if(Game.State == STATE.MENU && mx >= Game.GAME_WIDTH / 2 - 165 && mx <= Game.GAME_WIDTH / 2 - 25) {

            if(my >= 560 && my <= 630) {

                // Pressed Quit Button
                System.exit(1);
            }
        }

        if(Game.State == STATE.OPTIONS && mx >= Game.GAME_WIDTH / 2 - 305 && mx <= Game.GAME_WIDTH / 2 - 25) {

            if(my >= 490 && my <= 560) {

                // Pressed Options Quit Button
                System.exit(1);
                } 
        }

        if(Game.State == STATE.OPTIONS && mx >= Game.GAME_WIDTH / 2 + 25 && mx <= Game.GAME_WIDTH / 2 + 305) {

            if (my >= 490 && my <= 560) {

                // Pressed Options Continue Button
                Game.State = Game.STATE.GAME;

                if (Game.State == Game.STATE.GAME) {
                    PlayMenuMusic.stopMenuMusic();
                    PlayBackgroundMusic.playBackgroundMusic("C:/University/Q1/Programming/fruitmerge/res/backgroundMusic.wav");
                } 
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //gamePanel.getGame().getPlayer().setRecPosition(e.getX(), e.getY());
    }
    
}

