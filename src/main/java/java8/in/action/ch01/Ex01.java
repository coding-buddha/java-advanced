package java8.in.action.ch01;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

/**
 * 메소드를 람다와 일급시민으로 사용하자.
 */
public class Ex01 {

    public static void main(String[]args) {
        process();          // 기존 코드
        processOpt();       // 변경 코드
    }

    private static void process(){
        File[] hiddenFiles = new File(".").listFiles(new FileFilter(){
            public boolean accept(File file){
                return file.isHidden();
            }
        });

        System.out.println(Arrays.toString(hiddenFiles));
    }

    private static void processOpt(){
        /** 메소드 레퍼런스 이용 :: isHidden 함수를 listFiles 메소드로 전달 **/
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
        System.out.println(Arrays.toString(hiddenFiles));
    }
}
