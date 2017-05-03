/*
 * The MIT License
 *
 * Copyright 2017 cam.
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

import csc420.models.TwitterUser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Cameron J. Everett, Chase Toy, Laura Buckman
 */
public class UserProfileSearchHistoryListCell extends JPanel implements ListCellRenderer<TwitterUser> {
    JLabel userProfileName;
    JLabel userFollowersCount;
    JPanel userInfoBox;
    JLabel userProfilePhoto;
    
    public UserProfileSearchHistoryListCell() {
        userProfileName = new JLabel();
        userFollowersCount = new JLabel();
        userInfoBox = new JPanel();
        userProfilePhoto = new JLabel();
        
    }
    
    @Override
    public Component getListCellRendererComponent(
            JList<? extends TwitterUser> list,
            TwitterUser user, 
            int index, 
            boolean isSelected, 
            boolean cellHasFocus) {
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);
        userInfoBox.setLayout(new BoxLayout(userInfoBox, BoxLayout.Y_AXIS));

        
        Font listFont = list.getFont();
        userProfileName.setFont(listFont);
        userFollowersCount.setFont(listFont);
        
        BufferedImage photo;
        try {
            photo = ImageIO.read(new URL(user.getProfileImageUrl()));
            if(photo != null) {
                userProfilePhoto.setIcon(new ImageIcon(photo));
            }
            else {
                URL defaultPhotoUrl = getClass().getResource("csc420/resources/twitter_logo.png");
                photo = ImageIO.read(defaultPhotoUrl);
                userProfilePhoto.setIcon(new ImageIcon(photo));
            }
        } catch (IOException e) {
            System.out.println("Bad link provided for profile photo.");
        }
        
        userProfileName.setText("@" + user.getHandle());
        userFollowersCount.setText("Followers: " + String.valueOf(user.getFollowersCount()));
        
        userInfoBox.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        add(userProfilePhoto);
        userInfoBox.add(userProfileName);
        userInfoBox.add(userFollowersCount);
        add(userInfoBox);
        return this;
    }
    
}
