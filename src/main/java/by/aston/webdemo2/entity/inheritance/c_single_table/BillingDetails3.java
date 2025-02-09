package by.aston.webdemo2.entity.inheritance.c_single_table;

import jakarta.persistence.*;

@Entity
@Table(name = "billing_details3")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "BD_TYPE")
//@DiscriminatorFormula("CASE WHEN CARD_NUMBER IS NOT NULL THEN 'CC' ELSE 'BA' END")
public abstract class BillingDetails3 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String owner;

    public BillingDetails3() {
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