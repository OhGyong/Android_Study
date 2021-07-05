# Activity와 Fragment 무엇을 사용해야 할까

## Activity는 무엇일까
안드로이드 Activity는 화면에 표시되는 UI 구성을 위해 가장 기본이 되는 요소이다.</br>
일반적으로 하나의 화면을 구성하며, 사용자에게 UI가 있는 화면을 제공하는 앱 컴포넌트로 일종의 페이지라고 할 수 있다. </br>
Activity를 사용하기 위해서는 manifest에 Activity 관련 정보를 등록하고 수명주기(life-cycle)를 적절히 관리해야 한다.

![image](https://user-images.githubusercontent.com/52282493/124486128-3ec23c00-dde8-11eb-93b6-18d04fed0b92.png)



## Fragment는 무엇일까
Fragment가 처읍 도입된 것은 2011년2월 Android3.0 부터이다. </br>
기본적으로 태블릿과 같은 큰화면에서 역동적이고 유연한 UI디자인을 지원하기 위해 나왔다고 한다. </br>
일반적인 핸드폰들은 화면의 크기가 작기 때문에 Activity(화면 하나)로 충분했다. </br>
하지만, 태블릿이 등장하면서 큰 화면에 굳이 하나의 View만 보여줄 필요가 없어졌고, 큰 화면을 효율적으로 활용하기 위한 방법으로 Fragment가 나왔다. </br>
물론, 하나의 Activity에 여러 View를 사용할 순 있지만 생명주기, 재사용성 등의 관리 측면에서 문제가 있었다. </br>
반면, Fragment는 하나의 Activity에 여러 개를 사용할 수 있다.

![image](https://user-images.githubusercontent.com/52282493/124490956-ae86f580-dded-11eb-8951-71a2ead01b33.png)


## 사용
최근에 Navigation Drawer와 Bottom Navigation 처럼 단일 Activity에 다중 Fragment를 사용하는 경우가 많아지고 있다.</br>

![image](https://user-images.githubusercontent.com/52282493/124498882-0dea0300-ddf8-11eb-8748-1600d0bf7456.png)
![image](https://user-images.githubusercontent.com/52282493/124498910-18a49800-ddf8-11eb-8dec-036a8cc04298.png)

그중에서 Navigation Drawer는 디자인 적인 문제가 있어 Bottom Navigation이 많이 쓰인다. </br>
https://uxmovement.com/mobile/why-mobile-menus-belong-at-the-bottom-of-the-screen/ (참고자료)

자료를 찾던 중, 어느 개발자 분은 'Fragment는 생명주기를 지는 View 수준의 객체로 Activity를 보충하는 기능이다' 라고 한다. </br>
가장 기본이 되는 Activity를 바탕으로 동적인 부분을 Fragment로 적절히 사용하는 것이 옳다고 본다.

