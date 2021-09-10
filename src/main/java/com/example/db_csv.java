package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 参考にした記事.<br>
 * https://techacademy.jp/magazine/22226
 * 
 * @author nakamuratomoya
 *
 */
public class db_csv {

	// カンマ
	private static final String COMMA = ",";
	// 改行
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) {

		try {
			// インストールしたpostgreSQLのドライバを指定
			Class.forName("org.postgresql.Driver");

			// postgreSQLデータベースに接続 (DB名,ID,パスワードを指定)
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ec-202107c", "postgres",
					"postgres");

			// ステートメントを作成
			PreparedStatement stmts = conn
					.prepareStatement("SELECT id,name,description, price_m, price_l, image_path, deleted FROM items ;");

			ResultSet rs = stmts.executeQuery();

			// Itemクラスの空のListを作成
			List<Item> itemList = new ArrayList<>();

			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDescription(rs.getString("description"));
				item.setPriceM(rs.getInt("price_m"));
				item.setPriceL(rs.getInt("price_l"));
				item.setImagePath(rs.getString("image_path"));
				item.setDeleted(rs.getBoolean("deleted"));
				System.out.println(item);
				itemList.add(item);
			}
			System.out.println(itemList);

			FileWriter fileWriter = null;

			try {
				fileWriter = new FileWriter("item.csv");
				
				fileWriter.append("id");
				fileWriter.append(COMMA);
				fileWriter.append("name");
				fileWriter.append(COMMA);
				fileWriter.append("price_m");
				fileWriter.append(COMMA);
				fileWriter.append("price_l");
				fileWriter.append(COMMA);
				fileWriter.append("image_path");
				fileWriter.append(COMMA);
				fileWriter.append("deleted");
				fileWriter.append(NEW_LINE);
				

				// リストの内容を順に処理
				for (Item item : itemList) {

					fileWriter.append(String.valueOf(item.getId()));
					fileWriter.append(COMMA);
					fileWriter.append(item.getName());
					fileWriter.append(COMMA);
					fileWriter.append(String.valueOf(item.getPriceM()));
					fileWriter.append(COMMA);
					fileWriter.append(String.valueOf(item.getPriceL()));
					fileWriter.append(COMMA);
					fileWriter.append(item.getImagePath());
					fileWriter.append(COMMA);
					fileWriter.append(item.getDescription());
					fileWriter.append(NEW_LINE);
				}

				System.out.println("CSVファイル出力完了");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
