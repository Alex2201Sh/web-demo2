package by.aston.webdemo2.entity.inheritance.d_join;

import jakarta.persistence.*;

@Entity
@Table(name = "billing_details4")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetails4 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String owner;

    public BillingDetails4() {
    }

    public int getId() {
        return id;
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
                "id=" + id +
                ", owner='" + owner + '\'' +
                '}';
    }
}