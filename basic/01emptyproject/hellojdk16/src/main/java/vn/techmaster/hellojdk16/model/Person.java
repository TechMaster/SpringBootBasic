package vn.techmaster.hellojdk16.model;

import com.fasterxml.jackson.annotation.JsonProperty;
public record Person(@JsonProperty("id") long id,
                     @JsonProperty("name") String full_name) {};
