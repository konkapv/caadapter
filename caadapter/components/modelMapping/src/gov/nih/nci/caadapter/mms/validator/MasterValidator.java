/**
 * <!-- LICENSE_TEXT_START -->
The contents of this file are subject to the caAdapter Software License (the "License"). You may obtain a copy of the License at the following location: 
[caAdapter Home Directory]\docs\caAdapter_license.txt, or at:
http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caadapter/indexContent/docs/caAdapter_License
 * <!-- LICENSE_TEXT_END -->
 */

package gov.nih.nci.caadapter.mms.validator;
import com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator;

import gov.nih.nci.caadapter.mms.map.CumulativeMapping;
import gov.nih.nci.caadapter.common.metadata.ModelMetadata;

/**
 * This is a singleton class and it's purpose is to coordinate all
 * aspects of the object to database mapping validation tasks.
 * @version 1.0
 * @created 11-Aug-2006 8:18:17 AM
 */
public class MasterValidator {

	public CumulativeMapping currentMapping;
	public DatatypeValidator datatypeValidator;
	public AttributeMappingValidator dependencyMappingValidator;
	public SingleAssociationMappingValidator singleAssociationMappingValidator;
	public ManyToManyMappingValidator manyToManyMappingValidator;
	public ModelMetadata modelMetadata;
	private static MasterValidator uniqueInstance;

	private MasterValidator(){
	}

	public static MasterValidator getInstance(){
		return null;
	}

	public boolean validate(){
		return false;
	}

	public boolean validateAttributeMapping(){
		return false;
	}

	public boolean validateDependencyMapping(){
		return false;
	}

	public boolean validateManyToManyMapping(){
		return false;
	}

	public boolean validateSemanticMapping(){
		return false;
	}

	public boolean validateSingleAssociationMapping(){
		return false;
	}

}
