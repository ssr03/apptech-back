# Apptech-back

Restful Api Server With Spring Boot

## Prerequisites

- java 17
- postgresql

## 개발 환경 구성

### Tools

- IntelliJ (Community ver.) for Backend (Spring boot)
- DBeaver for SQL (connect to DB)

### Settings

- Download Zulu 17 (Java)

### DB

> mariaDB

### DB 셋팅

- postresql 설치

```powershell
# postgresql 설치
brew install postgresql

# 서비스 실행
brew services start postgresql

# 실행 확인
postgres -V
```

- postgresql 사용자 권한 생성 및 사용자 생성

```powershell
# postsql 접속
psql postgres

# default 사용자 확인
\du

# db 생성
create database app_tech;

# user 생성
create user app_tech with encrypted password '1234';

# db생성 권한 부여
alter user app_tech createdb;

# user에게 app_tech db에 대한 권한 부여
grant all privileges on database app_tech to app_tech;
```

### **Project folder structure**

- core패키지: 공통기능(security, file, config등)
    - config: 설정 패키지
        - r2dbc: r2dc관련 설정 패키지
        - redis: redis 관련 설정 패키지
    - exception: 예외처리 관련 패키지
    - file: 파일 관련 패키지
    - security: security관련 패키지
    - util: 기타
- domain패키지
    - common: 공통으로 사용하는 domain 패키지
    - user: 사용자 domain 패키지
        - api: 컨트롤러 패키지
        - dto: 주로 Request/Response객체들로 구성된 패키지
        - entity:도메인 엔티티 패키지
        - enums
        - exception: 예외 클래스
        - repository
        - service