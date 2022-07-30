package moneshtilavi.com.python;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity<as> extends AppCompatActivity {

    private InputStream img;
    private Bitmap bitmap;
    private String imagestring;
    PyObject pyobj;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File image = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "adisAssets/grayFrame21.jpg");
        String myDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "adisAssets").toString();

        // "context" must be an Activity, Service or Application object from your app.
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
            Log.i("monu", "python ready");
        }

//        try {
//            AssetManager as = this.getAssets();
//            img = as.open("img.jpg");
//            bitmap = BitmapFactory.decodeStream(img);
//            Log.i("monu", "onCreate: "+bitmap);
//            imagestring = getStringImage(bitmap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Python py = Python.getInstance();
        pyobj = py.getModule("myscript");
        PyObject obj = pyobj.callAttr("main",image,myDirectory);
        tv = (TextView)findViewById(R.id.tv);
//        tv.setText(obj.toString());
//        muzzleMatch();
    }

    private String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return encodedImage;
    }


    public void muzzleMatch()
    {
        File image = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "adisAssets/19.jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(image.toString());
        String firstimage = getStringImage(bitmap);
        String cattelName = null;
        int matchPoint = 0;
        String myDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "adisAssets").toString();
        File directories = new File(myDirectory);
        File[] files = directories.listFiles();
        for (File inFile : files) {
            if (inFile.isDirectory()) {
                Log.i("monu", "Directory: "+inFile.getName());
                File imagePath = new File(inFile+"/newFrames/grayscale");
                File[] images = imagePath.listFiles();
                if(images != null)
                {
                    for (File img : images) {
                        if (img.isFile()) {
                            bitmap = BitmapFactory.decodeFile(img.toString());
                            imagestring = getStringImage(bitmap);
                            PyObject obj = pyobj.callAttr("main",firstimage,imagestring);
                            Log.i("monu", "Cattel Name "+ cattelName +" points = "+obj.toString());
                            if(Integer.parseInt(obj.toString()) > matchPoint)
                            {
                                cattelName = inFile.getName();
                                matchPoint = Integer.parseInt(obj.toString());
                            }
                        }
                    }
                }
            }
        }
        Log.i("monu", "Cattel Name "+ cattelName +" points = "+matchPoint);
        tv.setText("Cattel Name "+ cattelName +" points = "+matchPoint);
    }
}