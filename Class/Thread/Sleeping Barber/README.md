# Sleeping Barber
잠자는 이발사 문제(sleeping barber problem)는 운영체제의 프로세스 간 통신과 그들의 동기화 문제를 다루는 고전적인 문제

[문제 링크](https://ko.wikipedia.org/wiki/잠자는_이발사_문제) 



### 문제
이 문제는 이발사가 손님이 있을 때는 일하고 없을 때는 쉬는 것을 반복하는 일련의 과정이다.

- 이발사는 손님이 없으면 잠을 잔다.
- 손님이 오면 이발사는 일어나서 머리를 자른다.
- 손님들은 이발소에 있는 의자의 개수만큼 들어올 수 있고 의자가 모두 차면 들어올 수 없다.
- 모든 손님의 이발이 끝나면 이발사는 다시 잠을 잔다.

