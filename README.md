# 스프링 JDBC와 JPA
### 메시지 앱의 영속성 기능 구현
+ JDBC 드라이버 직접 사용하기
+ spring JDBC 사용하기
+ 하이버네이트 사용하기
------
### 설명
+ JDBC : Java Database Connectivity API, 관계형 데이터베이스에 저장된 데이터에 접근하는 방법 정의. 데이터베이스와의 상호작용을 해결. Spring JDBC를 사용하자.
+ JPA : Java Persistence API, 자바 객체의 영속성을 위한 자바의 표준화된 접근 방식 정의. 객체 관계형 매핑(ORM)으로 객체 지향 방식으로 데이터베이스에 객체를 저장, 가져오는 방법을 담당한다. - JPA구현체는 JDBC 드라이버에 의존하여 DB에 접근한다.
+ Hibernate ORM : 가장 흔히 사용하는 JPA 표준을 구현한 구현체. 영속성 처리를 위해 사용

------------------
### 순서

> 1. mysql에 DB와 messages 테이블 생성
>
> 2. pom.xml 수정 -> 스프링 컨테이너에서 빈 사용 가능, DB 커넥션 풀 설정
>
> 3. application.properties 파일에 프로퍼티 추가 -> 스프링이 DataSource를 인스턴스화하는 데 필요한 매개변수를 설정
>    
> 4. MessageRepository 클래스 변경 -> DataSource에서 DB연결을 얻을 수 있도록 데이터 주입 스프링에 요청
>
> 5. MessageService, Controller 클래스 변경 -> 메시지 저장을 위한 HTTP API를 제공. 
>
> 6. MessageData : API의 요청 본문에 대한 정의

------
### app 실행 시 순서

> 1. Controller의 해당 request 메소드 호출
> 2. controller에서 service 호출
> 3. service에서 repository 호출

### 문제점 -> 해결
> 1. repository의 ps.getGeneratedKeys() 익셉션(아마도 앱을 불러오는 동안 예외발생 되는듯)
> id가 null이 들어갔군(not null이여야함)
>     -> insert 'id'  에서 ' 제거했더니 실행됨. sql문장 예외였던 것 같다.
> 2. controller에서 msg service.save가 null되었음 ( 1번과 같은 에러)
