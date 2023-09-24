package org.acme;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "location")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private double longitude;
    private double latitude;
    private double altitude;
    private double accuracy;
    private double altitudeAccuracy;
    private double heading;
    private double speed;
    private String email;
    private String ip;
    @Column(length = 500)
    private String headers;
    private Date createdAt;
    private String macAddress;
    private String acquisitionMode;
    private String cookie;

    public LocationEntity() {
    }

    public LocationEntity(double longitude, double latitude, double altitude, double accuracy, double altitudeAccuracy, double heading, double speed, String email, String ip, String headers, Date createdAt, String macAddress, String acquisitionMode, String cookie) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.accuracy = accuracy;
        this.altitudeAccuracy = altitudeAccuracy;
        this.heading = heading;
        this.speed = speed;
        this.email = email;
        this.ip = ip;
        this.headers = headers;
        this.createdAt = createdAt;
        this.macAddress = macAddress;
        this.acquisitionMode = acquisitionMode;
        this.cookie = cookie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getAltitudeAccuracy() {
        return altitudeAccuracy;
    }

    public void setAltitudeAccuracy(double altitudeAccuracy) {
        this.altitudeAccuracy = altitudeAccuracy;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getAcquisitionMode() {
        return acquisitionMode;
    }

    public void setAcquisitionMode(String acquisitionMode) {
        this.acquisitionMode = acquisitionMode;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    @Override
    public String toString() {
        return "LocationEntity{" +
                "id=" + id +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", altitude=" + altitude +
                ", accuracy=" + accuracy +
                ", altitudeAccuracy=" + altitudeAccuracy +
                ", heading=" + heading +
                ", speed=" + speed +
                ", email=" + email +
                ", ip='" + ip + '\'' +
                ", headers='" + headers + '\'' +
                ", createdAt=" + createdAt +
                ", macAddress='" + macAddress + '\'' +
                ", acquisitionMode='" + acquisitionMode + '\'' +
                ", cookie='" + cookie + '\'' +
                '}';
    }
}