/**
 * This class is the abstract class of shapes that clients can draw
 * on the canvas. It contains only the coordinates of when the client
 * presses the mouse and when the client releases the mouse press.
 *
 * Student name: Le Minh Truong
 * Student ID: 1078113
 */
import java.io.Serializable;

public abstract class WhiteboardShapes implements Serializable {
    int x,y, endX, endY;
    public WhiteboardShapes(int x, int y, int lastX, int lastY){
        this.x = x;
        this.y = y;
        this.endX = lastX;
        this.endY = lastY;
    }
}