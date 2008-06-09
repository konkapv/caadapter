/**
 * <!-- LICENSE_TEXT_START -->
The contents of this file are subject to the caAdapter Software License (the "License"). You may obtain a copy of the License at the following location: 
[caAdapter Home Directory]\docs\caAdapter_license.txt, or at:
http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caadapter/indexContent/docs/caAdapter_License
 * <!-- LICENSE_TEXT_END -->
 */


 package gov.nih.nci.caadapter.hl7.map;

import gov.nih.nci.caadapter.common.ApplicationException;

/**
 * An exception for the mapping subsystem.
 *
 * @author OWNER: Matthew Giordano
 * @author LAST UPDATE $Author: phadkes $
 * @since     caAdapter v1.2
 * @version    $Revision: 1.2 $
 * @date        $Date: 2008-06-09 19:53:50 $
 */

public class MappingException extends ApplicationException{
    public MappingException(String message, Throwable cause)
	{
        super(message, cause);
    }

	public MappingException(String message, Throwable cause, String severity)
	{
		super(message, cause, severity);
	}
}
