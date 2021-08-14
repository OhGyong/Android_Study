# PATHWAY 3: Display a scrollable list
[PATHWAY 3 사이트](https://developer.android.com/courses/pathways/android-basics-kotlin-unit-2-pathway-3)

## 1. Affirmations app introduction
이번 PATHWAY에서는 기운을 복돋는 메시지나 문구 목록을 보여줄 수 있는 명언 앱을 개발할 것이다.</br>
앱을 개발하기 위해서 데이터를 목록과 배열로 나타내고, 스크롤을 할 수 있는 텍스트와 이미지의 목록을 표시하는 법을 배운다.

## 2. Use Lists in Kotlin
[코틀린에서 List 사용하기](https://developer.android.com/codelabs/basic-android-kotlin-training-lists?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-2-pathway-3%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-lists#0)

- `List 데이터 유형` List는 특정 순서가 있는 항목의 모음이다. 일반적인 'List'는 생성 후 수정할 수 없지만 'MutableList'는 생성 후 수정할 수 있다(요소를 추가하거나 삭제, 업데이트 가능).</br>
List를 사용할 때는 포함될 수 있는 요소 유형을 명시적으로 지정해야 한다. 요소 유형을 표시할때 꺾쇠괄호롤 묶어 추가하면 된다. 예를들면 정수 목록으로 List<'Int'>, 문자열 목록으로 List<'String'>, 객체 인스턴스 목록으로 List<'클래스 명'>으로 표현할 수 있다.</br>
    ```kotlin
    /*
    * 1. listOf<>() 또는 mutableListOf<>()로 생성하기
    * 2. List<> 또는 mutableList<>로 변수의 데이터 유형을 미리 지정하여 생성하기
    */

    // List로 생성
    val entress = listOf<Int>()
    val entress: List<Int> = listOf()

    // mutableList로 생성
    val entress = mutableListOf<Int>()
    val entress: mutableList<Int> = mutableListOf()
    ```
- `listOf()` List 값을 만듬 → listOf(1, 2, 3, 4, 5) = [1, 2, 3, 4, 5]
- `mutableListOf()` mutableList 값을 만듬
- `List.size` List의 크기를 나타내는 함수로 List의 요소 개수를 반환한다.
- `List의 index 개념`
- `List.first(), List.last()` first()는 List의 첫 번째 요소를 반환하고 last()는 List의 마지막 요소를 반환한다.
- `List.contains()` contains(value)는 주어진 value가 List안에 있는지 확인해준다. 있으면 true를 반환하고 없으면 false를 반환한다.
- `List.reversed()` List의 요소들을 역순으로 하는 새 List를 반환한다.
- `List.sorted()`  List의 요소들을 오름차순으로 정렬한 새 List를 반환한다.
- `List.add()` List에 요소를 추가하는 함수이며, 성공하면 true 실패하면 false를 반환한다. 참고로 List가 아닌 MutableList로 데이터 유형을 지정하여 변수를 생성해야 가능하다.
- `List.addAll()` .add()를 사용하여 요소를 하나씩 넣지 않고 한 번에 여러 요소를 추가할 수 있다. → List.addAll(listOf('value'))
- `List.remove()` List에서 해당 요소를 삭제하는 함수이다. 해당 요소가 없을 경우 false를 반환한다.
- `List.removeAt()` remove('value')와 달리 value에 index값을 넣어 List에서 해당 index를 삭제한다.
- `List.clear()` List의 모든 요소를 삭제한다.
- `List.isEmpty()` List가 비어 있는지 확인한다. 목록이 비어있으면 true, 요소가 하나라도 있으면 false를 반환한다.
- `while 문` while 괄호 안의 표현식이 true이면 중괄호 안의 코드가 계속해서 반복 실행되는 함수이다.
- `for 문`
- `String.length` String 변수의 문자 수를 확인할 수 있다.

### 다양한 조합의 음식을 주문하여 출력하는 코드작성
[예제코드](https://github.com/OhGyong/Android_Study/tree/master/Android%20Kotlin%20Basics%20in%20Kotlin/Unit%202-%20Layouts/PATHWAY%203-Display%20a%20scrollable%20list/PATHWAY%203-2%20code)

- `toString()` 코틀린에서는 모든 클래스가 자동으로 toString() 메서드를 상속을 하게된다. toString() 메서드는 객체 인스턴스를 출력하면 자동으로 호출된다.
- `vararg (가변인자)` 매개변수로 인자의 개수를 유동적으로 받을 수 있게한다. 인자가 유동적으로 변경되기 때문에 불필요한 오버로딩을 하는 코드가 필요없다.
- `joinToString()` 리스트로 구성된 자료를 하나의 문자열로 표현
- `빌더 패턴` 빌더 패턴은 단계별 접근 방식으로 복잡한 객체를 빌드할 수 있는 프로그래밍의 디자인 패턴이다.
