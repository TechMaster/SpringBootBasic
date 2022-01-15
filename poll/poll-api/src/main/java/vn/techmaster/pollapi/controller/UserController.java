package vn.techmaster.pollapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.pollapi.model.User;
import vn.techmaster.pollapi.payload.UserIdentityAvailability;
import vn.techmaster.pollapi.payload.UserProfile;
import vn.techmaster.pollapi.payload.UserSummary;
import vn.techmaster.pollapi.security.CurrentUser;
import vn.techmaster.pollapi.security.UserPrincipal;
import vn.techmaster.pollapi.service.PollService;
import vn.techmaster.pollapi.service.UserService;



@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PollService pollService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('User')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getName(), currentUser.getUsername(), currentUser.getEmail());
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUserNameAvailability(@RequestParam("username") String username) {
        return new UserIdentityAvailability(!userService.isUserExistedByUserName(username));
    }
    
    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam("email") String email) {
        return new UserIdentityAvailability(!userService.isUserExistedByEmail(email));
    }

    @GetMapping("/users/{username}")
    public UserProfile getUseProfile(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);
        Long userId = user.getId();
        long pollCount = pollService.countPollByCreatedUserId(userId);
        long voteCount = pollService.countVoteByUserId(userId);
        return UserProfile.builder()
                            .withId(userId)
                            .withName(user.getName())
                            .withUsername(username)
                            .withJoinedAt(user.getCreatedAt())
                            .withPollCount(pollCount)
                            .withVoteCount(voteCount)
                            .build();
    }
    // TODO get polls created by username /users/{username}/polls -> PageResponse<PollResponse>
    //TODO get polls voted by username /users/{username}/votes -> PageResponse<PollResponse>
}
