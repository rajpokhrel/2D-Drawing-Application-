
/*  
    Line class that allows use to draw
 */
package drawing;

import java.awt.geom.Line2D;
import java.awt.Point;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.GradientPaint;

public class Line extends Shape 
{
    private Color firstColor;  // primary color
    private Color secondColor;  // secondary color
    private Point startingPosition;  // start point of the drawing
    private Point endPosition;  // the end point of the drawing
    private boolean fill;  // specifies to fill or leave a shape blank
    private boolean gradient;  // specifies to have a gradient or not
    private boolean dashed;  // specifies dashed lines or solid
    private int width;  // width of the lines
    private int dash;  // length of dashed lines
    
    // constructor to store values
    public Line(Shape newShape) 
    {
        firstColor = newShape.getColor1();
        secondColor = newShape.getColor2();
        startingPosition = newShape.getBegin();
        endPosition = newShape.getEnd();
        fill = newShape.isFill();
        gradient = newShape.isGradient();
        dashed = newShape.isDashed();
        width = newShape.getWidth();
        dash = newShape.getDash();
    }
    
    // called to draw the shape to the drawing
    void drawDifferentShapes(Graphics2D g2d)
    {
        Point2D start = (Point2D) getBegin();
        Point2D finish = (Point2D) getEnd();
        
        float dashLength = (float) getDash();
        float dashWidth = (float) getWidth();

        if (isGradient()) 
        {
            g2d.setPaint(new GradientPaint(start, getColor1(), finish, getColor2()));
        }
        else 
        {
            g2d.setPaint(getColor1());
        }
        
        if (!isDashed())
        {
            g2d.setStroke (new BasicStroke(dashWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 100f, new float[] {5f}, 0f));
        }
        else 
        {
            g2d.setStroke (new BasicStroke(dashWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 100f, new float[] {dashLength}, 0f));
        }
        g2d.draw(new Line2D.Double(getBegin(), getEnd()));
    }

 
    //getter and setting methods to set up colors, line, dash, and others
    public void setColor1(Color color1)
    {
        this.firstColor = color1;
    }
    
    public void setColor2(Color color2)
    {
        this.secondColor = color2;
    }
     
    public void setDash(int dash)
    {
        this.dash = dash;
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
    
    public void setGradient(boolean gradient) 
    {
        this.gradient = gradient;
    }

    public void setDashed(boolean dashed) 
    {
        this.dashed = dashed;
    }

    public void setWidth(int width)
    {
        this.width = width;
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
        return width;
    }

    public int getDash() 
    {
        return dash;
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
