package entities;

import static utilz.HelpMethods.CanMoveHere;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;

public class Bush extends Entity{

    private BufferedImage bushImage;
    
    //gravity
    private boolean left, up, right, down;
    private int[][] lvlData;
    private float playerSpeed = 2.0f;
    private boolean inAir = false;
    private Player player;
    
    public Bush(float x, float y, int width, int height, BufferedImage bushImage, Player player) {
        super(x, y, width, height);
        this.bushImage = bushImage;
        this.player = player;
    }
    
    public void render(Graphics g) {
        g.drawImage(bushImage, (int) x - 45, (int) y - 70, (int)(width * Game.SCALE), (int)(height * Game.SCALE), null);
    }

    public void update() {
        updatePos();
    }

    public void updatePos() {

        if (!left && !right && !inAir) {
            return;
        }
        
        float xSpeed = 0;
        
        if (left) {
            xSpeed = -playerSpeed;
            updateXPos(xSpeed);
        }
        if (right) {
            xSpeed = playerSpeed;
            updateXPos(xSpeed);
        }
    }

    protected void updateXPos(float xSpeed) {
        if (CanMoveHere(x + xSpeed, y, player.hitbox.width, height, lvlData)) {
            x += xSpeed;
        } else {
            //x = GetEntityXPosNextToWall(player.hitbox, xSpeed);
        }
    }

     public int getSpawningPointX() {
        return (int)(x);
    }

    public int getSpawningPointY() {
        return (int)(y);
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
