package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextFileWriting implements Runnable {

	private final String folderPath = "C:\\Users\\Meetkumar_Prajapati\\Desktop\\Files";

	private final static String logFilePath = "C:\\Users\\Meetkumar_Prajapati\\Desktop\\Files\\search_results.log";

	private final static int initialDelayOfTask = 3;

	private final static int periodicDelayOfTask = 2;

	static DistributedRandomWordGenerator drng = null;

	public static void main(String[] args) {

		drng = new DistributedRandomWordGenerator();
		drng.addWord("CDS", 0.5d);

		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

		Runnable task = new TextFileWriting();

		scheduler.scheduleAtFixedRate(task, initialDelayOfTask, periodicDelayOfTask, TimeUnit.SECONDS);
	}

	public void run() {
		final File folder = new File(folderPath);
		drng.addWord(generateRandomWord(3), 0.5d);
		writeIntoFilesInSpecifiedFolder(folder);
		readFromTheFiles(folder);
	}

	public static void writeIntoFile(File file) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;

		try {
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			System.out.println(drng.getDistributedRandomWord());
			pw.println(drng.getDistributedRandomWord());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				pw.close();
				bw.close();
				fw.close();
			} catch (IOException io) {
			}
		}

	}

	public static void writeIntoFilesInSpecifiedFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				writeIntoFilesInSpecifiedFolder(fileEntry);
			} else {
				writeIntoFile(fileEntry);
			}
		}
		System.out.println("Writing Done!!!");
	}

	public static String generateRandomWord(int wordLength) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder(wordLength);
		for (int i = 0; i < wordLength; i++) {
			char tmp = (char) ('a' + r.nextInt('z' - 'a'));
			sb.append(tmp);
		}
		return sb.toString();
	}

	public static void readFromTheFiles(final File folder) {

		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				readFromTheFiles(fileEntry);
			} else {
				readFromFile(fileEntry);
			}
		}
		System.out.println("Reading Done!!!");

	}

	public static void readFromFile(File file) {

		Pattern pattern = Pattern.compile("[CDS]+");
		try (BufferedReader br = Files.newBufferedReader(Paths.get(file.getAbsolutePath()))) {
			br.lines().map(pattern::matcher).flatMap(Matcher::results).map(matchResult -> matchResult.group(0))
					.collect(Collectors.groupingBy(String::toLowerCase, TreeMap::new, Collectors.counting()))
					.forEach((word, count) -> writeIntoResultFile(file.getName(), count));
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

	}

	public static void writeIntoResultFile(String fileName, Long wordCount) {
		final File logFile = new File(logFilePath);
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;

		try {
			fw = new FileWriter(logFile, true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			pw.println(fileName + " --> " + wordCount);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				pw.close();
				bw.close();
				fw.close();
			} catch (IOException io) {
			}
		}

	}

}
