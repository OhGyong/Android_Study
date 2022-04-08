## fun
`fun`은 함수를 의미하며, 함수란 특정 작업을 실행하는 프로그램의 섹션(영역)이다.

<br/><br/>

## val, var
코틀린에서 사용하는 특수 단어로, **키워드**라고 뒤에 오는 단어가 변수임을 나타낸다.<br/>

`val` 키워드를 사용하여 선언된 변수는 수정이 불가능하다.</br>
하지만 `var` 키워드로 선언된 변수는 변경이 가능하다.

<br/><br/>

## repeat()
repeat()문은 `루프문`이다.

```kotlin
    repeat(5){
        print("hi ") // hi hi hi hi hi 
    }
```

<br/><br/>

## IntRange
코틀린에 있는 데이터 유형으로, 시작점부터 끝점까지의 정수 범위를 표현할 수 있다.<br/>

```kotlin
    var range = 1..6
    println(range) // 1..6
    range.forEach{print("$it ")} // 1 2 3 4 5 6
```

<br/><br/>

## random()
random() 함수는 주어진 범위의 랜덤 숫자를 생성하고 반환한다.

```kotlin
    println((1..5).random())
```