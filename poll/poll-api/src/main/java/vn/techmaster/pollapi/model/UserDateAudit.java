package vn.techmaster.pollapi.model;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@JsonIgnoreProperties(value = {"createdBy", "updatedBy"}, allowGetters = true
)
@Getter
@Setter
public class UserDateAudit extends DateAudit {
    @CreatedBy
    private Long createdBy;
    @LastModifiedBy
    private Long updatedBy;
}
