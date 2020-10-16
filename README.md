# Spring MVC
+ 메시지 앱 -> 웹 애플리케이션
+ HTTP 요청을 받는 컨트롤러 추가
+  pom.xml 파일에 스트링 부트 의존성 추가

------------------
### JAVA EE Servlet
###   : 톰캣과 같은 애플리케이션 서버인 서블릿 컨테이너 내에서 동작.
 
> 1. 클라이언트가 애플리케이션 서버에게 HTTP 요청을 보냄
>
> 2. 애플리케이션 서버 내에서 필터를 통과(인증, 로깅, 감사 등)
>
> 3. 서버는 서블릿(요청 처리 서블릿, 첫번째 요청 : HttpSession - session ID를 가진다.)에게 해당 요청을 넘김
>    
> 4. 서블릿이 요청에 대한 처리를 마치면 HTTP 응답(HttpServletRequest)은 필터를 통과한 후 클라이언트로 전송됨

    ------
### Spring MVC
###  : spring MVC를 사용하면 Servlet을 생성할 필요가 없다.

> @Controller 어노테이션 추가하고, @RequestMapping 어노테이션으로 특정 URI패턴에 매핑할 수 있다.
> Spring은 DispatcherServlet(핵심 요청 처리 서블릿, 알맞는 컨트롤러를 찾음)을 활용한다.
> 
> M : model, 데이터
> V : View, html 마크업이 위치, C에게 M(data)를 받아 최종 결과를 렌더링
> C : controller, M(data)을 생성하고 HTTP 응답을 통해 클라이언트에 전송

### View 추가
> src/resources/templates에 위치
> html에 있는 "${message}"를 통해 Controller에서 전달할 모델에서 message값을 가져올 수 있다.
> -> @ResponseBody 어노테이션이 불필요하므로 제거
> 스프링은 handler의 return 값을 view의 이름으로 사용하고, timeleap로 응답을 생성한다.
> 
> 스프링 MVC에서 org.springframework.web.servlet.ModeAndView 인스턴트를 반환함으로써 모델과 뷰를 사용할 수도 있다.
> 매개변수로 model을 주느냐, 새로 model객체를 생성하느냐의 차이다. 아래에서는 객체를 생성하여 데이터를 추가하였다. 
      @GetMapping("/welcome")
      public ModelAndView welcome() {
        ModelAndView mv = new ModelAndView("welcome");
        mv.addObject("message", "Hello, Welcome to Spring Boot!");
        return mv;
      }

### 필터(filter)
> 책임 연쇄 패턴(Chain of Responsibility) 구현
> 서블릿에 도달하기 전에 HTTP 요청에 대한 필터링 작업을 수행하려고 할 때 유용
> 
> javax.servlet.Filter 인터페이스 구현/org.springframework.web.filter.GenericFilterBean을 확장하여 필터 구현 가능

#### 필터 등록 방법
+ web.xml파일에 <filter>, <filter-mapping>을 추가, 등록. 웹 어플리케이션을 위해 사용된다.
+ FilterRegistrationBean을 만들어 AppConfig 설정 클래스에 등록.

### 프로퍼티(properties)
> 필요한 정보들을 파일로 저장해놓고 사용한다. 
> key=value 형식의 텍스트 파일을 다룰 때 사용하는 클래스이다. 아래처럼 사용할 수 있다.

Properties properties = new Properties();
properties.load(new FileReader("C:../application.properties"));
 
for (Object object: properties.keySet()) {
    System.out.println(object + " = " + properties.get(object));
}



### maven 변화
> mvn install 실행한 뒤,
> java -jar target/messages-jar-with-dependencies.jar 명령어를 실행하면 이제 동작하지 않는다.
> -> Application run failed 에러 표시

> maven-assembly-plugin은 스프링 부트 어플리케이션을 실행할 수 없다.
> -> <build>에서 maven-assembly-plugin 대신에 spring-boot-maven-plugin을 추가하자.

> mvn spring-boot:run : 이제 mvn install 명령어를 실행할 필요가 없다.

  ### @ResponseBody 어노테이션을 핸들러 메소드에 추가
>  (클래스에도 추가 가능 - 모든 메소드 적용 효과)
> 스프링은 해당 메소드의 반환값을 HTTP 응답의 본문으로 처리, 대응하는 HttpMessageConverter를 찾아 응답에 값을 쓴다.
    
  ### 기타
+ 서버 예열(Server Warming Up) : 서버의 첫번째 요청까지 걸린 시간과 비교하면 페이지를 새로 고쳤을 때 걸리는 시간이 매우 빠르다. 전자는 DispatcherServlet을 초기화하는데 오래 걸리기 때문이다.

+ WebFlux : 스프링 프레임워크 5의 새로운 웹 스택. 많은 양의 동시 연결을 처리하기 위해 만들어진 논블로킹 웹 프레임워크다. 이 프로젝트에서는 전통적인 스프링 MVC를 사용한다.