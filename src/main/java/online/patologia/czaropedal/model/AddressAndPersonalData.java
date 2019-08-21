package online.patologia.czaropedal.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class AddressAndPersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotEmpty(message = "Podaj imię")
    @Size(min = 2, message = "Zbyt krótkie imie")
    @Size(max = 30, message = "Zbyt długie imie")
    private String firstName;
    @NotBlank
    @NotEmpty(message = "Podaj nazwisko")
    @Size(min = 2, message = "Nazwisko zbyt krótkie")
    @Size(max = 30, message = "Nazwisko zbyt długie")
    private String lastName;
    @NotBlank
    @NotEmpty(message = "Podaj miasto")
    @Size(min = 2, message = "Zbyt krótka nazwa miasta")
    @Size(max = 30, message = "Zbyt długa nazwa miasta")
    private String city;
    @NotBlank
    @NotEmpty(message = "Podaj ulice")
    @Size(min = 2, message = "Nazwa ulicy zbyt krótka")
    @Size(max = 30, message = "Nazwa ulicy zbyt długa")
    private String street;
    @NotBlank
    @NotEmpty(message = "Podaj kod pocztowy")
    @Size(min = 2, message = "Kod pocztowy zbyt krótki")
    @Size(max = 30, message = "Kod pocztowy zbyt długi")
    private String zipCode;
    @NotBlank
    @NotEmpty(message = "Podaj numer telefonu")
    @Size(min = 2, message = "Zbyt krótki numer telefonu")
    @Size(max = 30, message = "Zbyt długi numer telefonu")
    private String phoneNumber;

    public AddressAndPersonalData(String firstName, String lastName, String city, String street, String zipCode, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.phoneNumber=phoneNumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressAndPersonalData() {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipPost() {
        return zipCode;
    }

    public void setZipPost(String zipPost) {
        this.zipCode = zipPost;
    }
}
