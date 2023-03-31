// todo-item-template 이용해서 todolist 만들기
function createTodoList(){

    const daysInMonth = getDaysInMonth(navi.getYear(),navi.getMonth());
    console.log("daysInMonth:" + daysInMonth);
    const todoItemContainer = document.getElementById("todo-item-container");
    const template = document.getElementById("todo-item-template");

    for(i=1; i<=daysInMonth; i++ ){
       //https://developer.mozilla.org/ko/docs/Web/API/Document/importNode
        const todoItem = document.importNode(template.content,true);
        const todoItemDay = todoItem.querySelector(".todo-item-day");

        //날짜표시
        todoItemDay.innerText="";
        const span1 = document.createElement("span");
        span1.innerText=i;
        todoItemDay.appendChild(span1);
        console.log("span1: " + span1);

        //form 날짜 설정 name=date
        //https://developer.mozilla.org/ko/docs/Web/API/Document/querySelector
        const todoDate = todoItem.querySelector("input[name=todoDate]");
        const zeroDate = navi.getYear() + "-" + navi.getMonth() + "-" + navi.convertToZeroMonthAndDay(i);
        todoDate.value = zeroDate;

        //등록 버튼
        const form = todoItem.querySelector("form");
        form.addEventListener("submit",todoSubmit);


        const todoItemList = todoItem.querySelector(".todo-item-list");
        todoItemList.setAttribute("id", "todo-item-list-" + zeroDate);

        // 모두 삭제 버튼
        const btnRemoveAll = todoItem.querySelector(".btn-remove-all");
        btnRemoveAll.setAttribute("todoDate", zeroDate);
        btnRemoveAll.addEventListener("click", removeAllByTodoDate);

        // 템플릿에 넣기
        todoItemContainer.appendChild(todoItem);
        //화면에 띄우기
        displayTodoItemList(zeroDate);
    }
}

//ex) 2023-02 = 28일 , 2023-03 = 31 .. 해당달의 day count 구하기
function getDaysInMonth(targetYear, targetMonth){
    return new Date(targetYear, parseInt(targetMonth), 0).getDate();
}

// form event 처리
function todoSubmit(event){
    //TODO#2 - form 이벤트 구현
    // event.preventDefault();
    // const validateForm = (form) => {
    //     if (form['todoDate'].value.trim() == 0) {
    //         alert("todoDate 비어있음");
    //         return false;
    //     } else if (form['todoSubject'].value.trim() == 0) {
    //         alert("todo-Subject 비어있음");
    //         form['togoSubject'].focus();
    //         return false;
    //     }
    //     return true;
    // };

    // if (validateForm(event.target)) {

    //     const todoDate = event.target['todoDate'].value;
    //     const todoSubject = event.target['todoSubject'].value;
    //     try {
    //         store.save(todoDate, todoSubject);
    //     } catch(e) {
    //         alert(e);
    //     } finally {
    //         event.target['todoSubject'].value="";
    //         displayTodoItemList(todoDate);
    //     }
    // }
}

function removeAllByTodoDate(event){
    // const todoDate  = event.target.getAttribute("todoDate");
    // const answer =confirm("모두삭제 하시겠습니까?");

    // if(answer){
    //     try{
    //         store.deleteByTodoDate(todoDate);
    //     }catch(e){
    //         alert(e);
    //     }finally{
    //         clearTodoItemList(todoDate);
    //     }
    // }
}

function clearTodoItemList(todoDate){
    // const todoItemList = document.getElementById("todo-item-list-" + todoDate);
    // while(todoItemList.firstChild){
    //     todoItemList.removeChild(todoItemList.firstChild)
    // }
}

function  displayTodoItemList(todoDate){
    // clearTodoItemList(todoDate);
    // const todoItemList = document.getElementById("todo-item-list-" + todoDate);
    // try{
    //     const todoItems = store.getTodoItemList(todoDate);

    //     for(let i=0; i<todoItems.length; i++){
    //         const li = document.createElement("li");
    //         li.innerText=todoItems[i].todoSubject;
    //         li.setAttribute("todoDate",todoDate);
    //         li.setAttribute("id", todoItems[i].id);
    //         li.addEventListener("click", removeTodoItem)
    //         todoItemList.appendChild(li);
    //     }
    // }catch(e){
    //     alert(e);
    // }
}

function removeTodoItem(event){
    // const id = event.target.getAttribute("id");
    // const todoDate = event.target.getAttribute("todoDate");
    // const answer = confirm("삭제하시겠습니까?");

    // if(answer){
    //     try{
    //         store.delete(todoDate,id);
    //     }catch(e){
    //         alert(e);
    //     }finally{
    //         displayTodoItemList(todoDate);
    //     }
    // }

}



