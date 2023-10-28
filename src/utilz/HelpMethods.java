package utilz;

import java.awt.geom.Rectangle2D;

import main.Game;

public class HelpMethods {
    
       public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
           if(!IsSolid(x, y, lvlData)) {
               if(!IsSolid(x + width, y + height, lvlData)) {
                   if(!IsSolid(x + width, y, lvlData)){
                       if(!IsSolid(x, y + height, lvlData)){
                           return true;
                       }
                   }
               }
           }
           return false;
       }
   
    
     
    public static boolean IsSolid(float x, float y, int[][] lvlData) {
        
        if(x < 435 || x > 1000) {
            return true;
        }
        
        if(y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }
        
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        //BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        //Color color = new Color(img.getRGB((int) xIndex, (int) yIndex));
        //int greenValue = color.getGreen();

        if(value >= 48 || value < 0 || value != 11 /*|| greenValue == APPLE*/) {
            return true;
        }
        
        return false;

    } 

    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int)(hitbox.x / Game.TILES_SIZE);

        if (xSpeed > 0) {
            //right
            int xTilePos = currentTile * Game.TILES_SIZE;
            int xOffset = (int)(Game.TILES_SIZE - hitbox.width);
            return xTilePos + xOffset - 1;
        } else {
            //left
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int)(hitbox.y / Game.TILES_SIZE);
        if (airSpeed > 0) {
            //falling - touching floor
            int yTilePos = currentTile * Game.TILES_SIZE;
            int yOffset = (int)(Game.TILES_SIZE - hitbox.height);
            return yTilePos + yOffset - 1;
        } else {
            //jumping
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        //check pixel below bottom left and bottom right
        if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData)) {
            if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)) {
                return false;
            }
        }
        return true;
    }

    public static void mergeFruits() {
        
    }
}
