/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;
/*
Programmer:         Raj Pokhrel
Class:              CMPSC 221   
Assignment:         5
Description:        2D Drawing
*/

import javax.swing.JFrame;
import java.awt.event.ItemEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Draw
{ 
    public static void main(String[] args)
    {
        //Main class to start the program
         DrawFrame panel = new DrawFrame();
         panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         panel.setVisible(true);
    }   
}
