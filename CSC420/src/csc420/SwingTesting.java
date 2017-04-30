/*
 * The MIT License
 *
 * Copyright 2017 chasetoy.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package csc420;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author chasetoy
 */
public class SwingTesting extends JFrame {
    
    public static void main(String[] args) {
        SwingTesting gst = new SwingTesting();
        gst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gst.pack();
        gst.setVisible(true);
    }
    
    public SwingTesting() {
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(3,2));
        JButton button = new JButton("First");
        pane.add(button);
        button = new JButton("Second");
        pane.add(button);
        button = new JButton("Hi");
        button.setFont(new Font("Courier", Font.PLAIN, 36));
        pane.add(button);
        button = new JButton("There");
        pane.add(button);
    }
    
}
