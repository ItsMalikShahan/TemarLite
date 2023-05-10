package com.example.practise.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static DatabaseHelper instance;
    public static final String DB_NAME = "LoginData.DB";
    static final int VERSION = 10;


    public static String TABLE_LOGIN = "patientLogin";
    public static final String ID = "Id_";
    public static final String USERNAME = "Username";
    public static final String FULL_NAME = "FullName";
    public static final String FIRST_NAME = "FirstName";
    public static final String BRANCH_NAME = "BranchName";
    public static final String LAST_NAME = "LastName";
    public static final String IMAGE_PATH = "ImagePath";
    public static final String ORGANIZATION = "OrganizationPicturePath";
    public static final String BRANCH_ID = "BranchId";
    public static final String BRANCH_ADDRESS = "BranchAddress";
    public static final String BRANCH_TEL_NO = "BranchTelNo";
    public static final String BRANCH_EMAIL = "BranchEmail";
    public static final String TOKEN = "Token";
    //    public static final String STATUS = "Status";
    public static final String MR_NUMBER = "mrno";
    public static final String COUNTRY = "country";
    public static final String STATE = "state";
    public static final String CITY = "city";
    public static final String CNIC_NUMBER = "cnicNumber";
    public static final String PASSPORT = "passport";
    public static final String GENDER = "gender";
    public static final String PANEL_EXPIRY_DATE = "panel_expiry";
    public static final String PANEL_ORGANIZATION_NAME = "panel_organization";
    public static final String REGISTRATION_DATE = "registrationDate";

    public static final String TABLE_PROFILE = "patientProfile";
    public static final String STATUS = "status";
    public static final String TITLE = "title";
    public static final String MIDDLE_NAME = "middleName";
    public static final String GENDER_ID = "gender_id";
    public static final String RELATIONSHIP_TYPE_ID = "relation_id";
    public static final String RELATIONSHIP_NAME = "relation_name";
    public static final String GUARDIAN_NAME = "guardian_name";
    public static final String MARITAL_STATUS_ID = "marital_id";
    public static final String MARITAL_STATUS = "marital_status";
    public static final String DATE_OF_BIRTH = "date_of_birth";
    public static final String REFERENCE_ID = "reference_id";
    public static String PICTURE_PATH = "picture_path";
    public static String COUNTRY_ID = "country_id";
    public static String ADDRESS = "address";
    public static String CELL_NUMBER = "cell_number";
    public static String TELEPHONE_NUMBER = "telephone_number";
    public static String EMAIL = "email";
    public static String OCCUPATION = "occupation";
    public static String NOK_FIRSTNAME = "nok_firstname";
    public static String NOK_LASTNAME = "nok_lastname";
    public static String NOK_RELATION = "nok_relation";
    public static String NOK_CNICNUMBER = "nok_cnic";
    public static String BLOODGROUP = "blood_group";
    public static String AGE = "age";
//


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private static final String CREATE_TABLE_LOGIN = "create table " + TABLE_LOGIN + "(" + ID + " TEXT, " + USERNAME + " TEXT, "
            + FULL_NAME + " TEXT, " + FIRST_NAME + " TEXT, " + BRANCH_NAME + " TEXT, " + LAST_NAME + " TEXT, " + IMAGE_PATH + " TEXT, "
            + ORGANIZATION + " TEXT, " + BRANCH_ID + " TEXT, " + BRANCH_ADDRESS + " TEXT, " + BRANCH_TEL_NO + " TEXT, "
            + BRANCH_EMAIL + " TEXT, " + TOKEN + " TEXT, " + COUNTRY + " TEXT, " + STATE + " TEXT, "
            + CITY + " TEXT, " + CNIC_NUMBER + " TEXT, " + PASSPORT + " TEXT, " + MR_NUMBER + " TEXT, "
            + GENDER + " TEXT, " + REGISTRATION_DATE + " TEXT, " + PANEL_EXPIRY_DATE + " TEXT, "
            + PANEL_ORGANIZATION_NAME + " TEXT);";


    private static final String CREATE_PROFILE_TABLE = "create table " + TABLE_PROFILE + "(" + ID + " TEXT, " + TITLE + " TEXT, "
            + MIDDLE_NAME + " TEXT, " + GENDER_ID + " TEXT, " + RELATIONSHIP_TYPE_ID + " TEXT, " + RELATIONSHIP_NAME + " TEXT, " + LAST_NAME + " TEXT,"
            + GUARDIAN_NAME + " TEXT, " + MARITAL_STATUS_ID + " TEXT, " + MARITAL_STATUS + " TEXT, " + DATE_OF_BIRTH + " TEXT, " + CNIC_NUMBER + " TEXT,"
            + REFERENCE_ID + " TEXT, " + PICTURE_PATH + " TEXT, " + COUNTRY_ID + " TEXT, " + ADDRESS + " TEXT, " + CELL_NUMBER + " TEXT, " + GENDER + " TEXT, "
            + TELEPHONE_NUMBER + " TEXT, " + EMAIL + " TEXT, " + OCCUPATION + " TEXT, " + NOK_FIRSTNAME + " TEXT, " + NOK_LASTNAME + " TEXT, " + FIRST_NAME + " TEXT, "
            + COUNTRY + " Text, " + STATE + " TEXT, " + CITY + " TEXT, "
            + NOK_RELATION + " TEXT, " + NOK_CNICNUMBER + " TEXT, " + BLOODGROUP + " TEXT, " + AGE + " TEXT );";

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL(CREATE_TABLE_LOGIN);
        DB.execSQL(CREATE_PROFILE_TABLE);
        Log.e("TAG", "onCreate: table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);

        if (i<10) {
            //DB.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
            DB.execSQL("ALTER TABLE " + TABLE_PROFILE + " ADD COLUMN " + ID);
            Log.e("TAG", "onUpgrade: table update");

        }
    }
}
