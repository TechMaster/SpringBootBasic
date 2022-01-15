package vn.techmaster.pollapi.payload;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import vn.techmaster.pollapi.model.Choice;

public class PollRequest {
    @NotBlank
    @Size(max = 150)
    private String question;

    @Size(min = 2, max = 10)
    private List<Choice> choices = new ArrayList<>();

    @NotNull
    @Valid
    private PollLength poiLength;
    
}
