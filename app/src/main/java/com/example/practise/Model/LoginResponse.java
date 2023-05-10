package com.example.practise.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.SplittableRandom;

public class LoginResponse implements Serializable {

    @SerializedName("Id")
    public String Id;
    @SerializedName("MRNo")
    public String MRNo;
    @SerializedName("CNICNumber")
    public String CNICNumber;
    @SerializedName("Email")
    public String Email;
    @SerializedName("SwitchUserId")
    public String  SwitchUserId;
    @SerializedName("SwitchByUser")
    public String SwitchByUser;
    @SerializedName("IsChildAccount")
    public Boolean IsChildAccount;
    @SerializedName("IsSwitchAccount")
    public Boolean IsSwitchAccount;
    @SerializedName("Username")
    public String Username;
    @SerializedName("FullName")
    public String FullName;
    @SerializedName("FirstName")
    public String FirstName;
    @SerializedName("Displayname")
    public String Displayname;
    @SerializedName("BranchName")
    public String BranchName;
    @SerializedName("ImagePath")
    public String ImagePath;
    @SerializedName("OrganizationPicturePath")
    public String OrganizationPicturePath;
    @SerializedName("BranchId")
    public String BranchId;
    @SerializedName("BranchAddress")
    public String BranchAddress;
    @SerializedName("BranchTelNo")
    public String BranchTelNo;
    @SerializedName("BranchEmail")
    public String BranchEmail;
    @SerializedName("UserType")
    public int UserType;
    @SerializedName("Token")
    public String Token;
    @SerializedName("Status")
    public int Status;
    @SerializedName("ErrorMessage")
    public String ErrorMessage;
    @SerializedName("WorkingSessionId")
    public String WorkingSessionId;
    @SerializedName("CountryId")
    public String CountryId;
    @SerializedName("CityId")
    public String CityId;
    @SerializedName("StateOrProvinceId")
    public String StateOrProvinceId;
    @SerializedName("Country")
    public String Country;
    @SerializedName("City")
    public String City;
    @SerializedName("StateOrProvince")
    public String StateOrProvince;
    @SerializedName("DeviceToken")
    public String DeviceToken;
    @SerializedName("WebToken")
    public String WebToken;
    @SerializedName("CellNumber")
    public String CellNumber;
    @SerializedName("TelephoneNumber")
    public String TelephoneNumber;
    @SerializedName("PatientAddress")
    public String PatientAddress;
    @SerializedName("Passport")
    public Object Passport;
    @SerializedName("IsFlightDetail")
    public int IsFlightDetail;
    @SerializedName("WorkingSessions")
    public String WorkingSessions;
    //    List<PatientBranches> userData1, userData2;
    @SerializedName("GenderName")
    public String GenderName;
    @SerializedName("RegistrationDate")
    public String RegistrationDate;
    @SerializedName("PanelExpiryDate")
    public String PanelExpiryDate;
    @SerializedName("PanelOrganizationName")
    public String PanelOrganizationName;
    @SerializedName("PanelPackageName")
    public String PanelPackageName;
    @SerializedName("PanelPackageDiscount")
    public double PanelPackageDiscount;
    @SerializedName("PanelPackageDiscountType")
    public String PanelPackageDiscountType;

}
