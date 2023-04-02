package jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Query;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			
			Member member=  new Member();
			member.setUsername("member1");
			member.setAge(10);
			em.persist(member);
			
			TypedQuery<Member> query = em.createQuery("select m from Member m where m.username = :username", Member.class);
			query.setParameter("username", "member1");
			TypedQuery<String>  query2 = em.createQuery("select m.username from Member m", String.class);
			javax.persistence.Query query3 = em.createQuery("select m.username, m.age from Member m");
			
			Member result = query.getSingleResult();
			System.out.println(result.getAge());
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}

		emf.close();
	}

}
