/**
 * This class is the represents a triangle clients can draw
 * on the canvas.
 *
 * Student name: Le Minh Truong
 * Student ID: 1078113
 */
public class WhiteboardTriangle extends WhiteboardShapes{

    private double side;
    private String color;
    private int id = 3;
    private int thirdX, thirdY;
    public WhiteboardTriangle(int firstX, int firstY, int lastX, int lastY){
        super(firstX, firstY, lastX, lastY);
        this.side = Math.sqrt(Math.pow((firstX - lastX), 2) + Math.pow((firstY - lastY), 2));
        this.thirdX = (int) ((side*side - side*side + side*side) / (2*side));
        this.thirdY = (int) Math.sqrt(side*side - thirdX*thirdX);

    }

    public void setColor(String color){
        this.color = color;
    }

    public int getThirdX(){
        return this.thirdX;
    }

    public int getThirdY(){
        return this.thirdY;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }
}
