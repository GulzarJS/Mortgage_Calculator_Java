package model;

import java.time.LocalDate;
import java.time.Period;



public class Customer {

    private int id;
    private String name;
    private String surName;
    private LocalDate birthDate;
    private String gender;
    private int retireAge;

    public Customer() {
    }



    public Customer(String name, String surName, LocalDate birthDate) {
        this.name = name;
        this.surName = surName;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setRetireAge(String gender) {
        if(gender.equals("m")){
            this.retireAge = 65;
        }else if (gender.equals("f")){
            this.retireAge = 63;
        }
    }

    public int getRetireAge() {
        return retireAge;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String  getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        setRetireAge(gender);
    }

    public int getRestWorkYear(){
        LocalDate current = LocalDate.now();
        int restWorkYear = retireAge - Period.between(birthDate, current).getYears();
        return restWorkYear;
    }

    @Override
    public String toString() {
        return "model.Customer{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", surName='" + getSurName() + '\'' +
                ", birthDate=" + getBirthDate() +
                '}';
    }
}
