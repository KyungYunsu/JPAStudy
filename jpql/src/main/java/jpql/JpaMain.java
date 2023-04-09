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
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);
			
			Member member = new Member();
			member.setUsername("member1");
			
			member.setAge(10);
			member.setType(MemberType.ADMIN);
			
			Member member1 = new Member();
			member1.setUsername("관리자");
			member1.setAge(10);
			
			
			member.setTeam(team);
			
			
			em.persist(team);
			em.persist(member);
			em.persist(member1);

			em.flush();
			em.clear();
			
			String query  = "select t.members from Team t";
					
			Collection result = em.createQuery(query, Collection.class).getResultList();
			
			for (Object s: result) {
				System.out.println(s);
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
