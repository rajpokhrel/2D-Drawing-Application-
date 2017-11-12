/*
    Oval class that allows use to draw oval 
 */
package drawing;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.geom.Ellipse2D;
import java.awt.Point;
import java.awt.Color;
import java.awt.geom.Point2D;


public class Oval extends Shape  
{
     private Color firstColor;  // primary color
    private Color secondColor;  // secondary color
    private Point startingPosition;  // start point of the drawing
    private Point endPosition;  //end point of the drawing
    private boolean fill;  // specifies to fill or leave a shape blank
    private boolean gradient;  // have a gradient or not
    private boolean dashed;  // specifies dashed lines or solid
    private int lineWidth;  // width of the lines
    private int lineLength;  // length of dashed lines
    
    public Oval() 
    {
        firstColor = Color.BLACK;
        secondColor = Color.WHITE;
        startingPosition = new Point(0,0);
        endPosition = new Point(0,0);
        fill = false;
        gradient = false;
        dashed = false;
        lineWidth = 5;
        lineLength = 10;
    }
    // constructor to store values
    public Oval(Shape newShape)
    {
        firstColor = newShape.getColor1();
        secondColor = newShape.getColor2();
        startingPosition = newShape.getBegin();
        endPosition = newShape.getEnd();
        fill = newShape.isFill();
        gradient = newShape.isGradient();
        dashed = newShape.isDashed();
        lineWidth = newShape.getWidth();
        lineLength = newShape.getDash();
    }
    
    // called to draw the shape to the drawing
    void drawDifferentShapes(Graphics2D g2d)
    {
        Point drawBegin;
        Point drawEnd;
        Point temp = new Point(0,0);
        drawBegin = new Point(getBegin());
        drawEnd = new Point(getEnd());
        if (drawBegin.x > drawEnd.x) 
        {
            temp.x = drawBegin.x;
            drawBegin.x = drawEnd.x;
            drawEnd.x = temp.x;
        }
        if (drawBegin.y > drawEnd.y) 
        {
            temp.y = drawBegin.y;
            drawBegin.y = drawEnd.y;
            drawEnd.y = temp.y;
        }
        Point2D start = (Point2D) drawBegin;
        Point2D finish = (Point2D) drawEnd;
        double width = finish.getX() - start.getX();
        double height = finish.getY() - start.getY();
        
        float dashWidth = (float) getWidth();
        float dashLength = (float) getDash();
        
        if (isGradient()) //if the use selects the gradient generate the colors 
        {
            g2d.setPaint(new GradientPaint(start, getColor1(), finish, getColor2()));
        }
        else 
        {
            g2d.setPaint(getColor1());
        }
        
        if (isFill()) //if the use selects the fill, fill the current shape
        {
            g2d.fill(new Ellipse2D.Double(start.getX(), start.getY(), width, height));
        }
        else 
        {
            if (!isDashed()) 
            {
                g2d.setStroke (new BasicStroke(dashWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 100f, new float[] {1f}, 0f));
            }
            else 
            {
                g2d.setStroke (new BasicStroke(dashWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 100f, new float[] {dashLength}, 0f));
            }
            g2d.draw(new Ellipse2D.Double(start.getX(), start.getY(), width, height));
        }
    }


    //setters amd getters methods
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
        this.startingPosition = begin;
    }

    public void setEnd(Point end) 
    {
        this.endPosition = end;
    }
    
    public void setFill(boolean fill)
    {
        this.fill = fill;
    }
   
    public void setDashed(boolean dashed)
    {
        this.dashed = dashed;
    }

    public void setGradient(boolean gradient)
    {
        this.gradient = gradient;
    }
    
    public void setWidth(int width)
    {
        this.lineWidth = width;
    }
    
    public void setDash(int dash) 
    {
        this.lineLength = dash;
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
        return startingPosition;
    }
    public Point getEnd()
    {
        return endPosition;
    }
        
    public int getWidth() 
    {
        return lineWidth;
    }
    public int getDash() 
    {
        return lineLength;
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
        return dashed;
    }

}
    
