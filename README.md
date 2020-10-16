# Spring MVC
  메시지 앱 -> 웹 애플리케이션
  HTTP 요청을 받는 컨트롤러 추가
  pom.xml 파일에 스트링 부트 의존성 추가

------------------
### JAVA EE Servlet
    : 톰캣과 같은 애플리케이션 서버인 서블릿 컨테이너 내에서 동작.

    1. 클라이언트가 애플리케이션 서버에게 HTTP 요청을 보냄
    
    2. 애플리케이션 서버 내에서 필터를 통과(인증, 로깅, 감사 등)
    
    3. 서버는 서블릿(요청 처리 서블릿, 첫번째 요청 : HttpSession - session ID를 가진다.)에게 해당 요청을 넘김
    
    4. 서블릿이 요청에 대한 처리를 마치면 HTTP 응답(HttpServletRequest)은 필터를 통과한 후 클라이언트로 전송됨

    ------
  ### Spring MVC
      : spring MVC를 사용하면 Servlet을 생성할 필요가 없다.
      @Controller 어노테이션 추가하고, @RequestMapping 어노테이션으로 특정 URI패턴에 매핑할 수 있다.
      Spring은 DispatcherServlet(핵심 요청 처리 서블릿, 알맞는 컨트롤러를 찾음)을 활용한다.



  ### maven 변화
    mvn install 실행한 뒤,
    java -jar target/messages-jar-with-dependencies.jar 명령어를 실행하면 이제 동작하지 않는다.
    -> Application run failed 에러 표시

    maven-assembly-plugin은 스프링 부트 어플리케이션을 실행할 수 없다.
    -> <build>에서 maven-assembly-plugin 대신에 spring-boot-maven-plugin을 추가하자.

    mvn spring-boot:run : 이제 mvn install 명령어를 실행할 필요가 없다.

  ### @ResponseBody 어노테이션을 핸들러 메소드에 추가
    (클래스에도 추가 가능 - 모든 메소드 적용 효과)
    스프링은 해당 메소드의 반환값을 HTTP 응답의 본문으로 처리, 대응하는 HttpMessageConverter를 찾아 응답에 값을 쓴다.
    