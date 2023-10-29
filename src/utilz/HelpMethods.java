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

        if(x < 432 || x > 847) {
            return true;
        }
        
        if(y < 0 || y > Game.GAME_HEIGHT) {
            return true;
        }

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if((value >= 48) || value < 0 || value != 11) {
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

    public static float GetEntityYPosAboveOtherPlayer(Rectangle2D.Float hitboxPlayer, Rectangle2D.Float hitboxOtherPlayer, float airSpeed) {
        float newYPos = hitboxOtherPlayer.y - hitboxPlayer.height - airSpeed;
        
        // Ensure that the player doesn't go above the other player
        if (newYPos < 0) {
            newYPos = 0;
        }
        
        return newYPos;
    }

    // public static float GetEntityYPosAboveOtherPlayer(Rectangle2D.Float playerHitbox, Rectangle2D.Float anotherPlayerHitbox, float airSpeed) {
    //     // Calculate the Y position such that the two players don't intersect
    //     float yPos = anotherPlayerHitbox.y - playerHitbox.height - 20;
        
    //     // Ensure the player doesn't go below the game's lower boundary
    //     if (yPos < 0) {
    //         yPos = 0;
    //     }
    
    //     // Ensure the player doesn't go above the game's upper boundary
    //     float maxY = Game.GAME_HEIGHT - playerHitbox.height; // Adjust 'gameHeight' as needed
    //     if (yPos > maxY) {
    //         yPos = maxY;
    //     }
    
    //     return yPos;
    // }
    

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
