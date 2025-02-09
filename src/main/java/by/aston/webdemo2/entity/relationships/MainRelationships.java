package by.aston.webdemo2.entity.relationships;

import by.aston.webdemo2.entity.inheritance.d_join.BankAccount4;
import by.aston.webdemo2.entity.inheritance.d_join.BillingDetails4;
import by.aston.webdemo2.entity.inheritance.d_join.CreditCard4;
import by.aston.webdemo2.util.HibernateUtil;
import org.aspectj.weaver.ast.Or;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainRelationships {
    public static void main(String[] args) throws Exception {

        Address address1 = new Address(1L, "test city", "test city", "11B", "3C");
        User user1 = new User(
                UUID.randomUUID().toString(),
                "test name",
                11,
                address1,
                new UserProfile(),
                new ArrayList<Order>(),
                new ArrayList<Role>());
        List<Order> orders = List.of(new Order(user1, 10L), new Order(user1, 11L), new Order(user1, 12L));
        user1.setOrders(orders);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(address1);
            session.save(user1);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

        Transaction transaction2 = null;
        try {
            transaction2 = session.beginTransaction();
            List<BillingDetails4> billingDetails = session.createQuery("FROM User").list();
            for (int i = 0; i < billingDetails.size(); i++) {
                System.out.println(billingDetails.get(i));
            }
        } catch (Exception e) {
            transaction2.rollback();
            throw e;
        }
        session.close();

    }
}
