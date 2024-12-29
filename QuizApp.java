package com.quiz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
class Questions{
	String questionText;
	String options[];
	int correctOption;
	Questions(String questionText,String options[],int correctOption){
		this.questionText=questionText;
		this.options=options;
		this.correctOption= correctOption;
	}
	public static Questions parseQuestions(String csv) {
		String ar[]=csv.split(",");
		if(ar.length!=3) throw new IllegalArgumentException("The csv is not properly formatted");
		String questionText=ar[0];
		String options[]=ar[1].split("\\|");
	    int correctAns=0;
	    try {
	    	correctAns=Integer.parseInt(ar[2]);
	    }catch(NumberFormatException e) {
	    	System.out.println("correct option is not in the number format");
	    }
	    return new Questions(questionText,options,correctAns);
	}
	public String toCSV() {
		return questionText+","+String.join("|", options)+","+correctOption;
	}
	public void displayQuestion(int qn) {
		System.out.println("Question no:"+qn+" "+questionText);
		for(int i=0;i<options.length;i++) {
			System.out.println(i+1+"."+options[i]);
		}
	}
	public boolean isCorrect(int op) {
		return correctOption==op;
	}
}
public class QuizApp {
	private static final String Question_File="questions.csv";
	static Scanner sc=new Scanner(System.in);
	static ArrayList<Questions> questionBank=loadQuestions();
	public static ArrayList<Questions> loadQuestions()  {
		ArrayList<Questions> questions=new ArrayList<>();
		try(BufferedReader br=new BufferedReader(new FileReader(Question_File))){
			String line;
			while((line=br.readLine())!=null){
				questions.add(Questions.parseQuestions(line));
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return questions;
		}
	public static void saveQuestions() {
		try(BufferedWriter bw=new BufferedWriter(new FileWriter(Question_File))){
			for(Questions question:questionBank) {
				bw.write(question.toCSV());
				bw.newLine();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
public static void addQuestion() {
	System.out.println("Enter Question Text:");
	String questionText=sc.nextLine();
	System.out.println("Enter options by separated with , ");
	String options=sc.nextLine();
	System.out.println("Enter correct option: ");
	int op =sc.nextInt();
	sc.nextLine();
	questionBank.add(new Questions(questionText,options.split(","),op));
	saveQuestions();
	System.out.println("Question added Successfully");
}
public static void conductQuiz() {
	if(questionBank==null) {
		System.out.println("please add questions first");
		return;
	}
	System.out.println("enter number of questions to solve:");
	int op=sc.nextInt();
	if(op>questionBank.size()) {
		System.out.println("insufficent questions...");
		return;
	}
	int score=0;
	for(int i=0;i<questionBank.size();i++) {
		Questions question=questionBank.get(i);
		question.displayQuestion(i+1);
		System.out.println("Enter Correct option:");
		int cop=sc.nextInt();
		if(question.isCorrect(cop))score++;	
	}
	System.out.println("your final score="+score);
}
	public static void main(String[] args) {
		System.out.println("Hello");
//		addQuestion();
//		saveQuestions();
//	for(int i=0;i<questionBank.size();i++) {
//		questionBank.get(i).displayQuestion(i+1);
		boolean isExit=false;
		while(!isExit) {
			System.out.println("1.Add question\n2.conduct quiz\n3.save questions\n4.Exit");	
			int op=sc.nextInt();
			sc.nextLine();
			switch(op) {
			case 1:addQuestion();break;
			case 2:conductQuiz();break;
			case 3:saveQuestions();break;
			case 4:isExit=true;break;
			}
	}
}
}
