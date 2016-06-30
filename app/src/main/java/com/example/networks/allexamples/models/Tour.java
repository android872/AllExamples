package com.example.networks.allexamples.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.NumberFormat;

public class Tour implements Parcelable {
	private long id;
	private String title;
	private String description;
	private double price;
	private String image;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return title + "\n(" + nf.format(price) + ")";
	}


	public Tour() {
	}

	public Tour(Parcel in) {
		Log.i("Tour", "Parcel constructor");

		id = in.readLong();
		title = in.readString();
		description = in.readString();
		price = in.readDouble();
		image = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Log.i("Tour", "writeToParcel");

		dest.writeLong(id);
		dest.writeString(title);
		dest.writeString(description);
		dest.writeDouble(price);
		dest.writeString(image);
	}

	public static final Parcelable.Creator<Tour> CREATOR =
			new Parcelable.Creator<Tour>() {

				@Override
				public Tour createFromParcel(Parcel source) {
					Log.i("Tour", "createFromParcel");
					return new Tour(source);
				}

				@Override
				public Tour[] newArray(int size) {
					Log.i("Tour", "newArray");
					return new Tour[size];
				}

			};
}
