package com.mercadolivre.simian.domain.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatsDTO implements Serializable {

	private static final long serialVersionUID = 8237288139115337632L;

	@JsonProperty("count_mutant_dna")
    private Long countMutantDna;
    @JsonProperty("count_human_dna")
    private Long countHumanDna;
    private Double ratio;
	public Long getCountMutantDna() {
		return countMutantDna;
	}
	
	public void setCountMutantDna(Long countMutantDna) {
		this.countMutantDna = countMutantDna;
	}
	
	public Long getCountHumanDna() {
		return countHumanDna;
	}
	
	public void setCountHumanDna(Long countHumanDna) {
		this.countHumanDna = countHumanDna;
	}
	
	public Double getRatio() {
		return ratio;
	}
	
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}   
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private StatsDTO dto;
		
		private Builder() {
			this.dto = new StatsDTO();
		}
		public Builder countMutantDna(long countMutantDna) {
			dto.countMutantDna = countMutantDna;
			return this;
		}
		public Builder countHumanDna(long countHumanDna) {
			dto.countHumanDna = countHumanDna;
			return this;
		}
		public Builder ratio(Double ratio) {
			dto.ratio = ratio;
			return this;
		}

		public StatsDTO build() {
			return dto;
		}
	}
}
