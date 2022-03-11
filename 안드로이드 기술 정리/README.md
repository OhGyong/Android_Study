## 사용자 인터페이스 정보
앱의 사용자 `인터페이스(UI)`는 화면에 표시되는 Text, Image, Button 등 여러 유형의 요소이다. UI를 통해 앱은 사용자에게 콘텐츠를 표시하고 사용자는 앱과 상호작용할 수 있다.
<br/><br/>

이때, 위에서 언급한 여러 유형의 요소들을 `View`라고 한다. 즉, 앱에 표시되는 대부분의 것들은 View라고 볼 수 있다. View는 화면에 자체적으로 있을 수 없다. 예를 들자면 Image옆에 Text가 있고 그 아래에 Button이 있다고 했을 때, 이 요소들을 정렬 시킬 수 있는 컨테이너가 필요하다.
<br/><br/>

View는 `ViewGroup`이라는 View 객체가 있을 수 있는 컨테이너에 배치된다. ViewGroup의 한 종류로 `ConstraintLayout`이 있다.<br/>

![image](https://user-images.githubusercontent.com/52282493/157878072-05acce1c-7c74-43f0-a3c5-392cb30e3608.png)


## 크기 측정 단위
안드로이드에서 View나 ViewGroup의 크기를 지정할 때 `wrap_content`, `match_parent`, `dp`, `sp` 등이 있다.<br/>

- `dp`는 Density-independent Pixels로 `밀도 독립형 픽셀`이라고 한다. UI의 레이아웃을 정의할 때 치수나 위치를 dp 방식으로 표현하기 위해 사용해야 하는 가상 픽셀 단위이다.<br/>
같은 화면 사이즈(크기)를 가진 두 대의 휴대폰 기기가 있을 때, 스펙 차이 등의 이유로 두 화면의 밀도는 다를 수 있다. 밀도가 다르다는 것은 인치당 도트(화소, 픽셀) 수가 다르다는 것을 의미한다.<br/>
![image](https://user-images.githubusercontent.com/52282493/157889215-16630f73-74c0-491b-bea5-0a047cf72308.png) <br/><br/>
안드로이드에서 width, height 측정 단위를 dp를 사용하면 밀도가 다른 기기라도 같은 사이즈로 보이도록 해준다.<br/>
즉, dp는 다양한 기기에서 UI 요소들의 크기에 일관성을 부여할 수 있다.

- `sp`는 Scale-independent Pixels로 `배율 독립형 픽셀`이라고 한다. dp가 화면상의 거리 측정 단위라면 sp는 글꼴 크기 측정 단위이다.<br/>
sp는 텍스트 크기를 지정할 때 화면 밀도와 사용자의 환경설정 모두에 따라 조정이 되기 때문에 텍스트에서 권장되는 단위이다.