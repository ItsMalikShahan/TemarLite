package com.example.practise.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.example.practise.Activity.ProfileActivity;
import com.example.practise.Model.ProfileDetail;

public class DBManager {

    DatabaseHelper dbHelper;
    Context context;
    SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = DatabaseHelper.getInstance(context);
//        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void loginInsert(String id, String username, String fullName, String firstName,
                            String organization, String branchaddress, String branchName, String imagePath, String branchId,
                            String branchtelNo, String branchemail, String token, String mrno,
                            String country, String state, String city) {

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.ID, id);
        cv.put(DatabaseHelper.USERNAME, username);
        cv.put(DatabaseHelper.FULL_NAME, fullName);
        cv.put(DatabaseHelper.FIRST_NAME, firstName);
        cv.put(DatabaseHelper.BRANCH_NAME, branchName);
        cv.put(DatabaseHelper.IMAGE_PATH, imagePath);
        cv.put(DatabaseHelper.BRANCH_ID, branchId);
        cv.put(DatabaseHelper.ORGANIZATION, organization);
        cv.put(DatabaseHelper.BRANCH_ADDRESS, branchaddress);
        cv.put(DatabaseHelper.BRANCH_TEL_NO, branchtelNo);
        cv.put(DatabaseHelper.BRANCH_EMAIL, branchemail);
        cv.put(DatabaseHelper.TOKEN, token);
        cv.put(DatabaseHelper.MR_NUMBER, mrno);
        cv.put(DatabaseHelper.COUNTRY, country);
        cv.put(DatabaseHelper.STATE, state);
        cv.put(DatabaseHelper.CITY, city);

        database.insert(DatabaseHelper.TABLE_LOGIN, null, cv);
    }

    public Cursor getProfileValue() {
        String[] ColumnArgs = new String[]{DatabaseHelper.ID, DatabaseHelper.TOKEN, DatabaseHelper.BRANCH_ID};
        Cursor cursor = database.query(DatabaseHelper.TABLE_LOGIN, ColumnArgs, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    public void profileInsert(
//            String Status, String Title, String MiddleName, String Gender, String RelationshipTypeId, String RelationshipTypeName
//            , String GuardianName, String MaritalStatusId, String MaritalStatus, String DateOfBirth, String ReferenceId, String PicturePath, String CountryId,
//                              String Address, String CellNumber, String TelephoneNumber, String Email, String Occupation, String NOKFirstName,
//                              String NOKLastName, String NOKCNICNumber, String NOKRelation, String BloodGroup, String Age, String FirstName, String LastName,
//                              String CNICNumber
//           String Country, String State, String City
            ProfileDetail profileDetail
    ) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.ID, profileDetail.Id);
        cv.put(DatabaseHelper.TITLE, profileDetail.Title);
        cv.put(DatabaseHelper.MIDDLE_NAME, profileDetail.MiddleName);
        cv.put(DatabaseHelper.GENDER, profileDetail.Gender);
        cv.put(DatabaseHelper.RELATIONSHIP_TYPE_ID, profileDetail.RelationshipTypeId);
        cv.put(DatabaseHelper.RELATIONSHIP_NAME, profileDetail.RelationshipTypeName);
        cv.put(DatabaseHelper.GUARDIAN_NAME, profileDetail.GuardianName);
        cv.put(DatabaseHelper.MARITAL_STATUS_ID, profileDetail.MaritalStatusId);
        cv.put(DatabaseHelper.MARITAL_STATUS, profileDetail.MaritalStatus);
        cv.put(DatabaseHelper.DATE_OF_BIRTH, profileDetail.DateOfBirth);
        cv.put(DatabaseHelper.REFERENCE_ID, profileDetail.ReferenceId);
        cv.put(DatabaseHelper.PICTURE_PATH, profileDetail.PicturePath);
        cv.put(DatabaseHelper.COUNTRY_ID, profileDetail.CountryId);
        cv.put(DatabaseHelper.ADDRESS, profileDetail.Address);
        cv.put(DatabaseHelper.CELL_NUMBER, profileDetail.CellNumber);
        cv.put(DatabaseHelper.TELEPHONE_NUMBER, profileDetail.TelephoneNumber);
        cv.put(DatabaseHelper.EMAIL, profileDetail.Email);
        cv.put(DatabaseHelper.OCCUPATION, profileDetail.Occupation);
        cv.put(DatabaseHelper.NOK_FIRSTNAME, profileDetail.NOKFirstName);
        cv.put(DatabaseHelper.NOK_LASTNAME, profileDetail.NOKLastName);
        cv.put(DatabaseHelper.NOK_CNICNUMBER, profileDetail.NOKCNICNumber);
        cv.put(DatabaseHelper.NOK_RELATION, profileDetail.NOKRelation);
        cv.put(DatabaseHelper.BLOODGROUP, profileDetail.BloodGroup);
        cv.put(DatabaseHelper.AGE, profileDetail.Age);
        cv.put(DatabaseHelper.FIRST_NAME, profileDetail.FirstName);
        cv.put(DatabaseHelper.LAST_NAME, profileDetail.LastName);
        cv.put(DatabaseHelper.CNIC_NUMBER, profileDetail.CNICNumber);
        cv.put(DatabaseHelper.COUNTRY, profileDetail.Country);
        cv.put(DatabaseHelper.STATE, profileDetail.StateOrProvince);
        cv.put(DatabaseHelper.CITY, profileDetail.City);

        Cursor cursor = database.query(DatabaseHelper.TABLE_PROFILE, null, DatabaseHelper.ID + " =?",new String[]{profileDetail.Id}, null, null, null);
        if (cursor.moveToFirst()){
            return;
        }else {
            database.insert(DatabaseHelper.TABLE_PROFILE, null, cv);

        }

    }

    public Cursor FetchPersonalViewValue() {
        String[] ColumnValues = new String[]{DatabaseHelper.TITLE, DatabaseHelper.FIRST_NAME, DatabaseHelper.MIDDLE_NAME, DatabaseHelper.LAST_NAME,
                DatabaseHelper.CNIC_NUMBER, DatabaseHelper.REFERENCE_ID, DatabaseHelper.BLOODGROUP, DatabaseHelper.GENDER, DatabaseHelper.AGE,
                DatabaseHelper.DATE_OF_BIRTH, DatabaseHelper.MARITAL_STATUS, DatabaseHelper.OCCUPATION};
        Cursor cursor = database.query(DatabaseHelper.TABLE_PROFILE, ColumnValues, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor FetchContactViewValues() {
        String[] columnValues = new String[]{DatabaseHelper.COUNTRY, DatabaseHelper.STATE, DatabaseHelper.CITY, DatabaseHelper.ADDRESS,
                DatabaseHelper.CELL_NUMBER, DatabaseHelper.TELEPHONE_NUMBER, DatabaseHelper.EMAIL};
        Cursor cursor = database.query(DatabaseHelper.TABLE_PROFILE, columnValues, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Log.e("Cursor size", "FetchContactViewValue: " + cursor.getCount());

        }
        return cursor;
    }

    public void DeleteData() {
        database.execSQL(" delete from "+DatabaseHelper.TABLE_PROFILE);
        database.execSQL(" delete from " +DatabaseHelper.TABLE_LOGIN);

    }

    public void UpdatePersonal(
            String Id, String Title, String MiddleName, String Gender,
            String BloodGroup, String Age, String FirstName, String LastName,
            String CNICNumber, String ReferenceId, String MaritalStatus, String DateOfBirth, String Occupation
            ) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.ID, Id);
        cv.put(DatabaseHelper.TITLE, Title);
        cv.put(DatabaseHelper.FIRST_NAME, FirstName);
        cv.put(DatabaseHelper.MIDDLE_NAME, MiddleName);
        cv.put(DatabaseHelper.GENDER, Gender);
        cv.put(DatabaseHelper.AGE, Age);
        cv.put(DatabaseHelper.LAST_NAME, LastName);
        cv.put(DatabaseHelper.CNIC_NUMBER, CNICNumber);
        cv.put(DatabaseHelper.REFERENCE_ID, ReferenceId);
        cv.put(DatabaseHelper.BLOODGROUP, BloodGroup);
        cv.put(DatabaseHelper.DATE_OF_BIRTH, DateOfBirth);
        cv.put(DatabaseHelper.MARITAL_STATUS, MaritalStatus);
        cv.put(DatabaseHelper.OCCUPATION, Occupation);

        String[] columnName = new String[]{Id};

        String sqlQuery = "select * from " + DatabaseHelper.TABLE_PROFILE + " where " + DatabaseHelper.ID + " =?";
//        Log.e("QueryUpdate", "UpdatePersonal: " );
        Cursor cursor = database.rawQuery(sqlQuery, columnName);
        if (cursor.moveToFirst()) {
            Toast.makeText(context, "Data Updated", Toast.LENGTH_SHORT).show();
            database.update(DatabaseHelper.TABLE_PROFILE, cv, DatabaseHelper.ID + " =?", columnName);
        }
    }
}



