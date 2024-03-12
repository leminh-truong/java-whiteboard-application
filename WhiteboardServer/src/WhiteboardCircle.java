/**
 * This class is the represents a circle clients can draw
 * on the canvas.
 *
 * Student name: Le Minh Truong
 * Student ID: 1078113
 */
public class WhiteboardCircle extends WhiteboardShapes{

    private int width, height;
    private String color;
    private int id = 2;
    public WhiteboardCircle (int firstX, int firstY, int lastX, int lastY){
        super(firstX, firstY, lastX, lastY);
        this.width = Math.abs(firstX - lastX);
        this.height = Math.abs(firstY - lastY);
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }
}
