package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import main.Game;
import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;
import utilz.LoadSave;

public class Player extends Entity {

    private BufferedImage[] animations;
    private int aniTick;
    private int aniIndex;
    private int aniSpeed = 23;
    private int playerAction = FRUIT_IDLE;
    private boolean left, up, right, down, jump;
    private float playerSpeed = 2.0f;
    private int[][] lvlData;
    private float xDriveOffset;
    private float yDriveOffset;
    private int hitboxScale, newWidth;
    public BufferedImage[] Images = {LoadSave.GetSpriteAtlas(LoadSave.ORANGE_SPRITE), LoadSave.GetSpriteAtlas(LoadSave.APPLE_SPRITE), 
                                        LoadSave.GetSpriteAtlas(LoadSave.KIWI_SPRITE)};
    private Random random = new Random();
    private int randomIndex = random.nextInt(Images.length);

    //private long timer;

    //gravity
    public float airSpeed = 0f;
    private float gravity = 0.1f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    public boolean inAir = false;
    public boolean freeMove = true;
    public boolean onTop = true;

    public Player(float x, float y, int width, int height, int index) {
        super(x, y, width, height);
        newWidth = width;
        hitboxScale = 14;
        xDriveOffset = 16.5f * Game.SCALE;
        yDriveOffset = 16.5f * Game.SCALE;

        if(randomIndex == 1) {
            newWidth = 63;
            hitboxScale = 16;
            xDriveOffset = 33;
            yDriveOffset = 33;
        } else if(randomIndex == 2) {
            newWidth = 78;
            hitboxScale = 22;
            xDriveOffset = 40;
            yDriveOffset = 40;
        } 
        initHitbox(x, y, hitboxScale * Game.SCALE, hitboxScale * Game.SCALE);
        loadAnimations(randomIndex);
    }
    
    public void update() {
        updatePos();
        updateAnimationTick();
    }
    
    public void render(Graphics g) {
        g.drawImage(animations[aniIndex], (int) (hitbox.x - xDriveOffset), (int) (hitbox.y - yDriveOffset), (int)(newWidth*Game.SCALE), (int)(newWidth*Game.SCALE), null);
        drawHitbox(g);
    }

    public void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }

    public int getSprite() {
        return randomIndex;
    }
        
    public void updatePos() {
        //make 2 classes for bush and for orange  --- DONE :D
        //make so bush remains on its initial position and can move with a/d --- DONE :D
        //make so orange drops, you cant use a/d anymore --- DONE :D
        //make so orange moves with the bush when on top --- DONE :D
        //make so orange respawns --- DONE :D
        //make so it animates, moves according to bush and can drop it --- DONE :D
        //make so it stays on dropped location with a hitbox --- DONE :D
        //make so random objects get respawned --- DONE :D
        //make so objects collide --- DONE :D
        //make merging mechanic --- DONE :D


        if (jump) {
            drop();
        }
        
        float xSpeed = 0;

        if (left && freeMove && onTop) {
            xSpeed = -playerSpeed;
            updateXPos(xSpeed);
        }

        if (right && freeMove && onTop) {
            xSpeed = playerSpeed;
            updateXPos(xSpeed);
        }
        
        if (!onTop) {
            if (!IsEntityOnFloor(hitbox, lvlData)) {
                inAir = true;
                freeMove = false;
            }
        }

        if(IsEntityOnFloor(hitbox, lvlData)){
            resetInAir();
        }
        
        if (!onTop) {
            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
            } else {
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = fallSpeedAfterCollision;
                }
            }
        }
    }
    
    protected void updateXPos(float xSpeed) {
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
        } else {
            //hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
        }
    }

    protected void drop() {
        if (inAir) {
            return;
        }
        inAir = true;
        freeMove = false;
        onTop = false;
        //timer = 0;
    }

    protected void resetInAir() {
        inAir = false;
        freeMove = false;
        onTop = true;
        airSpeed = 0;
    }

    public boolean getInAir() {
        return inAir;
    }

    public boolean getFreeMove() {
        return freeMove;
    }
    
    private void loadAnimations(int index) {
        BufferedImage img = Images[index];
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

    public void setJump(boolean jump) {
        this.jump = jump;
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
