package givenClauseLoop.bean;

import java.util.*;

/**
 * @author Enrico Scapin
 *
 */
public class CNFformula implements Comparable<CNFformula>{
	Set<Predicate> atoms;
	Map<String, Variable>  variables;
	int symNumber;
	int litNumber;
	
	public CNFformula(Set<Predicate> atoms, Map<String, Variable> variables, int symNumber, int litNumber){
		this.atoms=atoms;
		this.variables=variables;
		this.symNumber=symNumber;
		this.litNumber=litNumber;
	}
	
	public Set<Predicate> getAtoms(){
		return atoms;
	}
	
	/*
	 * The set of variables of that formula
	 * 
	 * @return the variables' set
	 */
	public Map<String, Variable> getVariables(){
		return variables;
	}

	public int getSymNumber(){
		return symNumber;
	}

	public int getLitNumber(){
		return litNumber;
	}
	
	public String toString(){
		StringBuffer s = new StringBuffer();
		for(Predicate p: atoms)
			s.append(p.toString() + " | ");
		s.delete(s.length()-3, s.length());
		return s.toString();
	}
	/**
	 * Inconsistent with equality.
	 * Compare respect the number of symbols in these two formulae.
	 *
	 *@see java.lang.Comparable#compareTo ( )
	 *
	 */
	public int compareTo(CNFformula f){
		return this.symNumber-f.getSymNumber();
	}
	/**
	 * The equals() and hashCode() methods are inherited from object,
	 * since every time we create a new CNFformula object, it must be different
	 * from the others.
	 */
}