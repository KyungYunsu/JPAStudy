package hello.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;

@Configuration
public class SpringConfig {

//	@PersistenceContext
//	private EntityManager em;
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	
	@Bean
	public MemberService memberServie() {
		return new MemberService(memberRepository);
	}
	
//	@Bean
//	public MemberRepository memberRepository() {
////		return new MemoryMemberRepository();
////		return new JdbcMemberRepositoy(dataSource);
////		return new JdbcTemplateMemberRepository(dataSource); 
////		return new JpaMemberRepository(em);
//		
//	}
}
