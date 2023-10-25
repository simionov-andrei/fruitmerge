package utilz;

import main.Game;

public class Constants {

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
        public static final int ORANGE_IDLE = 0;
        public static final int LEMON_IDLE = 1;
        public static final int WATERMELON_IDLE = 2;

        public static int GetSpriteAmount(int player_action) {
            switch(player_action) {
                case ORANGE_IDLE:
                case LEMON_IDLE:
                case WATERMELON_IDLE:
                    return 17;
                default:
                    return 17;
            }
        }
    }
}
