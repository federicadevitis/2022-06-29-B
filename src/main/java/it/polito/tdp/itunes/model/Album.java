package it.polito.tdp.itunes.model;

import java.util.Comparator;

public class Album implements Comparable<Album>{
	private Integer albumId;
	private String title;
	private int durata;
	private int bilancio;
	
	



	public int getBilancio() {
		return bilancio;
	}



	public void setBilancio(int bilancio) {
		this.bilancio = bilancio;
	}



	public Album(Integer albumId, String title) {
		super();
		this.albumId = albumId;
		this.title = title;
	}
	
	

	public Album(Integer albumId, String title, int durata) {
		super();
		this.albumId = albumId;
		this.title = title;
		this.durata = durata;
	}





	public int getDurata() {
		return durata;
	}



	public void setDurata(int durata) {
		this.durata = durata;
	}



	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((albumId == null) ? 0 : albumId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		if (albumId == null) {
			if (other.albumId != null)
				return false;
		} else if (!albumId.equals(other.albumId))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return title;
	}



	@Override
	public int compareTo(Album o) {
		// TODO Auto-generated method stub
		return this.bilancio-o.bilancio;
	}



	
	
	
	
}
