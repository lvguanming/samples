package ramp.chirper.android;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rpalaniappan on 18/06/14.
 */
public class TwitterListViewAdapter extends BaseAdapter {

    ArrayList<String> tweetList = null;
    Activity activity = null;
    public TwitterListViewAdapter(ArrayList<String> tweetList, Activity activity) {
        if (tweetList == null) {
            throw new IllegalArgumentException("Null list");
        }
        this.tweetList = tweetList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.tweetList.size();
    }

    @Override
    public Object getItem(int i) {
        return tweetList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        String tweet = tweetList.get(i);
        if (tweet == null) {
            return view;
        }
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.twitter_layout, null);
        }
        TextView tweetTextView = (TextView) view.findViewById(R.id.textView);
        tweetTextView.setText(tweet);
        return view;
    }
}
