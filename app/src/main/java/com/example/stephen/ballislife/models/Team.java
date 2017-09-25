package com.example.stephen.ballislife.models;


public class Team {
    private String mName;
    private String mAbbreviation;
    private String mConference;
    private String mDivision;
    private String mState;

    public Team(String name, String abbreviation, String conference, String division, String state)
    {
        this.mName = name;
        this.mAbbreviation = abbreviation;
        this.mConference = conference;
        this.mDivision = division;
        this.mState = state;
    }

    public String getName() {
        return mName;
    }

    public String getAbbreviation() {
        return mAbbreviation;
    }

    public String getConference() {
        return mConference;
    }

    public String getDivision() {
        return mDivision;
    }

    public String getState () {
        return mState;
    }
}

