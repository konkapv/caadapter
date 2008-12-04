/**
 * <!-- LICENSE_TEXT_START -->
The contents of this file are subject to the caAdapter Software License (the "License"). You may obtain a copy of the License at the following location: 
[caAdapter Home Directory]\docs\caAdapter_license.txt, or at:
http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caadapter/indexContent/docs/caAdapter_License
 * <!-- LICENSE_TEXT_END -->
 */


package gov.nih.nci.caadapter.common.function;

import gov.nih.nci.caadapter.common.Log;
import gov.nih.nci.caadapter.common.util.CaadapterUtil;
import gov.nih.nci.caadapter.common.util.Config;
import gov.nih.nci.caadapter.common.util.GeneralUtilities;
import gov.nih.nci.caadapter.common.util.NullFlavorSetting;

import java.util.ArrayList;
import java.util.List;

/**
 * Perfoms String Functions.
 *
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: wangeug $
 * @version Since caAdapter v1.2
 *          revision    $Revision: 1.5 $
 *          date        $Date: 2008-12-04 20:35:27 $
 */

public class StringFunction {

    /**
     * Accepts two String objects.
     * Returns an Object containing the results of concatenating two strings.
     *
     * @param strParam1 the first string to which strParam2 will be concatenated
     * @param strParam2 the second string that will be appended to strParam1
     * @return an String containing the final concatenated String
     */
    public String concat(String strParam1, String strParam2) {
        if (strParam1 == null) strParam1 = "";
        if (strParam2 == null) strParam2 = "";
        return strParam1 + strParam2;
    }

    /**
     * Accepts a String and an int.  Splits a string at position defined by iSplitPos.
     * Returns the split strings in a list of Objects.
     *
     * @param strParam  the String object to be split
     * @param iSplitPos the position in the string to start the split
     * @return an Object array containing two String objects that result from the split.
     */
    //public Object[] split(String strParam, int iSplitPos)
    public List<String> split(String strParam, int iSplitPos) {
        strParam.substring(iSplitPos);
        if (strParam == null || strParam.length() == 0) {
            List<String> lt = new ArrayList<String>(2);
            lt.add("");
            lt.add("");
            return lt;
        }
        if (iSplitPos == 0) {
            List<String> lt = new ArrayList<String>(2);
            lt.add(strParam);
            lt.add("");
            return lt;
        }
        ArrayList<String> lstStrSplit = null;  //Stores two string objects
        int iBeginingIndex = 0;        //Stores the beginning index of the split
        int iArrayCapacity = 2;        //Stores the initial capacity for the ArrayList

        //Split the string
        lstStrSplit = new ArrayList<String>(iArrayCapacity);
        lstStrSplit.add(strParam.substring(iBeginingIndex, iSplitPos)); //Store first part of the string
        lstStrSplit.add(strParam.substring(iSplitPos)); //Store the second part of the string

        return lstStrSplit;  //return a list of Strings
    }

    /**
     * Examine the input data to determine the output value and its corresponding NullFlavor value
     * <i>If dataString is NULL or BLANK, the output value is NULL, otherwise the output value 
     * the same as dataString
     * 
     * <i>If nullFlavorInput is NULL or BLANK, use nullFlavorDefault to decide the output nullFlavor
     * 
     * @param dataString The input data to determine output data value and nullFlavor value
     * @param nullFlavorInput The input setting of list of key:value pair of NullFlavor constants
     * @param nullFlavorDefault The default setting of list of key:value pair of NullFlavor constants
     * @return
     */
    public List<String> nullFlavor(String dataString, String nullFlavorInput, String nullFlavorDefault)
    {
    	NullFlavorSetting nullSetting;
    	if (nullFlavorInput==null||nullFlavorInput.equals(""))
    		nullSetting=new NullFlavorSetting(nullFlavorDefault);
    	else
    		nullSetting=new NullFlavorSetting(nullFlavorInput);
    	
    	//retrieve NullFlavor constant based on input data
    	String rtnNullValue=dataString;
    	String nullFlavorKey="NULL";
    	if (dataString==null)
    		nullFlavorKey="NULL";
    	else if(dataString.equals(""))
    		nullFlavorKey="BLANK";
    	else  if(dataString.equalsIgnoreCase(GeneralUtilities.CAADAPTER_DATA_FIELD_NULL))
    	{
    		rtnNullValue=null;
    		nullFlavorKey="NULL";
    	}
    	else
    		nullFlavorKey=dataString;
     		
    	String rtnNullFlavor=(String)nullSetting.get(nullFlavorKey);
    	    	
    	String nullifyMissingData=CaadapterUtil.findApplicationConfigValue(Config.CAADAPTER_COMPONENT_HL7_MISSING_DATA_NULLFLAVOR_NULLIFIED);
		if (nullifyMissingData!=null&&nullifyMissingData.equalsIgnoreCase("true"))
		 	rtnNullValue=null;

		//verify if it is a valid NULL FLAVOR constant defined by HL7
		String nfValuesAllowed=CaadapterUtil.findApplicationConfigValue(Config.CAADAPTER_COMPONENT_HL7_NULLFLAVOR_VALUES_ALLOWED);
		if (rtnNullFlavor!=null&&nfValuesAllowed!=null)
		{
			String[] valuesAlled=nfValuesAllowed.split(",");
			boolean isValidNF=false;
			for (String nfValue:valuesAlled)
			{
				if (nfValue!=null&&nfValue.equalsIgnoreCase(rtnNullFlavor))
				{
					isValidNF=true;
					break;
				}
			}
			if (!isValidNF)
				Log.logWarning(this, "Invalid NullFlavor value is found:"+rtnNullFlavor);
		}
		
		List<String> rtnList=new ArrayList<String>();
		rtnList.add(rtnNullValue);
    	rtnList.add(rtnNullFlavor);
    	return rtnList;
    }
   
