package borhanamini.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    private static final int SIMPLE_DIALOG = 0;
    private static final int CUSTOM_DIALOG = 1;

    private static final String CUSTOM_DIALOG_FRAGMENT = "customDialogFragment";

    private int count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onOldAlertDialogClick(View view) {
        showDialog(SIMPLE_DIALOG);
    }

    public void onOldCustomDialogClick(View view) {
        showDialog(CUSTOM_DIALOG);
    }

    public void onNewCustomDialogClick(View view) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        DialogFragment df = new MyCustomDialogFragment();
        df.show(ft, CUSTOM_DIALOG_FRAGMENT);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;

        switch(id) {
            case SIMPLE_DIALOG:
                dialog = new AlertDialog.Builder(this).setTitle("My Alert Dialog")
                        .setMessage("Pancakes or Waffles?")

                        .setNegativeButton("Boring Pancakes", dialogListener)
                        .setPositiveButton("Awesome Waffles!!", dialogListener)

                        .create();
                break;
            case CUSTOM_DIALOG:
                dialog = new Dialog(this);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("My Custom Dialog");
                ((Button)dialog.findViewById(R.id.cancel_btn)).setOnClickListener(customDialogClickListener);
                ((Button)dialog.findViewById(R.id.okay_btn)).setOnClickListener(customDialogClickListener);
                break;
        }

        return dialog;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id)
        {
            case SIMPLE_DIALOG:
                count++;
                dialog.setTitle("Dialog "+count);
                break;
        }
    }

    private View.OnClickListener customDialogClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            removeDialog(CUSTOM_DIALOG);
        }
    };

    private DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener()  {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case Dialog.BUTTON_NEGATIVE:
                    Toast.makeText(MainActivity.this, "Pancakes? Really?", Toast.LENGTH_LONG).show();
                    break;
                case Dialog.BUTTON_POSITIVE:
                    Toast.makeText(MainActivity.this, "Waffles are where it's at!", Toast.LENGTH_LONG).show();
                    break;
            }
            removeDialog(SIMPLE_DIALOG);
        }
    };
}
