/**
 * This class is the represents a text clients can put
 * on the canvas.
 *
 * Student name: Le Minh Truong
 * Student ID: 1078113
 */
public class WhiteboardText extends WhiteboardShapes{

    private String text;
    private int id = 4;
    private String color;
    public WhiteboardText(int firstX, int firstY, int lastX, int lastY){
        super(firstX, firstY, lastX, lastY);

    }

    public void setText(String text){
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public String getColor(){
        return this.color;
    }

    public void setColor(String color){
        this.color = color;
    }
}

