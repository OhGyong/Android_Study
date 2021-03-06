# 안드로이드 디자인패턴 정리

## MVC
MVC의 디자인패턴은 개발자들이 사용하는 가장 대중적인 구조이다.</br>
안드로이드가 등장했을때 가장 대중적인 구조인만큼 개발자들은 MVC를 적용시키려고 했다고 한다.</br>
우선 MVC의 약자를 알아보면,
- M : Model
- V : View
- C : Controller

Model은 데이터를 처리(관리)하고, View는 UI요소들로 사용자에게 보여지는 화면, Controller는 사용자의 요청에 따라 Model의 작업을 View에 적용시킨다.</br>
사용자의 입력은 Controller에 전달되고, Model에서 데이터를 처리 후 Controller에서 View로 전달되어 수정된 View를 사용자가 보게 된다고 볼 수 있다.</br>
![image](https://user-images.githubusercontent.com/52282493/125837106-b3eb4d42-8bef-4612-bbee-859d6d7023c3.png)


**안드로이드에서 이것을 어떻게 적용시킬 수 있을까?**
- Model : Model
- View : Activity 또는 Fragment(xml 파일) 
- Controller : Activity 또는 Fragment

Model과 View가 분리되어 있기 때문에 괜찮아 보일 수 있으나 View와 Controller가 하나의 클래스에서 이루어지는 문제가 있다.</br>
(뷰에서 컨트롤러의 역할까지 모두 담당한다고도 함)</br>
View가 복잡해질 수록 Controller가 담아야할 코드의 양이 늘어난다. 흔히 말하는 스파게티 코드가 될 가능성이 크다.</br>
즉, 유지보수가 어렵다.</br>

참고로 내가 작업했던 [Idle_Project_Android](https://github.com/OhGyong/Idle_Project_Android) 가 MVC 패턴이 적용된게 아닐까 생각된다.</br>

MVC 예제 → [MVC_Example](https://github.com/OhGyong/Android_Study/tree/master/Android%20Design%20Patterns/MVC_Example)

---

## MVP
MVC의 단점을 보완하기 위해 나온 디자인 패턴이라고 한다.</br>
MVP의 약자를 알아보면,
- M : Model
- V : View
- P : Presenter

Model과 View는 MVC와 비슷하다. 대신 Controller 대신 Presenter가 들어가는데 역할은 비슷하지만 Interface를 사용하는 것에서 차이가 있다. MVC에서는 Controller가 View와 연결되었지만, Presenter는 Interface로 View와 상호작용 한다.</br>
![image](https://user-images.githubusercontent.com/52282493/126066034-9083e940-137e-4cee-9913-20327d451298.png)

**안드로이드 적용**
- M : Model
- V : Activity, Fragement(xml 파일)
- P : Presenter

**+버튼과 -버튼으로 값을 변경시키는 애플리케이션을 MVP 패턴으로 적용해보기**
- View: 화면구성(UI, 인터페이스)
- Presenter: 버튼 이벤트에 따른 값의 처리, Model에 데이터 전달
- Model : 전달 받은 데이터 저장

![image](https://user-images.githubusercontent.com/52282493/126065845-54061665-cabf-4dce-bba9-6d07a6260210.png)


Presenter는 View와 Model 사이의 data 전달 역할을 한다. 다만, View와 Presenter 사이의 의존성이 있기 때문에 복잡한 애플리케이션일 수록 둘 사이의 의존성이 커진다. 즉 작업의 영역이 커질수록 유지보수가 어려워진다.

---

## MVVM
여러 자료를 찾아보면서 MVP가 안드로이드 개발자들이 가장 많이 사용하는 디자인 패턴이라는 글을 봤었다. 하지만 MVP에도 단점은 있었으며, 그것을 보완하기 위해 등장한 것이 MVVM이라고 한다.</br>
MVVM의 약자를 알아보면,
- M : Model
- V : View
- VM : ViewModel

MVP의 경우 View와 Presenter가 1:1의 결합을 가져 Presenter를 재활용하는데 어려움이 있지만, MVVM에서 View와 ViewModel의 경우 n:1 관계이기 때문에 재활용성이 좋다. → 데이터 바인딩 or LiveData를 통해 View와의 결합을 끊어낸다고 한다.

안드로이드 개발자 가이드를 보면 AAC(Android Architecture Component)라고 애플리케이션의 테스트와 유지관리를 쉽게 할 수 있도록 하는 라이브러리 모음을 사용할 것을 권장하고 있다.</br>
![image](https://user-images.githubusercontent.com/52282493/126359875-92055e0e-075a-446e-b6ba-beed4146c792.png)

이 다이어그램의 흐름도를 따라하게 되면 자연스럽게 MVVM 패턴을 사용한 것과 같다고 한다.</br>
