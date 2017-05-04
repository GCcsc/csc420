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

import csc420.AppEventManager;
import csc420.models.TwitterUser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author chasetoy
 */
public class UserDetails extends JPanel {
    TwitterUser currentUser;
    JLabel UserPhotos;
    JPanel UserInfo;
    JLabel UserHandle;
    JLabel UserFollowers;
    JLabel UserFollowing;
    JLabel UserSummary;
    
    public UserDetails(){
        UserPhotos = new JLabel();
        UserInfo = new JPanel();
        AppEventManager.setUserDetailsPanel(this);
        initComponents();
    }
    
    private void initComponents(){
        setOpaque(true);
        setLayout(new GridLayout(1,2));
        setBackground(Color.orange);
        Dimension dim1 = getPreferredSize();
        dim1.width = 720;
        setMinimumSize(dim1);
        initPhoto();
        initInfo();
        add(UserPhotos);
        add(UserInfo);
    }
    
    private void initPhoto(){
        UserPhotos.setOpaque(true);
        UserPhotos.setBackground(Color.DARK_GRAY);
        UserPhotos.setAlignmentX(RIGHT_ALIGNMENT);
    }
    
    private void initInfo(){
        UserInfo.setOpaque(true);
        UserInfo.setBackground(Color.DARK_GRAY);
        UserHandle = new JLabel("UserHandle");
        UserFollowers = new JLabel("UserFollowers");
        UserFollowing = new JLabel("UserFollowing");
        UserSummary = new JLabel("UserSummary");
        UserInfo.add(UserHandle);
        UserInfo.add(UserFollowers);
        UserInfo.add(UserFollowing);
        UserInfo.add(UserSummary);
        UserInfo.setLayout(new BoxLayout(UserInfo, BoxLayout.Y_AXIS));
        UserHandle.setFont(UserHandle.getFont().deriveFont(20.0f));
        UserFollowers.setFont(UserFollowers.getFont().deriveFont(20.0f));
        UserFollowing.setFont(UserFollowing.getFont().deriveFont(20.0f));
        UserSummary.setFont(UserSummary.getFont().deriveFont(20.0f));
        UserHandle.setForeground(Color.WHITE);
        UserFollowers.setForeground(Color.WHITE);
        UserFollowing.setForeground(Color.WHITE);
        UserSummary.setForeground(Color.WHITE);
    }

    public void getCurrentUser(TwitterUser currentUser) {
        this.currentUser = currentUser;        
        UserHandle.setText("Name: " + currentUser.getHandle());
        UserFollowers.setText("Followers: " + currentUser.getFollowersCount());
        
        BufferedImage photo;
        try {
            photo = ImageIO.read(new URL(currentUser.getProfileImageUrl()));
            if(photo != null) {
                Image image = new ImageIcon(photo).getImage(); 
                UserPhotos.setIcon(new ImageIcon(image.getScaledInstance(400, 400, Image.SCALE_SMOOTH)));
                
            }
            else {
                URL defaultPhotoUrl = getClass().getResource("csc420/resources/twitter_logo.png");
                photo = ImageIO.read(defaultPhotoUrl);
                UserPhotos.setIcon(new ImageIcon(photo));
            }
        } catch (IOException e) {
            System.out.println("Bad link provided for profile photo.");
        }
    }
}
