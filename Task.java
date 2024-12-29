package com.task;

 class Task {
	private String Description;
	private boolean isCompleted;
	Task(String Description){
		this.Description=Description;
	}
	 String getDescription(){
		return this.Description;
	}
	 boolean isCompleted(){
		return this.isCompleted; 
	}
	 void markCompleted(){
		isCompleted=true;
	}
 }
 class TaskManager{
	Task task[];
	 int taskCount;
	 TaskManager(int initalCapasity){
		 task=new Task[initalCapasity];
	 }
	 void addTask(String desc) {
		 if(taskCount==task.length) {
			 System.out.println("Task List is completed...");
		 }else {
			 task[taskCount]=new Task(desc);
			 taskCount++;
			 System.out.println("Task Added is successfully added");
		 }
	 }
	 void markTaskComplete(int index) {
		  task[index].markComplete();
		  System.out.println("Status Updated Successfully");
		 }
	 void listPendingTasks() {
		 for(int i=0;i<taskCount;i++) {
				 
				 
			 
			 
		 }
	 }
	public class TestMain{
		
public static void main(String [] args) {
	TaskManager tm=new TaskManager(10);
	
	
		
	
	
	
}
}
 
	
}