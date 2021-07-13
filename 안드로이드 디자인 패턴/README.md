# 안드로이드 디자인패턴 정리

## MVC
MVC의 디자인패턴은 개발자들이 사용하는 가장 대중적인 구조이다.</br>
안드로이드가 등장했을때 가장 대중적인 구조인만큼 개발자들은 MVC를 적용시키려고 했다고 한다.</br>
우선 MVC를 알아보면
- M : Model
- V : View
- C : Controller

의 약자를 가진다. Model은 데이터를 처리(관리)하고, View는 UI요소들로 사용자에게 보여지는 화면, Controller는 사용자의 요청에 따라 Model의 작업을 View에 적용시킨다. </br>
![image](https://user-images.githubusercontent.com/52282493/125495579-43a851ec-8466-4995-901e-60b656767774.png)

`안드로이드에서 이것을 어떻게 적용시킬 수 있을까?`
- Model : Model (독립적)
- View : xml 파일
- Controller : Activity 또는 Fragment

Model과 View가 완전히 분리되어 있기 때문에 괜찮아 보일 수 있으나 View와 Controller가 하나의 클래스에서 이루어지는 문제가 있다.</br>
View가 복잡해질 수록 Controller가 담아야할 코드의 양이 늘어난다. 흔히 말하는 스파게티 코드가 될 가능성이 크다.</br>
즉, 유지보수가 어렵다.</br>

내가 작업했던 `Idle_Project_Android` (https://github.com/OhGyong/Idle_Project_Android)가 MVC 패턴이 적용된게 아닐까 생각된다.</br>




## MVP

## MVVM