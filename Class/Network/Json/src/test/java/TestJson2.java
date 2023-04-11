//File Read

import org.json.JSONObject;

import java.io.*;

public class TestJson2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("./info.json")));  //Buffer로 파일 가져오기

        String line;    // 가져올 line 정의
        StringBuilder info = new StringBuilder();   //!
        while((line = reader.readLine()) != null) { //readLine 이용해 가녀와서 찍기 (read 사용해도 됨)
//            System.out.println(line);
            info.append(line);      //!
            info.append('\n');      //!     // 안 쓰면 결과가 한 줄로 나옴

        }
        System.out.println("---------- 파일 읽기 ----------");
        System.out.println(info.toString());    //! 모두 쓰면 [System.out.println(line);]와 같음

       //읽어온 파일을 JSON으로 만들기
        JSONObject object = new JSONObject(info.toString());

        System.out.println("---------- 파일 만들기 ----------");
        System.out.println(object.toString(4));

        System.out.println("---------- object 찾기 ----------");
        Object field = object.get("주소");
        System.out.println(field.toString());
        System.out.println(field.getClass().getName());
        System.out.println("\n");

        Object field2 = object.get("나이");
        System.out.println(field2.toString());
        System.out.println(field2.getClass().getName());
    }
}
