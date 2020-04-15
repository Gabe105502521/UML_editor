package editor_shape;

import editor_main.Panel;
import editor_main.UMLEditor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class GroupObj extends BaseObj{
    private List<BaseObj> shapesIngroup;

    public GroupObj(List<BaseObj> shapesIngroup) {
        this.shapesIngroup = shapesIngroup;
        this.setWandH();
    }

    private void setWandH() {
        Shape left = null, right = null, top = null, down = null;
        int tmpSmall1 = 10000000, tmpSmall2 = 10000000, tmpLarge1 = 0, tmpLarge2 = 0;
        for (Shape s: shapesIngroup) {
            if (s.p1.x < tmpSmall1) {
                tmpSmall1 = s.p1.x;
                left = s;
            }
            if (s.p1.x > tmpLarge1) {
                tmpLarge1 = s.p1.x;
                right = s;
            }
            if (s.p1.y < tmpSmall2) {
                tmpSmall1 = s.p1.y;
                top = s;
            }
            if (s.p1.y > tmpLarge2) {
                tmpLarge2 = s.p1.y;
                down = s;
            }
        }
        this.width = right.p1.x - left.p1.x + right.width;
        this.height = down.p1.y - top.p1.y + down.height;
    }

    @Override
    public void draw(Graphics2D g) {
        for (Shape s: shapesIngroup) {
            s.draw(g);
        }
    }

    @Override
    public List<Port> getPorts() {
        List<Port> portsTmp = new ArrayList<>();
        for (BaseObj obj: shapesIngroup) {
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
        }
    }

    @Override
    public boolean inside(Point p) {
        for (Shape s : shapesIngroup) {
            if (s.inside(p))
                return true;
        }
        return false;
    }

    @Override
    public boolean inRectangle(Rectangle bound) {
        for (Shape s : shapesIngroup) {
            if (s.inRectangle(bound)){
                return true;
            }
        }
        return false;
    }
}
