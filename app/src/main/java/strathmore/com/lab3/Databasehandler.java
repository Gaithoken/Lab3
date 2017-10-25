package strathmore.com.lab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ken on 10/24/2017.
 */
public class Databasehandler extends SQLiteOpenHelper {
    //All Static variables
    //Database Version
    private static final int DATABASE_VERSION = 1;


    //Database Name
    private static final String DATABASE_NAME = "contactsManager";

    //Contacts table name
    private static final String TABLE_CONTACTS = "contacts";
    //Newspaper table name
    private static final String TABLE_NEWSPAPER = "newspaper";


    //Newspaper/contacts table Columns names

    private static final String KEY_NEWSPAPER_NAME = "newspaper_name";
    private static final String KEY_NEWSPAPER_ID = "newspaper_id";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public Databasehandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_NEWSPAPER_TABLE = "CREATE TABLE " + TABLE_NEWSPAPER + "("
                + KEY_NEWSPAPER_ID + " INTEGER PRIMARY KEY," + KEY_NEWSPAPER_NAME + " TEXT" + ")";
        db.execSQL(CREATE_NEWSPAPER_TABLE);
    }

    //Updating database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWSPAPER);

        //Create tables again
        onCreate(db);
    }

    //Adding new contact
    public void addContact(Contacts contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); //Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); //Contact Phone Number

        //Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); //Closing database connection
    }

    public void addNewspaper(Newspaper newspaper) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NEWSPAPER_NAME, newspaper.getNewspaper_name());
        //Inserting Row
        db.insert(TABLE_NEWSPAPER, null, values);
        db.close();
    }

    //Getting single contact
    public Contacts getContacts(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                        KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contacts contacts = new Contacts(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return contacts;
    }

    public Newspaper getNewspaper(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NEWSPAPER, new String[]{KEY_NEWSPAPER_ID,
                        KEY_NEWSPAPER_NAME}, KEY_NEWSPAPER_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Newspaper newspaper = new Newspaper(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));

        return newspaper;
    }
    //Getting All Contacts
    public List<Contacts> getAllContacts() {
        List<Contacts> contactsList = new ArrayList<Contacts>();
        //Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;



        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contacts contacts = new Contacts();
                contacts.setId(Integer.parseInt(cursor.getString(0)));
                contacts.setName(cursor.getString(1));
                contacts.setPhoneNumber(cursor.getString(2));
                //Adding contact to list
                contactsList.add(contacts);
            } while (cursor.moveToNext());
        }
        //return contact list
        return contactsList;
    }


    //Getting All Newspapers
    public List<Newspaper> getAllNewspaper() {
        List<Newspaper> newspaperList = new ArrayList<Newspaper>();
        //Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_NEWSPAPER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Newspaper newspaper = new Newspaper();
                newspaper.setNewspaper_id(Integer.parseInt(cursor.getString(0)));
                newspaper.setNewspaper_name(cursor.getString(1));
                //Adding newspaper to list
                newspaperList.add(newspaper);
            } while (cursor.moveToNext());
        }

        return newspaperList;
    }

    //Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();
    }

    public int getNewspaperCount() {
        String countQuery = "SELECT * FROM " + TABLE_NEWSPAPER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();
    }

    //Updating single contact
    public int updateContacts(Contacts contacts) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contacts.getName());
        values.put(KEY_PH_NO, contacts.getPhoneNumber());

        //updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contacts.getId())});
    }

    public int updateNewspaper(Newspaper newspaper) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NEWSPAPER_NAME, newspaper.getNewspaper_name());

        //updating row
        return db.update(TABLE_NEWSPAPER, values, KEY_NEWSPAPER_ID + "=?", new String[]{String.valueOf(newspaper.newspaper_id)});
    }


    //Deleting single contact
    public void deleteContacts(Contacts contacts) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(contacts.getId())});
        db.close();
    }

    public void deleteNewspaper(Newspaper newspaper) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NEWSPAPER, KEY_NEWSPAPER_ID + "=?", new String[]{String.valueOf(newspaper.getNewspaper_id())});
        db.close();
        {
        }


    }


}
