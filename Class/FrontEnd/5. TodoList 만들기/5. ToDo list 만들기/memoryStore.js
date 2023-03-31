//[https://developer.mozilla.org/ko/docs/Web/JavaScript/Closures](https://developer.mozilla.org/ko/docs/Web/JavaScript/Closures)
//[https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global\_Objects/Map](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Map)
const memoryStore = function(){
    'use strict';
    const todoMap = new Map();
    const DAILY_MAX_TODO_COUNT=8;
    const api = new Object();

    /*TODO#1저장 구현
        map을 이용해서 구현합니다.
        key = 2023-03-01
        value = {item,item,item} array 입니다.
        item 객체의 구조는 다음과 같습니다.
        {
            'id' : '36b8f84d-df4e-4d49-b662-bcde71a8764f',
            'todoDate' : '2023-03-01',
            'todoSubject' : 'js 하루종일 공부하기!'
        }
    */
    api.save = function( todoDate, todoSubject){
        /*TODO#1-1
            id는 uuid를 사용합니다. 아래를 참고해서 만들어 주세요
            [https://developer.mozilla.org/en-US/docs/Web/API/Crypto/randomUUID](https://developer.mozilla.org/en-US/docs/Web/API/Crypto/randomUUID)
        */
       let uuid = self.crypto.randomUUID();
       //uuid: 고유 식별에 사용할 id를 뜻함. (중복되지 않은 고유 값을 생성할 때 사용) -> js 라이브러리에 있음.
       //crypto.randomUUID(): uuid를 대체함 (uuid라이브러리없이 uuid를 만들 수 있다.)
       //crypto: 암호화 기법을 제공하는 객체 -> 무작위 숫자 생성기나 암호황 필요한 기본 요소에 접근 가능

       const item = {
            'id' : uuid,
            'todoDate' : todoDate,
            'todoSubject' : todoSubject
        }

        /*TODO#1-2 해당 날짜에 >=DAILY_MAX_TODO_COUNT 이면 적절한 Error 발생 시키기.
         countByTodoDate(todoDate); 사용해서 등록 count를 구합니다.
        */
        const count = countByTodoDate(todoDate);
        if (count >= DAILY_MAX_TODO_COUNT) {
            throw new Error("DAILY_MAX_TODO_COUNT 에러");
        }

        /* TODO#1-3 map  key인 todoDate로 조회
            1. value  존재하지 않는다면 : array를 생성 후 item을 push
            2. value가 존재하면 array에 바로 push ..
        */
        if (!todoMap.has(todoDate)) {
            const todoItems = new Array();
            todoItems.push(item);
            todoMap.set(todoDate, todoItems);
        } else {
            const todoItems = todoMap.get(todoDate);
            todoItems.push(item);
            todoMap.set(todoDate, todoItems);
        }

        //map에 잘 담겼는지 출력(디버깅 용도);
        printMap();
    }

    /*TODO#2- 삭제구현하기*/
    api.delete=function(todoDate,id){
        /*
            TODO#2-1 todoMap에  todoDate가 존재하는 확인 후  존재하면
            해당 배열의 몇번째 index인지 찾습니다.
            해당 인덱스에 해당하는 요소를 삭제합니다.
            아래 두 함수를 참고해서 구현합니다.
            //[https://developer.mozilla.org/docs/Web/JavaScript/Reference/Global\_Objects/Array/findIndex](https://developer.mozilla.org/docs/Web/JavaScript/Reference/Global_Objects/Array/findIndex)
            //[https://developer.mozilla.org/docs/Web/JavaScript/Reference/Global\_Objects/Array/splice](https://developer.mozilla.org/docs/Web/JavaScript/Reference/Global_Objects/Array/splice)
        */
        if (todoMap.has(todoDate)) {
            const todoItems = todoMap.get(todoDate);
            const idx = todoItems.findIndex(function(item) {
                return item.id == id;
            });

            if (idx > -1) {
                todoItems.splice(idx, 1);
                //splice(): 1. 특정범위 삭제  2. 새로운 값을 추가  3. 기존 값 대체
                //splice(시작 인덱스, 몇개의 값을 삭제할 지, 추가할 값을 넘김 (삭제된 값을 담고있는 배열 반환))
            }
            /*
            TODO#2-2 index가 존재하지 않는다면 적절한 Error를 발생해주세요.
            ex) `${id} not found` ..
            */
            else {
                throw new Error(`${id} not found`);
                //${}: 안에 변수나 연산 등을 삽입 할 수 있다.
            }
        }
    }

    //TODO#3 해당 날짜에 존재하는 모든 todo 삭제하기
    api.deleteByTodoDate=function(todoDate){
        if (todoMap.has(todoDate)) {
            todoMap.delete(todoDate);
        }
    }

    //TODO#4 해당 날짜에 존재하는 모든 todo 조회, 존재하지 않는다면 빈 배열을 리턴합니다.
    api.getTodoItemList=function(todoDate){
        if (todoMap.has(todoDate)) {    // 존재하면 조회
            return todoMap.get(todoDate);
        }

        return [];  // 존재하지 않는다면 빈 배열 리턴
    }

    //TODO#5 해당 날짜의 모든 todo item count, 참고로 countByTodoDate 함수는 api 내부에서만 접근가능 합니다.
    function countByTodoDate(todoDate){
        if (todoMap.has(todoDate)) {
            return todoMap.get(todoDate).lenggh;
        }

        return 0;
    }

    //디버깅 용도 map에 있는 모든 entries 출력하기... 적절히 활용해주세요.
    function printMap(){
        //[https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global\_Objects/Array/push](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array/push)
        for (const [key, value] of todoMap.entries()) {
            console.log(`${key} = ${value}`);
        }
    }

    return api;
}