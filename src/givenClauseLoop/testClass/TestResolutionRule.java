package givenClauseLoop.testClass;

import java.util.*;
import givenClauseLoop.bean.Variable;
import givenClauseLoop.core.*;
import givenClauseLoop.parser.Parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractQueue;

public class TestResolutionRule {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, Exception{
		String input=args[0];
		try{
			BufferedReader in = new BufferedReader(new FileReader(input));
			input="";
			String s;
			while((s=in.readLine())!=null)
				input+=s + "\n";
		}catch (FileNotFoundException e){
			System.out.println("Can not open file. Maybe path is wrong or file does not exist."); 
		}catch (IOException e){
			throw new IOException("Failed to open the file.");
		}
		//System.out.println(input);
		AbstractQueue<Clause> clauses=null;
		try{
			//PARSING
			clauses= Parser.parsing(input);
		}catch(Throwable e){
			System.out.println(e.getMessage());
		}
		
		StringBuffer s;
		for(Clause c: clauses){
			System.out.println(c);
			s = new StringBuffer("\t");
			for(Variable v: c.findVariables())
				s.append(v.toString() + "  ");
			System.out.println(s);
		}
		System.out.print("\n\n");
		System.out.println("RESOLUTION:");
		AbstractQueue<Clause> qNew;
		Map<Clause, Clause> alreadyConsidered = new HashMap<Clause, Clause>();
		for(Clause c1: clauses)
			for(Clause c2: clauses)
				if(c1!=c2 && alreadyConsidered.get(c2)!=c1){
					alreadyConsidered.put(c1, c2);
					System.out.println("\n" + c1 + "\t\t" +  c2 + 
		"\n---------------------------------------------------------------------------------------------------------------------------------");
					qNew=ExpansionRules.binaryResolution(c1, c2);
					for(Clause c3: qNew)
						System.out.println("\t" + c3);
				}
	}
}


