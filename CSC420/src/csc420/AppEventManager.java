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
import twitter4j.TwitterException;

/**
 *
 * @author cam
 */
public class AppEventManager {
    private static TwitterAPI twitterApi;
    private static TwitterUser currentUser;

    private static UserProfileSearchHistory searchHistoryPanel;
    private static UserDetails userDetailsPanel;
    private static ResultsPane resultsPanel;
    
    public AppEventManager() {
        try {
            twitterApi = new TwitterAPI();
        } catch(TwitterException e) {
            System.out.println("Check your internet connection.");
        }
    }
    
    public static void setSearchHistoryPanel(UserProfileSearchHistory target) {
        searchHistoryPanel = target;
    }
    
    public static void setUserDetailsPanel(UserDetails target) {
        userDetailsPanel = target;
    }
    
    public static void setResultsPanel(ResultsPane target) {
        resultsPanel = target;
    }
    
    public static TwitterUser getCurrentUser() {
        return currentUser;
    }
    
    public static void setCurrentUser(TwitterUser user) {
        currentUser = user;
        searchHistoryPanel.addUser(currentUser);
        userDetailsPanel.getCurrentUser(currentUser);
    }
    
    public static void apiGetUserByName(String username) {
        try {
            currentUser = twitterApi.getByUsername(username);
            setCurrentUser(currentUser);
        } catch(TwitterException e) {
            System.out.println("An error occurred while processing request. \nPlease check your network connection and spelling of username.");
        }
    }
    
    public static void apiGetUserById(long userId) {
        try {
            currentUser = twitterApi.getById(userId);
        } catch(TwitterException e) {
            System.out.println("Could not find user with this twitter handle.");
            e.printStackTrace();
        }
    }
}
