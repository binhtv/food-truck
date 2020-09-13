package food.truck.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FoodTruck {
    private int dayorder;
    private String dayofweekstr;
    private String starttime;
    private String endtime;
    private String permit;
    private String location;
    private String locationdesc;
    private String optionaltext;
    private long locationid;
    private long scheduleid;
    private String start24;
    private String end24;
    private int cnn;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date addr_date_create;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date addr_date_modified;
    private String block;
    private String lot;
    private String coldtruck;
    private String applicant;
    private double x;
    private double y;
    private double latitude;
    private double longitude;
    private Point location_2;


    public int getDayorder() {
        return dayorder;
    }

    public void setDayorder(int dayorder) {
        this.dayorder = dayorder;
    }

    public String getDayofweekstr() {
        return dayofweekstr;
    }

    public void setDayofweekstr(String dayofweek) {
        this.dayofweekstr = dayofweek;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationdesc() {
        return locationdesc;
    }

    public void setLocationdesc(String locationdesc) {
        this.locationdesc = locationdesc;
    }

    public String getOptionaltext() {
        return optionaltext;
    }

    public void setOptionaltext(String optionaltext) {
        this.optionaltext = optionaltext;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public long getLocationid() {
        return locationid;
    }

    public void setLocationid(long locationid) {
        this.locationid = locationid;
    }

    public long getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(long scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getStart24() {
        return start24;
    }

    public void setStart24(String start24) {
        this.start24 = start24;
    }

    public String getEnd24() {
        return end24;
    }

    public void setEnd24(String end24) {
        this.end24 = end24;
    }

    public int getCnn() {
        return cnn;
    }

    public void setCnn(int cnn) {
        this.cnn = cnn;
    }

    public Date getAddr_date_create() {
        return addr_date_create;
    }

    public void setAddr_date_create(Date addr_date_create) {
        this.addr_date_create = addr_date_create;
    }

    public Date getAddr_date_modified() {
        return addr_date_modified;
    }

    public void setAddr_date_modified(Date addr_date_modified) {
        this.addr_date_modified = addr_date_modified;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getColdtruck() {
        return coldtruck;
    }

    public void setColdtruck(String coldtruck) {
        this.coldtruck = coldtruck;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLonggitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Point getLocation_2() {
        return location_2;
    }

    public void setLocation_2(Point location_2) {
        this.location_2 = location_2;
    }
}
