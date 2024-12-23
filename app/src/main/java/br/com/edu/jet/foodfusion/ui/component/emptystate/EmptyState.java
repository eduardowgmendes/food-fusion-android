package br.com.edu.jet.foodfusion.ui.component.emptystate;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class EmptyState implements Parcelable {

    private int backdropRes;
    private String title;
    private String description;

    public EmptyState(int backdropRes, String title, String description) {
        this.backdropRes = backdropRes;
        this.title = title;
        this.description = description;
    }

    protected EmptyState(Parcel in) {
        backdropRes = in.readInt();
        title = in.readString();
        description = in.readString();
    }

    public static final Creator<EmptyState> CREATOR = new Creator<EmptyState>() {
        @Override
        public EmptyState createFromParcel(Parcel in) {
            return new EmptyState(in);
        }

        @Override
        public EmptyState[] newArray(int size) {
            return new EmptyState[size];
        }
    };

    public int getBackdropRes() {
        return backdropRes;
    }

    public void setBackdropRes(int backdropRes) {
        this.backdropRes = backdropRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "EmptyState{" +
                "backdropRes=" + backdropRes +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(backdropRes);
        dest.writeString(title);
        dest.writeString(description);
    }
}
