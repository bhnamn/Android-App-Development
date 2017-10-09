package borhanamini.homeworkmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText myEditText;
    private Button myButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myEditText = (EditText) findViewById(R.id.my_edit_text);

        myButton = (Button) findViewById(R.id.my_button);
        String next = getString(R.string.next);
        myButton.setText(next);

        MyApplication app = (MyApplication) getApplication();
        myEditText.setHint(app.defaultString);
    }

    public void handleMyButtonClick(View view){
        Intent myIntent = new Intent(MainActivity.this, HermesActivity.class);
        String userMessage = myEditText.getText().toString();
        myIntent.putExtra(HermesActivity.MY_EXTRA, userMessage);
        //startActivity(myIntent);
        startActivityForResult(myIntent, HermesActivity.EXTRA_REQUEST);
    }

    public void handleSendEmailClick(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"predefined@email"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "predefined Subject");
        startActivity(Intent.createChooser(emailIntent, "Email"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case HermesActivity.EXTRA_REQUEST:
                if (requestCode == RESULT_OK){
                    String stringExtra = data.getStringExtra(HermesActivity.MY_EXTRA);
                    myEditText.setText(stringExtra);
                }
                break;
        }
    }
}
