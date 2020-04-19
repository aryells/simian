package com.mercadolivre.simian.domain;

import java.util.List;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Field;
import org.springframework.data.annotation.Id;

@Entity(name = "human")
public class Human {

	@Id
	@Field(name = "human_id")
	private Long humanId;
	@Field(name = "dns")
	private List<String> dna;
	@Field(name = "is_simian")
	private Boolean isSimian;
	@Field(name = "md5")
	private String md5;

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private Human human;
		
		private Builder() {
			this.human = new Human();
		}
		
		public Builder dna(List<String> dna) {
			human.dna = dna;
			return this;
		}
		public Builder isSimian(Boolean isSimian) {
			human.isSimian = isSimian;
			return this;
		}
		public Builder md5(String md5) {
			human.md5 = md5;
			return this;
		}
		public Human build() {
			return human;
		}
	}
}
