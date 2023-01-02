package edu.ucam.clients;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
public class Prueba {
   public static void main(String args[]) throws BadLocationException {
      JFrame frame = new JFrame("Demo");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container container = frame.getContentPane();
      JTextPane textPane = new JTextPane();
      //textPane.setBackground(Color.BLUE);
      //Font font = new Font("Serif", Font.ITALIC, 22);
     // textPane.setFont(font);
      //SimpleAttributeSet attributeSet = new SimpleAttributeSet();
     // StyleConstants.setItalic(attributeSet, true);
     // StyleConstants.setForeground(attributeSet, Color.blue);
      //StyleConstants.setBackground(attributeSet, Color.white);
      //textPane.setCharacterAttributes(attributeSet, true);
      /*textPane.setText("This is demo text1. This is demo text2. This is demo text3."
         + "This is demo text4.This is demo text5. This is demo text6. "
         + "This is demo text7. This is demo text8. This is demo text9. "
         + "This is demo text10. This is demo text11. This is demo text12."
         + "This is demo text13. This is demo text13. This is demo text14."
         + "This is demo text15. This is demo text13. This is demo text16."
         + " This is demo text17. This is demo text13. This is demo text18."
         + " This is demo text19.This is demo text13.This is demo text20.");*/
      JScrollPane scrollPane = new JScrollPane(textPane);
      scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
      container.add(scrollPane, BorderLayout.CENTER);
      frame.setSize(500, 300);
      frame.setVisible(true);
   }
}