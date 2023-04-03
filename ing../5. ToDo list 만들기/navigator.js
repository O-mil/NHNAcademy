/**
 * 함수명이 대문자로 시작하면 관례적으로 생성자 함수 입니다.
 * @param {*} uiBtnPrevMonthId 이전 button id
 * @param {*} uiBtnNextMonthId  다음 button id
 * @param {*} uiBtnCurrentMonthId 오늘 button id
 * @returns
 */
function Navigator(uiBtnPrevMonthId, uiBtnNextMonthId, uiBtnCurrentMonthId){
    //TODO#0 strict mode 선언하기
    'user strict'

    let year = null;
    let month = null;

    //TODO#1
    //생성자 함수의 parameter로 넘어오는 uiBtnPrevMonthId, uiBtnNextMonthId, uiBtnCurrentMonthId 초기화 해주세요
    this.uiBtnCurrentMonthId = uiBtnCurrentMonthId;
    this.uiBtnNextMonthId = uiBtnNextMonthId;
    this.uiBtnPrevMonthId = uiBtnPrevMonthId;
    /*
     //TODO#2
     //즉시 실행함수 : url 주소를 기준으로 year, month를 얻습니다.
     //url 주소 : ..../index.html?year=2023&month=04
     //year == null or year == null 이면 오늘 날짜로 설정합니다.
     //month <10 이면 01,02,03 .. 형태로 설정합니다.
     */
    (function() {
        // https://developer.mozilla.org/en-US/docs/Web/API/URLSearchParams/get
        const searchParam = new URLSearchParams(document.location.search);
        //TODO#2-1 query parameter 파싱해서 year, month 설정.
        year = searchParam.get("year");
        month = searchParam.get("month");

        const today = new Date();       //Date(): 날짜를 얻기 위해서는 생성자로 Date객체를 호출해야 함. -> 새로운 Date 객체 반환.
        // https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Date/getFullYear
        if(year == null){
            //TODO#2-2 year 설정
            year = today.getFullYear();     //getFullYear(): Date()에서 년도를 가져올 때 쓰는 함수
        }

        // https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Date/getMonth
        if(month == null){
            //TODO#2-3 month설정 0부터 시작 즉 1월은 = 0
            month = today.getMonth() + 1;
        }
        // 생성된 Date 객체에서
        // getFullYear(): 년도 4자리
        // getMonth(): 월(0~11) -> getMonth() + 1을 해줘야 함
        // getDate(): 일(1~31)
        // 날짜는 string임!
    })();


    //TODO#3
    //button event 설정 , DOMContentLoaded 시점에 ..
    window.addEventListener("DOMContentLoaded",function(){

        let btnPrevMonth = document.getElementById(uiBtnPrevMonthId);
        let btnNextMonth = document.getElementById(uiBtnNextMonthId);
        let btnCurrentMonth = document.getElementById(uiBtnCurrentMonthId);

        //https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Error
        if(btnPrevMonth == null){
            //TODO#3-1 이전 button을 찾을 수 없다면 오류던지기
            // 오류 던지기 throw new Error()
            throw new Error("이전 달 버튼이 없습니다.");
        }
        if(btnNextMonth == null){
            //TODO#3-2 다음 button을 찾을 수 없다면 오류던지기
            throw new Error("다음 달 버튼이 없습니다.");
        }
        if(btnCurrentMonth == null){
            //TODO#3-3 오늘 button을 찾을 수 없다면 오류던지기
            throw new Error("이번 달 버튼이 없습니다.");
        }

        //버튼 이벤트 등록
        //이전
        btnPrevMonth.addEventListener("click",function(){
            //TODO#3-4 이전 button click event 구현 : 이전 달 이동
            //...
            if (month == 1) {   // 만약 1월이면 12월로 넘어가고 년도 -1 해주기
                month = 12;
                year = parseInt(year) - 1;  // 날짜는 string이니까 int로 바꿔줘서 빼야 함.
            } else {
                month = parseInt(month) -1;
            }

            _navigate(year,month);
        });

        //다음
        btnNextMonth.addEventListener("click",function(){
            //TODO#3-5 다음 button click event 구현 : 다음 달 이동
            //let targetYear=null;
            //let targetMonth=null;
            //...
            if (month == 12) {
                month = 1;
                year = parseInt(year) + 1;
            } else {
                month = parseInt(month) + 1;
            }
            _navigate(year,month);
        });

        //오늘
        btnCurrentMonth.addEventListener("click",function(){
           //TODO#3-6 오늘 button click event 구현 : 이번 달 이동
            //let targetYear=null;
            //let targetMonth=null;
            //...
            const today = new Date();
            year = today.getFullYear();
            month = today.getMonth() + 1;

            _navigate(year,month);
        });
    });

    //TODO#4
    function _navigate(targetYear,targetMonth){
       //페이지 이동 : ./index.html?year=2023&month=03
       //https://developer.mozilla.org/en-US/docs/Web/API/Location
       targetYear = _convertToZeroMonthAndDay(targetYear);
       targetMonth = _convertToZeroMonthAndDay(targetMonth);
       location.href = "./index.html?year=" + targetYear + "&month=" + targetMonth;
       //location.href: 페이지 이동
       //location.href; : 현재 페이지 확인
       //location.href="이동할 페이지 주소";

    }


    //TODO#5 month or day -> d=9 return "09" 형태로 반환하는 함수구현
    //https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/parseInt
    function _convertToZeroMonthAndDay(d){
       //...
       d = parseInt(d)
       if (d <= 9) {
        d = "0" + d;
       }
        return d;
    }

    return {
        getYear : function(){
            return year;
        },
        getMonth : function(){
            return _convertToZeroMonthAndDay(month);
        },
        convertToZeroMonthAndDay : function(d){
            return _convertToZeroMonthAndDay(d);
        }
    }

}