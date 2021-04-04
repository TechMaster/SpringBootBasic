package vn.techmaster.hellojdk16.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "color")
public record Color(
		@Id @GeneratedValue int id,
		@JsonProperty int R,
		@JsonProperty("Green") int G,
		@JsonProperty("Blue") int B) {}