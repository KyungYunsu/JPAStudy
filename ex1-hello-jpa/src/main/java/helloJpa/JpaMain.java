package helloJpa;

import java.util.List;
import java.util.Set;

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
			member.setUserName("member1");
			member.setHomeAddress(new Address("city", "street", "10000"));

			member.getFavoriteFoods().add("치킨");
			member.getFavoriteFoods().add("족발");
			member.getFavoriteFoods().add("피자");

			member.getAddressHistory().add(new Address("old1", "street", "10000"));
			member.getAddressHistory().add(new Address("old2", "street", "10000"));

			em.persist(member);

			em.flush();
			em.clear();

			System.out.println("start---------------");
			Member findMember = em.find(Member.class, member.getId());

			List<Address> addressHistory = findMember.getAddressHistory();
			for (Address address : addressHistory) {
				System.out.println("address = " + address.getCity());
			}
			Set<String> favoriteFoods = findMember.getFavoriteFoods();
			for (String favoriteFood : favoriteFoods) {
				System.out.println("favoriteFood = " + favoriteFood);
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
