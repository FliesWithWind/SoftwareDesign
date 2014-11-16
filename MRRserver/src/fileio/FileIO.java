package fileio;

import java.io.*;
import java.util.ArrayList;

import datatype.*;

public class FileIO
{
	private static final String DATALOC = "data.bin";
	private static final String REGISTERSLOC = "registers.bin";
	
	private static ObjectInputStream	is;
	private static ObjectOutputStream	os;
	
	public static ArrayList<Account> loadData() throws Exception
	{
		ArrayList<Account> list = null;
		is = new ObjectInputStream(new FileInputStream(DATALOC));
		list = (ArrayList<Account>) is.readObject(); 
		is.close();
		return list;
	}

	public static void saveData(ArrayList<Account> list) throws Exception
	{
		os = new ObjectOutputStream(new FileOutputStream(DATALOC));
		os.writeObject(list);
		os.close();
	}
	
	public static ArrayList<Account> loadRegisters() throws Exception
	{
		ArrayList<Account> list = null;
		is = new ObjectInputStream(new FileInputStream(REGISTERSLOC));
		list = (ArrayList<Account>) is.readObject(); 
		is.close();
		return list;
	}

	public static void saveRegisters(ArrayList<Account> list) throws Exception
	{
		os = new ObjectOutputStream(new FileOutputStream(REGISTERSLOC));
		os.writeObject(list);
		os.close();
	}
	
}
