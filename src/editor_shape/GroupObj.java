package editor_shape;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class GroupObj extends Shape{
    private List<Shape> shapesIngroup;

    public GroupObj(List<Shape> shapesIngroup) {
        this.shapesIngroup = new ArrayList<>();
        for (Shape s:shapesIngroup) {
            this.shapesIngroup.add(s);
        }
        System.out.println(this.shapesIngroup.size());
    }
    //for null thing
    public GroupObj() {
    }


    @Override
    public void draw(Graphics2D g) {
        if (this.isSelected) {
            for (Shape s : shapesIngroup) {
                s.setSelected(true);
                s.draw(g);
            }
        }
        else {
            for (Shape s : shapesIngroup) {
                s.setSelected(false);
                s.draw(g);
            }
        }
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
            //s.checkOverlap();
        }
    }

    @Override
    public boolean inside(Point p) {
        for (Shape s: shapesIngroup) {
            if (s.inside(p)) {
                return true;
            }
        }
        return false;
    }

    public boolean isContain(Shape s) {
        if (shapesIngroup.contains(s))
            return true;
        else
            return false;
    }
    @Override
    public void checkOverlap() {
        int tmpDepth = 999;
        for (Shape s: shapesIngroup) {
            s.checkOverlap();
            if (tmpDepth > s.depth) {
                tmpDepth = s.depth;
            }
        }
        //扶起來8
        this.depth = tmpDepth;
    }

    @Override
    public boolean isIntersected(Rectangle r) {
        for (Shape s: shapesIngroup) {
            if (r.intersects(s.getOwnShape())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean inRectangle(Rectangle bound) {
        for (Shape s: shapesIngroup) {
            if (s.inRectangle(bound)) {
                return true;
            }
        }
        return false;
    }

    //use this to check whether groupobj is null
    @Override
    public List<Shape> getShapesIngroup() {
        return shapesIngroup;
    }

}
