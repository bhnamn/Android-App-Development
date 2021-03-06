package borhanamini.lists;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import borhanamini.lists.MainActivity.MyData;
import android.widget.TextView;

/**
 * Created by borha on 9/24/2017.
 */

public class MyListAdapter extends ArrayAdapter<MyData> {

    private LayoutInflater inflater;

    public MyListAdapter(Context context, MyData[] data){
        super(context, R.layout.my_list_item, data);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup root){
        View view = convertView;
        if (view == null){
            view = inflater.inflate(R.layout.my_list_item, null);
        }
        MyData data = getItem(position);

        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(data.name);

        View imageView = view.findViewById(R.id.color);
        int color = data.clicked ? Color.RED : Color.BLUE;
        imageView.setBackgroundColor(color);

        return view;
    }
}
