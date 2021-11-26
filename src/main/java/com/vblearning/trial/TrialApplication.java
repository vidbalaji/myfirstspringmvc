package com.vblearning.trial;

import java.io.FileWriter;
import java.io.IOException;

public class TrialApplication {

	public static void main(String[] args) {
		String path = System.getProperty("user.dir") + "\\src\\test.txt";
		String text = "Added text\n";
		System.out.println(path);
		try {
			FileWriter fw = new FileWriter(path, true);
			fw.write(text);
			fw.close();
			System.out.println(path);
		} catch (IOException e) {
		}
	}

}
