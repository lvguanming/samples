package ramp.chirper.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by rpalaniappan on 18/07/14.
 */
public class NullChecker {

    public void verify(String str, Context context)  {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("Null or empty search query");
        }
    }
}
