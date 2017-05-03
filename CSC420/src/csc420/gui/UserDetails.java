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
package csc420.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author chasetoy
 */
public class UserDetails extends JPanel {
    UserDetails userDetails;
    JPanel UserPhotos;
    JPanel UserInfo;
    JLabel UserHandle;
    JLabel UserFollowers;
    JLabel UserFollowing;
    JLabel UserSummary;
    
    public UserDetails(){
        UserPhotos = new JPanel();
        UserInfo = new JPanel();
        initComponents();
    }
    
    private void initComponents(){
        setOpaque(true);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.orange);
        Dimension dim1 = getPreferredSize();
        dim1.width = 250;
        setMinimumSize(dim1);
        initPhoto();
        initInfo();
        add(UserPhotos);
        add(UserInfo);
    }
    
    private void initPhoto(){
        UserPhotos.setOpaque(true);
        UserPhotos.setBackground(Color.DARK_GRAY);
    }
    
    private void initInfo(){
        UserInfo.setOpaque(true);
        UserInfo.setBackground(Color.LIGHT_GRAY);
        UserHandle = new JLabel("UserHandle");
        UserFollowers = new JLabel("UserFollowers");
        UserFollowing = new JLabel("UserFollowing");
        UserSummary = new JLabel("UserSummary");
        UserInfo.add(UserHandle);
        UserInfo.add(UserFollowers);
        UserInfo.add(UserFollowing);
        UserInfo.add(UserSummary);
        UserInfo.setLayout(new BoxLayout(UserInfo, BoxLayout.Y_AXIS));
    }
}
