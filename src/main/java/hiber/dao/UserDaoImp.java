package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
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
   public User getUserWithCar(String model, int series) {
      String sql = "from User where car.model = :param1 " +
               " and car.series = :param2";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where " +
                                                                              "car.model = :param1 " +
                                                                           "and car.series = :param2");
      query.setParameter("param1", model);
      query.setParameter("param2", series);
      return query.getResultList().stream().findFirst().orElse(null);
   }
}
