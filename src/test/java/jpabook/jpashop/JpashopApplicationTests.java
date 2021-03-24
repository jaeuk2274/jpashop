package jpabook.jpashop;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.QMember;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
class JpashopApplicationTests {

	@PersistenceContext
	EntityManager em;

	@Test
	void contextLoads() {
		Member member = new Member();
		em.persist(member);

		JPAQueryFactory query = new JPAQueryFactory(em);
		QMember qMember = QMember.member;

		Member result = query
				.selectFrom(qMember)
				.fetchFirst();

		System.out.println("result querydsl : " + result.getId());
		System.out.println("result querydsl : " + result.getName());
	}

}
