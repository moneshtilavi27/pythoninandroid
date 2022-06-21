package moneshtilavi.com.python;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tv1 = (TextView)findViewById(R.id.textView);

        // "context" must be an Activity, Service or Application object from your app.
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

//        Python py = Python.getInstance();
//        PyObject pyobj = py.getModule("");
//        PyObject obj = pyobj.callAttr("main");
        tv1.setText("Monesh");
        // tv.setText(obj.toString());
    }
}