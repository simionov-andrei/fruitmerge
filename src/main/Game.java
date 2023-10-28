package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import entities.Bush;
import entities.Player;
import levels.LevelManager;
import levels.Playing;
import utilz.LoadSave;
import utilz.HelpMethods;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;
    private LevelManager levelManager;
    private Playing playing;
    private Bush bush;
    private BufferedImage bushImage = LoadSave.GetSpriteAtlas(LoadSave.BUSH_SPRITE);
    private List<Player> activePlayers = new ArrayList<>();

    public static final int TILES_DEFAULT_SIZE = 32;
    public static final float SCALE = 1.5f;
    public static final int TILES_IN_WIDTH = 26;
    public static final int TILES_IN_HEIGHT = 14;
    public static final int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public static final int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public static final int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    public Game() {
        
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        startGameLoop();

    }

    private void initClasses() {
        playing = new Playing(this);
        levelManager = new LevelManager(this);
        player = new Player(435, 80, (int) (32 * SCALE), (int) (32 * SCALE));
        bush = new Bush(435, 80, (int)(32 * SCALE * 1.5), (int)(32 * SCALE * 1.5), bushImage, player);
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
        bush.loadLvlData(levelManager.getCurrentLevel().getLevelData());
    }

    private void spawnNewPlayer() {
        if (HelpMethods.IsEntityOnFloor(player.getHitbox(), LoadSave.GetLevelData())) {
            activePlayers.add(player);
            player = new Player(bush.getSpawningPointX(), bush.getSpawningPointY(), (int) (32 * SCALE), (int) (32 * SCALE));
            player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
        }
    }

    //From the try that worked
    // public boolean checkCollisions() {
    //     boolean collision = false;
        
    //     // Check collisions with the active player
    //     if (player.collidesWithSomething()) {
    //         collision = true;
    //     }
    
    //     // Check collisions with the previous player objects
    //     for (Player oldPlayer : previousPlayers) {
    //         if (oldPlayer.collidesWithSomething()) {
    //             collision = true;
    //         }
    //     }
    
    //     return collision;
    // }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        bush.update();
        levelManager.update();
        player.update();
        for(Player player : activePlayers) {
            player.update();
        }
    }

    public void render(Graphics g) {
        playing.draw(g);
        levelManager.draw(g);
        bush.render(g);
        player.render(g);
        for(Player player : activePlayers) {
            player.render(g);
        }
    }

    @Override
    public void run() {
        
        double timePerFrame = 1000000000.0 / FPS_SET; // NANOSECONDS
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();
        
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        
        double deltaU = 0;
        double deltaF = 0;

        while (!false) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            
            if (deltaU >= 1) {
                spawnNewPlayer();
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }
            
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS:" + frames + "| UPS:" + updates);
                frames = 0;
                updates = 0;
            }
        }

    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }


    public Player getPlayer() {
        return player;
    }

    public Bush getBush(){
        return bush;
    }
}
