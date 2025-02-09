package by.aston.webdemo2.entity.inheritance.b_union;

import jakarta.persistence.*;

@Entity
@Table(name = "bank_account2")
public class BankAccount2 extends BillingDetails2 {


    private int account;

    @Column(name = "bank_name")
    private String bankName;

    private String swift;

    public BankAccount2() {
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "account=" + account +
                ", bankName='" + bankName + '\'' +
                ", swift='" + swift + '\'' +
                '}';
    }
}