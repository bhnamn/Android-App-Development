package borhanamini.intentexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class Bacon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacon);

        Bundle applesData = getIntent().getExtras();
        if (applesData == null){
            return;
        }
        String applesMessage = applesData.getString("applesMessage");
        final TextView Messages = (TextView) findViewById(R.id.MessageArea);
        Messages.setText(applesMessage);
    }

    public void onClick(View view){
        Intent myIntent = new Intent(this, Apples.class);
        startActivity(myIntent);
    }
}
