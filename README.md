# 단축 URL 관리
> URL을 입력받아 단축 URL을 생성하는 웹어플리케이션 프로젝트


## 제약조건
단축 URL Key는 8자로 생성  
동일한 URL에 대해서는 동일한 Key로 응답


## Application 구성
Java 1.8 + Spring Boot 2.1.6.RELEASE + DynamoDB


## Package 구조
> Front와 Api를 한개의 프로젝트에 구현하고 Package로 구분함

Front: com.yangjs.urlshortener.*  
Api: com.yangjs.urlshortener.api.*


## 개발의도
웹어플리케이션을 제공하기 위해 Spring MVC 사용  
단축 URL은 응답속도가 중요하므로 일관된 응답속도를 기대할 수 있는 DynamoDB 사용  
MSA로 분할을 고려해서 Package 구성


## 빌드방법
```
mvn package
```


## 실행방법
#### Java 웹어플리케이션 실행
```
java -jar bin/UrlShortener-0.0.1-SNAPSHOT.jar
```

#### 웹어플리케이션 접속
메인 페이지: http://localhost/  
단축URL redirect 요청: http://localhost/{key}
