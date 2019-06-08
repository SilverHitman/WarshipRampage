
package view.info;

public class MarkX {
    
    private int xpos,ypos;
    private boolean state;

    public MarkX(int xpos, int ypos, boolean state) {
        this.xpos = xpos + 1;
        this.ypos = ypos + 1;
        this.state = state;
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public boolean isState() {
        return state;
    }
    
    
    
    
}
