# Spring AOP

### 보안검사 
+ 보안검사 : 중앙에서 수행하는 것이 바람직하다.
+ @Aspect 어노테이션 : 관심사를 다루는 로직을 하나의 애스펙트에 넣는다. 
+ Advices
  > @Before : 조인 포인트 이전에 실행되는 어드바이스로, 예외를 던지지 않는 한 코드가 실행된다.
  > @AfterReturning(정상적 반환 이후) : 예외를 던지지 않고 정상적으로 완료되었을 때 실행된다.
  > @AfterThrowing(예외 발생 이후) : 예외를 던져 메소드가 종료될 때 실행된다.
  > @After : 조인 포인트 실행과 관계없이 실행된다.
  > @Around(메소드 실행 전후) : 코드 실행을 완전히 제어한다 -> 가장 강력하다.

+ Pointcuts : 여러 조인트를 결합한 것이다.
+ AOP 프락시 : aspect의 대상(target) 객체, 어드바이스 객체. 스프링 AOP에서 런타임 동안 aspect 계약을 이행하기 위해 객체에 대한 프락시 객체를 생성.
+ Weaving : 다른 필수 객체와 aspect를 연결하여 어드바이스 객체를 생성하는 프로세스. 스프링 AOP에서는 런타임 동안에 발생, AspectJ는 컴파일/로드 타임동안 수행한다.

------------------
### AOP 실행 흐름

#### 적용하지 않을 경우
+ controller.saveMsg -> service.save로 직접 흐른다.
#### @SecurityCheck 적용할 경우
+ controller.saveMsg -> AOP 프락시 객체(이 예제에서는 spring AOP가 CGLIB로 생성한 service 하위 클래스 인스턴스) -> checkSecurity() 어드바이저 -> service.save()(어드바이저 내부에서 호출) -> 완료 시 controller로 돌아감
------------
### 오류
+ pom.xml에 org.aspectj 하위 두개 의존성 추가

----------------
# Spring Transection 관리
전역 트랜잭션 : JTA(Java Transaction API) - RDBMS와 JMS와 같은 다양한 트랜잭션 자원과 함께 동작 가능, 애플리케이션 서버가 관리한다.
로컬 트랜잭션 : JDBC API, 하이버네이트 트랜잭션, JPA 트랜잭션 - 자원에 한정적으로, 다양한 자원들과 함께 동작 불가.

### 스프링 트랜잭션 관리 - 2가지 방법
+ 프로그래밍적 트랜잭션 관리(TransactionTemplate API) : 롤백 규칙을 지원
+ 선언적 트랜잭션 관리(PlatformTransactionManager API) : 트랜잭션 관리가 필요한 클래스나 메소드에 @Transactional 어노테이션을 붙인다. AOP 프레임워크를 기반으로 구현하며, 전역/로컬 트랜잭션을 위한 모든 환경에서 동작한다. 롤백규칙을 지원

### 시간별로 게시된 메시지의 통계
> 메세지를 저장한 후에 통계를 업데이트하고 이 정보를 DB에 저장
> 1. AppConfig에 HibernateTransactionManager 추가
> 2. Service에 updateStatistics() 메소드 작성, save에 트랜잭션 관리 추가(어노테이션)
> 3. application.properties 수정
> 4. Repository.saveMessage에서 sessionFactory.getCurrentSession()으로 수정 -> 현재 세션 획득(트랜잭션 어드바이저와 공유하고 있는 세션)
> (service.updateStatistics에서 일부로 exception을 만들었다.)
---------

# 조합(front end - back end 합치기)
1. UI 렌더링
controller에 UI 렌더링하는 index 핸들러 메소드 추가(view 이름 반환하는 메소드)

2. API 요청
JSON 메시지 load/save

3. HDL
Hibernate Query Language, 하이버네이트 세션을 얻은 후에 모든 메시지를 가져오기 위해 생성. 하이버네이트는 해당 hdl을 sql로 번역해 db로 전달한다.

4. Axois
HTTP 클라이언트로 백엔드와 통신하는 방법 중 하나. 해당 버전을 프로젝트 내에 넣자. 
+ 생성
> 메시지 페이지를 열었을 때 가장 빠르게 기존 메시지를 보여주기 위해 axios.get을 이용한다(vue 인스턴스의 created() 안에)
> index.html -> created()에서 axios.get으로 메시지 가져옴 -> 메시지 json 데이터는 response 매개변수의 data property로 전달된다.

+ 추가
> 메시지 페이지 -> Submit 버튼 -> Vue의 메소드인 addMessage -> axios.post(url, 전달 데이터) -> controller(백엔드로 전달 됨) -> ... ->(성공) Vue의 메소드인 addMessage의 .then() 수행// 실패일 때 .catch() 수행

+ 삭제
> 위와 동일(@delete는 MessageList.js에 구현되어있다.)
> delete 구현 시, 원칙적으로는 delete에 데이터를 보낼 수 없다. -> 한번 더 싸주면 된다.  axios.delete(url, {data : { id: message.id}}) 

### 클래스 설명
+ AppConfig.java : 루트 설정 클래스
+ Application.java : 애플리케이션 부트스트랩 클래스
+ AuditingFilter.java : 요청 감사 필터
+ Message.java : Message 엔티티 클래스
+ MessageController.java : HTTP API에 대한 Message 컨트롤러
+ MessageData.java : 메시지 요청을 저장하는 데이터 구조
+ MessageRepository.java : 메시지의 리파지토리 클래스
+ MessageService.java : 메시지의 서비스 클래스
+ SecurityCheck.java : 보안 검사 어노테이션
+ SecurityChecker.java : 보안 검사 애스팩트