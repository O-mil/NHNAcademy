# Boil Ramen
- Template Method를 이용한 라면 끓이기

### Template Method
: 기초 클래스에서 알고리즘의 골격을 정의할 수 있도록 하는 행동 디자인 패턴
또 이 패턴은 자식 클래스들이 전체 알고리즘의 구조를 변경하지 않고도 기본 알고리즘의 단계들을 오버라이드할 수 있도록 해줌.


### 구현 내용
- 모든 라면이 공통으로 가지고 있는 물 넣기, 면 넣기는 템플릿 메서드를 이용하고 다른 행동들은 서브 클래스에 넘겨준다.
