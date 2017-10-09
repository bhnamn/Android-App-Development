package borhanamini.homeworkmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by borha on 9/19/2017.
 */

public class HermesActivity extends Activity {
    public static final String MY_EXTRA = "myExtra";
    public static final int EXTRA_REQUEST = 0;
    private EditText hermesEitText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hermes_view);

        hermesEitText = (EditText) findViewById(R.id.hermes_edit_text);
        Intent i = getIntent();
        if (i.hasExtra(MY_EXTRA))
            hermesEitText.setText(i.getStringExtra(MY_EXTRA));
    }

    public void onFinishClick(View view){
        String text = hermesEitText.getText().toString();
        Intent i = new Intent();
        i.putExtra(MY_EXTRA, text);
        setResult(RESULT_OK, i);
        finish();
    }
}
