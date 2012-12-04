package com.robotoworks.mechanoid.sqlite.generator

import com.robotoworks.mechanoid.sqlite.sqliteModel.CreateTableStatement
import com.robotoworks.mechanoid.sqlite.sqliteModel.CreateViewStatement
import com.robotoworks.mechanoid.sqlite.sqliteModel.MigrationBlock
import com.robotoworks.mechanoid.sqlite.sqliteModel.Model
import com.robotoworks.mechanoid.sqlite.sqliteModel.ResultColumnAll
import com.robotoworks.mechanoid.sqlite.sqliteModel.ResultColumnAllWithTableRef
import com.robotoworks.mechanoid.sqlite.sqliteModel.ResultColumnExpression

import static extension com.robotoworks.mechanoid.sqlite.generator.Extensions.*
import static extension com.robotoworks.mechanoid.common.util.Strings.*

class ContentProviderContractGenerator {
		def CharSequence generate(Model model, MigrationBlock snapshot) { 
			'''
			/*
			 * Generated by Robotoworks Mechanoid
			 */
			package �model.packageName�;
			
			import android.content.ContentValues;
			import android.net.Uri;
			import android.provider.BaseColumns;
			import android.content.ContentResolver;
			
			public class �model.database.name.pascalize�Contract  {
			    public static final String CONTENT_AUTHORITY = "�model.packageName�.�model.database.name.toLowerCase�";
			
			    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
			
				�FOR tbl : snapshot.statements.filter(typeof(CreateTableStatement))�
				interface �tbl.name.pascalize�Columns {
					�FOR col : tbl.columnDefs.filter([!name.equals("_id")])�
					String �col.name.underscore.toUpperCase� = "�col.name�";
					�ENDFOR�
				}
				
				�ENDFOR�

				�FOR vw :  snapshot.statements.filter(typeof(CreateViewStatement))�
				interface �vw.name.pascalize�Columns {
					�FOR col : vw.selectStatement.coreStatements.get(0).resultColumns�
					�generateInterfaceMemberForResultColumn(col)�
					�ENDFOR�
				}
				
				�ENDFOR�
						
				�FOR tbl : snapshot.statements.filter(typeof(CreateTableStatement))�
				public static class �tbl.name.pascalize� implements �tbl.name.pascalize�Columns�IF tbl.hasAndroidPrimaryKey�, BaseColumns�ENDIF� {
				    public static final Uri CONTENT_URI = 
							BASE_CONTENT_URI.buildUpon().appendPath("�tbl.name�").build();
				
				    public static final String CONTENT_TYPE =
				            "vnd.android.cursor.dir/vnd.�model.database.name.toLowerCase�.�tbl.name�";
				    public static final String ITEM_CONTENT_TYPE =
				            "vnd.android.cursor.item/vnd.�model.database.name.toLowerCase�.�tbl.name�";
				
				    public static Uri buildGetByIdUri(String id) {
				        return CONTENT_URI.buildUpon().appendPath(id).build();
				    }
				
					public static ContentValues createContentValues(�createMethodArgsFromColumns(tbl)�) {
						ContentValues values = new ContentValues();
						�FOR col : tbl.columnDefs.filter([!name.equals("_id")])�
						values.put(�tbl.name.pascalize�.�col.name.underscore.toUpperCase�, �col.name.camelize�);
						�ENDFOR�
						return values;
					}
					
					public static Uri insert(ContentResolver contentResolver, �createMethodArgsFromColumns(tbl)�) {
						ContentValues values = createContentValues(
						�FOR col : tbl.columnDefs.filter([!name.equals("_id")]) SEPARATOR ", "�
							�col.name.camelize�
						�ENDFOR�
						);
						return contentResolver.insert(CONTENT_URI, values);
					}
					
					public static int delete(ContentResolver contentResolver) {
						return contentResolver.delete(CONTENT_URI, null, null);
					}
					
					public static int delete(ContentResolver contentResolver, String where, String[] selectionArgs) {
						return contentResolver.delete(CONTENT_URI, where, selectionArgs);
					}
				}
				
				�ENDFOR�
			
				�FOR vw : snapshot.statements.filter(typeof(CreateViewStatement))�
				public static class �vw.name.pascalize� implements �vw.name.pascalize�Columns�IF vw.hasAndroidPrimaryKey�, BaseColumns�ENDIF� {
				    public static final Uri CONTENT_URI = 
							BASE_CONTENT_URI.buildUpon().appendPath("�vw.name�").build();
				
				    public static final String CONTENT_TYPE =
				            "vnd.android.cursor.dir/vnd.�model.database.name.toLowerCase�.�vw.name�";
					�IF vw.hasAndroidPrimaryKey�
					public static final String ITEM_CONTENT_TYPE =
						"vnd.android.cursor.item/vnd.�model.database.name.toLowerCase�.�vw.name�";
					�ENDIF�
				}

				�ENDFOR�
				
				�IF model.database.actions != null�
				�FOR action : model.database.actions.actions�
				public static class �action.name.pascalize� {
				    public static final Uri CONTENT_URI = 
							BASE_CONTENT_URI.buildUpon().appendPath("�action.path�").build();
				
				    public static final String CONTENT_TYPE =
				            "vnd.android.cursor.dir/vnd.�model.database.name.toLowerCase�.�action.name�";
				}

				�ENDFOR�
				�ENDIF�
			
				private �model.database.name.pascalize�Contract(){}
			}
			'''
	}
	
	def createMethodArgsFromColumns(CreateTableStatement tbl) {
		'''�FOR col : tbl.columnDefs.filter([!name.equals("_id")]) SEPARATOR ", "��col.type.toJavaTypeName()� �col.name.camelize��ENDFOR�'''
	}
	
	def dispatch generateInterfaceMemberForResultColumn(ResultColumnAll column) { 
	}
	
	def dispatch generateInterfaceMemberForResultColumn(ResultColumnExpression column) { 
		'''
		�IF column.name != null && !column.name.equals("") && !column.name.equals("_id")�
		String �column.name.underscore.toUpperCase� = "�column.name�";
		�ENDIF�
		'''		
	}

	def dispatch generateInterfaceMemberForResultColumn(ResultColumnAllWithTableRef column) { 
		
	}
	
}