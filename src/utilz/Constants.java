package utilz;

public class Constants {
    
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
