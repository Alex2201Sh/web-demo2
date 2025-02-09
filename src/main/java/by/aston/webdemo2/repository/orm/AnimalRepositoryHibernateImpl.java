package by.aston.webdemo2.repository.orm;

import by.aston.webdemo2.entity.Animal;
import by.aston.webdemo2.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnimalRepositoryHibernateImpl implements AnimalRepositoryHibernate {

    @Override
    public List<Animal> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Animal> fromAnimal = session.createQuery("FROM Animal");  // HQL example
        List<Animal> list = fromAnimal.list();
        session.close();
        return list;
    }

    @Override
    public Optional<Animal> findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();                 // Criteria API example
        CriteriaQuery<Animal> criteriaQuery = builder.createQuery(Animal.class);
        Root<Animal> root = criteriaQuery.from(Animal.class);
        criteriaQuery.select(root).where(builder.equal(root.get("id"), id));
        Animal singleResult = session.createQuery(criteriaQuery).getSingleResult();
        session.close();
        return Optional.ofNullable(singleResult);
    }

    @Override
    public List<Animal> getAnimalsByBreed(String breed) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Animal WHERE breed = :breed";                //HQL example
        Query query = session.createQuery(hql);
        query.setParameter("breed", breed);
        List<Animal> animals = query.list();
        session.close();
        return animals;
    }

    @Override
    public Animal save(Animal animal) {
        String NATIVE_SQL_UPDATE_ANIMAL = "UPDATE animals SET breed=:breed, " +
                "age=:age, origin=:origin, " +
                "gender=:gender WHERE id=:id;";
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (animal.getId() == null) {
            session.save(animal);
        } else {
            Transaction transaction = session.beginTransaction();
            Query query = session.createNativeQuery(NATIVE_SQL_UPDATE_ANIMAL, Animal.class);    // Native SQL example
            query.setParameter("breed", animal.getBreed());
            query.setParameter("age", animal.getAge());
            query.setParameter("origin", animal.getOrigin());
            query.setParameter("gender", animal.getGender());
            query.setParameter("id", animal.getId());
            query.executeUpdate();
            transaction.commit();
        }
        session.close();
        return animal;
    }

    @Override
    public Animal deleteById(Integer animalId) {
        return null;
    }

    public List<Animal> findByAge(int age) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("Animal.findByAge");
        query.setParameter("age", 18);
        List<Animal> animals = query.list();
        session.close();
        return animals;
    }

}
