/*
 * Generated by Robotoworks Mechanoid
 */
package com.robotoworks.example.movies.db;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.robotoworks.example.movies.db.MovieDBContract.Movies;
import com.robotoworks.example.movies.db.MovieDBContract.Movies.Builder;
import com.robotoworks.mechanoid.util.Closeables;
import com.robotoworks.mechanoid.db.ActiveRecord;
import com.robotoworks.mechanoid.db.ActiveRecordFactory;
import com.robotoworks.mechanoid.Mechanoid;
import com.robotoworks.mechanoid.db.AbstractValuesBuilder;

public class MoviesRecord extends ActiveRecord implements Parcelable {

	private static ActiveRecordFactory<MoviesRecord> sFactory = new ActiveRecordFactory<MoviesRecord>() {
		@Override
		public MoviesRecord create(Cursor c) {
			return fromCursor(c);
		}
		
		@Override
		public String[] getProjection() {
			return PROJECTION;
		}
	};
	
	public static ActiveRecordFactory<MoviesRecord> getFactory() {
		return sFactory;
	}

    public static final Parcelable.Creator<MoviesRecord> CREATOR 
    	= new Parcelable.Creator<MoviesRecord>() {
        public MoviesRecord createFromParcel(Parcel in) {
            return new MoviesRecord(in);
        }

        public MoviesRecord[] newArray(int size) {
            return new MoviesRecord[size];
        }
    };
    
    public static String[] PROJECTION = {
    	Movies._ID,
    	Movies.TITLE,
    	Movies.DESCRIPTION,
    	Movies.YEAR
    };
    
    public interface Indices {
    	int _ID = 0;
    	int TITLE = 1;
    	int DESCRIPTION = 2;
    	int YEAR = 3;
    }
    
    private String mTitle;
    private boolean mTitleDirty;
    private String mDescription;
    private boolean mDescriptionDirty;
    private long mYear;
    private boolean mYearDirty;
    
    @Override
    protected String[] _getProjection() {
    	return PROJECTION;
    }
    
    public void setTitle(String title) {
    	mTitle = title;
    	mTitleDirty = true;
    }
    
    public String getTitle() {
    	return mTitle;
    }
    
    public void setDescription(String description) {
    	mDescription = description;
    	mDescriptionDirty = true;
    }
    
    public String getDescription() {
    	return mDescription;
    }
    
    public void setYear(long year) {
    	mYear = year;
    	mYearDirty = true;
    }
    
    public long getYear() {
    	return mYear;
    }
    
    
    public MoviesRecord() {
    	super(Movies.CONTENT_URI);
	}
	
	private MoviesRecord(Parcel in) {
    	super(Movies.CONTENT_URI);
    	
		setId(in.readLong());
		
		mTitle = in.readString();
		mDescription = in.readString();
		mYear = in.readLong();
		
		boolean[] dirtyFlags = new boolean[3];
		in.readBooleanArray(dirtyFlags);
		mTitleDirty = dirtyFlags[0];
		mDescriptionDirty = dirtyFlags[1];
		mYearDirty = dirtyFlags[2];
	}
	
	@Override
	public int describeContents() {
	    return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(getId());
		dest.writeString(mTitle);
		dest.writeString(mDescription);
		dest.writeLong(mYear);
		dest.writeBooleanArray(new boolean[] {
			mTitleDirty,
			mDescriptionDirty,
			mYearDirty
		});
	}
	
	@Override
	protected AbstractValuesBuilder createBuilder() {
		Builder builder = Movies.newBuilder();

		if(mTitleDirty) {
			builder.setTitle(mTitle);
		}
		if(mDescriptionDirty) {
			builder.setDescription(mDescription);
		}
		if(mYearDirty) {
			builder.setYear(mYear);
		}
		
		return builder;
	}
	
    @Override
	public void makeDirty(boolean dirty){
		mTitleDirty = dirty;
		mDescriptionDirty = dirty;
		mYearDirty = dirty;
	}

	@Override
	protected void setPropertiesFromCursor(Cursor c) {
		setId(c.getLong(Indices._ID));
		setTitle(c.getString(Indices.TITLE));
		setDescription(c.getString(Indices.DESCRIPTION));
		setYear(c.getLong(Indices.YEAR));
	}
	
	public static MoviesRecord fromCursor(Cursor c) {
	    MoviesRecord item = new MoviesRecord();
	    
		item.setPropertiesFromCursor(c);
		
		item.makeDirty(false);
		
	    return item;
	}
	
	public static MoviesRecord fromBundle(Bundle bundle, String key) {
		bundle.setClassLoader(MoviesRecord.class.getClassLoader());
		return bundle.getParcelable(key);
	}
	
	public static MoviesRecord get(long id) {
	    Cursor c = null;
	    
	    ContentResolver resolver = Mechanoid.getContentResolver();
	    
	    try {
	        c = resolver.query(Movies.CONTENT_URI.buildUpon()
			.appendPath(String.valueOf(id)).build(), PROJECTION, null, null, null);
	        
	        if(!c.moveToFirst()) {
	            return null;
	        }
	        
	        return fromCursor(c);
	    } finally {
	        Closeables.closeSilently(c);
	    }
	}
}
