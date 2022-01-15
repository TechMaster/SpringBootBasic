package vn.techmaster.pollapi.payload;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class VoteRequest {
    @NotNull
    private Long choiceId;
}
