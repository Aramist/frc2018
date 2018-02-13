package org.usfirst.frc.team5472.robot.autonomous;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

//FileReader begin
public class FileReader {
	private String fileName;
	private String content;
	private BufferedReader buf;

	public FileReader(String startPosition, String switchOrScale, String gameData) {
		fileName = startPosition + switchOrScale + gameData + ".txt";
		File file = Paths.get("/home/lvuser/stage-one/" + fileName).toFile();
		try {
			buf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void readFile() {
		try {
			content = buf.readLine();
			System.out.println(content);
		} catch (Exception e) {
			System.out.println("Could not GIB");
		}
	}

}