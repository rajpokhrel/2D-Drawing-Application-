
/*
 The DrawFram is where I setup the panel for the display with
 buttons, shap type, text, size of the panel, and others from the assignment
 */
package drawing;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JColorChooser;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class DrawFrame extends JFrame
{
    private JComboBox<String> shape;  // allow user to select shape
    private String[] names = {"Oval", "Rectangle", "Line"};  // types of shapes
     private JLabel mousePosition;  // track current mouse position
    private JPanel panelDisplay1;  // top panel of layout
    private JPanel panelDisplay2;  // top row of the top panel
    private JPanel panelDisplay3;  // bottom row of the top panel
    private JButton undo;  // undo the last action
    private JButton clear;  // clear all drawings
    private JLabel shapeLabel;
    private JCheckBox fill;  // allow user to specify fill or no fill
    private JLabel  fillLabel;
    private JCheckBox gradient;  // allow user to specify gadient or not
    private JLabel gradientLabel;
    private JButton color1;  // allow user to select primary color
    private JButton color2;  // allow user to select secondary color
    private JColorChooser primary;  // specify primary color
    private JColorChooser secondary;  // specify secondary color
    private JTextField strokeWidth;  // allow user to enter width of strokes
    private JLabel strokeWidthLabel;
    private JTextField dashLength;  // allow user to enter dash length
    private JLabel dashLengthLabel;
    private JCheckBox dashed;  // allow user to specify dashed lines
    private JLabel dashedLabel;
    private DrawPanel canvas;  // drawing area
    // stores the shapes that is drawn
    private ArrayList<Shape> Drawings = new ArrayList(); 
    // temporary variables for current drawing status
    private Shape currentShape;  // stores current shape being drawn
    private String action;  // stores the temporary action if needed

    public DrawFrame() 
    {
        //setting you the panel layout with buttons and text
        super("You think you can draw? Try me!!");
        undo = new JButton("Undo");  // undo button
        undo.setVisible(true);
        undo.addActionListener(new ButtonHandler()); //handler
        
        clear = new JButton("Clear");  // clear all drawings
        clear.setVisible(true);
        clear.addActionListener(new ButtonHandler());
        
        shape = new JComboBox<String>(names);  // shape selector dropdown
        shape.setVisible(true);
        shape.addItemListener(new ComboBoxHandler());
        shape.setMaximumRowCount(3);
        shapeLabel = new JLabel("Shape:");
        shapeLabel.setVisible(true);
        
        fill = new JCheckBox();  // allow user to fill or no fill 
        fill.setVisible(true);
        fill.addItemListener(new CheckBoxHandler());
        fillLabel = new JLabel("Filled");
        fillLabel.setVisible(true);
        
        gradient = new JCheckBox();  // allow user to specify gadient or not
        gradient.setVisible(true);
        gradient.addItemListener(new CheckBoxHandler());
        gradientLabel = new JLabel("Use Gradient");
        gradientLabel.setVisible(true);
        
        color1 = new JButton("1st Color...");  // allow user to select primary color
        color1.setVisible(true);
        color1.addActionListener(new ButtonHandler());
        
        color2 = new JButton("2nd Color...");  // allow user to select secondary color
        color2.setVisible(true);
        color2.addActionListener(new ButtonHandler());
        
        primary = new JColorChooser();  // specify primary color
        primary.setVisible(false);
        secondary = new JColorChooser();  // specify secondary color
        secondary.setVisible(false);
        
        // allow user to enter width of strokes
        strokeWidth = new JTextField("10");  
        strokeWidth.setVisible(true);
        strokeWidth.addActionListener(new TextFieldHandler());
        strokeWidth.setColumns(3);
        strokeWidthLabel = new JLabel("Line Width:");
        strokeWidthLabel.setVisible(true);
        
        // allow user to enter dash length
        dashLength = new JTextField("15");  
        dashLength.setVisible(true);
        dashLength.addActionListener(new TextFieldHandler());
        dashLength.setColumns(3);
        dashLengthLabel = new JLabel("Dash Length:");
        dashLengthLabel.setVisible(true);
        
        // allow user to specify dashed lines
        dashed = new JCheckBox();  
        dashed.setVisible(true);
        dashed.addItemListener(new CheckBoxHandler());
        dashedLabel = new JLabel("Dashed");
        dashedLabel.setVisible(true);
        
        // area on which shapes are drawn
        canvas = new DrawPanel(); 
        canvas.setSize(800,1000);
        canvas.setVisible(true);
        canvas.addMouseMotionListener(new MouseHandler());
        canvas.addMouseListener(new MouseHandler());
        
        // displays current mouse position
        mousePosition = new JLabel("");  
        mousePosition.setVisible(true);
        
        panelDisplay1 = new JPanel();
        panelDisplay2 = new JPanel();
        panelDisplay3 = new JPanel();
        
        // initiliaze the new shape 
        currentShape = new Oval();
        Drawings.add(currentShape);
        
        //adding all the componets to the panel
        panelDisplay2.setLayout(new FlowLayout());
        panelDisplay2.add(undo);
        panelDisplay2.add(clear);
        panelDisplay2.add(shapeLabel);
        panelDisplay2.add(shape);
        panelDisplay2.add(fill);
        panelDisplay2.add(fillLabel);
        setLayout(new BorderLayout(5, 5));
        add(panelDisplay1, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(mousePosition, BorderLayout.SOUTH);
        setSize(700, 500); 
        panelDisplay3.setLayout(new FlowLayout());
        panelDisplay3.add(gradient);
        panelDisplay3.add(gradientLabel);
        panelDisplay3.add(color1);
        panelDisplay3.add(color2);
        panelDisplay3.add(strokeWidthLabel);
        panelDisplay3.add(strokeWidth);
        panelDisplay3.add(dashLengthLabel);
        panelDisplay3.add(dashLength);
        panelDisplay3.add(dashed);
        panelDisplay3.add(dashedLabel);
        panelDisplay1.setLayout(new GridLayout(2,1));
        panelDisplay1.add(panelDisplay2);
        panelDisplay1.add(panelDisplay3);
       
    }
    
    // class to create a component to draw on 
    private class DrawPanel extends JPanel 
    {
        @Override
        public void paintComponent(Graphics g) 
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            this.setBackground(Color.white);
            for (Shape drawing: Drawings) 
            {
                drawing.drawDifferentShapes(g2d);
            }
        }
    }
    
    // user presses enter in the text fields
    private class TextFieldHandler implements ActionListener 
    {
        
        @Override
        public void actionPerformed(ActionEvent event)
        {
            action = event.getActionCommand();
            
            // strokeWidth text field
            if (event.getSource() == strokeWidth) 
            {
                currentShape.setWidth(Integer.parseInt(action));
            }
            // dashLength text field
            else if (event.getSource() == dashLength) 
            {
                currentShape.setDash(Integer.parseInt(action));
            }
        }  
    }
    
    // user clicks on the button
    private class ButtonHandler implements ActionListener 
    {
        Color tempColor;
        
        @Override
        public void actionPerformed(ActionEvent event)
        {
            action = event.getActionCommand();
            // undo
            if (action == "Undo") 
            {
                Drawings.remove(Drawings.size()-1);
                if (!Drawings.isEmpty()) Drawings.remove(Drawings.size()-1);
                Drawings.add(currentShape);
            }
            // clear
            else if (action == "Clear") 
            {
                Drawings.removeAll(Drawings);
                Drawings.add(currentShape);
            }
            // color1
            else if (action == "1st Color...") 
            {
                tempColor = JColorChooser.showDialog(DrawFrame.this, "Primary Color: ", tempColor);
                if (tempColor != null) 
                {
                    currentShape.setColor1(tempColor);
                }
                tempColor = currentShape.getColor1();
            }
            // color2
            else if (action == "2nd Color...") 
            {
                tempColor = JColorChooser.showDialog(DrawFrame.this, "Secondary Color: ", tempColor);
                if (tempColor != null)
                {
                    currentShape.setColor2(tempColor);
                }
                tempColor = currentShape.getColor2();
            }
            repaint();
        }
    }
    
    
   
     //this event is triggered when the user clicks on the shaps
    private class ComboBoxHandler implements ItemListener 
    {
        @Override
        public void itemStateChanged(ItemEvent event)
        {
            int index;
            // click on the shape combo box
            if (event.getStateChange() == ItemEvent.SELECTED)
            {
                index = shape.getSelectedIndex();
                if (index == 0) 
                {
                    currentShape = new Oval(currentShape);
                }
                else if (index == 1)
                {
                    currentShape = new Rectangle(currentShape);
                }
                else if (index == 2) 
                {
                    currentShape = new Line(currentShape);
                }
            }
        }
    }
    
    //This is triggered when the user clicks on the checkbox
    private class CheckBoxHandler implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent event) 
        {   
            if (event.getSource() == fill) 
            {
                if (fill.isSelected()) {
                    currentShape.setFill(true);
                    dashed.setSelected(false);
                }
                else 
                {
                    currentShape.setFill(false);
                }
            }

            else if (event.getSource() == gradient) 
            {
                // gradient
                if (gradient.isSelected()) 
                {
                    currentShape.setGradient(true);
                }
                else
                {
                    currentShape.setGradient(false);
                }
            }
            
            else if (event.getSource() == dashed) 
            {               
                // dashed line
                if (dashed.isSelected()) 
                {
                    currentShape.setDashed(true);
                    fill.setSelected(false);
                }
                else 
                {
                    currentShape.setDashed(false);
                }
            }
        }
    } 
    
    // user moves the mouse
    private class MouseHandler implements MouseMotionListener, MouseListener 
    {
        
        @Override
        public void mouseMoved(MouseEvent event) 
        {
            mousePosition.setText(String.format("(%d, %d)", event.getX(), event.getY()));  // change the mouse position text
        }
        
        @Override
        public void mousePressed(MouseEvent event) 
        {
            currentShape.setBegin(new Point(event.getX(), event.getY()));
            currentShape.setEnd(new Point(event.getX(), event.getY()));
        }
        
        @Override
        public void mouseReleased(MouseEvent event)
        {
            if (currentShape.getBegin() != currentShape.getEnd()) 
            {
                Drawings.remove(Drawings.size()-1);
                Drawings.add(currentShape);
                repaint();
                
                // HERE WE HAVE TO GET ATTRIBUTES FOR THE NEW SHAPES AND
                // AND SET THE CURRENT SHAPE TO BE THIS NEW ONE
                if (currentShape instanceof Oval)
                {
                    currentShape = new Oval(currentShape);
                }
                else if (currentShape instanceof Line) 
                {
                    currentShape = new Line(currentShape);
                }
                else if (currentShape instanceof Rectangle) 
                {
                    currentShape = new Rectangle(currentShape);
                }
                currentShape.setBegin(new Point(0,0));
                currentShape.setEnd(new Point(0,0));
                Drawings.add(currentShape);
            }
        }
        
         @Override
        public void mouseDragged(MouseEvent event)
        {
            // DRAW SHAPE AS MOUSE IS DRAGGED
            currentShape.setEnd(new Point(event.getX(), event.getY()));
            Drawings.remove(Drawings.size()-1);
            Drawings.add(currentShape);
            repaint();
            mousePosition.setText(String.format("(%d, %d)", event.getX(), event.getY()));  // change the mouse position text
        }
        
        @Override
        public void mouseClicked(MouseEvent event) 
        {
            
        }
        
        @Override
        public void mouseEntered(MouseEvent event) 
        {
            
        }
        @Override
        public void mouseExited(MouseEvent event)
        {
            
        }
    }
}

