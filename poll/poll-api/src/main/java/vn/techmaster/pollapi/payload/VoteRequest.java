package vn.techmaster.pollapi.payload;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VoteRequest {
    @NotNull
    private Long choiceId;
}
