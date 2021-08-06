# PATHWAY 4: Add a button to an app
[PATHWAY 4 사이트](https://developer.android.com/courses/pathways/android-basics-kotlin-four)

## 1. Classes and object instance in Kotlin
[주사위 굴리기 앱 만들기](https://developer.android.com/codelabs/basic-android-kotlin-training-create-dice-roller-in-kotlin?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-four%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-create-dice-roller-in-kotlin#0)

- `IntRange` 시작점부터 끝점까지의 정수 범위를 나타내는 데이터 유형
- `코틀린에서 변수 선언 특징` 코틀린에서는 데이터 변수를 선언할 때 데이터 형식을 지정하지 않아도 된다. val 또는 var로 정의해주면 시스템이 알아서 해석해준다.</br>
ex) val diceRange=1..6  → val dicRange: IntRange=1..6
- `random()` 주어진 범위의 랜덤 숫자를 생성하고 반환하는 함수
- `캡슐화 의미` 논리적으로 관련된 기능을 단일 위치로 묶을 수 있다는 의미
- `class 생성 및 명명 규칙` 클래스 이름은 대문자 카멜 표기법(파스칼 표기법)으로 작성
- `클래스의 객체 인스턴스 생성` 클래스에 ()를 붙여 클래스에서 새 객체 인스턴스를 만듬
- `메서드 의미` 클래스 내에서 정의된 함수를 메서드라고 함
- `함수에서 데이터를 반환하는 법` 함수가 반환하는 데이터 유형을 지정
- `함수가 입력의 인수를 허용하는 법` 함수명의 ()안에 인수로 받을 데이터를 설정
- `리팩터링 의미` 코드를 더 간결하고 효율적이며 읽고 이해하기 쉽게 변경하는 것

## 2. Create an interactive Dice Roller app