# 🗺️ SSAFY EnjoyTrip (전국 관광지 및 축제 정보 시스템)

> **SSAFY EnjoyTrip**은 전국 관광지 및 축제 데이터를 파싱하여 사용자에게 편리한 관광지 검색 및 축제 정보를 제공하는 Java Swing 기반 데스크톱 애플리케이션입니다.

---

## 📌 프로젝트 개요 (Project Overview)

- **프로젝트명**: SSAFY EnjoyTrip
- **개발 기간**: 2026.07
- **소개**: 공공데이터(XML/CSV/JSON) 기반의 전국 관광지 표준 데이터를 활용하여, 사용자가 원하는 지역 및 키워드로 관광지 및 축제 정보를 손쉽게 검색하고 상세 정보를 확인할 수 있도록 개발된 Java 응용 프로그램입니다.
- **주요 목표**:
  - **XML SAX Parser**를 활용한 대용량 관광지 데이터의 효율적인 메모리 파싱
  - **Layered Architecture (View - Service - DAO - DTO)** 패턴 적용으로 모듈화 및 유지보수성 확보
  - **Java Swing & 람다(Lambda)** 표현식을 활용한 현대적 GUI 구현 및 반응형 이벤트 처리

---

## 🛠️ 기술 스택 (Tech Stack)

### Language & Environment
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Eclipse](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipseide&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)

### Core Technologies
- **GUI Framework**: Java Swing (`JFrame`, `JTable`, `JPanel` 등)
- **Data Parsing**: Java SAX Parser (`DefaultHandler`, `SAXParserFactory`)
- **Design Pattern**: MVC / Layered Architecture (View, Service, DAO, DTO, Exception)
- **Java 8+ Features**: Lambda Expressions, Stream API

---

## 👥 팀원 및 역할 분담 (Team & Roles)

Git 커밋 및 머지 이력 분석을 바탕으로 정리한 각 팀원의 담당 역할 및 주요 기여 내역입니다.

