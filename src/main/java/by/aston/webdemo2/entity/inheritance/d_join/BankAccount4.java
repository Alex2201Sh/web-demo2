package by.aston.webdemo2.entity.inheritance.d_join;

import jakarta.persistence.*;
@Entity
@Table(name = "bank_account4")
public class BankAccount4 extends BillingDetails4 {

    private int account;

    @Column(name = "bank_name")
    private String bankName;

    private String swift;

    public BankAccount4() {
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