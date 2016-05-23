/**********************************************************/
/* WE WILL TREAT GROUPS AS USER */
/*NEED TO DECIDE HOW NOMINATION WORK*/
/*******************************************************/

//CREATE A CLOUD CODE FOR POSTING QUESTION/////
package io.github.froger.instamaterial.ui.parse_backend;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by ankit on 21/2/16.
 */
public class Basicfunctionality {

    /* ***questionToPost ****
    ***FORMAT****
    questionName -- String
    description -- String
    Number of comments --String
    Number of likes -- String
    Number of Votes --String
    Question Tags -- tag1,tag2

    */

    //It will Question Object ID//
    /*** Store it for future processing***/
    /*** Pass the single group for the categorization ***/
    public String postQuestion(HashMap<String,String> questionToPost,
                             ArrayList<String> options,
                             ArrayList<Integer> stats,
                             String typeOfQuestion,
                             ParseFile image,
                             ParseUser toUser,
                             ParseUser group){

        final Question question = new Question();
        for(HashMap.Entry<String,String> entry:questionToPost.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            question.put(key, value);
        }
        //check if image is present in question
        if(image.isDataAvailable()){
            question.setImage(image);
        }
        final QuestionOption questionOption = new QuestionOption();
        questionOption.setOptions(options);
        questionOption.setQuestion(question);
        questionOption.setStats(stats);
        questionOption.setType(typeOfQuestion);

        //if type of question is image only then need to handle that case
        final Nomination nomination = new Nomination();
        nomination.setQuestion(question);
        nomination.setFromUser(ParseUser.getCurrentUser());
        nomination.setToUser(toUser);
        nomination.setGroup(group);

        final Like like = new Like();
        like.setQuestion(question);
        like.setAuthor(ParseUser.getCurrentUser());
        like.setLikedBy(ParseUser.getCurrentUser());

        final Activity activityForUser = new Activity();
        activityForUser.setQuestion(question);
        activityForUser.setFromUser(ParseUser.getCurrentUser());
        activityForUser.setToUser(toUser);
        activityForUser.setType("A2A");

        final Activity activityForGroup = new Activity();
        activityForGroup.setQuestion(question);
        activityForGroup.setFromUser(ParseUser.getCurrentUser());
        activityForGroup.setToUser(group);
        activityForGroup.setType("A2A");

        //create Notification
        //execute all the above querry

        return question.getObjectId();
    }
    //need to get objectid and store them it in the array so that when user clicks on question pass it to getQuestionDetail as ObjectID also in the

    public static ParseQuery<Question> getFeedQuery(){

        ParseQuery<Activity> followingActivityQuery = ParseQuery.getQuery(Activity.class);
        followingActivityQuery.whereMatches("type", "follow");
        followingActivityQuery.whereEqualTo("fromUser", ParseUser.getCurrentUser());

        ParseQuery<Question> questionFromFollowedUser = ParseQuery.getQuery(Question.class);
        questionFromFollowedUser.whereMatchesKeyInQuery("author", "toUser", followingActivityQuery);

        ParseQuery<Question> questionFromCurrentUser = ParseQuery.getQuery(Question.class);
        questionFromFollowedUser.whereEqualTo("author", ParseUser.getCurrentUser());

        ParseQuery<Question> trendingQuestion = ParseQuery.getQuery(Question.class);
        trendingQuestion.orderByDescending("likes,votes");
        trendingQuestion.setLimit(10);

        ParseQuery<Question> finalQuery = ParseQuery.or(Arrays.asList(trendingQuestion,questionFromFollowedUser,questionFromCurrentUser));
        finalQuery.include("user");
        finalQuery.orderByDescending("createdAt");
        return  finalQuery;
    }
    public void getTrendingQuestion(){

        ParseQuery<Question> trendingQuestion = ParseQuery.getQuery(Question.class);
        trendingQuestion.orderByDescending("likes,votes");
        trendingQuestion.setLimit(10);
        trendingQuestion.findInBackground(new FindCallback<Question>() {
            @Override
            public void done(List<Question> objects, ParseException e) {

                for(int i =0 ;i<objects.size();i++){
                    //need to return detail of the required format
                    //save the person id of person asking question
                    getQuestionDetail(objects.get(i).getObjectId());
                }
            }
        });
    }
/***************************************************************
 * Needed to be complete
 *
 ***************************************************************/
    public void getFriendFeed(){

    }
    public void updateFeed(){
        getTrendingQuestion();
        getFriendFeed();
    }
    // need to call it after user has successful login and wants to see the details of himself

