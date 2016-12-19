package ramp.chirper.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class TweetListActivity extends ActionBarActivity {

    private Context context = null;
    private Twitter twitter = null;
    private ListView listView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweets_list);

        // Get the message from the intent
        Intent intent = getIntent();
        String twitterSearchQuery = intent.getStringExtra(Constants.TWITTER_SEARCH_QUERY);

        listView = (ListView) findViewById(R.id.listView);
        TextView searchTextView = (TextView) findViewById(R.id.searchQuery);
        searchTextView.setText(twitterSearchQuery);
        context = getApplicationContext();
        twitter = getTwitter();
        new TwitterAsyncTask(twitter, this).execute(twitterSearchQuery);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.twitter_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Twitter getTwitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(getString(R.string.twitter_consumer_key));
        cb.setOAuthConsumerSecret(getString(R.string.twitter_consumer_secret));
        cb.setOAuthAccessToken(getString(R.string.twitter_access_token));
        cb.setOAuthAccessTokenSecret(getString(R.string.twitter_access_token_secret));
        return new TwitterFactory(cb.build()).getInstance();
    }

    public void setListViewAdapter(ArrayList<String> list) {
        TwitterListViewAdapter twitterListViewAdapter = new TwitterListViewAdapter(list, this);
        listView.setAdapter(twitterListViewAdapter);
    }
}
