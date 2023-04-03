package jpql;

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
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);
			
			Member member = new Member();
			member.setUsername("member1");
			
			member.setAge(10);
			member.setType(MemberType.ADMIN);
			
			Member member1 = new Member();
			member1.setUsername("member2");
			member1.setAge(10);
			
			
			member.setTeam(team);
			
			
			em.persist(team);
			em.persist(member);
			em.persist(member1);

			em.flush();
			em.clear();

			String query = "select m.username, 'Hello', TRUE from Member m where m.type =:userType";
			List<Object[]> result = em.createQuery(query).setParameter("userType", MemberType.ADMIN).getResultList();
			
//			System.out.println(result.size());
//			System.out.println(result.get(0).);
			for (Object[] objects: result) {
				System.out.println(objects[0]);
				System.out.println(objects[1]);
				System.out.println(objects[2]);
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
