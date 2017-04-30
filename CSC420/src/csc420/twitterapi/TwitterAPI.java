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
package csc420.twitterapi;
import csc420.models.TwitterUser;
import java.util.ResourceBundle;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

/**
 *
 * @author Cameron J. Everett
 * 
 * Responsible for creating connection to Twitter API.
 */
public class TwitterAPI {
    Twitter twitter;
    
    /**
     * Initializes a connection to the Twitter API.
     * @throws TwitterException 
     */
    public TwitterAPI() throws TwitterException {
        ResourceBundle twitterProps = ResourceBundle.getBundle("resources.twitter4j");        

        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(
                twitterProps.getString(TwitterOAuthProps.CONSUMER_KEY),
                twitterProps.getString(TwitterOAuthProps.CONSUMER_SECRET)
        );
        twitter.setOAuthAccessToken(new AccessToken(
                twitterProps.getString(TwitterOAuthProps.ACCESS_TOKEN),
                twitterProps.getString(TwitterOAuthProps.ACCESS_TOKEN_SECRET)
        ));
    }
    
    /**
     * Fetch a user's profile information by id from the Twitter API endpoint
     * and return a new instance of the TwitterUser model.
     * @param userId
     * @return TwitterUser
     * @throws TwitterException 
     */
    public TwitterUser getById(long userId) throws TwitterException {
        User user = twitter.showUser(userId);
        return new TwitterUser(
                user.getId(),
                user.getName(),
                user.getFollowersCount(),
                user.getProfileImageURL()
        );
    }
    
    /**
     * Fetch a user's profile information by username from the Twitter API endpoint
     * and return a new instance of the TwitterUser model.
     * @param username
     * @return TwitterUser
     * @throws TwitterException 
     */
    public TwitterUser getByUsername(String username) throws TwitterException {
        User user = twitter.showUser(username);
        return new TwitterUser(
                user.getId(),
                user.getName(),
                user.getFollowersCount(),
                user.getProfileImageURL()
        );
    }
    
    /**
     * Fetch a user's followers associated with the passed userId and return
     * a list of user ids from based on the page (cursor) from the Twitter API.
     * @param userId - Returns followers of the user with the passed userId.
     * @param cursor - Used for paginating results.
     * @return TwitterUser
     * @throws TwitterException 
     */
    public long[] getFollowersByUserId(long userId, long cursor) throws TwitterException {
        // Returns 20 of the selected users followers.
        return twitter.getFollowersIDs(userId, cursor, 20).getIDs();
    }
    
}
