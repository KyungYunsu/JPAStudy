package helloJpa;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			
			Member member = new Member();
			member.setUserName("user1");
			member.setCreatedBy("kim");
			member.setCreateDate(LocalDateTime.now());
			
			em.persist(member);
			em.flush();
			em.clear();
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();// TODO: handle exception
		} finally {
			em.close();
		}

		emf.close();
	}

}
