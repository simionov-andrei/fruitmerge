package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.Bush;
import entities.Player;
import entities.SpawningPoint;
import levels.LevelManager;
import levels.Playing;
import utilz.LoadSave;

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
        //bush.setSpawningPoint(new SpawningPoint(, ));
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
        bush.loadLvlData(levelManager.getCurrentLevel().getLevelData());
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        bush.update();
        levelManager.update();
    }

    public void render(Graphics g) {
        playing.draw(g);
        levelManager.draw(g);
        bush.render(g);
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
