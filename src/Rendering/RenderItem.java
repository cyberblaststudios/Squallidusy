package Rendering;

/**
 * Created by Jaden on 5/12/2017.
 */
public class RenderItem implements Comparable{

    public Renderable renderObject;

    public int RenderPriority;

    public RenderItem(Renderable item, int priority)
    {
        renderObject = item;
        RenderPriority = priority;
    }

    public int compareTo(Object o) {
        RenderItem other = (RenderItem)o;
        return RenderPriority < other.RenderPriority ? -1 : (RenderPriority < other.RenderPriority ) ? 1 : 0;
    }
}
