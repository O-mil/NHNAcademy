//Object 만들기 다른 방법

import org.json.JSONObject;

public class TestJson3 {
    public static class Info {
        String address;
        int age;

        public Info() {
            address = "IT 융합대학";
            age = 20;
        }

        public String getAddress() {
            return address;
        }

        public int getAge() {
            return age;
        }
    }


    public static void main(String[] args) {
        Info info = new Info();
        JSONObject object = new JSONObject(info);

        System.out.println(object.toString(4));
    }
}
