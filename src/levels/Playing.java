package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.Environment.*;

public class Playing {

    private BufferedImage backgroundImg,  bigCloud, smallCloud;
    private int[] smallCloudsPos;
    private Random rnd = new Random();

    public Playing(Game game) {
        //super(game);
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG);
        bigCloud = LoadSave.GetSpriteAtlas(LoadSave.BIG_CLOUDS);
        smallCloud = LoadSave.GetSpriteAtlas(LoadSave.SMALL_CLOUDS);
        smallCloudsPos = new int[8];
        for(int i = 0; i < smallCloudsPos.length; i++) {
            smallCloudsPos[i] = rnd.nextInt((int)(150 * Game.SCALE));
        }
    }

    //@Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        drawClouds(g);
    }

    private void drawClouds(Graphics g) {
        for(int i = 0; i < 3; i++) {
            g.drawImage(bigCloud, i * BIG_CLOUD_WIDTH, (int)(204 * Game.SCALE), BIG_CLOUD_WIDTH, BIG_CLOUD_HEIGHT, null);
        }
        for(int i = 0; i < smallCloudsPos.length; i++) {
            g.drawImage(smallCloud, i * SMALL_CLOUD_WIDTH * 3, smallCloudsPos[i], SMALL_CLOUD_WIDTH, SMALL_CLOUD_HEIGHT, null);
        }
    }
}
