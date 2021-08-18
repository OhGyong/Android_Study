# PATHWAY 1: Navigate between screens
[PATHWAY 1 사이트](https://developer.android.com/courses/pathways/android-basics-kotlin-unit-3-pathway-1)

## 1. Welcome to Unit 3: Navigation
이전 Unit들에서는 Layout이 한 개인 앱들을 개발하고 공부했다면 이번에는 탐색할 수 있는 여러 개의 화면이 있는 앱을 만들어 볼 것이다.

## 2. Collections in Kotlin
컬렉션, 코틀린에서의 람다 및 고차 함수에 관해 학습한다.

- `컬렉션` 단어 목록이나 직원 기록 모음과 같은 관련 항목 그룹이다. 컬렉션의 항목은 순서가 지정되거나 지정되지 않을 수 있으며 고유하거나 고유하지 않을 수 있다. 컬렉션의 유형으로 List, set, map이 있다.
- `set(집합)` List와 달리 중복될 수 없고 순서는 중요하지 않은 컬렉션이다. contains()를 통해 집합에 해당 요소가 있는지 확인할 수 있으며, 수학의 집학처럼 교집합 또는 합집합과 같은 연산을 intersect(), union()을 통해 할 수 있다.
- `'List'.toSet()` toSet()은 List를 set으로 변환하는 함수이다.
- `setOf(), mutableSetOf()` 집합을 정의하는 함수이다.
- `map(맵 or 사전)`  특정 키가 부여된 값을 쉽게 찾을 수 있도록 설계된 'key-value' 쌍의 집합이다. key는 고유하며 각 key는 하나의 value에 매핑되지만, value는 중복될 수 있다.
- `mutableMapOf` map 데이터를 생성할 수 있는 함수이다.
- `forEach()` forEach()를 사용하면 자동으로 모든 항목을 탐색한 후 항목별로 작업을 실행할 수 있다.
- `it` 특수 식별자
- `filter()` 컬렉션에서 표현식을 기반으로 일치하는 항목을 반환하는 함수이다.
- `람다식` 이름이 없으며 곧바로 표현식으로 사용할 수 있는 함수를 람다식이라고 한다. 
- `함수 유형` 
    ```
    람다와 같은 유형의 동작을 사용할 수 있도록 코틀린에서는 함수 유형이라는 것이 제공된다. 입력 매개변수와 반환 값을 기반으로 함수를 정의 할 수 있다.
    함수 유형 예시: (Int) -> Int
    위 예시를 설명하면 Int 유형의 매개변수를 사용하고 Int 유형의 값을 반환한다는 뜻이다. 괄호의 안은 매개변수를 나타내고 화살표의 오른쪽은 반환 유형을 나타낸다.
    람다 표현식 예시: {a:Int -> a * 3}
    위 식은 정수 입력 값에 3을 곱하는 람다 표현식이다. 매개변수가 먼저 나오고 ->가 나온 다음 함수의 본문이 나온다. 
    람다 표현식 예시를 분석하면 함수 유형은 (Int) -> Int 이고 값은 람다 표현식 {a:Int -> a * 3} 이다.
    람다는 일반 변수에 저장할 수도 있는데, 아래의 그림을 참고하자.
    ```
    ![image](https://user-images.githubusercontent.com/52282493/129927798-4cfd74e3-8365-4b10-bb69-76d5f67f9919.png)


