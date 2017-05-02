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
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
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
public class UserSearchProfileHistoryListCell extends JPanel implements ListCellRenderer<TwitterUser> {
    JLabel userProfilePhoto;
    JLabel userProfileName;
    JLabel userFollowersCount;
    JPanel userInfoBox;
    
    public UserSearchProfileHistoryListCell() {
        userProfilePhoto = new JLabel();
        userProfileName = new JLabel();
        userFollowersCount = new JLabel();
        userInfoBox = new JPanel();
    }
    
    /**
     * Return the user's main profile image to be displayed as a thumbnail in the
     * searched users history list.
     * @param twitterProfilePhotoUrl String providing the location of the twitter profile image.
     * @return (ImageIcon|null) ImageIcon used to represent a profile photo.
     */
    private ImageIcon loadImageFromUrl(String twitterProfilePhotoUrl) {
        try {
            URL url = new URL(twitterProfilePhotoUrl);
            return new ImageIcon(ImageIO.read(url));
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Component getListCellRendererComponent(
            JList<? extends TwitterUser> list,
            TwitterUser user, 
            int index, 
            boolean isSelected, 
            boolean cellHasFocus) {
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        Font listFont = list.getFont();
        userProfileName.setFont(listFont);
        userFollowersCount.setFont(listFont);
        userProfilePhoto.setIcon(loadImageFromUrl(user.getProfileImageUrl()));
        
        userInfoBox.add(userProfileName, BorderLayout.NORTH);
        userInfoBox.add(userFollowersCount, BorderLayout.SOUTH);
        add(userProfilePhoto);
        add(userInfoBox);
        
        return this;
        
    }
    
}
