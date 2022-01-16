# MVVM: retrofit2로 RecyclerView에 데이터 삽입하기

1. 계층 구조 확립</br>
api, data, repository 클래스 생성</br>
- api는 retrofit2를 사용하여 서버와 통신
- data는 api 클래스에서 처리할 데이터를 정의하는 data 클래스
- repostiroy는 data 클래스의 data를 추상화

2. 서버 통신 작업
