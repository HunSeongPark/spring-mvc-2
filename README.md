# spring-mvc-2
스프링 MVC 2 - 각종 기술 스택 정리                           
강의 : [스프링 MVC 2편 - 백엔드 웹 개발 활용 기술, 김영한 강사님](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-mvc-2/dashboard)

## Learning Skills
* [Thymeleaf](https://github.com/HunSeongPark/spring-mvc-2/tree/master/thymeleaf)
* Message, Internationalization
* Validation
* Cookie, Session
* Filter, Interceptor
* Exception Handling
* Converter
* File upload/download            

## Common Project Setting              
* Spring boot version : 2.6.6                  
* Dependencies
  - Spring Web
  - Thymeleaf
  - Lombok 
  - Validation (only validation project)

## README 정리 방식                 
실습 내용 중 중요하다 생각하는 부분 / 새롭게 알게 된 부분에 대한 내용을 기록       
* 이론과 관련한 부분은 pdf 기반으로 공부. 해당 repository에는 실습 관련 개인 공부용 정리             
* 해당 이론과 관련한 실습 코드는 커밋 링크
                  
------------------                   
                  
### Thymeleaf
- [Escape](https://github.com/HunSeongPark/spring-mvc-2/commit/f231533baf73e6de831e7d415a438a29ce439097)
  - 타임리프는 `<`, `>`와 같은 HTML 태그를 `&lt;`, `&gt;`와 같은 HTML Entity로 변환하는 이스케이프(Escape)를 기본적으로 지원한다.
  - 그래서 다음과 같은 문자는 출력시 변경된다. `"Hello <b> Spring! </b>"` -> `"Hello &lt;b&gt; Spring! &lt;/b&gt;"`
  - 이러한 Escape를 적용하지 않고 그대로 출력하기 위한 타임리프 문법은 다음과 같다.
  - `th:text` -> `th:utext` / `[[${data}]]` -> `[(${data})]`     
  - ! 실제 서비스 개발에서 Escape를 사용하지 않아 발생하는 많은 문제들이 있으니 Unescape는 조심해서 꼭 필요할 때만 사용할 수 있도록 하자.            

- [SpringEL - 프로퍼티 접근]()
  - *Object*
  - `user.username`
  - `user['username']`
  - `user.getUsername()`
  - *List*
  - `users[0].username`
  - `users[0]['username']`
  - `users[0].getUsername()`
  - *Map*
  - `userMap['userA'].username`
  - `userMap['userA']['username']`
  - `userMap['userA'].getUsername()`

