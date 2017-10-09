package borhanamini.homeworkmanager;

import android.app.Application;

/**
 * Created by borha on 9/22/2017.
 */

public class MyApplication extends Application {
    public String defaultString;

    @Override
    public void onCreate(){
        super.onCreate();

        defaultString = "some default text";
    }
}
