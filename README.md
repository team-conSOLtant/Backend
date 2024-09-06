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
- [포트폴리오 추천 시스템](https://github.com/team-conSOLtant/Backend/edit/master/README.md#%ED%8F%AC%ED%8A%B8%ED%8F%B4%EB%A6%AC%EC%98%A4-%EC%B6%94%EC%B2%9C-%EC%8B%9C%EC%8A%A4%ED%85%9C)

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

<GIF>

#### 예상 로드맵 생성

<GIF>
	
<br />
	
### 3. Elastic Search와 DB 동기화 문제
<p align = "center">
	<img width=600 src="https://github.com/user-attachments/assets/772753cb-8651-4647-ae4b-60823f660bda">

### 문제 상황

- 포트폴리오 데이터를 **DB**와 **Elastic Search(ES)**에 동기화하기 위해 전체 데이터를 저장할 때 **ES**에도 함께 저장하도록 구현한 상황. 그러나 일부 데이터를 **DB** 내에서 직접 수정할 때, **ES**에 그 수정 내용을 반영할 수 있는 방법이 없어 데이터의 불일치가 발생할 가능성이 있음.
- 또한, 대규모 데이터가 들어올 경우 실시간 반영을 위한 처리도 필요함. 이를 해결하기 위해 **Logstash**를 활용한 스케줄링 방식으로 **DB**와 **ES**의 동기화를 고려하고 있음.

### 해결 방안

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

> 
> 

### 박상욱

> 
> 

### 이동열

> "Everywhere Bank"라는 주제로 기획에 참여하여, 은행이 필요로 하는 데이터를 생각해 보고 이를 위해 제공해야 할 서비스를 고민할 수 있는 의미 있는 시간이었습니다. 2주간의 사전 개발 기간과 해커톤 기간 동안 엄청나게 몰입하여 개발에 집중했던 것 같습니다. 해커톤의 특성상 빠르게 필요한 기능을 구현해야 했고, 목표 달성을 위해 성과와 비용을 지속적으로 고려하며 판단해야 했지만, 그 과정이 잘 이루어져 만족스러웠습니다. 노력의 결실로 최우수상을 수상하게 되어 기분이 좋았고, 힘든 순간에도 서로 격려하며 끝까지 함께해 준 팀원들에게 감사한 마음이 듭니다.
>

