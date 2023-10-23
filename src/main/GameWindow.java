package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

public class GameWindow extends JFrame{

    public GameWindow(GamePanel gamePanel) {

        JFrame jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.pack(); // fit the size of the window to the preferred size of the panel
        jframe.addWindowFocusListener(new WindowFocusListener() {
            
            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
            }
        });
        jframe.setVisible(true);
    }
}
