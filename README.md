# CLUSH 백엔드 과제

## 1. 앱 설명
### 1.1 할 일(TO DO)
주요 기능:
- **기본 CRUD**: 할 일 생성, 상세 조회, 수정, 삭제
- **추가 업무 API**: 할 일 리스트 조회 기능 – 조건(ID, 제목, 내용, 상태, 우선순위 중 원하는 만큼 선택)에 따라 할 일 리스트를 정렬(생성일, 만료일, 우선순위 중 하나를 선택)하여 가져온다.

### 1.2 일정(CALENDAR)
주요 기능:
- **기본 CRUD**: 일정 생성, 상세 조회, 수정, 삭제
- **추가 업무 API**: 일정 공유 기능 – 특정 일정을 다른 사용자와 공유, 범위(오늘, 이번주, 이번달)에 맞는 일정 리스트 조회

## 2. 소스 빌드 및 실행 방법
### 2.1 빌드 방법
```bash
# 1. 저장소 클론
cd {저장소 저장 경로}
git clone https://github.com/gyulbbe/clush.git
cd clush

# 2-1. maven 의존성 설치(mac or lunux)
./mvnw clean install
# 2-2. maven 의존성 설치(windows)
mvnw.cmd clean install

# 3. JAR 파일 실행
bashCopyjava -jar target/clush.jar
```

### 2.2 설정 파일(aplication.properties)
```properties
# aplication.properties
server.port=80
spring.application.name=clush
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url={dbUrl}
spring.datasource.username={username}
spring.datasource.password={password}
mybatis.type-aliases-package=com.assignment.clush.**.vo, com.assignment.clush.**.dto
mybatis.mapper-locations=classpath:mappers/*.xml
mybatis.configuration.jdbc-type-for-null=null
```

