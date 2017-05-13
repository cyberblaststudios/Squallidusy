package Assets;

/**
 * Created by Jaden on 5/12/2017.
 */
public class Asset {

    // the actual asset
    public Object Item;

    public AssetType Type;

    public Asset(Object item, AssetType type)
    {
        Item = item;
        Type = type;
    }
}
