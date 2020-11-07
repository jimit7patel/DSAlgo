package com.epi;
public class Utf{
	
	
	
	public static void main(String[] args) {
		String t = "Jimit";
		
		String s = new String(t.getBytes(java.nio.charset.StandardCharsets.UTF_8),java.nio.charset.StandardCharsets.US_ASCII);
		System.out.println(s);
	}
}