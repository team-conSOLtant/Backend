#### ERD, 시스템 아키텍처, API 문서, 프로젝트 이슈관리 내용을 docs 폴더에 정리해두었습니다.
<hr>  

### 개발환경

- JAVA 17
- Spring Boot 3.3.2
- gradle 8.8
- MySql(RDS) 8.0.35
- ElasticSearch 8.7.1
- Gemini-pro

### 빌드 및 실행 방법

서비스는 [https://consoltant.site](https://consoltant.site/) 에 배포되어 있습니다.
RDS, Elastic Serach, gemini.api.key 등 깃허브에 올리면 안되는 정보들을 info.properties로 관리하여 해당 파일이 없으면 빌드가 되지 않습니다.

다음과 같은 정보들이 담긴 info.propeties 파일이 있어야 빌드가 가능합니다.

```
#RDS
spring.datasource.url= *
spring.datasource.username= *
spring.datasource.password= *
spring.datasource.driver-class-name= *

#금융 API 정보
api.key= *
institution.code= *
fintech.app.no= *
fintech.api.url= *

spring.elasticsearch.uris= *
spring.data.elasticsearch.repositories.enabled=true

jwt.expiration=3600000
spring.jwt.secret= *

gemini.api.url= *
gemini.api.key= *
```

필요시 추후 파일 제공하겠습니다.
