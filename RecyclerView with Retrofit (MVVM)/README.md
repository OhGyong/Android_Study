# MVVM: retrofit2로 RecyclerView에 데이터 삽입하기

1. 계층 구조 확립</br>
api, data, repository 클래스 생성</br>
- api는 retrofit2를 사용하여 서버와 통신
- data는 api 클래스에서 처리할 데이터를 정의하는 data 클래스
- repostiroy는 data 클래스의 data를 추상화

2. 서버 통신 작업
- gradle에 retrofit, gson 라이브러리 implementation
- manifests에 인터넷 권한, usesCleartextTraffic 권한 허용
- api, data, repository에 코드 작성

3. 프래그먼트 추가
- navigation 라이브러리 implementation
- activity_main에 FragmentContainerView를 사용하여 앱 시작시 프래그먼트가 켜지게 함

4. ViewModel, LiveData 추가
- ViewModel을 생성하고 LiveData를 사용하여 서버에서 얻은 데이터를 출력