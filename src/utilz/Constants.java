package utilz;

import main.Game;

public class Constants {

    public static class FruitConstants {
        public static final int ORANGE = 49;
        public static final int APPLE = 50;
        public static final int IDLE = 0;

        public static final int APPLE_WIDTH_DEFAULT = 32;
        public static final int APPLE_HEIGHT_DEFAULT = 32;
        public static final int APPLE_WIDTH = (int)(APPLE_WIDTH_DEFAULT * Game.SCALE);
        public static final int APPLE_HEIGHT = (int)(APPLE_HEIGHT_DEFAULT * Game.SCALE);

        public static final int APPLE_DRAWOFFSET_X = (int)(16.5f * Game.SCALE);
        public static final int APPLE_DRAWOFFSET_Y = (int)(16.5f * Game.SCALE);

        public static int GetSpriteAmount(int fruitType, int fruitState) {

            switch(fruitType) {
                case APPLE:
                    switch(fruitState) {
                        case IDLE:
                            return 17;
                        default:
                            return 17;
                    }
            }
            return 0;
        }
    }

    public static class Environment {
        public static final int BIG_CLOUD_WIDTH_DEFAULT = 448;
        public static final int BIG_CLOUD_HEIGHT_DEFAULT = 101;
        public static final int BIG_CLOUD_WIDTH = (int)(BIG_CLOUD_WIDTH_DEFAULT * Game.SCALE);
        public static final int BIG_CLOUD_HEIGHT = (int)(BIG_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
        public static final int SMALL_CLOUD_WIDTH_DEFAULT = 74;
        public static final int SMALL_CLOUD_HEIGHT_DEFAULT = 24;
        public static final int SMALL_CLOUD_WIDTH = (int)(SMALL_CLOUD_WIDTH_DEFAULT * Game.SCALE);
        public static final int SMALL_CLOUD_HEIGHT = (int)(SMALL_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
    }
    
    public static class PlayerConstants {
        public static final int FRUIT_IDLE = 0;

        public static int GetSpriteAmount(int player_action) {
            switch(player_action) {
                case FRUIT_IDLE:
                    return 17;
                default:
                    return 17;
            }
        }
    }
}
