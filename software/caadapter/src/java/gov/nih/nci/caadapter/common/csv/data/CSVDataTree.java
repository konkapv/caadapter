/*L
 * Copyright SAIC, SAIC-Frederick.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caadapter/LICENSE.txt for details.
 */

package gov.nih.nci.caadapter.common.csv.data;

import gov.nih.nci.caadapter.common.ApplicationException;
import gov.nih.nci.caadapter.common.standard.DataTree;

/**
 * This class defines ...
 *
 * @author OWNER: Kisung Um
 * @author LAST UPDATE $Author: phadkes $
 * @version Since caAdapter v3.3
 *          revision    $Revision: 1.3 $
 *          date        Jul 3, 2007
 *          Time:       11:10:00 AM $
 */
public interface CSVDataTree extends DataTree
{
    public void setCSVFileName(String fileName) throws ApplicationException;
    public String getCSVFileName();

}


/**
 * HISTORY      : $Log: not supported by cvs2svn $
 * HISTORY      : Revision 1.2  2008/06/06 18:53:45  phadkes
 * HISTORY      : Changes for License Text
 * HISTORY      :
 * HISTORY      : Revision 1.1  2007/07/09 15:36:40  umkis
 * HISTORY      : csv cardinality
 * HISTORY      :
 */

