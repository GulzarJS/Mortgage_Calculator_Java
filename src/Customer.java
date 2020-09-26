
import java.time.LocalDate;

/**
 * Created by Gulzar Safar on 9/25/2020
 */

public class Customer {

    private int id;
    private String name;
    private String surName;
    private LocalDate birthDate;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer(String name, String surName, LocalDate birthDate) {
        this.name = name;
        this.surName = surName;
        this.birthDate = birthDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", surName='" + getSurName() + '\'' +
                ", birthDate=" + getBirthDate() +
                '}';
    }
}
