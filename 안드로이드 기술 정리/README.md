## 사용자 인터페이스 정보
앱의 사용자 `인터페이스(UI)`는 화면에 표시되는 Text, Image, Button 등 여러 유형의 요소이다. UI를 통해 앱은 사용자에게 콘텐츠를 표시하고 사용자는 앱과 상호작용할 수 있다.
<br/><br/>

이때, 위에서 언급한 여러 유형의 요소들을 `View`라고 한다. 즉, 앱에 표시되는 대부분의 것들은 View라고 볼 수 있다. View는 화면에 자체적으로 있을 수 없다. 예를 들자면 Image옆에 Text가 있고 그 아래에 Button이 있다고 했을 때, 이 요소들을 정렬 시킬 수 있는 컨테이너가 필요하다.
<br/><br/>

View는 `ViewGroup`이라는 View 객체가 있을 수 있는 컨테이너에 배치된다. ViewGroup의 한 종류로 `ConstraintLayout`이 있다.<br/>

![image](https://user-images.githubusercontent.com/52282493/157878072-05acce1c-7c74-43f0-a3c5-392cb30e3608.png)

<br/><br/>

## 크기 측정 단위
안드로이드에서 View나 ViewGroup의 크기를 지정할 때 `wrap_content`, `match_parent`, `dp`, `sp` 등이 있다.<br/>

- `dp`는 Density-independent Pixels로 `밀도 독립형 픽셀`이라고 한다. UI의 레이아웃을 정의할 때 치수나 위치를 dp 방식으로 표현하기 위해 사용해야 하는 가상 픽셀 단위이다.<br/>
같은 화면 사이즈(크기)를 가진 두 대의 휴대폰 기기가 있을 때, 스펙 차이 등의 이유로 두 화면의 밀도는 다를 수 있다. 밀도가 다르다는 것은 인치당 도트(화소, 픽셀) 수가 다르다는 것을 의미한다.<br/>
![image](https://user-images.githubusercontent.com/52282493/157889215-16630f73-74c0-491b-bea5-0a047cf72308.png) <br/><br/>
안드로이드에서 width, height 측정 단위를 dp를 사용하면 밀도가 다른 기기라도 같은 사이즈로 보이도록 해준다.<br/>
즉, dp는 다양한 기기에서 UI 요소들의 크기에 일관성을 부여할 수 있다.

- `sp`는 Scale-independent Pixels로 `배율 독립형 픽셀`이라고 한다. dp가 화면상의 거리 측정 단위라면 sp는 글꼴 크기 측정 단위이다.<br/>
sp는 텍스트 크기를 지정할 때 화면 밀도와 사용자의 환경설정 모두에 따라 조정이 되기 때문에 텍스트에서 권장되는 단위이다.

<br/><br/>

## 안드로이드 4대 컴포넌트
안드로이드의 4대 컴포넌트는 **Activity**, **Service**, **Broadcast Receiver**, **Content Provider**이다.<br/>
컴포넌트는 앱의 구성 단위이며, 앱은 컴포넌트를 조합하여 만들어진다. 각 컴포넌트들은 하나의 독립된 형태로 존재하고, 정해진 역할을 수행한다. 인텐트(Intent)를 통해 각각의 컴포넌트들은 상호작용한다.

![image](https://user-images.githubusercontent.com/52282493/158025698-37aa67f1-f366-4a9b-a52f-305ea0a051ac.png)

### `액티비티(Activity)`
Activity는 화면을 구성하는 가장 기본적인 컴포넌트이다.<br/>
- Activity 클래스를 상속받아 사용한다.
- 최소 하나 이상의 Activity를 가져야한다.
- 동시에 두 개 이상의 Activity를 표시할 수 없다.
- Activity는 하나 이상의 View를 가질 수 있다.
- Intent를 통해 다른 Activity를 불러올 수 있다.


### `서비스(Service)`
서비스는 눈에 보이는 화면(Activity)과 상관없이 백그라운드에서 동작하는 컴포넌트이다.<br/>
서비스는 일반적으로 화면 없이 동작하는 프로그램을 말한다. 다른 말로는 데몬(Daemon), 백그라운드 프로세스(Background Process)라고도 한다. 서비스의 작업으로 음악 프로그램을 들으면서 다른 앱을 사용하는 것을 예로 들 수 있다.<br/>
- 앱이 종료되어도 서비스는 백그라운드에서 실행되기 때문에 계속 작동한다.(다른 앱으로 이동해도 작동.)
- 모든 서비스는 Service 클래스를 상속받아 사용한다.
- UI가 동작하는 스레드에서 동작한다.
- Activity와 마찬가지로 생명주기를 갖고 있다.<br/>
![image](https://user-images.githubusercontent.com/52282493/158046850-ba15e721-4a3b-4305-99a9-1ebf2ecba912.png) <br/>
왼쪽의 다이어그램은 startService()로 생성된 경우의 서비스, 오른쪽은 bindService()로 생성된 경우의 서비스이다. ([참고](https://developer.android.com/guide/components/services?hl=ko)) 


### `방송 수신자(Broadcast Receiver)`
방송 수신자는 Broadcasting Message가 발생하면 반응하는 컴포넌트이다.<br/>
방송 수신자를 활용하면 안드로이드에서 발생하는 많은 이벤트(문자 메시지, 배터리 방전, 날짜 변경 등)를 감지고하고 이를 처리하는 앱을 작성할 수 있다.<br/>
문자 메시지 도착, 배터리 상태, SD 카드 탈부착 등이 발생하면 broadcast 신호를 보내는데 이 신호를 Broadcasting Message라고 할 수 있다. 이런 broadcast 신호를 받아서 처리하는 것이 Broadcast Receiver이다.<br/>
- 디바이스에서 발생하는 이벤트를 정보를 받고 반응한다.
- 일반적으로 UI를 가지고 있지 않다.

### `콘텐트 제공자(Content Provider)`
콘텐트 제공자는 응용 프로그램 사이에 데이터를 공휴가 위한 컴포넌트이다.<br/>
안드로이드는 보안상 앱에서 사용하는 데이터를 외부에서 접근할 수 없다. SQLite 등의 데이터는 자신의 앱에서만 사용할 수 있고 다른 앱에서는 사용할 수 없다. 이러한 파일이나 데이터베이스를 외부 앱에서 사용하게 하기 위해서 콘텐트 제공자(줄여서 CP)를 만들어서 외부로 제공해야 한다.<br/>
카카오톡이나 페이스북에서 전화번호부에 저장되어 있는 데이터베이스를 사용하는 것을 예로 들 수 있다.
- 콘텐트 제공자는 앱에 데이터를 제공한다.
- 콘텐트 제공자를 통해 다른 앱의 데이터를 쿼리하는 등의 변경이 가능하다.