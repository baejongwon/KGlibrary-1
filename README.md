# 프로젝트 KGlibrary 입니다.



<h3>사용 기술 및 개발환경</h3>
server : Apach Tomcat 9 <br>
DB : MariaDB 10.6.14 <br>
Data Store : Redis<br>
Framework/Flatform : Spring MVC, SpringSecurity, MyBatis, Bootstrap, jQuery, Jsp, RESTful API<br>
Language : JAVA(version 17), Javascript, HTML5, CSS3<br>
Tool :  Git, GitHub,SQL Developer<br>

<h3>내용</h3>

<ul>
  <li><h4>구현 기능</h4></li>
  1.자료검색(book)<br>
  ㄴ 도서 검색 / 비치 희망 도서 / API 도서 등록<br>
  <br>
  2. 도서기증(donateguide)<br>
  ㄴ 기증 안내 / 기증 신청 / 기증목록<br>
  
<h5>application.properties에는 aws의 rds 접속 정보와 access-key, secret-key가 포함되어 github에는 포함되어 있지 않습니다.</h5>

```properties
# OracleDB connection settings
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

# JSP setting
spring.mvc.view.prefix=/jsp/
spring.mvc.view.suffix=.jsp

# mybatis framework setting
mybatis.mapper-locations=/mappers/*.xml
 
# file settings
spring.servlet.multipart.max-file-size=10MB

# server port settings
server.port=80

coolsms.apikey=
coolsms.apisecret=
coolsms.fromnumber=

spring.redis.cluster.nodes=redis-cluster.delivery.svc.cluster.local:6379

# AWS S3 Configuration
aws.access-key=
aws.secret-key=
aws.region=
aws.s3.bucket-name=
