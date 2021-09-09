package com.example;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvRead {

	public static void main(String[] args) {
		
		Path path = Paths.get("sample.csv");
		
		try {
			
			//Files.readAllLinesでCSVファイルを読み込む → StringのListに代入
			List<String> lines = Files.readAllLines(path, Charset.forName("utf-8"));
			
			for(int i = 0; i < lines.size(); i++) {
				
				//Stringの「split」メソッドでカンマ区切りの文字列を分割 → Stringの配列に代入
				String[] data = lines.get(i).split(",");
				
				//System.out.printでString配列に代入された値を出力
				if(data.length > 5) {
					System.out.print(data[0] + ",");
                    System.out.print(data[1] + ",");
                    System.out.print(data[2] + ",");
                    System.out.print(data[3] + ",");
                    System.out.print(data[4] + ",");
                    System.out.println(data[5]);
				}
			}
		}catch(IOException e) {
			System.out.println("ファイル読み込みに失敗");
		}
		
	}

}
