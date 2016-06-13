package com.lapism.searchview;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;


public class SearchItem implements Parcelable {

    private CharSequence text;
    private int type;

    public SearchItem(CharSequence text) {
        this(SearchType.SEARCH_SUGGEST, text);
    }

    public SearchItem(SearchType type, CharSequence text) {
        this.type = type.getValue();
        this.text = text;
    }

    private SearchItem(Parcel in) {
        this.type = in.readInt();
        this.text = in.readParcelable(CharSequence.class.getClassLoader());
    }

    public int getType() {
        return type;
    }

    public void setType(SearchType type) {
        this.type = type.getValue();
    }

    CharSequence getText() {
        return this.text;
    }

    void setText(CharSequence text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        TextUtils.writeToParcel(this.text, dest, flags); // dest.writeValue(this.text);
    }

    public static final Parcelable.Creator<SearchItem> CREATOR =
            new Parcelable.Creator<SearchItem>() {
                public SearchItem createFromParcel(Parcel source) {
                    return new SearchItem(source);
                }

                public SearchItem[] newArray(int size) {
                    return new SearchItem[size];
                }
            };

    public enum SearchType {

        SEARCH_HISTORY(1),
        SEARCH_SUGGEST(2);

        private int value;

        SearchType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}