package org.example.api.dtos.responses;

public class ContactResponse {
    private String _id;
    private String firstName;
    private String lastName;
    private String birthdate;
    private String email;
    private String phone;
    private String street1;
    private String street2;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;
    private String owner;
    private int __v;

    public ContactResponse(String _id, String firstName, String lastName, String birthdate, String email, String phone, String street1, String street2, String city, String stateProvince, String postalCode, String country, String owner, int __v) {
        this._id = _id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.stateProvince = stateProvince;
        this.postalCode = postalCode;
        this.country = country;
        this.owner = owner;
        this.__v = __v;
    }

    public ContactResponse() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
