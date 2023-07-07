// Music.aidl
package com.example.aircraftWar;

// Declare any non-default types here with import statements

interface Music {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    String getAction();
    void setAction(String maction);
    int getBgmId();
    void setBgmId(int mbgmId);
    int getResId();
    void setResId(int mresId);
}