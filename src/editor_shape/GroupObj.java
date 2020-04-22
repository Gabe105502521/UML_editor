package editor_shape;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class GroupObj extends Shape{
    private List<Shape> shapesIngroup;

    public GroupObj(List<Shape> shapesIngroup) {
        this.shapesIngroup = shapesIngroup;
    }
    //for null thing
    public GroupObj() {
    }


    @Override
    public void draw(Graphics2D g) {
    }

    @Override
    public List<Port> getPorts() {
        List<Port> portsTmp = new ArrayList<>();
        for (Shape obj: shapesIngroup) {
            for (Port p: obj.getPorts()) {
                portsTmp.add(p);
            }
        }
        return portsTmp;
    }

    @Override
    public void adjust(int difX, int difY) {
        for (Shape s : shapesIngroup) {
            s.adjust(difX, difY);
            s.depth = 99; //reset
            s.checkOverlap();
        }
    }

    public boolean isContain(Shape s) {
        if (shapesIngroup.contains(s))
            return true;
        else
            return false;
    }
    @Override
    public void checkOverlap() {
        for (Shape s: shapesIngroup) {
            s.checkOverlap();
        }
    }

    //use this to check whether groupobj is null
    public List<Shape> getShapesIngroup() {
        return shapesIngroup;
    }

}
