package ramp.chirper.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class TwitterSearchActivity extends ActionBarActivity {

    NullChecker nullChecker = new NullChecker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_twitter);
        Toast toast = Toast.makeText(getApplicationContext(),
                    "Crashes are reported to ...",
                    Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0, 0);
        toast.show();
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

    public void searchTwitter(View view) {
        Intent intent = new Intent(this, TweetListActivity.class);
        EditText searchEditText = (EditText) findViewById(R.id.searchQuery);
        String query = searchEditText.getText().toString();
        nullChecker.verify(query, getApplicationContext());
        intent.putExtra(Constants.TWITTER_SEARCH_QUERY, query);
        startActivity(intent);
    }

}
