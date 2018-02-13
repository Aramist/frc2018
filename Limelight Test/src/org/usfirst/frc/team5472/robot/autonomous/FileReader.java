package org.usfirst.frc.team5472.robot.autonomous;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

//FileReader begin
public class FileReader {
	private String fileName;
	private BufferedReader buf;
	private ArrayList<String> content = new ArrayList<String>();
	public static ArrayList<String> commands = new ArrayList<String>();
	public static ArrayList<String> arguments = new ArrayList<String>();

	public FileReader(String startPosition, String switchOrScale, String gameData) {
		fileName = startPosition + switchOrScale + gameData + ".txt";
		File file = Paths.get("/home/lvuser/stage-one/" + fileName).toFile();
		try {
			buf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Stream<String> x = buf.lines();
			Object[] obj = x.toArray();
			for (Object s : obj) {
				content.add(new String((String) s));
			}

		} catch (Exception e) {
			System.out.println("Error Reading File");
		}
		for (String a : content) {
			if (a.equals("F") || a.equals("T"))
				commands.add(a);
			else
				arguments.add(a);
		}
	}

}
