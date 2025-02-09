package by.aston.webdemo2.entity.inheritance.c_single_table;

import by.aston.webdemo2.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main3 {
    public static void main(String[] args) throws Exception {


        CreditCard3 creditCard = new CreditCard3();
        creditCard.setCardNumber(44411111);
        creditCard.setExpMonth("Jan");
        creditCard.setExpYear("2017");
        creditCard.setOwner("Bill Gates");
        BankAccount3 bankAccount = new BankAccount3();
        bankAccount.setAccount(111222333);
        bankAccount.setBankName("Goldman Sachs");
        bankAccount.setSwift("GOLDUS33");
        bankAccount.setOwner("Donald Trump");

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(creditCard);
            session.persist(bankAccount);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

        Transaction transaction2 = null;
        try {
            transaction2 = session.beginTransaction();
            List<BillingDetails3> billingDetails = session.createQuery("FROM BillingDetails3").list();
            for (int i = 0; i < billingDetails.size(); i++) {
                System.out.println(billingDetails.get(i));
            }
        } catch (Exception e) {
            transaction2.rollback();
            throw e;
        }

    }
}
