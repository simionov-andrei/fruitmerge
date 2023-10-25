package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.CanMoveHere;
import utilz.LoadSave;

public class Player extends Entity {

    private BufferedImage[] animations;
    private int aniTick;
    private int aniIndex;
    private int aniSpeed = 23;
    private int playerAction = ORANGE_IDLE;
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;
    private int[][] lvlData;
    private float xDriveOffset = 16.5f * Game.SCALE;
    private float yDriveOffset = 16.5f * Game.SCALE;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, 14 * Game.SCALE, 14 * Game.SCALE);
    }

    public void update() {
        updatePos();
        updateAnimationTick();
    }

    public void render(Graphics g) {
        g.drawImage(animations[aniIndex], (int) (hitbox.x - xDriveOffset), (int) (hitbox.y - yDriveOffset), (int)(width*Game.SCALE), (int)(height*Game.SCALE), null);
        drawHitbox(g);
    }

    private void updateAnimationTick() {
        //long currentTime = System.currentTimeMillis();
        //if (currentTime - lastAniStartTime >= aniDelay) {
            aniTick++;
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(playerAction)) {
                    aniIndex = 0;
                }
            }
            //lastAniStartTime = currentTime;
            //}
        }
        
     public void updatePos() {

        if (!left && !right && !up && !down) {
            return;
        }

        float xSpeed = 0, ySpeed = 0;

        if (left && !right){
            xSpeed = -playerSpeed;
        }else if (right && !left){
            xSpeed = playerSpeed;
        }

        if (up && !down){
            ySpeed = -playerSpeed;
        }else if (down && !up){
            ySpeed = playerSpeed;
        }
        /* 
        if(CanMoveHere(x + xSpeed, y + ySpeed, 64, lvlData)) {
            this.x += xSpeed;
            this.y += ySpeed;
        }
        */

        boolean canMove = CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData);
        System.out.println("Can move: " + canMove);
        if (canMove) {
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
        }
    }
    
    private void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        int imageWidth = img.getWidth() / 17;
        int imageHeight = img.getHeight() / 1;
        
        animations = new BufferedImage[17];
        
        for (int i = 0; i < animations.length; i++) {
                animations[i] = img.getSubimage(i*imageWidth, 0, imageWidth, imageHeight);
        }
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
