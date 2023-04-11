//File Write

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class TestJson {
    public static void main(String[] args) throws IOException {
        JSONObject object = new JSONObject();

        object.put("name", "삼각형");
        object.put("내각", "[30, 60, 90]");
        object.put("detail", new JSONObject());

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./info.json")));
        writer.write(object.toString(4));
        writer.flush();
        fileWriter.write(object.toString(4));
        fileWriter.flush();

        System.out.println(object.toString(4));
    }
}