    /**
     * Accepts a String.
     * Returns an Object containing the numeric length of the String.
     *
     * @param strParam a String object
     * @return an Object containing the length of the String
     */
    public int length(String strParam) {
        if (strParam == null)
            return 0;
        else
            return strParam.length();
    }

    /**
     * Removes white space from both ends of this string.
     * This method may be used to trim whitespace from the beginning and end of a string.
     * It trims all ASCII control characters as well.
     *
     * @param strParam a string
     * @return an Object containing the trimmed string
     * @see String
     */

//    public String trim(String strParam) {
//        if (strParam == null) return null;
//        return strParam.trim();
//    }


    /**
     * Returns a new string that is a substring of this string.
     * The substring begins at the specified iStartPos and extends
     * to the character at index endIndex - 1.
     * Thus the length of the substring is endIndex-beginIndex.
     *
     * @param strParam  a string
     * @param iStartPos beginning index
     * @return iEndPos end index
     * @see String
     */

    public String substring(String strParam, int iStartPos, int iEndPos) {
        if (strParam == null || strParam.length() == 0)
            return "";
        if (iEndPos > strParam.length()) iEndPos = strParam.length();
        if (iEndPos < 0) iEndPos = strParam.length();
        if (iStartPos < 0) return "";
        if (iStartPos >= iEndPos) return "";
        String strSubString = null; //Holds the resulting substring.
        strSubString = strParam.substring(iStartPos, iEndPos); //Create a substring.
        return strSubString; //Return the resulting substring.
    }

    /**
     * Returns the position of a substring within a string.
     *
     * @param strSource  the source string
     * @param strPattern the string pattern to search
     * @return the position of the String
     * @see String
     */

    public int instring(String strSource, String strPattern) {
        if ((strSource == null || strSource.length() == 0) || (strPattern == null || strPattern.length() == 0))
            return -1;

        return strSource.indexOf(strPattern);
    }

    /**
     * Replaces all occurences of a pattern within a string.
     *
     * @param strSource      the source string
     * @param strPattern     the string pattern to search
     * @param strReplacement the string pattern that will replace the strPattern
     * @return A new string including the string replacements.
     * @see String
     */

    public Object replace(String strSource, String strPattern, String strReplacement) {
        if (strSource == null) return "";
        if ((strSource.length() == 0) || (strPattern == null || strPattern.length() == 0))
            return strSource;

        return strSource.replaceAll(strPattern, strReplacement);
    }

    /**
     * Replaces all characters in the string with upper case characters.
     *
     * @param strSource the source string
     * @return A new string with all characters in upper case.
     * @see String
     */
    public Object upper(String strSource) {
        //Check to see if the incoming value is empty
        if (strSource == null || strSource.length() == 0)
            return "";
        //else
        return strSource.toUpperCase();
    }

    /**
     * Replaces all characters in the string with lower case characters.
     *
     * @param strSource the source string
     * @return A new string with all characters in lower case.
     * @see String
     */
    public Object lower(String strSource) {

        if (strSource == null || strSource.length() == 0)
            return "";

        return strSource.toLowerCase();
    }

    /**
     * Replaces the fist character of each word in a string to uppercase
     * and all other characters to lower case.
     *
     * @param strSource the source string
     * @return A new string with first character changed to uppercase.
     * @see String
     */
    public Object initcap(String strSource) {
        if (strSource == null || strSource.length() == 0)
            return "";


        //boolean bcapitalize = false;   //Set a flag to make character capital based on whitespace character
        char[] caData = null;          //Hold the incoming string in a character array


        //Check to see if the incoming value is empty


        //else
        boolean bcapitalize = true;
        caData = strSource.toCharArray(); //convert to character array


        //Replace first character in each word to upper case.
        for (int i = 0; i < caData.length; i++) {
            if (caData[i] == ' ' || Character.isWhitespace(caData[i]))
                bcapitalize = true;
            else if (bcapitalize) {
                caData[i] = Character.toUpperCase(caData[i]);
                //System.out.println(caData[i]);
                bcapitalize = false;
            } else {
                caData[i] = Character.toLowerCase(caData[i]);
            }
        }
        return new String(caData);
    }	//initCap

}

/**
 * HISTORY      : $Log: not supported by cvs2svn $
 * HISTORY      : Revision 1.4  2008/09/25 18:57:45  phadkes
 * HISTORY      : Changes for code standards
 * HISTORY      :
*/
