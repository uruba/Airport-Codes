package cz.uruba.airportcodes.helpers;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Vaclav on 27.12.2014.
 */

public class AirportHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "airports.sqlite";
    private File DB_FILE;
    private static AirportHelper instance;

    private Context ctx;
    private SQLiteDatabase db;

    private boolean dbInvalid = false;


    public static AirportHelper getInstance(Context ctx) {
        if (instance == null) {
            instance = new AirportHelper(ctx.getApplicationContext());
        }

        return instance;
    }

    private AirportHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);

        DB_FILE = ctx.getDatabasePath(DB_NAME);
        this.ctx = ctx;

        try {
            db = getReadableDatabase();

            if (dbInvalid) {
                copyDBFromAssets();
            }

        } catch (SQLiteException e) {

        } finally {
            closeDBifOpen();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbInvalid = true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void copyDBFromAssets() {
        AssetManager assetManager = ctx.getResources().getAssets();
        InputStream in = null;
        OutputStream out = null;

        try {
            in = assetManager.open(DB_NAME);
            out = new FileOutputStream(DB_FILE);
            byte[] buffer = new byte[1024];
            int read;

            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        } catch (IOException e) {

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {

                }
            }

            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {

                }
            }
        }

        setDBVersion();
    }

    private void setDBVersion() {
        try {
            db = SQLiteDatabase.openDatabase(DB_FILE.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
            db.execSQL("PRAGMA user_version = " + DB_VERSION);
        } catch (SQLiteException e) {

        } finally {
            closeDBifOpen();
        }
    }

    private void closeDBifOpen(){
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
