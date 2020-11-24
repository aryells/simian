package com.mercadolivre.simian.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Field;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@Builder
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
}
