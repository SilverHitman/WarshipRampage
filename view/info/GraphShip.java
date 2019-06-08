
package view.info;

/**
 *
 * @author Andres Gonzalez
 */
public class GraphShip {
    
    
    private int xPos,yPos, xSize,ySize;
    private String photoShip;

    public GraphShip(int xPos, int yPos, int xSize, int ySize, String photoShip) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
        this.photoShip = photoShip;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    public String getPhotoShip() {
        return photoShip;
    }
    
    
    
    
}