| 이름 / GitHub ID | 담당 역할 | 주요 기여 내역 (Git Commit History) |
| :--- | :--- | :--- |
| **장지형** <br>(`medAndro` / `Meda`) | **Backend Lead & Service/DAO** | • **Model / Service / DAO 레이어 구축**: `TripDaoImpl`, `TripServiceImpl` 검색(search) 로직 및 데이터 매핑 구현<br>• **축제(Festival) 모듈 신규 개발**: `FestivalDto`, `FestivalDao`, `FestivalDaoImpl` 작성 및 `TripServiceImpl` 연결<br>• **UI 동적 기능 연동**: `TripInfoView` 내 관광지 랜덤 이미지 표시 기능 구현<br>• **코드 통합 및 PR 머지**: Feature 브랜치(#2, #3) 리뷰 및 메인 브랜치 통합 관리 |
| **서산** <br>(`dannysir`) | **Data Parsing & Utility** | • **XML SAX Parser 구현**: `TouristDestinationSAXHandler`, `TouristDestinationSAXParser`를 활용한 데이터 파싱 처리<br>• **데이터 정교화 및 버그 수정**: 관광지명 파싱 과정의 띄어쓰기/특수문자 파싱 버그 수정 및 예외 처리<br>• **단위 테스트**: `SaxTest` 작성 및 데이터 추출 검증 |
| **maark1106** <br>(`maark1106`) | **Frontend GUI & Event Handling** | • **프로젝트 초기화**: Base 패키지 구조 설계 및 프로젝트 Initial Commit<br>• **Swing GUI 뷰 구축**: `TripInfoView` 사용자 인터페이스 레이아웃 설계 및 컴포넌트 배치<br>• **이벤트 처리 리팩토링**: 검색 버튼 클릭 이벤트 핸들러를 람다(Lambda) 표현식으로 개선하여 코드 간결성 확보 |

---

## ✨ 주요 기능 (Key Features)

1. **전국 관광지 SAX 파싱 & 데이터 파싱 (XML SAX Parser)**
   - 대용량 `전국관광지정보표준데이터.xml` 파일을 SAX 방식을 통해 빠른 속도와 최소한의 메모리로 파싱
   - 관광지명, 주소, 전화번호, 위도/경도, 개요 등 주요 정보 추출

2. **관광지 및 축제 정보 다중 조건 검색 (Search & Filter)**
   - 지역(Sido/Gugun) 및 검색 키워드 기반의 동적 조건 검색 기능
   - `TripService` 및 `TripDao`를 통한 데이터 필터링

3. **축제/행사 정보 연동 (Festival Module)**
   - `FestivalDao` 및 `FestivalDto` 기반으로 축제 데이터 관리 및 연동

4. **Java Swing GUI & 이미지 동적 바인딩**
   - 람다식(Lambda Expression) 기반의 이벤트 리스너 등록으로 직관적이고 깔끔한 코드 구조
   - 검색 결과 선택 시 해당 관광지/축제에 대한 상응 이미지 및 랜덤 썸네일 표시

---

## 📁 프로젝트 구조 (Project Structure)

```text
ssafy-enjoytrip/
├── src/
│   └── com/
│       └── ssafy/
│           └── trip/
│               ├── Main.java                        # 애플리케이션 메인 실행 클래스
│               ├── ApiExplorer.java                 # 공공데이터 API 연동 탐색기
│               ├── EnjoyTripException.java          # 전용 커스텀 예외 클래스
│               ├── model/
│               │   ├── dao/                         # Data Access Object Layer
│               │   │   ├── TripDao.java
│               │   │   ├── TripDaoImpl.java
│               │   │   ├── FestivalDao.java
│               │   │   └── FestivalDaoImpl.java
│               │   ├── dto/                         # Data Transfer Object Layer
│               │   │   ├── TripDto.java
│               │   │   ├── TripSearchDto.java
│               │   │   └── FestivalDto.java
│               │   └── service/                     # Service Layer (비즈니스 로직)
│               │       ├── TripService.java
│               │       └── TripServiceImpl.java
│               ├── util/                            # 파서 및 유틸리티
│               │   ├── TouristDestinationSAXHandler.java
│               │   ├── TouristDestinationSAXParser.java
│               │   └── SaxTest.java
│               └── view/                            # GUI View Layer (Swing)
│                   └── TripInfoView.java
├── res/                                             # 데이터 리소스 (XML, JSON, CSV)
│   ├── 전국관광지정보표준데이터.xml
│   ├── 전국관광지정보표준데이터.json
│   └── 전국관광지정보표준데이터.csv
├── img/                                             # 관광지 및 UI 이미지 리소스
├── uml/                                             # 클래스 다이어그램 및 설계 문서
│   └── EnjoyTrip.cld
└── README.md                                        # 프로젝트 설명 문서
```

---

## 🚀 실행 방법 (Getting Started)

### 요구 사항 (Prerequisites)
- **Java SE Development Kit (JDK)** 8 이상
- **Eclipse IDE** 또는 **IntelliJ IDEA**

### 실행 방법 (Execution Steps)
1. Repository 클론
   ```bash
   git clone https://github.com/ssafy-14-3/ssafy-enjoytrip.git
   ```
2. IDE(Eclipse/IntelliJ)에서 프로젝트 Open / Import (`Existing Projects into Workspace`)
3. `src/com/ssafy/trip/Main.java` 선택 후 `Run As > Java Application` 실행

---

## 🔀 Git Commit & Workflow Strategy

본 프로젝트는 Git-Flow 기반의 기능별 브랜치 전략을 준수하였습니다.

- `master`: 배포 가능한 안정적인 메인 브랜치
- `feature/trip-info-view`: UI 뷰 및 람다 이벤트 처리 개발 브랜치 (PR #1)
- `feat/daoserviceimpl`: DAO 및 Service 비즈니스 로직 연동 브랜치 (PR #2)
- `feature/sax`: XML SAX 파서 및 데이터 정구화 개발 브랜치 (PR #3)

---

## 📄 라이선스 (License)

본 프로젝트는 SSAFY(삼성청년SW아카데미) 교육과정의 일환으로 제작된 프로젝트입니다.
