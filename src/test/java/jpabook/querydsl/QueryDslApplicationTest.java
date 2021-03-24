package jpabook.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.querydsl.entity.Member;
import jpabook.querydsl.entity.QMember;
import jpabook.querydsl.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class QueryDslApplicationTest {

    @PersistenceContext
    EntityManager em;
    // 여러 쓰레드에서 동시에 같은 EntityManager에 접근해도, 트랜잭션 마다 별도의 영속성 컨텍스트를 제공하기 때문에
    // 동시성 문제는 걱정하지 않아도 된다
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void startJPQL() {
        //member1을 찾아라.
        String qlString = "select m from Member m " +
                          "where m.username = :username";

        Member findMember = em.createQuery(qlString, Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertEquals(findMember.getUsername(),"member1");
    }
    @Test
    public void startQuerydsl() {
        //member1을 찾아라.
        QMember m = new QMember("m");
        Member findMember = queryFactory
                            .select(m)
                            .from(m)
                            .where(m.username.eq("member1"))//파라미터 바인딩 처리
                            .fetchOne();
        assertEquals(findMember.getUsername(),"member1");
    }
}