package com.example.zavrsnitest.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "filmovi")
public class Filmovi {

    @DatabaseField(generatedId = true)
    private int mId;

    @DatabaseField(columnName = "naziv")
    private String mNaziv;

    @DatabaseField(columnName = "mGodina")
    private String mGodina;

    @DatabaseField(columnName = "mTrajanje")
    private String mTrajanje;

    @DatabaseField(columnName = "mZanr")
    private String mZanr;

    @DatabaseField(columnName = "mPlot")
    private String mPlot;

    @DatabaseField(columnName = "mJezik")
    private String mJezik;

    @DatabaseField(columnName = "mImage")
    private String mImage;

    @DatabaseField(columnName = "mVreme")
    private String mVreme;

    @DatabaseField(columnName = "mCena")
    private String mCena;

    @DatabaseField(columnName = "imdbId")
    private String mImdbId;

    //ORMLite zahteva prazan konstuktur u klasama koje opisuju tabele u bazi!
    public Filmovi() {
    }


    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmNaziv() {
        return mNaziv;
    }

    public void setmNaziv(String mNaziv) {
        this.mNaziv = mNaziv;
    }

    public String getmGodina() {
        return mGodina;
    }

    public void setmGodina(String godina) {
        this.mGodina = godina;
    }

    public String getmTrajanje() {
        return mTrajanje;
    }

    public void setmTrajanje(String mTrajanje) {
        this.mTrajanje = mTrajanje;
    }

    public String getmZanr() {
        return mZanr;
    }

    public void setmZanr(String mZanr) {
        this.mZanr = mZanr;
    }

    public String getmPlot() {
        return mPlot;
    }

    public void setmPlot(String mPlot) {
        this.mPlot = mPlot;
    }

    public String getmJezik() {
        return mJezik;
    }

    public void setmJezik(String mJezik) {
        this.mJezik = mJezik;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmVreme() {
        return mVreme;
    }

    public void setmVreme(String mVreme) {
        this.mVreme = mVreme;
    }

    public String getmCena() {
        return mCena;
    }

    public void setmCena(String mCena) {
        this.mCena = mCena;
    }

    public String getmImdbId() {
        return mImdbId;
    }

    public void setmImdbId(String mImdbId) {
        this.mImdbId = mImdbId;
    }
}
