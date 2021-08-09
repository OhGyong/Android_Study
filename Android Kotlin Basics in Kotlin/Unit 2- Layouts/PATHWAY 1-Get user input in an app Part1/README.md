# PATHWAY 1: Get user input in an app Part1
[PATHWAY 1 사이트](https://developer.android.com/courses/pathways/android-basics-kotlin-unit-2-pathway-1)

## 1. Welcome to Unit 2: Layout
1. 팁 계산기 앱 프로젝트
2. 일일 명언 앱 프로젝트

두 개의 프로젝트를 진행하면서 클래스, 상속, 목록, 배열 등 코틀린의 기능을 학습

## 2. Tip calculator app introduction
텍스트 입력란에 텍스트를 입력하거나 라디오 버튼을 선택하는 등의 사용자가 앱에 정보를 입력하게 하는 방법을 배움</br>
- 팁 계산기 앱
    - 팁을 얼마나 줘야 하는지 계산할 수 있는 앱으로 텍스트 입력란에 서비스 가격을 입력한다. 라디오 버튼에서 서비스 만족도를 선택하여 선택 항목에따라 팁은 20%, 18%, 15%로 측정하여 팁 금액을 표시한다.


## 3. Classes and inheritance in Kotlin
[코틀린의 클래스 및 상속 설명](https://developer.android.com/codelabs/basic-android-kotlin-training-classes-and-inheritance?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-2-pathway-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-classes-and-inheritance#0)

- `클래스 계층구조`
    - 채소라는 카테고리 속에서 콩류라는 좀 더 구체적인 카테고리를 나누기 위한 관계를 프로그래밍 측면에서 어떻게 할까?</br>
    Vegetable이라는 클래스를 만들게 되면 Legume라는 클래스를 Vegetable 클래스의 하위 또는 서브 클래스로 만들 수 있다. 이렇게 되면, Vegetable 클래스의 모든 속성과 메서드가 Legume 클래스에 상속이 가능하다(사용이 가능하다).</br>
    ![image](https://user-images.githubusercontent.com/52282493/128620251-9d985148-4667-4d8b-a665-e966a931c6d9.png) </br>
    위 그림과 같이 Vegetable을 Legume 클래스의 상위 또는 슈퍼 클래스로 지칭한다.</br>
    하위 클래스인 Legume에서 또 다른 서브클래스를 만들어서 계층 구조를 더욱 확장할 수 있다. 예를 들면 콩류인 Lentil이나 Chickpea 클래스를 생성할 수 있다.</br>
    ![image](https://user-images.githubusercontent.com/52282493/128620306-f7942102-660d-44ba-bb1b-c1cef331a2be.png) </br>
    위 그림에서 Vegetable은 루트 또는 최상위 클래스라고 지칭한다.
    - 안드로이드에서의 클래스 상속은 어떻게 될까?</br>
    Unit 1에서 진행했던 프로젝트를 보면 굳이 클래스를 사용하지 않고도 코드를 작성하여 진행할 수 있었다. 하지만 안드로이드의 여러 부분에서 Activity, View, ViewGroup 등 클래스 형태로 제공을 한다. 따라서 클래스 계층 구조를 이해하는 것은 안드로이드 앱 개발에 있어서 중요하며, 이를 토대로 안드로이드 프레임워크에서 제공하는 기능을 활용할 수 있다.</br>
    안드로이드에서 화면의 직사각형 영역에 그리기와 이벤트 처리를 담당하는 View 클래스가 있다고 생각해보자.</br>
    TextView 클래스는 View 클래스의 서브 클래스이므로 View의 클래스의 모든 속성과 기능을 상속받아 사용자에게 텍스트를 보여주는 특정 로직을 추가한다.</br>
    더 나아가 EditText와 Button 클래스는 TextView의 하위 클래스이다. 마찬가지로 TextView와 View 클래스의 모든 속성과 기능을 상속받아 로직을 추가할 수 있다. 각 클래스의 코드는 해당 요소에 맞는 부분에 중점을 둘 수 있다.</br>
    ![image](https://user-images.githubusercontent.com/52282493/128620589-d7be1192-ba3c-4d06-8efb-f1c8a2ef9043.png) <br>
    참고로 코틀린에는 모든 클래스에 공통 슈퍼 클래스인 Any가 존재한다.</br>
    ![image](https://user-images.githubusercontent.com/52282493/128620600-45831b40-7a24-4464-8f58-e518f1846646.png)
    
- `abstract(추상 클래스)` 완전히 구현되지 않아서 인스턴스화 할 수 없는 클래스. 스케치라고 생각하면 된다.</br>
abstract로 클래스를 생성시 내부 변수에 값이 선언되지 않았다면 abstract를 붙여주어야 한다. 추상 함수 및 변수를 선언하는 것은 나중에 서브 클래스에서 값과 나머지 구현을 해야 한다는 약속이다.
- `private` private 접근자가 표시된 변수는 내부 클래스에서만 사용할 수 있고, 프로그램의 다른 위치에서는 액세스할 수 없도록 막는다.
- `constructor(생성자)` 클래스에서 객체 인스턴스를 만들 때 호출된다. 호출이 되면 객체 인스턴스를 초기화한다.
- `override` 서브 클래스에서 override를 통해 상위 클래스에서 정의된 값을 재정의 할 것이라고 나타낸다.
- `with` 상속받은 변수에서 속성이나 함수를 참조할 때 '변수.~'을 사용하게 되는데 이러한 반복적 행위를 예방하여 코드를 단순화 할 수 있는 명령어이다.
- `open` 코틀린에서는 부모 클래스에 open을 붙여줘야 한다. 단, 추상 클래스에는 open을 쓰지 않아도 된다.
- `라이브러리` 프로그램이 사용할 수 있는 프로그램 외부에 정의된 함수 및 값의 사전 정의된 모음
- `kotlin.math.PI , kotlin.math.sqrt` 수학 라이브러리에서의 파이와 제곱근 함수
- `super` 상위 클래스에 정의된 함수를 호출 → super.'상위 클래스 함수명'

[계층구조 공부를 위해 작업한 코드](https://github.com/OhGyong/Android_Study/tree/master/Android%20Kotlin%20Basics%20in%20Kotlin/Unit%202-%20Layouts/PATHWAY%201-Get%20user%20input%20in%20an%20app%20Part1/PATHWAY1-3%20code)


## 4. Create XML layouts for Android
[팁 계산기 앱 xml 레이아웃 만들기](https://developer.android.com/codelabs/basic-android-kotlin-training-xml-layouts?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-2-pathway-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-xml-layouts#0)

- `XML` XML은 확장성 마크업 언어를 의미하며 텍스트 기반 문서를 사용하여 데이터를 표현한다. 각 UI 요소는 XML 파일의 XML 요소로 표현된다. 각 요소는 태그로 시작하고 끝난다(<와 >).
- `XML 요소, 속성` '빈 요소 태그' 라고 하는 약식 표현과 요소를 채운 종료 태그는 같은 의미이다. → <'TextView'> />a == <'TextView'></'TextView'>
- `androidx.constraintlayout.widget.ConstraintLayout` ConstraintLayout이 안드로이드 플랫폼 이외에도 추가 기능을 제공하는 코드 라이브러리가 포함된 Android Jetpack의 일부이기 때문이다. UI 구성요소가 'adroidx'로 시작하는 것은 Jetpack의 일부인 것을 알 수 있다.
- `Android Jetpack` 앱을 더 쉽게 빌드하는데 활용할 수 있는 유용한 기능이 집약된 라이브러리이다.
- `xmlns` XML 네임스페이스를 나타내고 각 줄은 스키마 같은 속성의 어휘를 정의한다.
- `XML에서의 주석, <!-- -->`
- `<?xml version="1.0" encoding="utf-8"?>` 파일이 XML 파일이지만 모든 XML 파일에 이 내용이 포함되는 것은 아님을 나타낸다.
- `padding` 패딩은 여백과 비슷하지만 외부에 공간을 추가하는 대신 layout 내부에 공간을 추가한다.
- `constraintlayout 제약조건` LTR(왼쪽에서 오른쪽) 또는 RTL(오른쪽에서 왼쪽) 언어에서 모두 작동할 수 있도록 제약 조건을 추가한다.
- `EditText의 속성` inputType으로 text나 number로 지정, hint 사용