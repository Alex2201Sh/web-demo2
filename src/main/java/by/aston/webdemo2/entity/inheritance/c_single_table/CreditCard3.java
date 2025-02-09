package by.aston.webdemo2.entity.inheritance.c_single_table;

import jakarta.persistence.*;
@Entity
@DiscriminatorValue("CC")
public class CreditCard3 extends BillingDetails3 {

    @Column(name = "card_number")
    private int cardNumber;

    @Column(name = "exp_month")
    private String expMonth;

    @Column (name = "exp_year")
    private String expYear;

    public CreditCard3() {
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber=" + cardNumber +
                ", expMonth='" + expMonth + '\'' +
                ", expYear='" + expYear + '\'' +
                '}';
    }
}
