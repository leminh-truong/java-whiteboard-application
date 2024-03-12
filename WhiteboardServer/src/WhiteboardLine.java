/**
 * This class is the represents a line clients can draw
 * on the canvas.
 *
 * Student name: Le Minh Truong
 * Student ID: 1078113
 */
public class WhiteboardLine  extends WhiteboardShapes {

    private int id = 0;
    private String color;

    public WhiteboardLine(int firstX, int firstY, int lastX, int lastY){
        super(firstX, firstY, lastX, lastY);
    }

    public void setColor(String color){
        this.color = color;
    }

    public int getFirstX(){
        return this.x;
    }

    public int getFirstY(){
        return this.y;
    }

    public int getLastX() {
        return endX;
    }

    public int getLastY() {
        return endY;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }
}
