/*
 * generated by Xtext
 */
package com.robotoworks.mechanoid.db.ui.contentassist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;

import com.google.common.base.Predicates;
import com.robotoworks.mechanoid.db.sqliteModel.AlterTableAddColumnStatement;
import com.robotoworks.mechanoid.db.sqliteModel.ColumnDef;
import com.robotoworks.mechanoid.db.sqliteModel.ColumnSourceRef;
import com.robotoworks.mechanoid.db.sqliteModel.CreateTableStatement;
import com.robotoworks.mechanoid.db.sqliteModel.ResultColumn;
import com.robotoworks.mechanoid.db.sqliteModel.SelectList;
import com.robotoworks.mechanoid.db.sqliteModel.SingleSourceTable;
import com.robotoworks.mechanoid.db.sqliteModel.SqliteModelPackage;
import com.robotoworks.mechanoid.db.sqliteModel.TableDefinition;
import com.robotoworks.mechanoid.db.util.ModelUtil;


/**
 * see
 * http://www.eclipse.org/Xtext/documentation/latest/xtext.html#contentAssist on
 * how to customize content assistant
 */
public class SqliteModelProposalProvider extends
		AbstractSqliteModelProposalProvider {
	
	@Override
	public void completePrimaryExpression_Column(EObject model,
			Assignment assignment, ContentAssistContext context,
			final ICompletionProposalAcceptor acceptor) {
		
		EObject lastObj = NodeModelUtils.findActualSemanticObjectFor(context.getLastCompleteNode());
		EObject currentObj = NodeModelUtils.findActualSemanticObjectFor(context.getCurrentNode());
		
		ICompositeNode node = NodeModelUtils.getNode(lastObj);
		
		if(lastObj instanceof ColumnSourceRef) {
			ColumnSourceRef ref = (ColumnSourceRef) lastObj;
			
			if(ref.getColumn() == null) {
				lookupCrossReference(
						ref, 
						SqliteModelPackage.Literals.COLUMN_SOURCE_REF__COLUMN, 
						acceptor, 
						Predicates.<IEObjectDescription> alwaysTrue(), 
						getProposalFactory("column", context));
				return;
			}
		}
		
		SelectList selectList = ModelUtil.getAncestorOfType(currentObj, SelectList.class);
		
		if(selectList != null) {
			lookupCrossReference(
					selectList, 
					SqliteModelPackage.Literals.COLUMN_SOURCE_REF__COLUMN, 
					acceptor, 
					Predicates.<IEObjectDescription> alwaysTrue(), 
					getProposalFactory("column", context));
			return;
		}
			
		super.completePrimaryExpression_Column(model, assignment, context, acceptor);
	}
	
	@Override
	protected String getDisplayString(EObject element,
			String qualifiedNameAsString, String shortName) {
		if(element instanceof ColumnDef) {
			ColumnDef def = (ColumnDef) element;
			EObject container = def.eContainer();
			
			if(container instanceof TableDefinition) {
				return def.getName() + ":" + def.getType().getName() + " - " + ((TableDefinition)def.eContainer()).getName();
			} else if (container instanceof AlterTableAddColumnStatement) {
				return def.getName() + ":" + def.getType().getName() + " - " + ((AlterTableAddColumnStatement)def.eContainer()).getTable().getName();
			} else {
				return def.getName() + ":" + def.getType().getName();
			}
		} else if (element instanceof SingleSourceTable) {
			SingleSourceTable t = (SingleSourceTable) element;
			
			return (t.getName() == null ? t.getTableReference().getName() : t.getName() + " - " + t.getTableReference().getName());
		} else if (element instanceof CreateTableStatement) {
			CreateTableStatement t = (CreateTableStatement) element;
			return t.getName();
		} else if(element instanceof ResultColumn) {
			ResultColumn r = (ResultColumn) element;
			if(r.getName() != null) {
				return r.getName() + ":" + ModelUtil.getInferredColumnType(r).getName();
			}
		}
		
		return super.getDisplayString(element, qualifiedNameAsString, shortName);
	}
}
