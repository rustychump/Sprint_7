package Cards;

import java.util.List;

public class OrderCard {

    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String DeliveryDate;
    private String comment;
    private List<String> color;

    public OrderCard(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        DeliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMetroStation(int metroStation) {
        this.metroStation = metroStation;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }

    public void setDeliveryDate(String deliveryDate) {
        DeliveryDate = deliveryDate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getMetroStation() {
        return metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public int getRentTime() {
        return rentTime;
    }

    public String getDeliveryDate() {
        return DeliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public List<String> getColor() {
        return color;
    }



}
