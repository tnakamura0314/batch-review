package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 参考にした記事.<br>
 * https://teratail.com/questions/114495
 * https://medium-company.com/java-csv-%E8%AA%AD%E3%81%BF%E8%BE%BC%E3%81%BF/
 * 
 * @author nakamuratomoya
 *
 */
public class csv_db {

	public static void main(String[] args) {
		try {
			// インストールしたpostgreSQLのドライバを指定
			Class.forName("org.postgresql.Driver");

			// postgreSQLデータベースに接続 (DB名,ID,パスワードを指定)
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ec-202107c", "postgres",
					"postgres");

			// ステートメントを作成
			PreparedStatement stmts = conn.prepareStatement(
					"INSERT INTO items(id,name,description,price_m,price_l,image_path,deleted) VALUES (?, ?, ?, ?, ?, ?, ?);");

			try {
				File file = new File("sample.csv");
				BufferedReader br = new BufferedReader(new FileReader(file));
				
//				System.out.println(br.readLine());

				String line;
				// 1行ずつCSVファイルを読み込む
				while ((line = br.readLine()) != null) {

					//csvのカラムの1行をスキップ
					if (line.equals("id,name,description,price_m,price_l,image_path,deleted")) {
						continue;
					}

					String[] data = line.split(","); // 行をカンマ区切りで配列に変換

//					System.out.println(data[0]);
//					System.out.println(data[1]);
//					System.out.println(data[2]);
//					System.out.println(data[3]);
//					System.out.println(data[4]);
//					System.out.println(data[5]);
//					System.out.println(data[6]);

					stmts.setInt(1, Integer.parseInt(data[0]));
					stmts.setString(2, data[1]);
					stmts.setString(3, data[2]);
					stmts.setInt(4, Integer.parseInt(data[3]));
					stmts.setInt(5, Integer.parseInt(data[4]));
					stmts.setString(6, data[5]);
					stmts.setBoolean(7, Boolean.valueOf(data[6]));
					stmts.executeUpdate();
					
					System.out.println("インサート完了！！");

				}
				br.close();

			} catch (IOException e) {
				e.printStackTrace();

			}
			// ステートメントをクローズ
			stmts.close();
			// 接続をクローズ
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
