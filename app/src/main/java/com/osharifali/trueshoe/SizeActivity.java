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

public class SizeActivity extends AppCompatActivity {

    private static final int MAX_IMAGE_DIMENSION = 1920;
    DrawView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size);
        img = (DrawView) findViewById(R.id.img);
        Button save = (Button) findViewById(R.id.cardCap);
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
            options.inScaled = false;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }}
    protected void saveCardSize(View view) {
        Point[] points = img.points;
        ArrayList<Integer> xP = new ArrayList<>();
        ArrayList<Integer> yP = new ArrayList<>();
        for (Point p : points) {
            xP.add(p.x);
            yP.add(p.y);
        }

        int width = Collections.max(xP) - Collections.min(xP);
        int height = Collections.max(yP) - Collections.min(yP);


        Log.d("CARD" , "wid: " + width + " height: " + height);

        Intent foot = new Intent(this, ShoeSizeActivity.class);
        foot.putExtra("cardWidth", width);
        foot.putExtra("cardHeight", height);
        startActivity(foot);

    }

}
