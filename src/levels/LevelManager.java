package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import utilz.LoadSave;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;
    

    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();
        levelOne = new Level(LoadSave.GetLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        int tileWidth = 16;
        int tileHeight = 16;
        int numTilesX = img.getWidth() / tileWidth;
        int numTilesY = img.getHeight() / tileHeight;

        levelSprite = new BufferedImage[242];
        for(int i = 0; i < numTilesY; i++) {
            for(int j = 0; j < numTilesX; j++) {
                int index = i*numTilesX + j;
                levelSprite[index] = img.getSubimage(j*tileWidth, i*tileHeight, tileWidth, tileHeight);
            }
        }
    }

    public void draw(Graphics g) {
        for(int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
            for(int i = 0; i < Game.TILES_IN_WIDTH; i++) {
                int index = levelOne.getSpriteIndex(j, i);
                g.drawImage(levelSprite[index], Game.TILES_SIZE*i, Game.TILES_SIZE*j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }
    }

    public void update() {

    }
    
}
