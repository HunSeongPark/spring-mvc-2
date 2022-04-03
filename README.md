# spring-mvc-2
스프링 MVC 2 - 각종 기술 스택 정리                           
강의 : [스프링 MVC 2편 - 백엔드 웹 개발 활용 기술, 김영한 강사님](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-mvc-2/dashboard)

## Learning Skills
* [Thymeleaf](https://github.com/HunSeongPark/spring-mvc-2/tree/master/thymeleaf)
* [Message, Internationalization](https://github.com/HunSeongPark/spring-mvc-2/tree/master/message)
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

- [SpringEL - 프로퍼티 접근](https://github.com/HunSeongPark/spring-mvc-2/commit/be2cd24552fed26cb75fa27aad37fa02ca9ebe62)
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

- [기본 객체](https://github.com/HunSeongPark/spring-mvc-2/commit/7cf7cae8aa69e89c728ae6b365bb8b0ec33f4620)
  - `${#request}` : HttpServletRequest
  - `${#response}` : HttpServletResponse
  - `${#session}` : HttpSession
  - `${#servletContext}` : ApplicationContext
  - `${#locale}` : Locale
  - *편의 객체*
  - `${session.sessionData}` : 세션 조회
  - `${param.paramData}` : Parameter 조회
  - `${@helloBean.hello('Spring!')}` : Bean 조회

- [URL 링크](https://github.com/HunSeongPark/spring-mvc-2/commit/95039fc472e2d9c5938ad85dc284e4db3016f0c5)
  - 타임리프에서 URL 링크는 `@{...}` 표현식을 사용한다.
  - *단순한 URL*
  - `@{/hello}` -> `/hello`
  - *Query Parameter*
  - `@{/hello(param1=${param1}, param2=${param2})}` -> `/hello?param1=data1&param2=data2`
  - *Path Variable*
  - `@{/hello/{param1}/{param2}(param1=${param1}, param2=${param2})}` -> `/hello/data1/data2`
  - *Query Parameter + Path Variable*
  - `@{/hello/{param1}(param1=${param1}, param2=${param2})}` -> `/hello/data1?param2=data2`

- [Template Fragment](https://github.com/HunSeongPark/spring-mvc-2/commit/0a1deea75d34ec5feaf8cfa76ac7d66e53674a46)
  - 페이지의 공통 영역을 Fragment로 설정하여 적용할 수 있다.
  ```
  <footer th:fragment="copy"> 
    푸터 자리 입니다.
  </footer>
  
  <footer th:fragment="copyParam (param1, param2)">
    <p>파라미터 자리 입니다.</p>
    <p th:text="${param1}"></p>
    <p th:text="${param2}"></p>
  </footer>
  ```
  - `th:fragment="name"` 을 통해 fragment를 설정할 수 있으며, `th:fragment=name (param1, param2)"`를 통해 parameter를 가지는 fragment를 설정할 수 있다.
  - fragment를 사용할 때는 다음과 같은 문법을 사용한다. 
  - *th:insert를 통해 div 태그 내에 fragment 삽입*               
  `<div th:insert="~{template/fragment/footer :: copy}"></div>`                 
  - *th:replace를 통해 div 태그를 fragment로 대체*                 
  `<div th:replace="~{template/fragment/footer :: copy}"></div>`                  
  - *Parameter를 가지는 fragment 적용*                  
  `<div th:replace="~{template/fragment/footer :: copyParam ('데이터1', '데이터2')}"></div>`                

### Message, Internationalization
- **Message** : 다양한 메시지를 한 곳에서 관리하도록 하는 기능
```
// messages.properties
item=상품
item.itemName=상품명
``` 
- 이러한 메시지를 타임리프 등에서 편하게 꺼내쓰고, 원할 때 해당 properties 파일을 수정하여 일괄적인 유지보수가 가능하다.
- **Internationalization(국제화)** : HTTP 헤더 값의 `Accept-Language`를 인식하여 언어별로 위의 message 기능을 구분지어 놓는 것
```
// messages_en.properties
item=Item
item.itemName=Item Name
```
- 국제화를 통해 웹사이트에 다양한 언어 지원을 도와준다.
- 스프링은 이러한 기본적인 메시지, 국제화 기능을 지원한다. 
- 스프링은 기본적인 메시지 관리 기능을 `MessageSource` 인터페이스를 통해 지원한다.
- 스프링 부트를 사용하면 자동으로 `MessageSource`를 스프링 빈으로 등록하므로 편하게 사용할 수 있다.
- application.properties에서 `spring.messages.basename=source1, source2 ,...`을 통해 메시지 소스를 지정할 수 있다. (기본값은 `messages` 이다.)
- `messages_en.properties`와 같이 `_locale`을 파일명에 지정시, 자동으로 국제화가 이루어져 HTTP 헤더 값의 `Accept-Language`를 인식해 맞는 언어의 메시지가 적용된다.
- `item=상품 {0}`와 같이 `{0}, {1}`을 통해 파라미터 지정이 가능하다.
- [타임리프에서 `th:text=#{item.name}`과 같은 문법을 통해 메시지에 접근, 사용할 수 있다.]((https://github.com/HunSeongPark/spring-mvc-2/commit/a23a4a05fc7048f3b5c919d99dd66444016bdb8f)
-  `th:text=#{item.name(${item.itemName})}`과 같이 파라미터를 넘겨줄 수 있다.
