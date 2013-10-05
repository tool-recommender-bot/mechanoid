/*
 * Generated by Robotoworks Mechanoid
 */
package com.robotoworks.example.recipes.content.migrations;

import android.database.sqlite.SQLiteDatabase;
import com.robotoworks.mechanoid.db.SQLiteMigration;

public class DefaultRecipesDBMigrationV3 extends SQLiteMigration {
	@Override
	public void onBeforeUp(SQLiteDatabase db) {}
	
	@Override
	public void up(SQLiteDatabase db) {
		db.execSQL(
			"create table ingredients ( " +
			"_id integer primary key autoincrement, " +
			"recipe_id integer, " +
			"quantity text, " +
			"ingredient text " +
			") "
		);	
		db.execSQL(
			"create view recipes_and_ingredients as " +
			"select " +
			"_id as _id, " +
			"0 as row_type, " +
			"title as title, " +
			"description as description, " +
			"null as ingredient_quantity, " +
			"_id || \"-0\" as sort_key " +
			"from recipes " +
			"union " +
			"select " +
			"_id as _id, " +
			"1 as row_type, " +
			"ingredient as title, " +
			"null as description, " +
			"quantity as ingredient_quantity, " +
			"recipe_id || \"-\" || _id as sort_key " +
			"from ingredients " +
			"order by sort_key asc "
		);	
	}
	
	@Override
	public void onAfterUp(SQLiteDatabase db) {}
}