### 2.3 DB 스키마 및 기초 데이터
```sql
CREATE TABLE TODO (
    TODO_NO INT PRIMARY KEY AUTO_INCREMENT,
    USER_ID VARCHAR(255) NOT NULL,
    TITLE VARCHAR(255) NOT NULL,
    CONTENT VARCHAR(255) NOT NULL,
    CREATED_DATE DATETIME NOT NULL,
    DUE_DATE DATETIME NOT NULL,
    MODIFIED_DATE DATETIME DEFAULT NULL,
    STATUS ENUM('PENDING', 'DONE', 'IN_PROGRESS') default 'PENDING' not null,
    PRIORITY INT NOT NULL
);

CREATE TABLE CALENDAR (
    CALENDAR_NO INT PRIMARY KEY AUTO_INCREMENT,
    USER_ID VARCHAR(255) NOT NULL,
    TITLE VARCHAR(255) NOT NULL,
    CONTENT VARCHAR(255) NOT NULL,
    START_DATE DATETIME NOT NULL,
    END_DATE DATETIME NOT NULL,
    CREATED_DATE DATETIME NOT NULL,
    MODIFIED_DATE DATETIME DEFAULT NULL,
    REPEATS ENUM('DAY', 'WEEK', 'MONTH', 'YEAR')
);

CREATE TABLE SHARE (
    SHARE_NO INT PRIMARY KEY AUTO_INCREMENT,
    CALENDAR_NO INT NOT NULL,
    SHARED_USER_ID VARCHAR(255) NOT NULL,
    FOREIGN KEY (CALENDAR_NO) REFERENCES CALENDAR(CALENDAR_NO) ON DELETE CASCADE
);

INSERT INTO TODO (USER_ID, TITLE, CONTENT, CREATED_DATE, DUE_DATE, PRIORITY, STATUS) VALUES
('clush1', 'clush title1', 'clush content1', '2025-02-10 14:00:00', '2025-02-14 14:00:00', 1, 'IN_PROGRESS'),
('clush2', 'clush title2', 'clush content2', '2025-02-10 14:00:00', '2025-02-14 14:00:00', 1, 'DONE'),
('clush3', 'clush title3', 'clush content3', '2025-02-10 14:00:00', '2025-02-14 14:00:00', 1, 'IN_PROGRESS'),
('clush4', 'clush title4', 'clush content4', '2025-02-10 14:00:00', '2025-02-14 14:00:00', 1, 'DONE'),
('clush5', 'clush title5', 'clush content5', '2025-02-10 14:00:00', '2025-02-14 14:00:00', 1, 'IN_PROGRESS'),
('clush1', 'clush title1', 'clush content1', '2025-02-19 14:00:00', '2025-02-23 14:00:00', 1, 'DONE'),
('clush2', 'clush title2', 'clush content2', '2025-02-19 14:00:00', '2025-02-23 14:00:00', 1, 'IN_PROGRESS'),
('clush3', 'clush title3', 'clush content3', '2025-02-19 14:00:00', '2025-02-23 14:00:00', 1, 'DONE'),
('clush4', 'clush title4', 'clush content4', '2025-02-19 14:00:00', '2025-02-23 14:00:00', 1, 'IN_PROGRESS'),
('clush5', 'clush title5', 'clush content5', '2025-02-19 14:00:00', '2025-02-23 14:00:00', 1, 'DONE');

INSERT INTO CALENDAR (USER_ID, TITLE, CONTENT, START_DATE, END_DATE, CREATED_DATE, REPEATS) VALUES
('clush1', 'clush title1', 'clush content1', '2025-02-10 14:00:00', DATE_ADD(NOW(), INTERVAL 2 DAY), '2025-02-09 14:00:00', 'DAY'),
('clush2', 'clush title2', 'clush content2', '2025-02-10 14:00:00', DATE_ADD(NOW(), INTERVAL 2 DAY), '2025-02-09 14:00:00', 'MONTH'),
('clush3', 'clush title3', 'clush content3', '2025-02-10 14:00:00', DATE_ADD(NOW(), INTERVAL 2 DAY), '2025-02-09 14:00:00', 'YEAR'),
('clush4', 'clush title4', 'clush content4', '2025-02-10 14:00:00', DATE_ADD(NOW(), INTERVAL 2 DAY), '2025-02-09 14:00:00', 'WEEK'),
('clush5', 'clush title5', 'clush content5', '2025-02-10 14:00:00', DATE_ADD(NOW(), INTERVAL 2 DAY), '2025-02-09 14:00:00', NULL),
('clush1', 'clush title1', 'clush content1', '2025-02-19 14:00:00', DATE_ADD(NOW(), INTERVAL 2 DAY), '2025-02-18 14:00:00', NULL),
('clush2', 'clush title2', 'clush content2', '2025-02-19 14:00:00', DATE_ADD(NOW(), INTERVAL 2 DAY), '2025-02-18 14:00:00', 'YEAR'),
('clush3', 'clush title3', 'clush content3', '2025-02-19 14:00:00', DATE_ADD(NOW(), INTERVAL 2 DAY), '2025-02-18 14:00:00', 'MONTH'),
('clush4', 'clush title4', 'clush content4', '2025-02-19 14:00:00', DATE_ADD(NOW(), INTERVAL 2 DAY), '2025-02-18 14:00:00', 'WEEK'),
('clush5', 'clush title5', 'clush content5', '2025-02-19 14:00:00', DATE_ADD(NOW(), INTERVAL 2 DAY), '2025-02-18 14:00:00', 'DAY');

INSERT INTO SHARE (CALENDAR_NO, SHARED_USER_ID) VALUES
(1, 'clush1'),
(2, 'clush2'),
(3, 'clush3'),
(4, 'clush4'),
(5, 'clush5'),
(6, 'clush1'),
(7, 'clush2'),
(8, 'clush3'),
(9, 'clush4'),
(10, 'clush5'),
(1, 'clush2'),
(2, 'clush3'),
(3, 'clush4'),
(4, 'clush5'),
(5, 'clush1'),
(6, 'clush2'),
(7, 'clush3'),
(8, 'clush4'),
(9, 'clush5'),
(10, 'clush1'),
(1, 'clush3'),
(2, 'clush4'),
(3, 'clush5'),
(4, 'clush1'),
(5, 'clush2'),
(6, 'clush3'),
(7, 'clush4'),
(8, 'clush5'),
(9, 'clush1'),
(10, 'clush2');

commit;
```

## 3. 테스트 케이스
https://docs.google.com/spreadsheets/d/14KH-tbQL683Cjwuq0T7sGRS3Yd5ZG7RpPvYLjPXLf-Y/edit?usp=sharing

## 4. 주력 라이브러리
- **Lombok** : 어노테이션 기반으로 간편하게 객체의 기본 메서드를 자동 생성하여 가독성 증가
- **Validation** : 데이터 무결성을 검증하여 애플리케이션의 안정성 확보
- **Swagger** : API 명세서를 생성해 문서 관리에 용이
- **MyBatis** : 복잡하거나 동적 SQL 작성에 용이하며, 중복 코드 작성을 줄일 수 있음

## 5. 요구 사항
- **Java 21**
- **SPRING BOOT 3.4.2**
- **Maven**
- **MySQL**
