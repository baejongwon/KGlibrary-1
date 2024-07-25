# 배종원의 프로젝트 KGlibrary 입니다.

### 목차

> 1. [자료검색](#자료검색book)
> 2. [도서 기증](#도서기증donateguide)



# 사용 기술 및 개발환경
server : Apach Tomcat 9 <br>
DB : MariaDB 10.6.14 <br>
Data Store : Redis<br>
Framework/Flatform : Spring MVC, SpringSecurity, MyBatis, Bootstrap, jQuery, Jsp, RESTful API<br>
Language : JAVA(version 17), Javascript, HTML5, CSS3<br>
Tool :  Git, GitHub,SQL Developer<br>
CI/CD : Jenkins, ArgoCD
AWS : EC2 / RDS / Load Balancer / Auto Scaling Group / S3 / ECR / EKS

# 인프라-아키텍처
<img src="https://github.com/baejongwon/jongwon-git-img/blob/main/Infrastructure%20Architecture.jpg" width=1200px alt="아키텍처"> 

# CI/CD 구성
<img src="https://github.com/baejongwon/jongwon-git-img/blob/main/cicd.png" width=1200px alt="cicd구성"> 


# 구현 기능
  
  ### 자료검색(book)
  [사용자]
  
  - 등록된 도서를 찾아 대여 가능
  - 비치 희망도서 게시글 작성 가능
  - 마이페이지에 대출 목록에서 대출한 도서의 제목 / 반납일 확인 가능
  - 1회에 한해 연장가능
  
  [관리자]
  - 국립중앙도서관에서 등록된 도서 정보를 가져와 게시글 등록

  ### 도서기증(donateguide)
  [사용자]
  
  - 기증할 도서의 분야 / 수량 / 기증 방법 / 내용을 작성 후 등록 가능
  - 기증 목록에서 기증자 확인 가능
