# SpinUnContainer
### 의존성 주입
####  1. 객체가 직접 의존 관계에 있는 객체들의 생성자를 호출(인스턴스화)
#####   ex) this.user = new User();

####  2. 룩업(look-up) 패턴을 활용해 의존성들을 찾아 배치(생성자 또는 세터의 매개변수를 통해 의존성 주입, 컨테이너에 의존)
#####   ex) private User user;  생성자에서 this.user = user(매개변수)

-----------
### Spring Container
####  독립 실행형 어플리케이션에서 컨테이너 설정 방법

#### + IoC(inversion of control, 제어의 역전) : spring이 인스턴스를 인스턴스화 해야하는 책임을 지게 될 때
##### ex) org.springframework.context.ApplicationContext 인터페이스

#### + ClassPathXmlApplicationContext : xml기반

#### + AnnotationConfigApplicationContext : java기반(추천)

----------
#### + target폴더 : maven으로 빌드하면 생기는 폴더로, jar 파일을 주로 저장한다. 실서버에 반영할 때 target밑에 있는 프로젝트 결과 jar 또는 war을 반영한다. mvn clean하면 지워진다. git에 commit할 때는 제외하도록 하자.

#### + pom.xml에서 명령어 mvn clean install

#### + main(application.java)에서 명령어 java -jar target/messages-jar-with-dependencies.jar

