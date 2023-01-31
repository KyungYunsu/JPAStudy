package helloJpa;

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
			
			
			//영속
			Member member = em.find(Member.class, 150L);
			member.setName("AAAAA");
			
			em.clear();
			
			Member member2 = new Member(150L, "500");
			em.persist(member2);
			
			System.out.println("===========");
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();// TODO: handle exception
		} finally {
			em.close();
		}

		emf.close();
	}

}
