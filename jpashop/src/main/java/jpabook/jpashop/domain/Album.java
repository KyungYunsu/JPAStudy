package jpabook.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Album extends Item{
	
	private String artiset;
	private String etc;
	
	public String getArtiset() {
		return artiset;
	}
	public void setArtiset(String artiset) {
		this.artiset = artiset;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	
	

}
