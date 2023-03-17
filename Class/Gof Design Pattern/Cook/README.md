# Cook
- Visitor pattern을 이용해 요리하기


### Visitor Pattern
:Visitor는 사전적인 의미로 어떤 장소에 찾아오는 사람이라는 의미를 갖고 있다. 방문자 패턴에서는 데이터 구조와 처리를 분리한다. 데이터 구조 안을 돌아다니는 주체인 방문자를 나타내는 클래스를 준비해서 처리를 맡긴다. 새로운 처리를 추가하고 싶을 떈 새로운 방문자를 만들고 데이터 구조는 방문자를 받아들이면 된다.

### 구현 내용
- 돼지고기, 소고기라는 재료를 만들고 visitor과 acceptor을 이용해 레시피대로 요리(튀기기, 끓이기 등)를 한다.


참고)https://velog.io/@newtownboy/%EB%94%94%EC%9E%90%EC%9D%B8%ED%8C%A8%ED%84%B4-%EB%B0%A9%EB%AC%B8%EC%9E%90%ED%8C%A8%ED%84%B4Visitor-Pattern
