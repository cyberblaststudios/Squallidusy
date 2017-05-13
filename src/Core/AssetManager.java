package Core;

import Assets.Asset;
import Assets.AssetType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Jaden on 5/12/2017.
 */
public class AssetManager {

    private static AssetManager CurrentAssetManager;

    // stores all of the assets
    private HashMap<String, Asset> LoadedAssets = new HashMap<String, Asset>();

    public AssetManager()
    {
        CurrentAssetManager = this;

        // >>>>>> globally loaded assets go here <<<<<<<
        LoadAsset("image1", "./res/bandit.png");
    }

    public static AssetManager GetAssetManager()
    {
        AssetManager am = null;

        if (CurrentAssetManager == null)
        {
            // create a new instance
            am = new AssetManager();
        }
        else
        {
            am = CurrentAssetManager;
        }

        return am;
    }

    public BufferedImage GetImageAsset(String assetID)
    {
        Asset asset = LoadedAssets.get(assetID);

        if (asset.Item != null && asset.Type == AssetType.IMAGE)
        {
            return (BufferedImage)asset.Item;
        }

        return null;
    }

    public boolean LoadAsset(String newID, String filePath)
    {
        if (filePath.endsWith(".png"))
        {
            // the asset is an image
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(filePath));
            } catch (IOException e) {
            }

            if (img != null)
            {
                LoadedAssets.put(newID, new Asset(img, AssetType.IMAGE));
                return true;
            }
            else
            {
                System.out.println("FAILED TO LOAD ASSET " + newID);
                return false;
            }
        }
        else
        {
            // we can support sounds later
            System.out.println("SOUNDS ARE NOT YET SUPPORTED");
            return false;
        }
    }
}
