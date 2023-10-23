package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utilz.Constants.PlayerConstants.*;
import utilz.LoadSave;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int aniTick;
    private int aniIndex;
    private int aniSpeed = 240;
    private int playerAction = ORANGE_IDLE;
    private float xDelta = 100, yDelta = 100;

    public Player(float x, float y, int width, int height) {
        super(x, y);
        loadAnimations();
    }



    public void changeXDelta(int value) {
        this.xDelta += value;
        /* 
        if (100 <= this.xDelta && this.xDelta <= 300) {
            repaint();
        } else if (100 >= this.xDelta){
            this.xDelta = 100;
        } else if (this.xDelta >= 300){
            this.xDelta = 300;
        }
        */
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void setRecPosition(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }
    


    public void update() {
        updateAnimationTick();
    }

    public void render(Graphics g) {
        g.drawImage(animations[aniIndex][0], (int)xDelta, (int)yDelta, (694/2), (945/3), null);
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

    private void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        int imageWidth = img.getWidth() / 2;
        int imageHeight = img.getHeight() / 3;
        animations = new BufferedImage[2][3];
        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++){
                animations[i][j] = img.getSubimage(i*imageWidth, j*imageHeight, imageWidth, imageHeight);
            }
        }
    }

    public void resetDirBooleans() {
    }
}
