package cz.uruba.airportcodes.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vaclav on 31.12.2014.
 */
public class Airport implements Parcelable {
    private int id;
    private String name;
    private double latitude, longtitude;
    private int elevation;
    private AirportType airport_type;
    private Country country;

    public Airport(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLatLong(double latitude, double longtitude) {
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setAirportType(AirportType airport_type) {
        this.airport_type = airport_type;
    }

    public AirportType getAirportType() {
        return this.airport_type;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
