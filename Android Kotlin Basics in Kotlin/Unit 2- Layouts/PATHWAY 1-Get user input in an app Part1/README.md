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
    
