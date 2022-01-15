package vn.techmaster.pollapi.payload;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true, setterPrefix = "with")
public class UserProfile {
    private Long id;
    private String name;
    private String username;
    private Instant joinedAt;
    private Long pollCount;
    private Long voteCount;
}