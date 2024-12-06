package com.metacoding.upload;
import org.junit.jupiter.api.Test;
import java.util.Base64;
public class Base64Test {
    @Test
    public void base64Encoding_test(){
        String base64String = "data:image/png;base64,iVBO";
        // 데이터 URI에서 Base64 부분만 추출
        // base64String.split(",")[0] -> "data:image/png;base64"
        String base64Data = base64String.split(",")[1];
        // 디코딩
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
        for(byte b : decodedBytes){
            System.out.println(b);
        }
    }
    @Test
    public void mime_test(){
        String base64String = "data:image/png;base64,iVBO";
        // MIME 타입 추출 함수
        String mimeType = base64String.substring(5, base64String.indexOf(";base64,"));
        System.out.println(mimeType); // image/png

        String extension = mimeType.split("/")[1];
        System.out.println(extension); // png
    }
}