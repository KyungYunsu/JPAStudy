package helloJpa;

import java.util.List;

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
			String jpql = "select m From Member m where m.username like '%kim%'";
			List<Member> result = em.createQuery("select m From Member m where m.username like '%kim%'",
					Member.class)
					.getResultList();

			for (Member member : result) {
				System.out.println("member = " + member);
			}

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
