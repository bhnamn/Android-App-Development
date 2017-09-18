package borhanamini.intentexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class Apples extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apples);
    }

    public void onClick(View view){
        Intent myIntent = new Intent(this, Bacon.class);

        final EditText applesInput = (EditText) findViewById(R.id.ApplesInput);
        String userMessage = applesInput.getText().toString();
        myIntent.putExtra("applesMessage", userMessage);

        startActivity(myIntent);
    }
}
