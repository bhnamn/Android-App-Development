package borhanamini.eventlistenerandcallbackmethod;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = (Button) findViewById(R.id.button1);
        TextView changeableText = (TextView) findViewById(R.id.myTextView);
        String textAfterClick = String.valueOf(changeableText.getText());
        String textBeforeClick = String.valueOf(R.string.textView_beforeClick);

        myButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View view){
                        TextView changeableText = (TextView) findViewById(R.id.myTextView);
                        changeableText.setText(R.string.textView_afterClick);
                    }
                }
        );

        if (textAfterClick == textBeforeClick){
            myButton.setOnClickListener(
                    new Button.OnClickListener(){
                        public void onClick(View view){
                            TextView changeableText = (TextView) findViewById(R.id.myTextView);
                            changeableText.setText(R.string.textView_beforeClick);
                        }
                    }
            );
        }

        myButton.setOnLongClickListener(
                new Button.OnLongClickListener(){
                    public boolean onLongClick(View v){
                        TextView changeableText = (TextView) findViewById(R.id.myTextView);
                        changeableText.setText(R.string.textView_beforeClick);
                        return true;
                    }
                }
        );

    }
}
