package utilz;

import static utilz.Constants.FruitConstants.APPLE;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
//import entities.Apple;
import main.Game;

public class LoadSave {

    public static final String BUSH_SPRITE = "berrybush.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";
    public static final String PLAYING_BG_IMG = "playing_bg_img.png";
    public static final String BIG_CLOUDS = "big_clouds.png";
    public static final String SMALL_CLOUDS = "small_clouds.png";
    public static final String APPLE_SPRITE = "Apple.png";
    public static final String KIWI_SPRITE = "Kiwi.png";
    public static final String ORANGE_SPRITE = "Orange.png";
    
    public static BufferedImage GetSpriteAtlas(String filename) {
        InputStream is = LoadSave.class.getResourceAsStream("/" + filename);
        BufferedImage img = null;
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
    /* 
    public static ArrayList<Apple> GetApples() {
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        ArrayList<Apple> list = new ArrayList<>();

        for(int j = 0; j < img.getHeight(); j++) {
            for(int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getGreen();
                if (value == APPLE)
                    list.add(new Apple(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
            }
        }
        return list;
    }
    */

    public static int[][] GetPlayerData() {
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        int[][] playerData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];

        for(int j = 0; j < img.getHeight(); j++) {
            for(int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int playerValue = color.getGreen();
                if (playerValue != 49 || playerValue != 50)
                    playerValue = 49;

                playerData[j][i] = playerValue;
            }
        }
        return playerData;
    }

    public static int[][] GetLevelData() {
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];

        for(int j = 0; j < img.getHeight(); j++) {
            for(int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int levelValue = color.getRed();
                if (levelValue >= 48)
                    levelValue = 0;

                lvlData[j][i] = levelValue;
            }
        }
        return lvlData;
    }
}
