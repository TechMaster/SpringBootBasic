package vn.techmaster.pollapi.payload;

import java.time.Instant;
import java.util.List;

import lombok.Data;

@Data
public class PollResponse {
    private Long id;
    private String question;
    private List<ChoiceResponse> choices;
    private UserSummary createdBy;
    private Instant creationDateTime;
    private Instant expirationDateTime;
    private Boolean expired;
    private Long selectedChoice;
    private Long totalVotes;
}
