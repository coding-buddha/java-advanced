package java8.in.action.ch03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcessFile {

    public static String processFile() throws IOException {
        // 파일에서 한 행을 읽는 코드
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }

    public static String processFile(BufferedReaderProcessor processor) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return processor.process(br);   // bufferedReader 객체를 처리한다.
        }
    }
}
