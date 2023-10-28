package entities;

import static utilz.HelpMethods.CanMoveHere;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Bush {
    private float x, y;
    private int width, height;
    private BufferedImage bushImage;
    
    //gravity
    private boolean left, up, right, down;
    private float airSpeed = 0f;
    private int[][] lvlData;
    private float playerSpeed = 2.0f;
    private boolean inAir = false;
    private boolean freeMove = true;
    private Player player, newPlayer;
    private SpawningPoint spawningPoint;
    
    public Bush(float x, float y, int width, int height, BufferedImage bushImage, Player player) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bushImage = bushImage;
        this.player = player;
    }

    public void setSpawningPoint(SpawningPoint spawningPoint) {
        this.spawningPoint = spawningPoint;
    }
    
    public void render(Graphics g) {
        g.drawImage(bushImage, (int) x - 45, (int) y - 70, (int)(width * Game.SCALE), (int)(height * Game.SCALE), null);
        player.render(g);
    }

    public void update() {
        updatePos();
        player.update();
    }

    public void updatePos() {

        if (!left && !right && !inAir) {
            return;
        }
        
        float xSpeed = 0;
        
        if (left) {
            xSpeed = -playerSpeed;
            updateXPos(xSpeed);
            if(player.freeMove) {
                player.hitbox.x = x;
            }
        }
        if (right) {
            xSpeed = playerSpeed;
            updateXPos(xSpeed);
            if(player.freeMove && !inAir) {
                player.hitbox.x = x;
            }
        }
    }
    
    protected void updateXPos(float xSpeed) {
        if (CanMoveHere(x + xSpeed, y, player.hitbox.width, height, lvlData)) {
            freeMove = true;
            x += xSpeed;
        }
    }

    protected void resetInAir() {
        inAir = false;
        freeMove = true;
        airSpeed = 0;
    }

    protected void respawnPlayerAtOldLocation() {
        newPlayer = new Player(spawningPoint.getX(), spawningPoint.getY(), (int) (32 * Game.SCALE), (int) (32 * Game.SCALE));
    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;

    }
    
    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public boolean isLeft() {
        return left;
    }



    public boolean isUp() {
        return up;
    }



    public boolean isRight() {
        return right;
    }



    public boolean isDown() {
        return down;
    }


    public void setLeft(boolean left) {
        this.left = left;
    }



    public void setUp(boolean up) {
        this.up = up;
    }



    public void setRight(boolean right) {
        this.right = right;
    }



    public void setDown(boolean down) {
        this.down = down;
    }

}
