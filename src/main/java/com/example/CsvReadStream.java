package com.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class CsvReadStream {
	
	public static void main(String[] args) {
        // BufferedReader.readLineでCSVファイルの行を1行ずつ読み込む
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("sample.csv"), Charset.forName("utf-8")))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                if (index > 0) {
                	
                	//Stringの「split」メソッドでカンマ区切りの文字列を分割 → Stringの配列に代入
                    String[] data = line.split(",");
                    if (data.length > 3) {
                        // 読み込んだCSVファイルの内容を出力
                        System.out.print(data[0] + ",");
                        System.out.print(data[1] + ",");
                        System.out.print(data[2] + ",");
                        System.out.print(data[3] + ",");
                        System.out.print(data[4] + ",");
                        System.out.println(data[5]);
                    }
                }
                index++;
            }
        } catch (IOException e) {
            System.out.println("ファイル読み込みに失敗");
        }
    }

}
