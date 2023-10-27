/* 
package entities;

import static utilz.Constants.FruitConstants.*;
import static utilz.HelpMethods.CanMoveHere;
import static utilz.HelpMethods.GetEntityYPosUnderRoofOrAboveFloor;
import static utilz.HelpMethods.IsEntityOnFloor;
import main.Game;

public abstract class Fruit extends Entity{
    private int aniIndex, fruitState, fruitType;
    private int aniTick, aniSpeed = 23;
    private boolean firstUpdate = true;
    private boolean inAir;
    private float fallSpeed = 0f;
    private float gravity = 0.1f * Game.SCALE;


    public Fruit(float x, float y, int width, int height, int fruitType) {
        super(x, y, width, height);
        this.fruitType = fruitType;
        initHitbox(x, y, 14 * Game.SCALE, 14 * Game.SCALE);
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(fruitType, fruitState)) {
                aniIndex = 0;
            }
        }
    }

    public void update(int [][] lvlData) {
        updateMove(lvlData);
        updateAnimationTick();
    }

    private void updateMove(int [][] lvlData) {
        if(firstUpdate) {
            if(!IsEntityOnFloor(hitbox, lvlData)) {
                inAir = true;
            }
            firstUpdate = false;
        }

        if(inAir) {
            if(CanMoveHere(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += fallSpeed;
                fallSpeed += gravity;
            } else {
                inAir = false;
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, fallSpeed);
            }
        } 
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public int getFruitState() {
        return fruitState;
    }
    
}
*/