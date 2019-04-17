package com.example.android.earthquakes_json;

public class Earthquake {

    private float mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mUrl;

    /**
     * Constructs a new {@link Earthquake} object.
     *
     * @param mMagnitude          is the magnitude (size) of the earthquake
     * @param mLocation           is the city location of the earthquake
     * @param mTimeInMilliseconds is the time in milliseconds (from the Epoch) when the
     *                            earthquake happened
     */
    public Earthquake(float mMagnitude, String mLocation, long mTimeInMilliseconds, String mUrl) {
        setmMagnitude(mMagnitude);
        setmLocation(mLocation);
        setmTimeInMilliseconds(mTimeInMilliseconds);
        setmUrl(mUrl);
    }

    public float getmMagnitude() {
        return mMagnitude;
    }

    public void setmMagnitude(float mMagnitude) {
        this.mMagnitude = mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public void setmTimeInMilliseconds(long mTimeInMilliseconds) {
        this.mTimeInMilliseconds = mTimeInMilliseconds;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
