package jpql;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.OrderColumn;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Team teamA = new Team();
			teamA.setName("teamA");
			em.persist(teamA);
			Team teamB = new Team();
			teamB.setName("teamB");
			em.persist(teamB);
			
			Member member = new Member();
			member.setUsername("회원1");
			member.setTeam(teamA);
			em.persist(member);
			
			
			Member member1 = new Member();
			member1.setUsername("회원2");
			member1.setTeam(teamA);
			em.persist(member1);

			Member member2 = new Member();
			member2.setUsername("회원3");
			member2.setTeam(teamB);
			em.persist(member2);
			

			em.flush();
			em.clear();
			
			List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class).setParameter("username", "회원1").getResultList();
			

			for(Member members : resultList) {
				System.out.println("members : "+ members);
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
