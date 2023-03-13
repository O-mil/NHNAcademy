import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Test {


    public static void main(String[] args) throws IOException {
        List<String> list = new LinkedList<>();

        BufferedReader reader = new BufferedReader(new FileReader("words.txt"));

        Word word = new Word(1000);

        String str;
        int i = 1;
        while ((str = reader.readLine()) != null) {
            //list.add(str);
            String[] line = str.split(" ");

            word.add(new File(i, line[1], line[2]));
            i++;
        }
        reader.close();

        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("검색할 단어를 입력하세요: ");
            String dic = sc.nextLine();

            for (String value: list) {
                if (dic.equals(list.indexOf(word))) {
                    System.out.println(value);
                }
            }

            if (dic == "exit()") {
                System.exit(0);
            }
        }
    }
}