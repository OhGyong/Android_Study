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

## 코틀린의 고차 함수 호출
함수의 호출하여 인자와 반환값을 자유롭게 사용하려면 호출 방법을 이해해야 한다. **기본형 변수**로 할당된 값은 스택에 저장되고, 인자가 되는 경우에는 해당 값이 복사되어 전달된다. **참조형 변수**(주소 값)로 할당된 값은 참조 주소가 스택에 저장되고 참조 값은 힙에 저장된다. 그리고 인수가 참조형 변수일 때는 참조 주소가 복사되어 전달된다.<br/>

코틀린은 C언어에서의 포인터 주소 연산이 없어서 **참조에 의한 호출(Call By Reference)**을 사용하지 않는다(자바도 마찬가지). 대부분 인자의 값만 복사하는 **값에 의한 호출(Call By Value)**을 사용하는데, 코틀린은 함수와 람다식을 통한 세가지 호출 방법이 있다.

- `Call By Value(값에 의한 호출)`<br/>
함수를 호출할 때 전달되는 변수의 값을 복사하여 인자로 전달하는 방식이다.<br/>

```kotlin
    fun main(){
        var result = callByValue(funA())
        println("Main : $result")

    }

    fun callByValue(value: Boolean): Boolean{
        println("callByValue 호출")
        return value
    }

    fun funA(): Boolean{
        println("funA 호출")
        return false
    }

    /*
    출력결과
    1. funA 호출
    2. callByValue 호출
    3. Main 호출 : false
    */
```

Main 함수의 result 변수를 보면 callByValue() 함수에 funA() 함수를 인자로 넘겨주었다. 함수로 된 인자가 전달될 때(funA()), 그 즉시 함수를(funA()) 계산한 반환값이 전달된다.

- `Call By Name(이름에 의한 호출)`<br/>
Call By Value와 달리 람다식이 인자로 전달될 때, 람다식이 먼저 실행되지 않게 하고 실제로 호출할 때 실행되도록 하는 방식이다.

```kotlin
    fun main(){
        var result = callByValue(lambda)
        println("Main 호출 : $result")
    }

    // 매개변수 value가 람다식으로 선언됨
    fun callByName(value: () -> Boolean): Boolean{
        println("callByName 호출")
        return value()
    }

    val lambda: () -> Boolean = {
        println("lambda 호출")
        true
    }

    /*
    출력결과
    1. callByName 호출
    2. funA 호출
    3. Main 호출 : true
    */
```

람다식 자체가 매개변수 value에 복사되어 사용되기 전까지는 람다식이 실행되지 않는다. callByName() 함수에서 인자 value()가 호출이 되어야 람다식이 실행되기 때문에 출력문이 실행이되고, 반환 값으로 value()를 실행했을 때 람다식이 실행된다. 따라서 Call By Value와 출력결과가 다르다.<br/>

아무래도 람다식의 변수명이 호출되어야 한다는 점에서 Call By Name이라고 부르는 것 같다.

단, 함수의 형태에서는 Call By Value처럼 동작한다.<br/>
```kotlin
    fun main(){
        var result = callByValue(funA())
        println("Main : $result")
    }

    fun callByValue(value: () -> Boolean): Boolean{
        println("callByValue 호출")
        return value()
    }

    fun funA(): () -> Boolean{
        println("funA 호출")
        return {false}
    }

    /*
    출력결과
    1. funA 호출
    2. callByValue 호출
    3. Main 호출 : false
    */
```

- `Call By Function(다른 함수의 참조에 의한 일반 함수 호출)`<br/>
일반 함수를 또 다른 함수의 인자에서 호출하는 방식이다.<br/>

```kotlin
    fun main(){
        var result = callByFunc(3, 2, sum)
        println("Main 호출 : $result")
    }

    fun sum(a: Int, b: Int): Int{
        println("sum 호출")
        return a+b
    }

    fun callByFunc(a: Int, b: Int, c: (Int, Int)-> Int): Int{
        println("callByValue 호출")
        return c(a,b)
    }
    
    /*
    에러 발생, main() 함수의 callByFunc()을 호출할 때 세 번째 매개변수 sum이 함수이기 때문에 에러가 발생
    */
```

sum() 함수의 매개변수와 callByFunc()함수의 c라는 람다식 자료형을 보면 적용이 가능한 것을 확인할 수 있다. 이럴때 함수를 호출하는 방식으로 더블콜론(::)을 사용하면 소괄호와 인자를 생략하고 사용할 수 있다.

```kotlin
    fun main(){
        var result = callByFunc(3, 2, ::sum)
        println("Main 호출 : $result")
    }

    fun sum(a: Int, b: Int): Int{
        println("sum 호출")
        return a+b
    }

    fun callByFunc(a: Int, b: Int, c: (Int, Int)-> Int): Int{
        println("callByValue 호출")
        return c(a,b)
    }

    /*
    출력결과
    1. callByValue 호출
    2. sum 호출
    3. Main 호출 : 5
    */
```

main() 함수에서 callByFunc()을 호출할 때, sum() 함수가 실행되지 않고 callByFunc() 함수에서 sum() 함수가 적용된 c 람다식이 호출될 때 실행된 것을 확인할 수 있다.<br/>

[참고자료, 코틀린 공식 사이트](https://gold.gitbook.io/kotlin/call-by-value-call-by-name)<br/>
[참고자료, Do it! 코틀린 프로그래밍](https://books.google.co.kr/books?id=F6WeDwAAQBAJ&pg=PA123&lpg=PA123&dq=%EC%BD%94%ED%8B%80%EB%A6%B0+call+by+value&source=bl&ots=Jl2vhgzlSc&sig=ACfU3U3Xc0Y4qV70ehOgCAPnLGf2iERIvg&hl=ko&sa=X&ved=2ahUKEwjb0czG9In3AhVSL6YKHUJbAFc4ChDoAXoECBUQAw#v=onepage&q=%EC%BD%94%ED%8B%80%EB%A6%B0%20call%20by%20value&f=false)