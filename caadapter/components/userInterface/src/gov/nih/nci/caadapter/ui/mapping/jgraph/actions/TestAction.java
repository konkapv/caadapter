/**
 * <!-- LICENSE_TEXT_START -->
The contents of this file are subject to the caAdapter Software License (the "License"). You may obtain a copy of the License at the following location: 
[caAdapter Home Directory]\docs\caAdapter_license.txt, or at:
http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caadapter/indexContent/docs/caAdapter_License
 * <!-- LICENSE_TEXT_END -->
 */


package gov.nih.nci.caadapter.ui.mapping.jgraph.actions;

import gov.nih.nci.caadapter.common.metadata.ModelMetadata;
import gov.nih.nci.caadapter.ui.common.actions.AbstractContextAction;
import gov.nih.nci.caadapter.ui.mapping.AbstractMappingPanel;
import gov.nih.nci.caadapter.ui.mapping.MappingMiddlePanel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.util.HashSet;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * This class defines the action to delete selected graphic cells.
 * @author OWNER: Scott Jiang
 * @author LAST UPDATE $Author: phadkes $
 * @version Since caAdapter v1.2
 *          revision    $Revision: 1.6 $
 *          date        $Date: 2008-06-09 19:54:06 $
 */
public class TestAction extends AbstractContextAction
{
	private static final String LOGID = "$RCSfile: TestAction.java,v $";
	public static String RCSID = "$Header: /share/content/gforge/caadapter/caadapter/components/userInterface/src/gov/nih/nci/caadapter/ui/mapping/jgraph/actions/TestAction.java,v 1.6 2008-06-09 19:54:06 phadkes Exp $";

	private static final String COMMAND_NAME = "Set as Lazy";
	private static final Character COMMAND_MNEMONIC = new Character('L');
	private static final KeyStroke ACCELERATOR_KEY_STROKE = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);

	public String contextText = "Set as Lazy";	
	private AbstractMappingPanel absMappingPanel;
	private MappingMiddlePanel middlePanel;
	
	/**
	 * Defines an <code>Action</code> object with a default
	 * description string and default icon.
	 */
	public TestAction( AbstractMappingPanel abstractPanel, MappingMiddlePanel midPanel, String superText ) {					
        super(superText, null);
        contextText = superText;
        this.absMappingPanel = abstractPanel;
		this.middlePanel = midPanel;
		setMnemonic(COMMAND_MNEMONIC);
		setActionCommandType(DOCUMENT_ACTION_TYPE);
	}
	
	/**
	 * The abstract function that descendant classes must be overridden to provide customsized handling.
	 *
	 * @param e
	 * @return true if the action is finished successfully; otherwise, return false.
	 */
	protected boolean doAction(ActionEvent e)
	{	
		//TODO: if lazy/eager
		
		//Set node to lazy, add to lazyKey list
		if( contextText.equals("Set as Lazy") )
		{
			int userChoice = JOptionPane.showConfirmDialog(middlePanel,
					"Set this as Lazy?", "Question",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);		
				
				if (userChoice == JOptionPane.YES_OPTION)
				{				
				    try {	
						JTree sourceTree = absMappingPanel.getSourceTree();			
						JTree targetTree = absMappingPanel.getTargetTree();
										
				    	ModelMetadata modelMetadata = ModelMetadata.getInstance();				    	
				    	HashSet<String> lazyKeys = modelMetadata.getLazyKeys();
				    	
						System.out.println( "targetTree: " + targetTree.getSelectionPath() );
		
						if ( targetTree.getSelectionRows() != null )
						{						
								TreePath leadingPath = targetTree.getLeadSelectionPath();						
								String node = leadingPath.toString();						
								node = parseNode( node );
						        
								System.out.println( "IN LazyKeyAction > " + node );												

								TreePath paths[] = targetTree.getSelectionPaths();
								DefaultMutableTreeNode mutNode = (DefaultMutableTreeNode)leadingPath.getLastPathComponent();
								
								System.out.println( "Sibling count:" + mutNode.getSiblingCount() );
								System.out.println( "Child count: " + mutNode.getChildCount() );
								
								DefaultMutableTreeNode parent = (DefaultMutableTreeNode)mutNode.getParent();								
								lazyKeys.add( node );
							
							//update graphics
						}												
				    	
				    	if(lazyKeys != null ) {
				    		System.out.println( "Current lazy Keys = \n" + lazyKeys );
				    	} else {
				    		System.out.println( "No lazy Keys" );
				    	}
				    	
				    	modelMetadata.setLazyKeys( lazyKeys );
				    	
				    } catch (Exception exception){
				    	exception.printStackTrace();
				    }
				}
		} 
		else {
			//Remove node from lazyKey list (set as Eager)
			int userChoice = JOptionPane.showConfirmDialog(middlePanel,
					"Set this as Eager?", "Question",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if ( userChoice == JOptionPane.YES_OPTION )
			{				
			    try {
			    	
			    	JTree targetTree = absMappingPanel.getTargetTree();
			    	TreePath leadingPath = targetTree.getLeadSelectionPath();
			    	ModelMetadata modelMetadata = ModelMetadata.getInstance();				    	
			    	HashSet<String> lazyKeys = modelMetadata.getLazyKeys();
			    	
			        if ( lazyKeys.contains( parseNode( leadingPath.toString() ) ) )
			        {
			        	System.out.println( "Removing lazyKey... (setting as eager)" );
			        	lazyKeys.remove( parseNode( leadingPath.toString() ) );        	
			        }
			        
			        //update graphics
			        
			    } catch ( Exception exception ) {
			    	exception.printStackTrace();
			    }
			}
		}
		
		return isSuccessfullyPerformed();
	}
	
	public String parseNode( String node )
	{
		node = replace( node, ", ", "." );
		node = replace( node, "[", " " );
		node = replace( node, "]", " " ); 
        node = replace( node, "Data Model.", "" );  
        node = node.trim();     
		return node; 
	}
	
	public void traverse(JTree tree) 
	{ 
	   TreeModel model = tree.getModel();
	   if (model != null) 
	   {
		      Object root = model.getRoot();
		      System.out.println(root.toString());
		      walk(model,root);    
	   }
	   else
	   {
	        System.out.println("Tree is empty.");
	   }
    }
		    
	  protected void walk(TreeModel model, Object o)
	  {
	    int  cc;
	    cc = model.getChildCount(o);
	    for( int i=0; i < cc; i++) 
	    {
	      Object child = model.getChild(o, i );
	      if (model.isLeaf(child))
	      {
	        System.out.println(child.toString());
	      }
	      else {
	        System.out.print(child.toString()+"--");
	        walk(model,child ); 
	        }
	     }
	  }
	  
	protected Component getAssociatedUIComponent()
	{
		return middlePanel;	
	}
	
    static String replace(String str, String pattern, String replace) {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();
    
        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e+pattern.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }
}
