package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

public class MemoryMemberRepositoryTest {
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	@AfterEach
	public void afterEach() {
		repository.claerStore();
	}
	
	@Test
	public void save() {
		Member member = new Member();
		
		member.setName("spring");
		repository.save(member);
		
		Member result = repository.findById(member.getId()).get();
		
//		System.out.println("result : " + (result == member));
//		System.out.println(result.getName()+""+ member.getName());
		org.junit.jupiter.api.Assertions.assertEquals(member, result);
//		Assertions.assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findbyName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findByName("spring1").get();
		
		assertThat(result).isEqualTo(member1);
		
	}
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		
		assertThat(result.size()).isEqualTo(2);
	}
}
