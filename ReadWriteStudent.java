package Pertemuan7;

import java.io.*;
import java.util.*;

public class ReadWriteStudent {
	
	static ArrayList<Student> studentlist;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException
	{
		studentlist = new ArrayList<Student>();
		int choice;
		do
		{
			System.out.println("1. Add Student To List");
			System.out.println("2. View all Students");
			System.out.println("3. Read Student List From File");
			System.out.println("4. Write List to File");
			System.out.println("5. Quit");
			System.out.print("Your choice :");
			choice = sc.nextInt();
			sc.nextLine();
			switch(choice)
			{
			case 1: addStudentToList();break;
			case 2: viewStudents();break;
		//	case 3: readStudentsFromFile(); break;
			case 4: copyListToFile();break;
			case 5: System.out.println("bye");
			}
		} while (choice!=5);
	}
	
	public static void viewStudents()
	{
		for(Student s:studentlist)
			System.out.println(s.toString());
	}
	
	public static void addStudentToList()
	{
		System.out.print("Enter student name :");
		String name = sc.nextLine();
		System.out.print("Enter marks for " + name + " : ");
		double marks = sc.nextDouble();
		sc.nextLine();
		Student s = new Student(name, marks);
		studentlist.add(s);
	}
	
	// A method to copy an arraylist of students to a file 
	public static void copyListToFile()
	{
		// get filename
		System.out.print("Enter file name: ");
		String fileName = sc.nextLine();
		FileWriter fw = null; 
		// declare file input/output stream
		// declare reader or writer data stream
		BufferedWriter bw = null;
		   
		try
		{
			File file = new File(fileName);
			if (file.exists())
			{
				System.out.println("File '" + fileName + "' already exists.");
				System.out.print("Choose to (o)verwrite or (a)ppend: ");
				char response = sc.nextLine().charAt(0);
				if (response == 'o' || response == 'O')
					fw = new FileWriter(fileName); // throws IOException
				else
					fw = new FileWriter(fileName, true); // append
			}
			else
				fw = new FileWriter(fileName);   // file doesn’t exist
			// wrap the file stream in data stream
			bw = new BufferedWriter(fw);

			// while there is more information
			for(Student s:studentlist)
			{
				// do the I/O
				bw.write(s.getName()+","+s.getMarks());
				bw.newLine();
			}
		}
		catch(IOException ioe)
		{
			// handle IO Exceptions
			System.out.println(ioe.getMessage());
		}
		finally
		{
			// close any streams that were opened.
			try
			{
				if (bw!=null)
					bw.close();
				if (fw!=null)
					fw.close();
			}
			catch(IOException ioe)
			{
				System.out.println(ioe.getMessage());
			}
					
		}		
	}

	public static void readStudentsFromFile()
	{
		System.out.print("Enter file name: ");
		String fileName = sc.nextLine();
		FileReader fr = null;
		BufferedReader br = null;
	  	try
		{
		 	fr = new FileReader(fileName);
			br = new BufferedReader(fr);
				
			String line;
			line = br.readLine();
			while (line != null) // null indicates end of file
			{
				StringTokenizer st = new StringTokenizer(line, ",");
				String name = st.nextToken();
				double score = Double.parseDouble(st.nextToken());
				Student s = new Student(name, score);
				studentlist.add(s);
				line = br.readLine(); 
			}
		  }
		  catch (FileNotFoundException fnfe)
		  {
		    System.out.println("File '" + fileName + "' does not exist");
		    return;
		  }
		  catch (IOException ioe)
		  {
			System.out.println("Error in reading");
		  }
		  finally
		  {
			try
			{
				if (fr != null)
					fr.close();
			}
			catch (IOException ioe)
			{
				System.out.println(ioe.getMessage());
			}
		  } 
	}

	

}

