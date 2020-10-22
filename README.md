# hibernate
### 메시지 앱의 영속성 기능 구현
+ JDBC 드라이버 직접 사용하기
+ spring JDBC 사용하기
+ 하이버네이트 사용하기
------
### 설명
+ JDBC : Java Database Connectivity API, 관계형 데이터베이스에 저장된 데이터에 접근하는 방법 정의. 데이터베이스와의 상호작용을 해결. Spring JDBC를 사용하자.
+ JPA : Java Persistence API, 자바 객체의 영속성을 위한 자바의 표준화된 접근 방식 정의. 객체 관계형 매핑(ORM)으로 객체 지향 방식으로 데이터베이스에 객체를 저장, 가져오는 방법을 담당한다. - JPA구현체는 JDBC 드라이버에 의존하여 DB에 접근한다.
+ Hibernate ORM : 가장 흔히 사용하는 JPA 표준을 구현한 구현체. 영속성 처리를 위해 사용. 

> JDBC API 직접 사용하기, Spring JDBC사용하기, 하이버네이트 사용하기 중 JDBC API 직접 사용은 멀리하자
> 하이버네이트는 간단하지만 때에 따라 오버헤드로 인해 성능상의 문제를 일으킬 수 있다.

------------------
### 추가 사용 클래스
> JdbcTemplate클래스 : 연결 관리, JDBC API와 상호작용하는 워크플로를 제공하는 데 도움을 줌. 쿼리문 준비, 쿼리 결과를 처리할 방법만 지정하면 됨
> NamedParameterJdbcTemplated 클래스 : JdbcTemplate 객체를 래핑, JDBC의 ? placeholder 대신에 이름 지정한 매개변수 사용 가능
> SimpleJdbcInsert, SimpleJdbcCall : metadata로 JDBC작업 단순화. 

---------
### exception : bean 생성 오류
1. application.properties에 추가
2. pom.xml에 java 11 이상에서 명시해야 할 것 추가(제거해도 될듯)
3. javassist 추가(3.3 안됨, 3.23.1-GA로 해야함)