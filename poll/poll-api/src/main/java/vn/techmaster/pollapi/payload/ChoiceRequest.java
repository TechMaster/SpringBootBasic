package vn.techmaster.pollapi.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ChoiceRequest {
    @NotBlank
    @Size(max = 40)
    private String text;
}
