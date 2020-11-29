package Pertemuan7;

public class Student {
	
	private String name;
	private double marks;
	
	public Student(String name, double marks)
	{
		this.name = name;
		this.marks = marks;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getMarks()
	{
		return marks;
	}
	public String toString()
	{
		return name + " with marks " + marks;
	}

}
