package entities;

public class GameManager {
    private Player player;
    private Bush bush;
    private SpawningPoint spawningPoint;
    private static GameManager instance;

    private GameManager() {
        // Private constructor to prevent external instantiation
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setBush(Bush bush) {
        this.bush = bush;
    }

    public void setSpawningPoint(SpawningPoint spawningPoint) {
        this.spawningPoint = spawningPoint;
    }

    public void playerDropped() {
        if (player != null && bush != null && spawningPoint != null) {
            player.resetPosition(spawningPoint);
            bush.respawnPlayerAtOldLocation();
        }
    }
}
