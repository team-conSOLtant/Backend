# 📈 SOL 학생 로드맵 - Back-end

```git
⚡ 2024.08.16 ~ 2024.09.01
```

## 🛠 Using Stacks <br/>

```git
- JAVA 17
- Spring Boot 3.3.2
- gradle 8.8
- MySql(RDS) 8.0.35
- ElasticSearch 8.7.1
- Gemini-pro
```

## 아키텍처
<p align = "center">
	<img width=600 src="https://github.com/user-attachments/assets/419aa979-48b3-4e02-9165-4ca80f839260">
</p>

<br />

## Java Package Convention

```markdown
|---- domain
		|---- controller
		|---- dto
		    |---- request.class
			  |---- response.class
		|---- service
				|---- ModuleService (Only CRUD)
				|---- Service
		|---- repository
		|---- mapper
		|---- entity
|---- util
		|---- constant
|---- config
```

<br />

## 📌주요 기능
1. [포트폴리오 추천 시스템](https://github.com/team-conSOLtant/Backend?tab=readme-ov-file#1-%ED%8F%AC%ED%8A%B8%ED%8F%B4%EB%A6%AC%EC%98%A4-%EC%B6%94%EC%B2%9C-%EC%8B%9C%EC%8A%A4%ED%85%9C)
2. [금융 API 활용](https://github.com/team-conSOLtant/Backend?tab=readme-ov-file#2-%EA%B8%88%EC%9C%B5-api-%ED%99%9C%EC%9A%A9)
3. [Elasticsearch 활용 포트폴리오 검색](https://github.com/team-conSOLtant/Backend?tab=readme-ov-file#3-elastic-search%EC%99%80-db-%EB%8F%99%EA%B8%B0%ED%99%94-%EB%AC%B8%EC%A0%9C)
4. [CSV 파싱](https://github.com/team-conSOLtant/Backend?tab=readme-ov-file#4-csv-%ED%8C%8C%EC%8B%B1)

<br />

### 1. 포트폴리오 추천 시스템
> - 사용자의 전공, 수강 과목의 겹침 정도, 학점 유사도를 종합적으로 고려하여 맞춤형 동문 포트폴리오를 제공.
> - 점수 계산 방식: 전공 관련성 10점, 수강 과목이 겹치는 개수 × 2점, 학점 차이에 따라 0.5점 차이 +3점, 1점 차이 +1점.
> - 매일 자정 새로운 포트폴리오를 추천하는 **스케줄링** 기능 포함, 이미 추천된 포트폴리오는 재추천되지 않도록 설계.

<p align = "center">
	<img width=600 src="https://github.com/user-attachments/assets/8bf647d0-7318-48fa-a241-5b288dd5a598">
</p>

<br />

### 2. 금융 API 활용
#### 모범 로드맵 추천
> - 금융 키워드 별 로드맵 분류 [소확행, 중확행, 대확행].
> - 모범 로드맵을 선정 후 사용자와 유사한 월급 및 초기 자본을 기준으로 추천.

#### 예상 로드맵 생성
> - 추천받은 모범 로드맵과 사용자의 예상 로드맵을 시각화하여 비교.
> - 모범 로드맵의 상품을 자신의 로드맵에 추가하여 예상 로드맵의 변화 확인 가능.

 <p align = "center">
	<img width=600 src="https://github.com/user-attachments/assets/9cc864dd-5804-4ed2-8192-9e5b10b4cb08">
</p>

<br />

#### 문제 상황
> 금융 API 통신 시 과도한 중복 코드 발생
- 삼성 청년 SW 아카데미에서 제공하는 금융 API의 통신 형식 중 Body에 key를 비롯한 9개의 정보와 RequestDto를 RestTemplate Entity에 추가해주는 코드가 많이 중복되어 가독성이 떨어지는 상황이 발생했음
- Service에 금융 API 응답을 전달하기 위해서 30개에 달하는 API 별 전용 Dto를 추가로 개발해야하기 때문에 개발량이 매우 많아짐
- 매 요청마다 반드시 거래고유번호 생성하여 Header에 포함시켜줘야 하는 문제가 발생했음
- 금융 API 카테고리 ID 매핑을 위한 Enum 사용과 시간 저장 방식의 차이로 인해 금융 API Dto와 Service Dto간의 Type 불일치 발생

#### 해결 방안

1. **공통 Header 추가**
    - 공통된 Header를 Template으로 분리하여 API 요청 시 Header 추가 로직의 중복을 제거
    - 정수형 UUID와 LocalDateTime을 활용한 거래고유번호를 생성하여 금융 거래 요청마다 고유한 번호를 부여
    - Value 어노테이션을 사용하여 Header에서 사용되는 불변 값들을 주입
2. **공통 Response 추가**
    - Generic Type을 통해 30개의 ResponseDto들이 3개의 Response Template을 공유하여 각 API 별 Response 개발 시간 단축
3. **MapStruct를 사용한 Dto 매핑**
    - Mapper 어노테이션을 활용하여 Enum, LocalDate ↔ String 매핑해주는 작업을 멤버 메소드를 활용하여 개발 시간 단축 및 Type 검사를 통한 안정성 확보

<br />

### 3. Elasticsearch 활용 포트폴리오 검색
> - Elasticsearch의 역인덱스 기법과 단일 키워드 검색을 이용하여 O(1)의 시간 복잡도로 시간 최적화를 진행함
> - MySQL로 구현할 시 5개의 테이블 조인과 `%like%` 로 전체 탐색을 해야해서 O(N)의 시간 복잡도를 가짐
> - 포트폴리오 데이터를 **DB**와 **Elasticsearch**에 동기화하기 위해 전체 데이터를 저장할 때 **ES**에도 함께 저장하도록 구현

<p align = "center">
	<img width=600 src="https://github.com/user-attachments/assets/772753cb-8651-4647-ae4b-60823f660bda">
</p>

#### 문제 상황
> Elasticsearch와 DB 동기화 문제
- 일부 데이터를 **DB** 내에서 직접 수정할 때, **ES**에 그 수정 내용을 반영할 수 있는 방법이 없어 데이터의 불일치가 발생할 가능성이 있음.
- 또한, 대규모 데이터가 들어올 경우 실시간 반영을 위한 처리도 필요함. 이를 해결하기 위해 **Logstash**를 활용한 스케줄링 방식으로 **DB**와 **ES**의 동기화를 고려하고 있음.

#### 해결 방안

1. **DB에 `updated_at` 칼럼 추가**:
    - 데이터가 수정될 때마다 `updated_at` 칼럼을 자동으로 갱신하여 변경된 데이터를 추적할 수 있도록 DB 구조를 수정.
2. **Logstash를 통한 변화 감지**:
    - Logstash를 이용하여 일정 주기로 DB와 ES 간의 데이터를 동기화.
    - **`updated_at`** 칼럼을 기준으로 변경 내역을 추적하여 변경된 데이터만 ES에 반영하도록 함. 이를 통해 전체 데이터를 다시 처리하지 않고 효율적으로 동기화 가능.
3. **동기화 작업**:
    - Logstash의 스케줄링을 통해 주기적으로 DB의 변경 내역을 확인하고, 변경된 데이터가 있을 경우 이를 ES에 실시간으로 반영.
    - 이를 통해 대규모 데이터가 들어오거나 DB 내에서 수정이 발생해도 ES에 실시간으로 반영되도록 처리.

<br />

### 4. CSV 파싱
> 수강한 과목과 성적을 직접 입력할 필요 없이, 대학에서 제공하는 CSV 파일을 넣으면 자동으로 수강 과목 및 학점 계산
<p align = "center">
	<img width=600 src="https://github.com/user-attachments/assets/ba5f991c-99c8-4693-8b25-70a09ee00098">
</p>

<br />

## 📝느낀점

### 고다현

> 주제가 Everywhere Bank 인 만큼 대학생부터 은퇴이후까지 고객을 관리해주는 서비스는 주제와 적합도가 높다고 판단했습니다. 이를 시각화 하기 위해 `Chart.js`로 고객의 자산 현황을 표현하고 검색 시간 최적화를 위해 `Elasticsearch`를 도입하여 사용하기 편리하고 필요한 서비스를 개발하기 위해 노력했습니다.

> 해커톤 1차 평가를 진행하며 'Mau 500만에 달성하면 Elasticsearch, MySQl 대용량 처리와 동기화를 어떻게 할 것인가' 라는 질문에 단순히 검색 최적화만 생각해야 하는 것이 아닌 기업에서는 대용량 처리에 관심이 많고 이를 고민해야 함을 할게되었습니다. 그리하여 해커톤 이후 데이터 동기화를 위한 `Elasticsearch의 노드 구성법`과 `logstash를 활용한 스케쥴링 방식`을 통한 MySQL과 Elasticsearch의 동기화에 대하여 개발을 진행하며 고민을 하고 있습니다.

> 1차, 2차 심사위원 분들의 질의 응답을 받을 수 있는 기회에 감사함을 느끼고 1달간 쉬지 않고 함께 달려와준 팀원들 덕분에 좋은 결과가 있을 수 있어서 정말 감사했습니다.
 

### 박상욱

> 제한된 시간내에 서비스를 개발해야 해커톤 참여를 통해 짤은 시간 내에 주어진 문제를 해결할 수 있는 능력을 기를 수 있었습니다. 금융 API를 다뤄보면서 간단하게 생각했던 은행 서비스가 실제로 코어 뱅킹 서비스에서는 매우 복잡하게 이루어져있다는 것을 알게 됐습니다. 기획 기간을 포함하여 1달이라는 시간 동안 팀원들과 함께 개발을 진행하고 최우수상을 수상하게 돼서 열심히 달려온 팀원들에게 고맙다는 말을 전하고 싶습니다. 또한 나 뿐만이 아니라 팀원 모두가 함께 지속할 수 있는 개발 방식에 대해서 다시 생각하고 고민을 해보는 계기가 됐습니다.

### 이동열

> "Everywhere Bank"라는 주제로 기획에 참여하여, 은행이 필요로 하는 데이터를 생각해 보고 이를 위해 제공해야 할 서비스를 고민할 수 있는 의미 있는 시간이었습니다. 2주간의 사전 개발 기간과 해커톤 기간 동안 엄청나게 몰입하여 개발에 집중했던 것 같습니다. 해커톤의 특성상 빠르게 필요한 기능을 구현해야 했고, 목표 달성을 위해 성과와 비용을 지속적으로 고려하며 판단해야 했지만, 그 과정이 잘 이루어져 만족스러웠습니다. 노력의 결실로 최우수상을 수상하게 되어 기분이 좋았고, 힘든 순간에도 서로 격려하며 끝까지 함께해 준 팀원들에게 감사한 마음이 듭니다.

