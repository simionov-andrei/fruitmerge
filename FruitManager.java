/* 
package entities;

import levels.Playing;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.FruitConstants.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FruitManager {

    private Playing playing;
    private BufferedImage[] appleArray;
    private ArrayList<Apple> apple = new ArrayList<>();

    public FruitManager(Playing playing) {
        this.playing = playing;
        loadFruitImgs();
        addFruits();
    }

    private void addFruits() {
        apple = LoadSave.GetApples();
        System.out.println("size of apples: " + apple.size());
    }

    public void update(int [][] lvlData) {
        for(Apple a : apple) {
            a.update(lvlData);
        }
    }
    
    public void draw(Graphics g) {
        drawApples(g);
    }

    private void drawApples(Graphics g) {
        for(Apple a : apple) {
            g.drawImage(appleArray[a.getAniIndex()], (int)(a.getHitbox().x), (int)(a.getHitbox().y), (int)(APPLE_WIDTH * Game.SCALE), (int)(APPLE_HEIGHT * Game.SCALE), null);
            drawHitbox(g, a.getHitbox());
        }
    }

    protected void drawHitbox(Graphics g, Rectangle2D.Float hitbox) {
        //for debug
        g.setColor(Color.BLUE);
        g.drawRect((int)(hitbox.x), (int)(hitbox.y), (int)(APPLE_WIDTH * Game.SCALE), (int)(APPLE_WIDTH * Game.SCALE));
    }    

    private void loadFruitImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.APPLE_SPRITE);
        int imageWidth = temp.getWidth() / 17;
        int imageHeight = temp.getHeight() / 1;
        appleArray = new BufferedImage[17];

        for(int i = 0; i < 17; i++) {
            appleArray[i] = temp.getSubimage(i*imageWidth, 0, imageWidth, imageHeight);
        }
    }
}
*/