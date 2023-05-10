package com.example.practise.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateRequest implements Serializable {

    @SerializedName("PatientTypeId")
    public String PatientTypeId;


    @SerializedName("PatientId")
    public String PatientId;

    @SerializedName("Token")
    public String Token;

    @SerializedName("BranchId")
    public String BranchId;

    @SerializedName("ReferenceId")
    public String ReferenceId;

    @SerializedName("Prefix")
    public String Prefix;

    @SerializedName("NOKRelationId")
    public String NOKRelationId;

    @SerializedName("CityId")
    public String CityId;

    @SerializedName("CountryId")
    public String CountryId;

    @SerializedName("StateOrProvinceId")
    public String StateOrProvinceId;

    @SerializedName("MaritalStatusId")
    public String MaritalStatusId;

    @SerializedName("PersonTitleId")
    public String PersonTitleId;

    @SerializedName("RelationshipTypeId")
    public String RelationshipTypeId;

    @SerializedName("GenderId")
    public String GenderId;

    @SerializedName("OccupationId")
    public String OccupationId;

    @SerializedName("PicturePath")
    public String PicturePath;

    @SerializedName("NOKCellNumber")
    public String NOKCellNumber;

    @SerializedName("NOKCNICNumber")
    public String NOKCNICNumber;

    @SerializedName("NOKFirstName")
    public String NOKFirstName;

    @SerializedName("NOKLastName")
    public String NOKLastName;

    @SerializedName("TelephoneNumber")
    public String TelephoneNumber;

    @SerializedName("Email")
    public String Email;

    @SerializedName("Address")
    public String Address;

    @SerializedName("CellNumber")
    public String CellNumber;

    @SerializedName("DateOfBirth")
    public String DateOfBirth;

    @SerializedName("IdentityRelation")
    public String IdentityRelation;

    @SerializedName("BloodGroupId")
    public String BloodGroupId;

    @SerializedName("FirstName")
    public String FirstName;

    @SerializedName("MiddleName")
    public String MiddleName;

    @SerializedName("LastName")
    public String LastName;

    @SerializedName("GuardianName")
    public String GuardianName;
}
