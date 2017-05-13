package Levels;

import Entities.Characters.Player;
import Utils.Vector2D;

/**
 * Created by Jaden on 5/12/2017.
 */
public class MainLevel extends Level {

    public void StartLevel()
    {
        // spawn the entities here
        try {
            SpawnEntity(Player.class, new Vector2D(0.0f, 0.0f));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
