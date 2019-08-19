package com.example.ultil;

import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class CheckUploadFile {

	public static boolean checkExtension(String fileName) {
		String extension = fileName.substring(fileName.lastIndexOf("."));
		String acceptedExtension[] = { ".png", ".jpg", ".jpeg", ".png" };
		for (int i = 0; i < acceptedExtension.length; i++) {
			if (extension.equals(acceptedExtension[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkContent(File file) {
		try {
			return ImageIO.read(file) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String generateNewFileName(String fileName) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10; // random string length
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		String subFileName = fileName.substring(0, fileName.lastIndexOf("."));
		String newFileName = fileName.replace(subFileName, generatedString);
		return newFileName;
	}

	public static void main(String[] args) {
		// Check extension
		/*
		 * if (checkExtension("cuongdq.jpg")) { System.out.println("This is image!"); }
		 * else { System.out.println("Upload image, please!"); }
		 */

		// Check File is image
		/*
		 * //String file = "C:/Users/dangq/Pictures/hacker.jpg"; String file =
		 * "C:/Users/dangq/Pictures/malicious.jsp"; if(checkContent(new File(file))) {
		 * System.out.println("This is image!"); } else {
		 * System.out.println("Upload image, please!"); }
		 */

		// Check Generate New File Name

		String newFileName = generateNewFileName("hacker.png");
		System.out.println("New File Name: " + newFileName);

	}

}