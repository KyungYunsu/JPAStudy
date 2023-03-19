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
			Child child1 = new Child();
			Child child2 = new Child();

			Parent parent = new Parent();
			parent.addChild(child1);
			parent.addChild(child2);
			
			em.persist(parent);
			em.persist(child1);
			em.persist(child2);
			
			em.flush();
			em.clear();

			Parent findParentId = em.find(Parent.class, parent.getId());
//			findParentId.getChildList().remove(0);
			em.remove(findParentId);
			
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}

		emf.close();
	}

	private static void logic(Member m1, Member m2) {
		System.out.println("m1 == m2 :" + (m1 instanceof Member));
		System.out.println("m1 == m2 :" + (m2 instanceof Member));
	}

	private static void printMember(Member member) {
		// TODO Auto-generated method stub
		String userName = member.getUserName();
		System.out.println("Member : "+userName);
	}

	private static void printMemberAndTeam(Member member) {
		// TODO Auto-generated method stub
		String userName = member.getUserName();
		System.out.println("Member : "+userName);
		Team team = member.getTeam();
		System.out.println("Team : "+team);
		
	}

}
