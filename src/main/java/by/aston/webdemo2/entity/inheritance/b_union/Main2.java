package by.aston.webdemo2.entity.inheritance.b_union;

import by.aston.webdemo2.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main2 {
    public static void main(String[] args) throws Exception {


        CreditCard2 creditCard = new CreditCard2();
        creditCard.setCardNumber(44411111);
        creditCard.setExpMonth("Jan");
        creditCard.setExpYear("2017");
        creditCard.setOwner("Bill Gates");
        BankAccount2 bankAccount = new BankAccount2();
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
            List<BillingDetails2> billingDetails = session.createQuery("FROM BillingDetails2").list();
            for (int i = 0; i < billingDetails.size(); i++) {
                System.out.println(billingDetails.get(i));
            }
        } catch (Exception e) {
            transaction2.rollback();
            throw e;
        }

    }
}
