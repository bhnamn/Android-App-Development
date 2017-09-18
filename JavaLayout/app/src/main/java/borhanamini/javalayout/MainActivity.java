package borhanamini.javalayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Color;
import android.widget.EditText;
import android.content.res.Resources;
import android.util.TypedValue;

import static java.lang.Integer.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create a custom layout
        RelativeLayout myLayout = new RelativeLayout(this);
        myLayout.setBackgroundColor(Color.parseColor("#45B39D"));

        // create a button widget
        Button myButton = new Button(this);
        myButton.setId(1);
        myButton.setBackgroundColor(Color.parseColor("#A569BD"));
        myButton.setText("Sign in");

        // create username input
        EditText userName = new EditText(this);
        userName.setId(2);


        RelativeLayout.LayoutParams myButtonDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        RelativeLayout.LayoutParams userNameDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        userNameDetails.addRule(RelativeLayout.ABOVE, myButton.getId());
        userNameDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        userNameDetails.setMargins(0,0,0,50);

        myButtonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        myButtonDetails.addRule(RelativeLayout.CENTER_VERTICAL);

        // converting dip value into pixel value
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics());
        userName.setWidth(px);

        // add button widget to layout
        myLayout.addView(myButton, myButtonDetails);
        myLayout.addView(userName, userNameDetails);

        // set this activities content to this view
        setContentView(myLayout);
    }
}
