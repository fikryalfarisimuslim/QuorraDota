package com.scholarships.college.quorradota;

public class Note {
    public static final String TABLE_NAME = "notes";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IP = "ip";
    public static final String COLUMN_COUNTRY = "country";

    private int id;
    private String note;
    private String country;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_IP + " TEXT,"
                    + COLUMN_COUNTRY + " TEXT"
                    + ")";

    public Note() {
    }

    public Note(int id, String note, String country) {
        this.id = id;
        this.note = note;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCountry() {
        return country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}