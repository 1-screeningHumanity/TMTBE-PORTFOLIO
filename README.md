# Portfolio Server

## 📖 Description
- ReadOnly Server 에 있는 MemberStock 테이블에 접근합니다.
- 매수, 매도 기록은 MemebrStock 테이블을 참고합니다.
- Redis에 존재하는 현재 주식 시세를 비교후, 보유하고 있는 주식 평가금액을 제공합니다.

## ⚙ Function
1. 내 보유 주식 조회가 가능합니다.
2. 다른 회원이 보유한 주식 조회가 가능합니다.
3. 주식 가격을 실시간으로 업데이트되는 데이터를 사용해 실시간성을 보장합니다.
   
## 🔧 Stack
 - **Language** : Java 17
 - **Library & Framework** : Spring Boot 3.3.0
 - **Database** : MYSQL, Redis
 - **ORM** : ""
 - **Deploy** : AWS EC2 / Jenkins
 - **Dependencies** : JWT, Redis, Validation, Lombok, Model Mapper, Swagger, Eureka, FeignClient, QueryDsl, JPA, Spring Web

## 🔧 Architecture
- **Design Patter** : Hexagonal Architecture
- **Micro Service Architecture** : Spring Cloud
- **Event-Driven Architecture** : Kafka

## 👨‍👩‍👧‍👦 Developer
*  **강성욱** ([KangBaekGwa](https://github.com/KangBaekGwa))
*  **김도형** ([ddohyeong](https://github.com/ddohyeong))
*  **박태훈** ([hoontaepark](https://github.com/hoontaepark))
