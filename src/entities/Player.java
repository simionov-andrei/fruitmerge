package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
    private float xDriveOffset = 16.5f * Game.SCALE;
    private float yDriveOffset = 16.5f * Game.SCALE;

    //gravity
    private float airSpeed = 0f;
    private float gravity = 0.01f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;
    protected boolean freeMove = true;
    SpawningPoint spawningPoint;

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
        
    public void updatePos() {
        //make 2 classes for bush and for orange  --- DONE :D
        //make so bush remains on its initial position and can move with a/d --- DONE :D
        //make so orange drops, you cant use a/d anymore --- DONE :D
        //make so orange moves with the bush when on top --- DONE :D
        //make so orange respawns
        //make merging mechanic


        if (jump) {
            drop();
            GameManager.getInstance().playerDropped();
        }

        if (!inAir) {
            return;
        }
        
        float xSpeed = 0;
        
        if (!inAir) {
            if (!IsEntityOnFloor(hitbox, lvlData)) {
                inAir = true;
                freeMove = false;
            }
        }
        
        if (inAir) {
            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else {
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = fallSpeedAfterCollision;
                }
                updateXPos(xSpeed);
            }
        } else {
            updateXPos(xSpeed);
        } 
    }
    
    protected void updateXPos(float xSpeed) {
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
        }
    }

    protected void drop() {
        if (inAir) {
            return;
        }
        inAir = true;
        freeMove = false;
    }

    protected void resetInAir() {
        inAir = false;
        freeMove = false;
        airSpeed = 0;
    }
    
    private void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.ORANGE_SPRITE);
        int imageWidth = img.getWidth() / 17;
        int imageHeight = img.getHeight() / 1;
        
        animations = new BufferedImage[17];
        
        for (int i = 0; i < animations.length; i++) {
                animations[i] = img.getSubimage(i*imageWidth, 0, imageWidth, imageHeight);
        }
    }

    protected void resetPosition(SpawningPoint spawningPoint) {
        this.x = spawningPoint.getX();
        this.y = spawningPoint.getY();
        System.out.println(x + ";" + y);
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

}
