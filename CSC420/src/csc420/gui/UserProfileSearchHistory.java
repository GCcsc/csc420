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

import csc420.AppEventManager;
import csc420.models.TwitterUser;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author cam
 */
public class UserProfileSearchHistory extends JPanel {
    JList<TwitterUser> searchHistory;
    JScrollPane scrollPane;
    DefaultListModel<TwitterUser> searchModel;
    public UserProfileSearchHistory() {
        searchModel = new DefaultListModel<>(); 
        searchHistory = new JList<>(searchModel);
        scrollPane = new JScrollPane(searchHistory, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        initComponents();
    }
    
    /**
     * Adds a user to the list of recently searched user profiles.
     * @param searchedUser User really returned from the Twitter API.
     */
    public void addUser(TwitterUser searchedUser) {
        if(searchedUser != null) 
            searchModel.add(0, searchedUser);
    }
    
    /**
     * Removes an entry in the recently searched user profiles.
     * @param index Marker for deleting 
     */
    public void removeAt(int index) {
        if(index < searchModel.getSize() && index >= 0) {
            searchModel.remove(index);
        }
    }
    
    /**
     * Clears the list of recently searched users.
     */
    public void clear() {
        searchModel.clear();
    }
    
    private void initComponents() {
        searchHistory.setCellRenderer(new UserProfileSearchHistoryListCell());        
        scrollPane.setMinimumSize(new Dimension(250, 200));
        scrollPane.setPreferredSize(new Dimension(250, 600));
        
        searchHistory.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1) {
                    AppEventManager.apiGetUserByName(searchHistory.getSelectedValue().getHandle());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        add(scrollPane);
    }
    
}
