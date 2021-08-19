# PATHWAY 1: Navigate between screens
[PATHWAY 1 사이트](https://developer.android.com/courses/pathways/android-basics-kotlin-unit-3-pathway-1)

## 1. Welcome to Unit 3: Navigation
이전 Unit들에서는 Layout이 한 개인 앱들을 개발하고 공부했다면 이번에는 탐색할 수 있는 여러 개의 화면이 있는 앱을 만들어 볼 것이다.

## 2. Collections in Kotlin]
컬렉션, 코틀린에서의 람다 및 고차 함수에 관해 학습한다.</br>
[코틀린 컬렉션, 고차함수 소개 페이지](https://developer.android.com/codelabs/basic-android-kotlin-training-collections?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-collections#0)

- `컬렉션`</br>
단어 목록이나 직원 기록 모음과 같은 관련 항목 그룹이다. 컬렉션의 항목은 순서가 지정되거나 지정되지 않을 수 있으며 고유하거나 고유하지 않을 수 있다. 컬렉션의 유형으로 List, set, map이 있다.
- `set(집합)`</br>
List와 달리 중복될 수 없고 순서는 중요하지 않은 컬렉션이다.</br>
contains()를 통해 집합에 해당 요소가 있는지 확인할 수 있으며, 수학의 집학처럼 교집합 또는 합집합과 같은 연산을 intersect(), union()을 통해 할 수 있다.
- `'List'.toSet()`</br>
toSet()은 List를 set으로 변환하는 함수이다.
- `setOf(), mutableSetOf()`</br>
집합을 정의하는 함수이다.
- `map(맵 or 사전)`</br>
특정 키가 부여된 값을 쉽게 찾을 수 있도록 설계된 'key-value' 쌍의 집합이다. key는 고유하며 각 key는 하나의 value에 매핑되지만, value는 중복될 수 있다.
- `mutableMapOf`</br>
map 데이터를 생성할 수 있는 함수이다.
- `forEach()`</br>
forEach()를 사용하면 자동으로 모든 항목을 탐색한 후 항목별로 작업을 실행할 수 있다.
- `it`</br>
특수 식별자
- `filter()`</br>
컬렉션에서 표현식을 기반으로 일치하는 항목을 반환하는 함수이다.
- `람다식`</br>
이름이 없으며 곧바로 표현식으로 사용할 수 있는 함수를 람다식이라고 한다. 
- `함수 유형`</br>
람다와 같은 유형의 동작을 사용할 수 있도록 코틀린에서는 함수 유형이라는 것이 제공된다.</br>
입력 매개변수와 반환 값을 기반으로 함수를 정의 할 수 있다.</br>
```
함수 유형 예시: (Int) -> Int
```
위 예시를 설명하면 Int 유형의 매개변수를 사용하고 Int 유형의 값을 반환한다는 뜻이다.</br>
괄호의 안은 매개변수를 나타내고 화살표의 오른쪽은 반환 유형을 나타낸다.
```
람다 표현식 예시: {a:Int -> a * 3}
```
위 식은 정수 입력 값에 3을 곱하는 람다 표현식이다. 매개변수가 먼저 나오고 ->가 나온 다음 함수의 본문이 나온다.</br>
람다 표현식 예시를 분석하면 함수 유형은 (Int) -> Int 이고 값은 람다 표현식 {a:Int -> a * 3} 이다.</br>
람다는 일반 변수에 저장할 수도 있는데, 아래의 그림을 참고하자.</br>

![image](https://user-images.githubusercontent.com/52282493/129927798-4cfd74e3-8365-4b10-bb69-76d5f67f9919.png)</br>

- `고차 함수`</br>
함수(람다)를 다른 함수로 전달하거나 다른 함수에서 함수를 반환하는 것을 의미한다.</br>
map, filter, forEach 함수는 모두 매개변수를 사용했기 때문에 고차 함수의 예라고 볼 수 있다.
- `sortedWith()`</br>
문자열 길이를 기준으로 목록을 정렬하는 함수. 람다식을 이용하여 두 문자열의 길이를 비교하는 코드를 작성한다.</br>
```
sortedWith() 예시 : 'List 변수'.sortedWidth{str1: String, str2: String -> str1.length-str2.length}
```
위의 sortedWith()에 전달된 람다식에는 String 형의 str1, str2 두 개의 매개변수가 있다.</br>
-> 다음에는 str1.length-str2.length 함수 본문이 나온다.</br>
첫 번째 문자열과 두 번째 문자열 길이의 차이를 반환하여 낮은 순서대로 문자열을 정렬한다.
- `startsWidt()`</br>
filter() 함수를 사용할 때 startWidt()로 지정된 단어로 시작하는 항목을 찾아낼 수 있다.
- `shuffleed()`</br>
코틀린 컬렉션을 사용하면 shuffled() 함수를 사용하여 항목을 무작위로 섞어 반환한다.
- `take()`</br>
컬렉션에서 항목의 원하는 개수 만큼만 반환하도록 한다.

## 3. Activities and intents
[Activty와 인텐트 설명](https://developer.android.com/codelabs/basic-android-kotlin-training-activities-intents?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-activities-intents#0)

- `인텐트`</br>
실행할 작업을 나타내는 객체이다. 암시적 인텐트와 명시적 인텐트 두 가지 유형이 있다.</br>
명시적 인텐트는 매우 구체적이며 실행할 활동을 정확하게 알 수 있고 자체 앱의 화면인 경우가 많다.</br>
암시적 인텐트는 좀 더 추상적이면서 시스템에 링크 열기나 이메일 작성, 전화 걸기와 같은 작업 유형을 알려주고 시스템은 요청 처리 방법을 파악해야 한다.
