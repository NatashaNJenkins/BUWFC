package com.example.buwfc_test;

import java.sql.SQLException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

	public static final String KEY_ROWID = "id";
	public static final String KEY_TITLE = "title";
	public static final String KEY_EVENTDATE = "eventdate";
	public static final String KEY_DETAILS = "details";
	
	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME = "BUWFCDB";
	private static final String DATABASE_TABLE = "events";
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE = 
			"create table if not exists events (id integer primary key autoincrement,"
			+ "title VARCHAR not null, eventdate date, details VARCHAR);";
	
	private final Context context; 
	
	private static DatabaseHelper DBHelper;
	private static SQLiteDatabase db;
	
	public DBAdapter(Context ctx){
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}
	
	public static class DatabaseHelper extends SQLiteOpenHelper{
		
		DatabaseHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);		
		}
		
		@Override
		public void onCreate(SQLiteDatabase db){
			try {
				db.execSQL(DATABASE_CREATE);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVesion, int newVersion){
			Log.w(TAG, "Upgrading database from version " + oldVesion + " to " + newVersion + " which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS events");
			onCreate(db);
			
			//what you should really do is move the existing data into a new table you created THEN drop the old table
			//something to do on upgrading it
			
		}
		
		// open db
		public DatabaseHelper open() throws SQLException{
			db = DBHelper.getWritableDatabase();
			return this;			
		}
		
		//close db
		public void close(){
			DBHelper.close();
		}
		
		//add a new record
		public long insertRecord(String title, String eventdate, String details){
			ContentValues initialValues = new ContentValues();
			initialValues.put(KEY_TITLE, title);
			initialValues.put(KEY_EVENTDATE, eventdate);
			initialValues.put(KEY_DETAILS, details);
			return db.insert(DATABASE_TABLE, null, initialValues);
		}
		
		// gets all records 
		public Cursor getAllRecords(){
			return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_TITLE, KEY_EVENTDATE, KEY_DETAILS}, null, null, null, null, null);
		}
		
		//get a specific record
		public Cursor getRecord(long rowId) throws SQLException{
			Cursor mCursor =
					db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_TITLE, KEY_EVENTDATE, KEY_DETAILS}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		
	}
	
	
}
