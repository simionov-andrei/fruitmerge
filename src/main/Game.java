package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import entities.Bush;
import entities.Player;
import levels.LevelManager;
import levels.Playing;
import utilz.LoadSave;
import utilz.HelpMethods;
import main.StartingMenu;
import main.PlayBackgroundMusic;
import main.PlayMenuMusic;


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
    private Random random = new Random();
    private int randomIndex = random.nextInt(3);
    private PlayBackgroundMusic backgroundMusicPlayer;
    private PlayMenuMusic menuMusicPlayer;

    public enum STATE {
        MENU,
        GAME,
        OPTIONS
    };
    public static STATE State = STATE.MENU;
    public StartingMenu menu = new StartingMenu();
    public OptionsMenu options = new OptionsMenu();
    
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

        menuMusicPlayer = new PlayMenuMusic();

    }

    private void initClasses() {
        playing = new Playing(this);
        levelManager = new LevelManager(this);
        player = new Player(435, 80, (int) (32 * SCALE), (int) (32 * SCALE),  randomIndex);
        bush = new Bush(435, 80, (int)(32 * SCALE * 1.5), (int)(32 * SCALE * 1.5), bushImage, player);
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
        bush.loadLvlData(levelManager.getCurrentLevel().getLevelData());
    }

    private void spawnNewPlayer(int spawningPointX, int spawningPointY, int index) {
        if (HelpMethods.IsEntityOnFloor(player.getHitbox(), LoadSave.GetLevelData()) || checkCollisions()) {
            activePlayers.add(player);
            player = new Player(spawningPointX, spawningPointY, (int) (32 * SCALE), (int) (32 * SCALE), index);
            player.loadLvlData(levelManager.getCurrentLevel().getLevelData()); 
        }
    }
    
    public boolean checkCollisions() {
        for (Player anotherPlayer : activePlayers) {
            if (player != anotherPlayer && player.getHitbox().intersects(anotherPlayer.getHitbox())) {
                collision(player, anotherPlayer);
                System.out.println("Collision detected!");
                return true;
            }
        }
        return false;
    }

    public void collision(Player player, Player anotherPlayer) {
        int playerSpriteIndex = player.getSprite(); 
        int anotherPlayerSpriteIndex = anotherPlayer.getSprite(); 

        if (playerSpriteIndex == anotherPlayerSpriteIndex && playerSpriteIndex != 2) {
            playerSpriteIndex++; 
            
        if (playerSpriteIndex > 2) {
            playerSpriteIndex = 2;
        }
            
            float mergedSpawnX = (player.getHitbox().x + anotherPlayer.getHitbox().x) / 2;
            float mergedSpawnY = (player.getHitbox().y + anotherPlayer.getHitbox().y) / 2;

            activePlayers.remove(player);
            activePlayers.remove(anotherPlayer);

            spawnNewPlayer((int)mergedSpawnX, (int)mergedSpawnY, playerSpriteIndex);
        }
    }

    
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        if(State == STATE.GAME) {
            bush.update();
            levelManager.update();
            player.update();
        }

    }

    public void render(Graphics g) {
        if(State != STATE.MENU) {
            playing.draw(g);
            levelManager.draw(g);
            bush.render(g);
            player.render(g);
            for(Player player : activePlayers) {
                player.render(g);
            }
        } else if (State == STATE.MENU) {
            menu.render(g);
        } 
        
        if (State == STATE.OPTIONS) {
            options.render(g);
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
                update();
                spawnNewPlayer(bush.getSpawningPointX(), bush.getSpawningPointY(), randomIndex);
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
