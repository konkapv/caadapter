package gov.nih.nci.cbiit.cdms.formula.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.JAXBException;

import gov.nih.nci.cbiit.cdms.formula.FormulaFactory;
import gov.nih.nci.cbiit.cdms.formula.core.FormulaMeta;
import gov.nih.nci.cbiit.cdms.formula.core.FormulaType;
import gov.nih.nci.cbiit.cdms.formula.core.OperationType;
import gov.nih.nci.cbiit.cdms.formula.core.TermMeta;
import gov.nih.nci.cbiit.cdms.formula.core.TermType;
import org.junit.Test;

public class FormulaFactoryTest {
	
	/**
	 * test XSD parsing and marshaling of the generated Model Object
	 * @throws JAXBException 
	 */
	@Test
	public void testExcute() throws JAXBException
	{
		HashMap<String, String> paramHash=new HashMap<String, String>();
		String testFile="workingspace/BSA8.xml";
		FormulaMeta myFormula=FormulaFactory.loadFormula(new File(testFile));
		System.out.println("FormulaFactoryTest.testExcute()...paramter:"+myFormula.getExpression().listParameters());
		paramHash.put("weight", "5");
		paramHash.put("height", "3");		
		
		System.out.println("FormulaFactoryTest.testExcute()...result:"+myFormula.getExpression().excute(paramHash));	

	}
	

	/**
	 * test XSD parsing and marshaling of the generated Model Object
	 * @throws JAXBException 
	 */
	@Test
	public void testLoading() throws JAXBException
	{
		String testFile="workingspace/BSA7.xml";
		FormulaMeta myFormula=FormulaFactory.loadFormula(new File(testFile));
		System.out.println("FormulaFactoryTest.testLoading()..:\n"+myFormula.toString());
		System.out.println("FormulaFactoryTest.testLoading().. java Statement:\n"+myFormula.formatJavaStatement());
		System.out.println("FormulaFactoryTest.testLoading().. XML:\n"+FormulaFactory.convertFormulaToXml(myFormula));
	}
	
	/**
	 * test XSD parsing and marshaling of the generated Model Object
	 * @throws JAXBException 
	 */
	@Test
	public void testSaving() throws JAXBException
	{
		String outFile="workingspace/savedExample.xml";
		
		FormulaMeta formula=createTestingFormula();
		try {
			FormulaFactory.saveFormula(formula, new File(outFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private FormulaMeta createTestingFormula()
	{
		
		FormulaMeta formula=new FormulaMeta();
		formula.setName("testForula");
		formula.setType(FormulaType.MATH);
		formula.setAnnotation("This is a formula created from scratch..");
		
		//the following create formula expression
		TermMeta formulaExpression=new TermMeta();
		formulaExpression.setName("testExpression");
		formulaExpression.setType(TermType.EXPRESSION);
		formulaExpression.setOperation(OperationType.ADDITION);
		formulaExpression.setTerm(new ArrayList<TermMeta>());
		formula.setExpression(formulaExpression);
		
		
		//the following add terms to formula expression
		TermMeta addend1=new TermMeta();
		addend1.setName("addend");
		addend1.setType(TermType.CONSTANT);
		addend1.setValue("2345");
		
		TermMeta addend2=new TermMeta();
		addend2.setName("addend");
		addend2.setType(TermType.VARIABLE);
		addend2.setValue("xyz");
		
		formulaExpression.getTerm().add(addend1);
		formulaExpression.getTerm().add(addend2);
		return formula;
	}
}
