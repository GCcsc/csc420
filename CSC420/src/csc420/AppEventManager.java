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
package csc420;

import csc420.gui.ResultsPane;
import csc420.gui.UserDetails;
import csc420.gui.UserProfileSearchHistory;
import csc420.models.TwitterUser;
import csc420.twitterapi.TwitterAPI;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import twitter4j.TwitterException;

/**
 *
 * @author cam
 */
public class AppEventManager {
    private static TwitterAPI twitterApi;
    private static TwitterUser currentUser;
    private static UserStore userStore;

    private static UserProfileSearchHistory searchHistoryPanel;
    private static UserDetails userDetailsPanel;
    private static ResultsPane resultsPanel;
    
    public AppEventManager() {
        try {
            twitterApi = new TwitterAPI();
            loadSession();
        } catch(TwitterException e) {
            System.out.println("Check your internet connection.");
        }
    }
    
    public static void loadSession() {
        try {
            FileInputStream fileStream = new FileInputStream("src/resources/session.bin");
            ObjectInputStream oiStream = new ObjectInputStream(fileStream);
            userStore = (UserStore) oiStream.readObject();            
            setCurrentUser(userStore.getCurrentUser());
            updateResultsPanel();
            
            
        } catch(Exception e) {
            // Just start a new session otherwise.
            userStore = new UserStore();
        }
    }
    
    public static void saveSession() {
        try {
            FileOutputStream fileStream = new FileOutputStream("src/resources/session.bin");
            ObjectOutputStream ooStream = new ObjectOutputStream(fileStream);
            ooStream.writeObject(userStore);
            System.out.println("Okay");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Get instance of the UserProfileSearchHistory panel.
     * @param target 
     */
    public static void setSearchHistoryPanel(UserProfileSearchHistory target) {
        searchHistoryPanel = target;
    }
    
    /**
     * Get instance of the UserDetails panel.
     * @param target 
     */
    public static void setUserDetailsPanel(UserDetails target) {
        userDetailsPanel = target;
    }
    
    /**
     * Get an instance of the ResultsPane panel.
     * @param target 
     */
    public static void setResultsPanel(ResultsPane target) {
        resultsPanel = target;
    }
 /*   
    public static void setUserStore(UserStore target) {
        userStore = target;
    }
*/    
    public static void updateResultsPanel() {
        resultsPanel.getData(currentUser, userStore.getData());
    }
    
    /**
     * Return the currently focused user account.
     * @return 
     */
    public static TwitterUser getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Update the currently focused user account.
     * @param user 
     */
    public static void setCurrentUser(TwitterUser user) {
        if(searchHistoryPanel != null && userDetailsPanel != null) {
            currentUser = user;

            searchHistoryPanel.addUser(currentUser);
            userDetailsPanel.getCurrentUser(currentUser);
        }
    }
    
    /**
     * Both makes an HTTP request and updates current user account based on searched username.
     * @param username 
     */
    public static void apiGetUserByName(String username) {
        try {
            setCurrentUser(twitterApi.getByUsername(username));
            userStore.update(currentUser, twitterApi.getFollowersByUserId(currentUser.getId(), -1));
            resultsPanel.getData(currentUser, userStore.getData());
        } catch(TwitterException e) {
            System.out.println("An error occurred while processing request. \nPlease check your network connection and spelling of username.");
        }
    }
    
    /**
     * Both makes an HTTP request and updates current user account based on searched user id.
     * @param userId 
     */
    public static void apiGetUserById(long userId) {
        try {
            setCurrentUser(twitterApi.getById(userId));
        } catch(TwitterException e) {
            System.out.println("Could not find user with this twitter handle.");
            e.printStackTrace();
        }
    }
}