    public void getOtherUserDetail(String personID){
        ParseQuery<ParseUser> getUserDetail = ParseUser.getQuery();
        getUserDetail.whereEqualTo("objectId", personID);
        getUserDetail.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                int size = objects.size();
                assert (size <= 1 && size > 0);
                getQuestionAskedByUser(objects.get(0));
                getFollowingStatus(objects.get(0));
                ParseUser currentUser = objects.get(0);
                String userName = currentUser.getUsername();
                String email = currentUser.getEmail();

                //change the datamodel int==>> string

                String follower = currentUser.get("followers").toString();
                String following = currentUser.get("following").toString();
                String status = currentUser.get("status").toString();
                String gender = currentUser.get("gender").toString();
                String age = currentUser.get("age").toString();
                //need to Fetch the user image .. change this querry

                //ParseQuery<Photo> ph

                ParseFile image = currentUser.getParseFile("userPicture");
                //change image view to the parse image view
                //imageview.setParseFile(image);
                //imageview.loadInBackground();
                getQuestionAskedByUser(currentUser);

            }
        });
    }
    public void getFollowingStatus(ParseUser user){
        final boolean flag = true;
        ParseQuery<Activity> query = ParseQuery.getQuery(Activity.class);
        query.whereEqualTo("fromUser",ParseUser.getCurrentUser());
        query.whereEqualTo("toUser", user);
        query.whereEqualTo("type", "follow");
        query.getFirstInBackground(new GetCallback<Activity>() {
            @Override
            public void done(Activity object, ParseException e) {
                if(e == null){
                    //set button to following
                }
                else{
                    //set button to not following
                }
            }
        });
    }
    public void getCurrentUserDetail(){
        ParseUser currentUser = ParseUser.getCurrentUser();
        String userName = currentUser.getUsername();
        String email = currentUser.getEmail();

        //change the datamodel int==>> string

        String follower = currentUser.get("followers").toString();
        String following = currentUser.get("following").toString();
        String status = currentUser.get("status").toString();
        String gender = currentUser.get("gender").toString();
        String age = currentUser.get("age").toString();
        //need to Fetch the user image .. change this querry

        //ParseQuery<Photo> ph

        ParseFile image = currentUser.getParseFile("userPicture");
        //change image view to the parse image view
        //imageview.setParseFile(image);
        //imageview.loadInBackground();

        getQuestionAskedByUser(currentUser);
        getNotificationforUser(currentUser);
    }
    public void getNotificationforUser(ParseUser currentUser){
        ParseQuery<Activity> notification = ParseQuery.getQuery(Activity.class);
        notification.whereEqualTo("toUser", currentUser);
        notification.orderByDescending("createdAt");
        notification.findInBackground(new FindCallback<Activity>() {
            @Override
            public void done(List<Activity> objects, ParseException e) {
                //return the activity;
            }
        });
    }
    public void getQuestionAskedByUser(ParseUser currentUser){

        ParseQuery<Question> questionAskedByCurrentUser= ParseQuery.getQuery(Question.class);
        questionAskedByCurrentUser.whereEqualTo("author", currentUser);
        questionAskedByCurrentUser.orderByDescending("createdAt");
        questionAskedByCurrentUser.findInBackground(new FindCallback<Question>() {
            @Override
            public void done(List<Question> objects, ParseException e) {
                //return the parse object with detail;
            }
        });
    }
    public void updateUserDetail(ParseFile imageNew ,Boolean imageUpdated,HashMap<String,String> updatedDetail){
        ParseUser currentUser = ParseUser.getCurrentUser();

        if(imageUpdated == Boolean.TRUE){
            currentUser.put("userPicture",imageNew);
        }
        for(HashMap.Entry<String,String> entry:updatedDetail.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            currentUser.put(key,value);
        }
    }
    //*****************need to pass QuestionDetail id *******************************//

    public void getQuestionDetail(final String objectID){
        ParseQuery<QuestionOption> questionDetail = ParseQuery.getQuery(QuestionOption.class);
        questionDetail.include("question");
        questionDetail.include("question.author");
        questionDetail.whereEqualTo("objectId", objectID);
        questionDetail.getFirstInBackground(new GetCallback<QuestionOption>() {
            @Override
            public void done(QuestionOption object, ParseException e) {
                String type = object.getType();
                ArrayList<String> options = object.getOptions();

                //iterate the option and check for if present or not
                ArrayList<Integer> stats = object.getStats();
                //iterate over stats and store the stats per option

                String questionName = object.getQuestion().getQuestionName();
                String description = object.getQuestion().getDescription();
                int commentNumber = object.getQuestion().getCommentNumber();
                int likesNumber = object.getQuestion().getLikeNumber();
                int votes = object.getQuestion().getVotesNumber();
                String Tags = object.getQuestion().getTag();
                //split the tag and display accordingly

                ParseUser userProfile = object.getQuestion().getParseUser("author");
                ParseFile userimage = userProfile.getParseFile("userPicture");
                //change image view to the parse image view
                //imageview.setParseFile(image);
                //imageview.loadInBackground();
            }
        });
    }

    public void updateQuestion(String questionId, final HashMap<String,String> updatedDetail, final ParseFile image){
        ParseQuery<Question> updateQuery = ParseQuery.getQuery(Question.class);
        updateQuery.whereEqualTo("objectId",questionId);
        updateQuery.getFirstInBackground(new GetCallback<Question>() {
            @Override
            public void done(Question object, ParseException e) {
                for(HashMap.Entry<String,String>entry:updatedDetail.entrySet()){
                    object.put(entry.getKey(),entry.getValue());

                }
                if(image.isDataAvailable()){
                    object.setImage(image);
                }
                //need to save modified object
                object.saveEventually();
            }
        });
    }

    public void updateStats(QuestionOption questionOption,ArrayList<Integer> updatedStats){
        questionOption.setStats(updatedStats);
        //create a notification for the all user on the cloud code
        questionOption.saveEventually();

    }
    //Is there actually need for that//
    /*****************************************/
    public void getRelatedQuestion(){
    }
    /****************************************/
    public void getComments(){

    }
    public void updateComments(){

    }
    public void getLikes(){

    }
    public void updateLikes(){

    }
    public void unfollowPerson(){

    }
    public void followPerson() {

    }
}
