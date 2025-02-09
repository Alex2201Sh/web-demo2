package by.aston.webdemo2.entity.inheritance.a_table_per_class;

import by.aston.webdemo2.entity.Animal;
import by.aston.webdemo2.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {


        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(44411111);
        creditCard.setExpMonth("Jan");
        creditCard.setExpYear("2017");
        creditCard.setOwner("Bill Gates");
        BankAccount bankAccount = new BankAccount();
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
            List<BillingDetails> billingDetails = session.createQuery("FROM BillingDetails", BillingDetails.class).list();
            for (int i = 0; i < billingDetails.size(); i++) {
                System.out.println(billingDetails.get(i));
            }

//            CriteriaBuilder builder = session.getCriteriaBuilder();                 // Criteria API example
//            CriteriaQuery<BillingDetails> criteriaQuery = builder.createQuery(BillingDetails.class);
//            Root<BillingDetails> root = criteriaQuery.from(BillingDetails.class);
//            criteriaQuery.select(root);
//            List<BillingDetails> list = session.createQuery(criteriaQuery).list();
//            session.close();

        } catch (Exception e) {
            transaction2.rollback();
            throw e;
        }

    }
}
