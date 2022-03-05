package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public void clearUsers(){
      sessionFactory.getCurrentSession().createQuery("delete from User").executeUpdate();
   }

   @Override
   public User getUserByCar(String model, int serie) {
      User user = new User();
    Query query = sessionFactory.getCurrentSession().createQuery(" from Car " +
             "where model = :model and  series = :serie ");
    query.setParameter("model", model);
    query.setParameter("serie", serie);
    List <Car> cars = query.getResultList();

    for (Car c : cars) {
      user =  c.getUser();
    }
    return user;
   }

}
