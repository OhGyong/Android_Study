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

![image](https://user-images.githubusercontent.com/52282493/224609440-8ba4d758-957c-4cb9-8a31-99bdc92c764c.png)<br/>
[종속 항목 수동 삽입 항목에 첨부된 이미지]

<br/>

위 이미지에서 ViewModel에 사용된 클래스를 class A, Repository에 사용된 클래스를 class B라고 하자.<br/>
A 클래스는 B 클래스에 종속되어 A에서 B까지를 잇는 선을 연결할 수 있고 이는 종속성을 나타낸다.

<br/>

DI를 사용하면 클래스를 쉽게 연결할 수 있고 테스트를 위해 구현을 교체할 수 있다.<br/>
예를 들어 Repository에 종속된 ViewModel을 테스트할 때 Repository를 다르게 구현하여 다양하게 테스트 할 수 있다.

---

## Hilt
안드로이드에서는 DI를 위해 **Dagger**를 사용했지만 Dagger를 기반으로 빌드된 새로운 Jetpack 라이브러리인 **Hilt**가 권장되고 있다.

Hilt는 프로젝트의 모든 Android 클래스에 컨테이너를 제공하고 생명 주기를 자동으로 관리함으로써 애플리케이션에서 DI를 실행하는 표준 방법을 정의한다.

1. gradle에 의존성 추가
   + java 8 이상인지 확인
2. Hilt 애플리케이션 클래스 생성
   + Hilt를 사용하는 모든 앱은 **@HiltAndroidApp**으로 주석이 지정된 Application 클래스를 포함
3. Android 클래스에 종속 항목 삽입
4. **@Inject**을 사용하여 클래스의 인스턴스를 제공하는 방법을 Hilt에 알려줌

---

03.15<br/>
https://developer.android.com/codelabs/android-hilt?hl=ko#0 를 보면서

## 컨테이너
코드베이스에 종속 항목을 제공하는 클래스로, 다른 유형의 앱 인스턴스를 만드는 방법을 알고 있음.<br/>
그리고, 인스턴스를 생허아고 생명 주기를 관리하여 인스턴스를 제공하는데 필요한 종속 항목 그래프를 관리함.

컨테이너에서 제공하는 유형의 인스턴스를 가져올 수 있는 메서드를 노출함.

다른 블로그에서는 인스턴스를 저장하는 공간을 컨테이너라고 한다고..

<br/>
 
## @HiltAndroidApp
- 앱의 생명 주기에 연결된 컨테이너를 추가하려면 @HiltAndroidApp을 클래스 상단에 선언해야 함.
- 애플리케이션 수준 종속 항목 컨테이너 역할을 하는 애플리케이션의 기본 클래스가 포함된 Hilt 코드 생성을 트리거 함.

<br/>

- 컴파일 타임 시 표준 컴포넌트 빌딩에 필요한 클래스들을 초기화함.
- Hilt 셋업을 위해서 필수적으로 요구되는 과정.

<br/>

## @AndroidEntryPoint
- Android 클래스 생명 주기를 따르는 종속 항목 컨테이너가 생성되고, 해당 Fragment(Activity)에 인스턴스를 삽입할 수 있음
- 해당 주석이 있는 다른 Android 클래스에 종속 항목을 제공할 수 있음
- 이 클래스에 종속된 다른 Android 클래스에도 주석을 지정해야 함.
- 프로젝트의 각 Android 클래스에 관한 개별 Hilt 구성요소를 생성함.

<br/>

- 인스턴스를 주입할 대상에게 @AndroidEntryPoint를 추가하는 것만으로도 의존성 주입을 할 수 있음.

<br/>

## @Inject
- @Inject 주석을 사용하여 Hilt에서 삽입하려는 다른 유형의 인스턴스를 필드에 삽입하도록 할 수 있음. (필드 삽입)
- 종속 항목 제공 방법을 알리는 용도.

<br/>

- 종속 항목을 가져오는 방법

<br/>

- 클래스의 인스턴스 제공 방법을 Hilt에 알리기 위한 키워드

<br/>

## @Singleton
- 인스턴스 범위를 애플리케이션 컨테이너로 지정함. 이 주석을 사용하면 유형이 다른 유형의 종속 항목으로 사용되는지 또는 삽입된 필드여야 하는지와 관계없이 애플리케이션 컨테이너에서 항상 같은 인스턴스를 제공함.

</br>

## Hilt 모듈
- 모듈을 사용하여 Hilt에 다양한 유형의 인스턴스 제공 방법을 알림.
- 인터페이스나 프로젝트에 포함되지 않은 클래스(외부 라이브러리)와 같이 생성자가 삽입될 수 없는 유형의 결합을 Hilt 모듈에 포함함.<br/>
ex) 빌더를 사용하여 인스턴스를 생성해야 하는 OkHttpClient는 모듈을 사용해야 함.
- Hilt 모듈은 @Module과 @InstallIn 주석이 달린 클래스.
  - ### @Module
    - Hilt에 모듈임을 알려줌
  - ### @IntsallIn
    - 어느 컨테이너에서 Hilt 구성요소를 지정하여 결합을 사용할 수 있는지 Hilt에 알려줌.<br/>
      Hilt 구성요소는 컨테이너로 간주할 수 있으며 구성요소 전체 목록은 [여기](https://developer.android.com/training/dependency-injection/hilt-android?hl=ko#generated-components)를 참고.
    - ApplicationComponent는 deprecated가 되어 SingletonComponent를 사용해야 함.
  - ### @Provides
    - Hilt 모듈에 있는 함수에 @Provides 주석을 달아 Hilt에 생성자가 삽입될 수 없는 유형의 제공 방법을 알려 줄 수 있다.
    - 이 주석이 있는 함수 본문은 Hilt에서 이 유형의 인스턴스를 제공해야 할 때마다 실행됨

## @Binds
- 인터페이스에 사용할 구현을 Hilt에 알리려면 Hilt 모듈 내 함수에 @Binds 주석을 사용하면 됨.
- 이 주석은 추상 함수에 달아야 함.


---

https://developer.android.com/training/dependency-injection/hilt-jetpack?hl=ko

Hilt에는 다른 Jetpack 라이브러리의 클래스를 제공하기 위한 확장 프로그램이 포함되어 있음.<br/>
-> ViewModel, Navigation, Compose, WorkManager

## @HiltViewModel
해당 주석으로 ViewModel 객체의 생성자에서 @Inject 주석을 사용하여 ViewModel을 제공.