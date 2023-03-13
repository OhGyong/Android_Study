[DI 개요](https://developer.android.com/training/dependency-injection?hl=ko)

## DI(Dependency Injection)<br/>
DI는 '종속 항목 삽입'이라고 하며 훌륭한 앱 아키텍처의 토대가 되는 프로그래밍 기법이다.<br/>

DI를 구현하면 아래와 같은 이점을 얻을 수 있다.<br/>
- 코드의 재사용성 증가
- 리팩토링 편의성 증가(유지보수 용이)
- 테스트 편의성

<br/>

안드로이드의 권장 앱 아키텍처는 코드를 클래스로 분할하여 관심사를 분리하는 이점을 얻는 것을 권장한다.<br/>
관심사 분리는 계층 구조의 각 클래스가 하나의 정의된 기능을 갖는 것이 원칙이다.<br/>
클래스 간의 종속성은 그래프로 표시될 수 있고 그래프에서 각 클래스는 종속된 클래스에 연결된다.

![image](https://user-images.githubusercontent.com/52282493/224609440-8ba4d758-957c-4cb9-8a31-99bdc92c764c.png)
[종속 항목 수동 삽입 항목에 첨부된 이미지]

<br/>

위 이미지에서 ViewModel에 사용된 클래스를 class A, Repository에 사용된 클래스를 class B라고 하자.<br/>
A 클래스는 B 클래스에 종속되어 A에서 B까지를 잇는 선을 연결할 수 있고 이는 종속성을 나타낸다.

<br/>

DI를 사용하면 클래스를 쉽게 연결할 수 있고 테스트를 위해 구현을 교체할 수 있다.<br/>
예를 들어 Repository에 종속된 ViewModel을 테스트할 때 Repository를 다르게 구현하여 다양하게 테스트 할 수 있다.