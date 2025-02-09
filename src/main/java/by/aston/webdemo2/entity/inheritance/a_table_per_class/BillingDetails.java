package by.aston.webdemo2.entity.inheritance.a_table_per_class;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BillingDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String owner;

    public BillingDetails() {
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "BillingDetails{" +
                "owner='" + owner + '\'' +
                '}';
    }
}