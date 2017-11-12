/*
    Abstract class that is used by all the other classes 
 */
package drawing;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;

public abstract class Shape
{
    private Color firstColor;  // primary color
    private Color secondColor; // secondary color
    private boolean fill;  // specifies to fill
    private boolean gradient;  //gradient or not
    private boolean dashLine;  // dashed lines or solid
    private String shapeType;  // type of shape
    private Point startDrawing;  //start point of the drawing
    private Point endDrawing;  // end point of the drawing
    private int lineWidth;  // width of the lines
    private int lineLength;  // length of dashed lines
   
     //Deafult constractor
    public Shape()
    {
        
    }
    
    public Shape(Shape newShape) 
    {
        firstColor = newShape.getColor1();
        secondColor = newShape.getColor2();
        startDrawing = newShape.getBegin();
        endDrawing = newShape.getEnd();
        fill = newShape.isFill();
        gradient = newShape.isGradient();
        dashLine = newShape.isDashed();
        lineWidth = newShape.getWidth();
        lineLength = newShape.getDash();
    }
    
   
    // called to draw the shape to the drawing
    abstract void drawDifferentShapes(Graphics2D g2d);
    
    //Setters and getters methods
    public void setWidth(int width) 
    {
        this.lineWidth = width;
    }

    public void setColor1(Color color1) 
    {
        this.firstColor = color1;
    }

    public void setColor2(Color color2) 
    {
        this.secondColor = color2;
    }

    public void setBegin(Point begin) 
    {
        this.startDrawing = begin;
    }

    public void setEnd(Point end) 
    {
        this.endDrawing = end;
    }

    public void setFill(boolean fill) 
    {
        this.fill = fill;
    }

    public void setGradient(boolean gradient) 
    {
        this.gradient = gradient;
    }

    public void setDashed(boolean dashed)
    {
        this.dashLine = dashed;
    }

    public void setDash(int dash)
    {
        this.lineLength = dash;
    }

    public void setLabel(String label)
    {
        this.shapeType = label;
    }
    
    public Color getColor1() 
    {
        return firstColor;
    }

    public Color getColor2() 
    {
        return secondColor;
    }

    public Point getBegin() 
    {
        return startDrawing;
    }

    public Point getEnd() 
    {
        return endDrawing;
    }

    public int getWidth() 
    {
        return lineWidth;
    }

    public int getDash()
    {
        return lineLength;
    }

    public String getLabel() 
    {
        return shapeType;
    }
    
    public boolean isFill()
    {
        return fill;
    }

    public boolean isGradient() 
    {
        return gradient;
    }

    public boolean isDashed()
    {
        return dashLine;
    }
}
