'use strict';
const navi = new Navigator("btn-prev-month","btn-next-month","btn-current-month");
console.log("year:" + navi.getYear());
console.log("month:" + navi.getMonth());

(function(){
    'use strict';

    window.addEventListener("DOMContentLoaded",(event)=>{
        displayYearMonth(navi.getYear(), navi.getMonth());
        createTodoList();
    });

    function displayYearMonth(targetYear, targetMonth){
        // html element : todo-nav-year 표시
        const todoNavYear = document.getElementById("todo-nav-year");
        todoNavYear.innerText = targetYear
        // html element : todo-nav-month 표시
        const todoNavMonth = document.getElementById("todo-nav-month");
        todoNavMonth.innerText = targetMonth;
    }

})();