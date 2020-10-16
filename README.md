# AnnotationConfiguration
------------------
### Spring Annotation 사용 방법

###  1. 빈 선언 어노테이션

   스테레오 타입 어노테이션 : @Component, @Service, @Controller, @Repository. 스프링이 관리해야 하는 클래스에 적용한다. 
   스프링은 우선 @ComponentScan 어노테이션에 입력한 기본 패키지부터 스캔하고, 이후에 해당 어노테이션이 달린 클래스를 수집한다.

  + @Component : 자동 스캔 발견과 의존성 주입을 위해서 사용되는 가장 기본적인 어노테이션으로 제네릭(일반적) 스테레오 타입. 스프링이 이 어노테이션이 있는 클래스를 인스턴스화한다. 

  + @Service : @Component를 구체화한 것으로, 비즈니스 로직이나 repository 층을 호출하는 함수에 사용된다. 서비스 레이어에서 주로 쓰인다.

  + @Controller : @Component를 구체화한 것으로, web MVC 코드에 사용된다. 컴포턴트가 HTTP요청을 받을 수 있는 웹 컨트롤러임을 나타낸다. @RequestMapping 어노테이션을 해당 어노테이션 밑에서만 사용가능하다. 프레젠테이션 레이어에서 주로 쓰인다.

  + @Repository : @Component를 구체화한 것으로, data repository(영속성을 가지는 속성(파일, db))를 이용하기 위해 쓰인다. persistence 층에서 주로 쓰이며, 플랫폼 특정 예외를 잡아 spring unchecked 예외로 뱉어내준다.


###  2. 의존성 연결 어노테이션
  의존성 연결 어노테이션 : @Required, @Autowired

  + @Required : 세터 메소드

  + @Autowired : 생성자와 메소드, 필드에 적용

  + @Resource

  의존성 주입 방법
  * 생성자 기반 주입 : 필수 의존성
  * 세터 기반/메소드 기반 주입 : 선택적 의존성
  * 필드 기반 주입 : 세터 메소드 선언할 필요가 없다.(@Autowired)


  ----------
    ### + target폴더 : maven으로 빌드하면 생기는 폴더로, jar 파일을 주로 저장한다. 실서버에 반영할 때 target밑에 있는 프로젝트 결과 jar 또는 war을 반영한다. mvn clean하면 지워진다. git에 commit할 때는 제외하도록 하자.

    ### + pom.xml에서 명령어 mvn clean install
    #### pom.xml : 메이븐의 설정 파일로 pom은 maven의 작업 단위이다. npm에서의 package.json 파일과 동일하다.

    #### + main(application.java)에서 명령어 java -jar target/messages-jar-with-dependencies.jar

