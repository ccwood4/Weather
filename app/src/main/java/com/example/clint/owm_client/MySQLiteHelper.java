package com.example.clint.owm_client;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "UserDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_USER_TABLE = "CREATE TABLE users ( " +
                "username STRING PRIMARY KEY, " +
                "favone INTEGER, "+
                "favtwo INTEGER, "+
                "favthree INTEGER, "+
                "favfour INTEGER, "+
                "favfive INTEGER )";

        // create user table
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS users");

        // create fresh users table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */

    // Users table name
    private static final String TABLE_USERS = "users";

    // Users Table Columns names
    private static final String KEY_USERNAME = "username";
    private static final String KEY_FAVONE = "favone";
    private static final String KEY_FAVTWO = "favtwo";
    private static final String KEY_FAVTHREE = "favthree";
    private static final String KEY_FAVFOUR = "favfour";
    private static final String KEY_FAVFIVE = "favfive";

    private static final String[] COLUMNS = {KEY_USERNAME,KEY_FAVONE,KEY_FAVTWO,KEY_FAVTHREE,KEY_FAVFOUR,KEY_FAVFIVE};

    public void addUser(UserModel user){
        Log.d("addUser", user.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername()); // get username
        values.put(KEY_FAVONE, user.getFavone()); // get favs
        values.put(KEY_FAVTWO, user.getFavtwo()); // get favs
        values.put(KEY_FAVTHREE, user.getFavthree()); // get favs
        values.put(KEY_FAVFOUR, user.getFavfour()); // get favs
        values.put(KEY_FAVFIVE, user.getFavfive()); // get favs

        // 3. insert
        db.insert(TABLE_USERS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public UserModel getUser(String id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_USERS, // a. table
                        COLUMNS, // b. column names
                        " username = ?", // c. selections
                        new String[] { id }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
//        if (cursor != null)
//            cursor.moveToFirst();

        // 4. build user object
        UserModel user = new UserModel();
        if (cursor.moveToFirst()) {
            user.setUsername(cursor.getString(0));
            user.setFavone(Integer.parseInt(cursor.getString(1)));
            user.setFavtwo(Integer.parseInt(cursor.getString(2)));
            user.setFavthree(Integer.parseInt(cursor.getString(3)));
            user.setFavfour(Integer.parseInt(cursor.getString(4)));
            user.setFavfive(Integer.parseInt(cursor.getString(5)));
        }
        else {
            user.setUsername("none");
            user.setFavone(0);
            user.setFavtwo(0);
            user.setFavthree(0);
            user.setFavfour(0);
            user.setFavfive(0);
        }

        Log.d("getUser("+id+")", user.toString());

        // 5. return user
        return user;
    }

    // Get All Books
    public List<UserModel> getAllUsers() {
        List<UserModel> users = new LinkedList<UserModel>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_USERS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build user and add it to list
        UserModel user = null;
        if (cursor.moveToFirst()) {
            do {
                user = new UserModel();
                user.setUsername(cursor.getString(0));
                user.setFavone(Integer.parseInt(cursor.getString(1)));
                user.setFavtwo(Integer.parseInt(cursor.getString(2)));
                user.setFavthree(Integer.parseInt(cursor.getString(3)));
                user.setFavfour(Integer.parseInt(cursor.getString(4)));
                user.setFavfive(Integer.parseInt(cursor.getString(5)));

                // Add book to books
                users.add(user);
            } while (cursor.moveToNext());
        }

        Log.d("getAllUsers()", users.toString());

        // return books
        return users;
    }

    // Updating single User
    public int updateUser(UserModel user) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername()); // get username
        values.put("favone", user.getFavone()); // get favs
        values.put("favtwo", user.getFavtwo()); // get favs
        values.put("favthree", user.getFavthree()); // get favs
        values.put("favfour", user.getFavfour()); // get favs
        values.put("favfive", user.getFavfive()); // get favs

        // 3. updating row
        int i = db.update(TABLE_USERS, //table
                values, // column/value
                KEY_USERNAME + " = ?", // selections
                new String[] { String.valueOf(user.getUsername()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single user
    public void deleteUser(UserModel user) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_USERS,
                KEY_USERNAME + " = ?",
                new String[] { String.valueOf(user.getUsername()) });

        // 3. close
        db.close();

        Log.d("deleteUser", user.toString());

    }
}