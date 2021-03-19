### 영속성 컨텍스트 
- 엔티티를 영구 저장하는 환경
- 엔티티 매니저 팩토리, 엔티티 매니저
- 엔티티 매니저를 통해 영속성 컨텍스트에 접근
    

- 엔티티 생명주기
  - 비영속 (new/transient)
    - 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태
  - 영속 (managed)
    - 영속성 컨텍스트에 관리되는 상태
  - 준영속 (detached)
        - 영속성 컨텍스트에 저장되었다가 분리된 상태, detach
  - 삭제 (removed)  
    -  삭제된 상태, remove 
    

- 1차 캐시
- 동일성 보장
- 쓰기 지연  
- 변경 감시(Dirty Checking)
    - 처음 읽으면 스냅샷
- 지연 로딩(Lazy Loading)   


- 플러시
    - 영속성 컨텍스트 비우지 않고 변경내용만 DB에 동기화
  
- 상속관계 매핑
  - 전략 : SINGLE_TABLE, JOINED, TABLE_PER_CLASS
  - @DiscriminatorColumn - dtype
  - @DiscriminatorValue("name") - 기본 엔티티 명, 지정가능
  - 전략 바꿔도 코드 바꿀게 별로 없다. (JOINED -> SINGLE 가면 쿼리 다 뜯어고쳐야 하는데)
 
- fetch join
  - N+1 문제
  - 컬렉션 패치 조인
    - select t from Team t join fetch t.members
    - 중복 결과 / DISTINCT
    - select distinct t from Team t join fetch t.members
  
 - @BatchSize(size = 100) / 보통 실무에서 글로벌 세팅

- 벌크 연산
  - 수행 후 영속성 컨텍스트 초기화

  

  