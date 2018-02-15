package org.usfirst.frc.team5472.robot.autonomous;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.usfirst.frc.team5472.robot.autonomous.commands.Forward;
import org.usfirst.frc.team5472.robot.autonomous.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

//FileReader begin
public class FileReader extends CommandGroup{
	private ArrayList<String> content = new ArrayList<String>();

	public FileReader(String startPosition, String switchOrScale, String gameData) {
		readFile(startPosition + switchOrScale + gameData + ".txt");
		addCommands();
	}

	private void readFile(String fileName) {
		File file = Paths.get("/home/lvuser/stage-one/" + fileName).toFile();
		try {
			BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			Stream<String> x = buf.lines();
			String[] obj = (String[]) x.toArray();
			for (String s : obj) {
				content.add(s);
			}
			buf.close();
		} catch (IOException e) {
			System.err.println("Error reading file");
		} catch (Exception e) {
			System.err.println("Error converting Stream<String> to String[].");
		}
	}
	
	private void addCommands() {
		for(int i = 0; i < content.size() - 1; i+=2) {
			String commandType = p(content.get(i));
			String commandArgument = p(content.get(i+1));
			
			if(commandType.equals("f"))
				addSequential(new Forward(Double.parseDouble(commandArgument)));
			else if(commandType.equals("t"))
				addSequential(new Turn(Double.parseDouble(commandArgument)));
		}
	}
	
	private static String p(String a) {return a.toLowerCase().replaceAll(" ", "");}
	
}
