package Utils;

import Entities.Entity;

import java.util.ArrayList;

/**
 * Created by Jaden on 5/23/2017.
 */
public class CollisionCheckResult {

    public CollisionCheckResult(Vector2D inCorrection, ArrayList<Entity> hits)
    {
        CorrectionMove = inCorrection;
        HitEntities = hits;
    }

    public Vector2D CorrectionMove = new Vector2D(0,0);

    public ArrayList<Entity> HitEntities = new ArrayList<Entity>();

}
