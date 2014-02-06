/*
 * Generated by Robotoworks Mechanoid
 */
package com.robotoworks.example.recipes.content;

import android.net.Uri;
import android.provider.BaseColumns;
import com.robotoworks.mechanoid.Mechanoid;
import com.robotoworks.mechanoid.db.AbstractValuesBuilder;
import java.lang.reflect.Field;			
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

public class RecipesDBContract  {
    public static final String CONTENT_AUTHORITY = initAuthority();

	private static String initAuthority() {
		String authority = "com.robotoworks.example.recipes.content.recipesdb";

		try {
    		
    		ClassLoader loader = RecipesDBContract.class.getClassLoader();
    		
			Class<?> clz = loader.loadClass("com.robotoworks.example.recipes.content.RecipesDBContentProviderAuthority");
			Field declaredField = clz.getDeclaredField("CONTENT_AUTHORITY");
			
			authority = declaredField.get(null).toString();
		} catch (ClassNotFoundException e) {} 
    	catch (NoSuchFieldException e) {} 
    	catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}
		
		return authority;
	}
	
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

	interface AuthorsColumns {
		String NAME = "name";
	}
	
	interface IngredientsColumns {
		String RECIPE_ID = "recipe_id";
		String QUANTITY = "quantity";
		String INGREDIENT = "ingredient";
	}
	
	interface RecipesColumns {
		String TITLE = "title";
		String DESCRIPTION = "description";
		String AUTHOR_ID = "author_id";
	}
	
	interface RecipesAndIngredientsColumns {
		String ROW_TYPE = "row_type";
		String TITLE = "title";
		String DESCRIPTION = "description";
		String INGREDIENT_QUANTITY = "ingredient_quantity";
		String SORT_KEY = "sort_key";
	}
	interface RecipesWithAuthorsColumns {
		String RECIPE_TITLE = "recipe_title";
		String RECIPE_DESCRIPTION = "recipe_description";
		String AUTHOR_ID = "author_id";
		String AUTHOR_NAME = "author_name";
	}
			
	/**
	 * <p>Column definitions and helper methods to work with the Authors.</p>
	 */
	public static class Authors implements AuthorsColumns, BaseColumns {
	    public static final Uri CONTENT_URI = 
				BASE_CONTENT_URI.buildUpon().appendPath("authors").build();
	
		/**
		 * <p>The content type for a cursor that contains many Authors rows.</p>
		 */
	    public static final String CONTENT_TYPE =
	            "vnd.android.cursor.dir/vnd.recipesdb.authors";
	
		/**
		 * <p>The content type for a cursor that contains a single Authors row.</p>
		 */
		public static final String ITEM_CONTENT_TYPE =
			"vnd.android.cursor.item/vnd.recipesdb.authors";
	
		/**
		 * <p>Builds a Uri with appended id for a row in Authors, 
		 * eg:- content://com.robotoworks.example.recipes.content.recipesdb/authors/123.</p>
		 */
	    public static Uri buildUriWithId(long id) {
	        return CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();
	    }
		public static int delete() {
			return Mechanoid.getContentResolver().delete(Authors.CONTENT_URI, null, null);
		}
		
		public static int delete(String where, String[] selectionArgs) {
			return Mechanoid.getContentResolver().delete(Authors.CONTENT_URI, where, selectionArgs);
		}
		
		/**
		 * <p>Create a new Builder for Authors</p>
		 */
		public static Builder newBuilder() {
			return new Builder();
		}
		
		/**
		 * <p>Build and execute insert or update statements for Authors.</p>
		 *
		 * <p>Use {@link Authors#newBuilder()} to create new builder</p>
		 */
		public static class Builder extends AbstractValuesBuilder {
			private Builder() {
				super(Mechanoid.getApplicationContext(), Authors.CONTENT_URI);
			}
			
			public Builder setName(String value) {
				mValues.put(Authors.NAME, value);
				return this;
			}
		}
		
		static final Set<Uri> VIEW_URIS;
		
		static {
			HashSet<Uri> viewUris =  new HashSet<Uri>();
	
			viewUris.add(RecipesWithAuthors.CONTENT_URI);
			
			VIEW_URIS = Collections.unmodifiableSet(viewUris);
		}
	}
	/**
	 * <p>Column definitions and helper methods to work with the Ingredients.</p>
	 */
	public static class Ingredients implements IngredientsColumns, BaseColumns {
	    public static final Uri CONTENT_URI = 
				BASE_CONTENT_URI.buildUpon().appendPath("ingredients").build();
	
		/**
		 * <p>The content type for a cursor that contains many Ingredients rows.</p>
		 */
	    public static final String CONTENT_TYPE =
	            "vnd.android.cursor.dir/vnd.recipesdb.ingredients";
	
		/**
		 * <p>The content type for a cursor that contains a single Ingredients row.</p>
		 */
		public static final String ITEM_CONTENT_TYPE =
			"vnd.android.cursor.item/vnd.recipesdb.ingredients";
	
		/**
		 * <p>Builds a Uri with appended id for a row in Ingredients, 
		 * eg:- content://com.robotoworks.example.recipes.content.recipesdb/ingredients/123.</p>
		 */
	    public static Uri buildUriWithId(long id) {
	        return CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();
	    }
		public static int delete() {
			return Mechanoid.getContentResolver().delete(Ingredients.CONTENT_URI, null, null);
		}
		
		public static int delete(String where, String[] selectionArgs) {
			return Mechanoid.getContentResolver().delete(Ingredients.CONTENT_URI, where, selectionArgs);
		}
		
		/**
		 * <p>Create a new Builder for Ingredients</p>
		 */
		public static Builder newBuilder() {
			return new Builder();
		}
		
		/**
		 * <p>Build and execute insert or update statements for Ingredients.</p>
		 *
		 * <p>Use {@link Ingredients#newBuilder()} to create new builder</p>
		 */
		public static class Builder extends AbstractValuesBuilder {
			private Builder() {
				super(Mechanoid.getApplicationContext(), Ingredients.CONTENT_URI);
			}
			
			public Builder setRecipeId(long value) {
				mValues.put(Ingredients.RECIPE_ID, value);
				return this;
			}
			public Builder setQuantity(String value) {
				mValues.put(Ingredients.QUANTITY, value);
				return this;
			}
			public Builder setIngredient(String value) {
				mValues.put(Ingredients.INGREDIENT, value);
				return this;
			}
		}
		
		static final Set<Uri> VIEW_URIS;
		
		static {
			HashSet<Uri> viewUris =  new HashSet<Uri>();
	
			viewUris.add(RecipesAndIngredients.CONTENT_URI);
			
			VIEW_URIS = Collections.unmodifiableSet(viewUris);
		}
	}
	/**
	 * <p>Column definitions and helper methods to work with the Recipes.</p>
	 */
	public static class Recipes implements RecipesColumns, BaseColumns {
	    public static final Uri CONTENT_URI = 
				BASE_CONTENT_URI.buildUpon().appendPath("recipes").build();
	
		/**
		 * <p>The content type for a cursor that contains many Recipes rows.</p>
		 */
	    public static final String CONTENT_TYPE =
	            "vnd.android.cursor.dir/vnd.recipesdb.recipes";
	
		/**
		 * <p>The content type for a cursor that contains a single Recipes row.</p>
		 */
		public static final String ITEM_CONTENT_TYPE =
			"vnd.android.cursor.item/vnd.recipesdb.recipes";
	
		/**
		 * <p>Builds a Uri with appended id for a row in Recipes, 
		 * eg:- content://com.robotoworks.example.recipes.content.recipesdb/recipes/123.</p>
		 */
	    public static Uri buildUriWithId(long id) {
	        return CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();
	    }
		public static int delete() {
			return Mechanoid.getContentResolver().delete(Recipes.CONTENT_URI, null, null);
		}
		
		public static int delete(String where, String[] selectionArgs) {
			return Mechanoid.getContentResolver().delete(Recipes.CONTENT_URI, where, selectionArgs);
		}
		
		/**
		 * <p>Create a new Builder for Recipes</p>
		 */
		public static Builder newBuilder() {
			return new Builder();
		}
		
		/**
		 * <p>Build and execute insert or update statements for Recipes.</p>
		 *
		 * <p>Use {@link Recipes#newBuilder()} to create new builder</p>
		 */
		public static class Builder extends AbstractValuesBuilder {
			private Builder() {
				super(Mechanoid.getApplicationContext(), Recipes.CONTENT_URI);
			}
			
			public Builder setTitle(String value) {
				mValues.put(Recipes.TITLE, value);
				return this;
			}
			public Builder setDescription(String value) {
				mValues.put(Recipes.DESCRIPTION, value);
				return this;
			}
			public Builder setAuthorId(long value) {
				mValues.put(Recipes.AUTHOR_ID, value);
				return this;
			}
		}
		
		static final Set<Uri> VIEW_URIS;
		
		static {
			HashSet<Uri> viewUris =  new HashSet<Uri>();
	
			viewUris.add(RecipesAndIngredients.CONTENT_URI);
			viewUris.add(RecipesWithAuthors.CONTENT_URI);
			
			VIEW_URIS = Collections.unmodifiableSet(viewUris);
		}
	}

	/**
	 * <p>Column definitions and helper methods to work with the RecipesAndIngredients.</p>
	 */
	public static class RecipesAndIngredients implements RecipesAndIngredientsColumns, BaseColumns {
	    public static final Uri CONTENT_URI = 
				BASE_CONTENT_URI.buildUpon().appendPath("recipes_and_ingredients").build();
	
		/**
		 * <p>The content type for a cursor that contains many RecipesAndIngredients rows.</p>
		 */
	    public static final String CONTENT_TYPE =
	            "vnd.android.cursor.dir/vnd.recipesdb.recipes_and_ingredients";
	
		/**
		 * <p>The content type for a cursor that contains a single RecipesAndIngredients row.</p>
		 */
		public static final String ITEM_CONTENT_TYPE =
			"vnd.android.cursor.item/vnd.recipesdb.recipes_and_ingredients";
	
		/**
		 * <p>Builds a Uri with appended id for a row in RecipesAndIngredients, 
		 * eg:- content://com.robotoworks.example.recipes.content.recipesdb/recipes_and_ingredients/123.</p>
		 */
	    public static Uri buildUriWithId(long id) {
	        return CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();
	    }
		public static int delete() {
			return Mechanoid.getContentResolver().delete(RecipesAndIngredients.CONTENT_URI, null, null);
		}
		
		public static int delete(String where, String[] selectionArgs) {
			return Mechanoid.getContentResolver().delete(RecipesAndIngredients.CONTENT_URI, where, selectionArgs);
		}
		
		/**
		 * <p>Create a new Builder for RecipesAndIngredients</p>
		 */
		public static Builder newBuilder() {
			return new Builder();
		}
		
		/**
		 * <p>Build and execute insert or update statements for RecipesAndIngredients.</p>
		 *
		 * <p>Use {@link RecipesAndIngredients#newBuilder()} to create new builder</p>
		 */
		public static class Builder extends AbstractValuesBuilder {
			private Builder() {
				super(Mechanoid.getApplicationContext(), RecipesAndIngredients.CONTENT_URI);
			}
			
			public Builder setRowType(String value) {
				mValues.put(RecipesAndIngredients.ROW_TYPE, value);
				return this;
			}
			public Builder setTitle(String value) {
				mValues.put(RecipesAndIngredients.TITLE, value);
				return this;
			}
			public Builder setDescription(String value) {
				mValues.put(RecipesAndIngredients.DESCRIPTION, value);
				return this;
			}
			public Builder setIngredientQuantity(String value) {
				mValues.put(RecipesAndIngredients.INGREDIENT_QUANTITY, value);
				return this;
			}
			public Builder setSortKey(String value) {
				mValues.put(RecipesAndIngredients.SORT_KEY, value);
				return this;
			}
		}
		
		static final Set<Uri> VIEW_URIS;
		
		static {
			HashSet<Uri> viewUris =  new HashSet<Uri>();
	
			
			VIEW_URIS = Collections.unmodifiableSet(viewUris);
		}
	}
	/**
	 * <p>Column definitions and helper methods to work with the RecipesWithAuthors.</p>
	 */
	public static class RecipesWithAuthors implements RecipesWithAuthorsColumns, BaseColumns {
	    public static final Uri CONTENT_URI = 
				BASE_CONTENT_URI.buildUpon().appendPath("recipes_with_authors").build();
	
		/**
		 * <p>The content type for a cursor that contains many RecipesWithAuthors rows.</p>
		 */
	    public static final String CONTENT_TYPE =
	            "vnd.android.cursor.dir/vnd.recipesdb.recipes_with_authors";
	
		/**
		 * <p>The content type for a cursor that contains a single RecipesWithAuthors row.</p>
		 */
		public static final String ITEM_CONTENT_TYPE =
			"vnd.android.cursor.item/vnd.recipesdb.recipes_with_authors";
	
		/**
		 * <p>Builds a Uri with appended id for a row in RecipesWithAuthors, 
		 * eg:- content://com.robotoworks.example.recipes.content.recipesdb/recipes_with_authors/123.</p>
		 */
	    public static Uri buildUriWithId(long id) {
	        return CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();
	    }
		public static int delete() {
			return Mechanoid.getContentResolver().delete(RecipesWithAuthors.CONTENT_URI, null, null);
		}
		
		public static int delete(String where, String[] selectionArgs) {
			return Mechanoid.getContentResolver().delete(RecipesWithAuthors.CONTENT_URI, where, selectionArgs);
		}
		
		/**
		 * <p>Create a new Builder for RecipesWithAuthors</p>
		 */
		public static Builder newBuilder() {
			return new Builder();
		}
		
		/**
		 * <p>Build and execute insert or update statements for RecipesWithAuthors.</p>
		 *
		 * <p>Use {@link RecipesWithAuthors#newBuilder()} to create new builder</p>
		 */
		public static class Builder extends AbstractValuesBuilder {
			private Builder() {
				super(Mechanoid.getApplicationContext(), RecipesWithAuthors.CONTENT_URI);
			}
			
			public Builder setRecipeTitle(String value) {
				mValues.put(RecipesWithAuthors.RECIPE_TITLE, value);
				return this;
			}
			public Builder setRecipeDescription(String value) {
				mValues.put(RecipesWithAuthors.RECIPE_DESCRIPTION, value);
				return this;
			}
			public Builder setAuthorId(long value) {
				mValues.put(RecipesWithAuthors.AUTHOR_ID, value);
				return this;
			}
			public Builder setAuthorName(String value) {
				mValues.put(RecipesWithAuthors.AUTHOR_NAME, value);
				return this;
			}
		}
		
		static final Set<Uri> VIEW_URIS;
		
		static {
			HashSet<Uri> viewUris =  new HashSet<Uri>();
	
			
			VIEW_URIS = Collections.unmodifiableSet(viewUris);
		}
	}
	

	
	static Map<Uri, Set<Uri>> REFERENCING_VIEWS;
	
	static {
		Map<Uri, Set<Uri>> map = new HashMap<Uri, Set<Uri>>();
		
		map.put(Authors.CONTENT_URI, Authors.VIEW_URIS);
		map.put(Ingredients.CONTENT_URI, Ingredients.VIEW_URIS);
		map.put(Recipes.CONTENT_URI, Recipes.VIEW_URIS);
		map.put(RecipesAndIngredients.CONTENT_URI, RecipesAndIngredients.VIEW_URIS);
		map.put(RecipesWithAuthors.CONTENT_URI, RecipesWithAuthors.VIEW_URIS);
		
		REFERENCING_VIEWS = Collections.unmodifiableMap(map);
		
	}
	
	private RecipesDBContract(){}
	
	/**
	 * <p>Delete all rows from all tables</p>
	 */						
	public static void deleteAll() {
		Authors.delete();
		Ingredients.delete();
		Recipes.delete();
	}
}
