package com.myapp.cktscore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteClass extends SQLiteOpenHelper {

	public static final String DATABASE_ID = "id1";
	public static final String DATABASE_NAME = "Score";
	public static final String DATABASE_TABLE = "ScoreDetail";
	public static final String DATABASE_TABLEC = "ChasingDetails";
	public static final String TEAM_DETAILS = "teamname";
	public static final int DATABASE_VERSION = 11;

	public static final String KEY_ID = "_id";
	public static final String TEAM_NAME = "team_name";
	public static final String TOTAL_OVERS = "total_overs";
	public static final String TOTAL_SCORE = "total_score";
	public static final String WICKETS = "wickets";
	public static final String BATTING_TEAM = "battingteam";
	public static final String BOWLING_TEAM = "bowlingteam";

	static final String DATABASE_CREATE = "create table ScoreDetail (_id integer primary key autoincrement, team_name text not null, total_overs integer not null, total_score integer not null, wickets integer not null);";
	static final String DATABASE_CREATE2 = "create table ChasingDetails (_id integer primary key autoincrement, team_name text not null, total_overs text not null, total_score integer not null, wickets integer not null);";
	static final String DATABASE_CREATE1 = "create table teamname (_id integer primary key autoincrement, battingteam text not null, bowlingteam not null);";

	// static final String GET_SINGLEDATA =
	// "select 8 from ScoreDetails ORDER BY _id DESC ";

	public MySQLiteClass(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/*
		 * String CREATE_TABLE = "CREATE TABLE " + DATABASE_TABLE + "(" + KEY_ID
		 * + " integer primary key autoincrement " + TEAM_NAME + " TEXT " +
		 * TOTAL_OVERS + " INTEGER " + TOTAL_SCORE + " INTEGER " + ")";
		 */
		db.execSQL(DATABASE_CREATE);
		db.execSQL(DATABASE_CREATE1);
		db.execSQL(DATABASE_CREATE2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE);
		onCreate(db);

	}

	public void addScore(Boolean condition, String Database_name, String name,
			double Overs, int score, int wicket) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TEAM_NAME, name);
		values.put(TOTAL_OVERS, Overs);
		values.put(TOTAL_SCORE, score);
		values.put(WICKETS, wicket);
		if (condition) {
			db.insert(Database_name, null, values);
		} else {
			db.insert(Database_name, null, values);
		}
	}

	public void addTeam(String batting, String bowling) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(BATTING_TEAM, batting);
		values.put(BOWLING_TEAM, bowling);
		db.insert(TEAM_DETAILS, null, values);
	}

	public Cursor getScore(int rowid, String DataBase_table) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor mcursor = db.query(DataBase_table, null, KEY_ID + "="
				+ rowid,null,null,null,null);
//		Cursor mcursor = db.query(true, DataBase_table, null, KEY_ID + "="
//				+ rowid, null, null, null, null, null, null);
		if (mcursor != null) {
			mcursor.moveToFirst();
		}
		return mcursor;
	}

	public int getProfilesCount(String DataBase_table) {
		String countQuery = "SELECT  * FROM " + DataBase_table;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int cnt = cursor.getCount();
		cursor.close();
		return cnt;
	}

	public void DeleteAll() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(DATABASE_TABLE, null, null);
		db.close();
	}

	public void DeleteSigle(int rowid) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(DATABASE_TABLE, KEY_ID + "=" + rowid, null);
	}

}
