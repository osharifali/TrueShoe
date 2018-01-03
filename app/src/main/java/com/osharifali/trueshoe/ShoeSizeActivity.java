package com.osharifali.trueshoe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ShoeSizeActivity extends AppCompatActivity {

    FootDrawView img;

    final double ID_WIDTH = 2.125;
    final double ID_HEIGHT = 3.370;

    int cardHeight;
    int cardWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_size);

        Intent intent = getIntent();
        cardHeight = intent.getIntExtra("cardHeight" , 0);
        cardWidth = intent.getIntExtra("cardWidth", 0);
        Log.d("FOOT" , "cardwid: " + cardWidth + " cardheight: " + cardHeight);
        Button save = (Button) findViewById(R.id.shoeCap);



        img = (FootDrawView) findViewById(R.id.img2);
        img.destroyDrawingCache();
        String path = "/storage/emulated/0/Android/data/com.osharifali.trueshoe/files/Pictures/Image-img.png";
        Bitmap b = getBitmap(path);
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        Bitmap b2 = rotateBitmap(b, orientation);
        Drawable d = new BitmapDrawable(getResources(), b2);
        img.setBackground(d);
    }


    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {

        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        }
        catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }
    public Bitmap getBitmap(String path) {
        try {
            Bitmap bitmap=null;
            File f= new File(path);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }}
    protected void saveFootSize(View view) {
        Point[] points = img.points;
        ArrayList<Integer> xP = new ArrayList<>();
        ArrayList<Integer> yP = new ArrayList<>();
        for (Point p : points) {
            xP.add(p.x);
            yP.add(p.y);
        }

        int width = Collections.max(xP) - Collections.min(xP);
        int height = Collections.max(yP) - Collections.min(yP);
        Log.d("FOOT" , "wid: " + width + " height: " + height);

        double footHeight = (ID_HEIGHT * height) / cardHeight;
        double footWidth = (ID_WIDTH * width) / cardWidth;
        Log.d("Inches", "Foot height: " + footHeight +" Foot width:" + footWidth);

        Intent stats = new Intent(this, StatsActivity.class);
        stats.putExtra("footWidth", footWidth);
        stats.putExtra("footHeight", footHeight);
        startActivity(stats);
    }
}
