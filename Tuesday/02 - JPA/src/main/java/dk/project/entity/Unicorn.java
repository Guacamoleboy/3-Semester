package dk.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Unicorn {

    // Attributes
    @Id
    @GeneratedValue
    @Column(name = "unicorn_id", nullable = false)
    private int id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "power_strength")
    private double powerStrength;

    // __________________________________________

    public Unicorn(){
    }

    // __________________________________________

    public double getPowerStrength() {
        return powerStrength;
    }

    public void setPowerStrength(double powerStrength) {
        this.powerStrength = powerStrength;
    }

    // __________________________________________

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // __________________________________________

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // __________________________________________

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
