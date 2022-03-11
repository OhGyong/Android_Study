## 사용자 인터페이스 정보
앱의 사용자 `인터페이스(UI)`는 화면에 표시되는 Text, Image, Button 등 여러 유형의 요소이다. UI를 통해 앱은 사용자에게 콘텐츠를 표시하고 사용자는 앱과 상호작용할 수 있다.
<br/><br/>

이때, 위에서 언급한 여러 유형의 요소들을 `View`라고 한다. 즉, 앱에 표시되는 대부분의 것들은 View라고 볼 수 있다. View는 화면에 자체적으로 있을 수 없다. 예를 들자면 Image옆에 Text가 있고 그 아래에 Button이 있다고 했을 때, 이 요소들을 정렬 시킬 수 있는 컨테이너가 필요하다.
<br/><br/>

View는 `ViewGroup`이라는 View 객체가 있을 수 있는 컨테이너에 배치된다. ViewGroup의 한 종류로 `ConstraintLayout`이 있다.<br/>

![image](https://user-images.githubusercontent.com/52282493/157878072-05acce1c-7c74-43f0-a3c5-392cb30e3608.png)