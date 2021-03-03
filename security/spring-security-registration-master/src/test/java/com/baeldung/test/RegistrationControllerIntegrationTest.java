package com.baeldung.test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.baeldung.spring.TestDbConfig;
import com.baeldung.spring.TestIntegrationConfig;
import com.baeldung.Application;
import com.baeldung.persistence.model.User;
import com.baeldung.persistence.model.VerificationToken;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, TestDbConfig.class, TestIntegrationConfig.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class RegistrationControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PersistenceContext
    private EntityManager entityManager;

    private MockMvc mockMvc;
    private String token;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        User user = new User();
        user.setEmail(UUID.randomUUID().toString() + "@example.com");
        user.setPassword(UUID.randomUUID().toString());
        user.setFirstName("First");
        user.setLastName("Last");

        entityManager.persist(user);
        token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationToken.setExpiryDate(Date.from(Instant.now().plus(2, ChronoUnit.DAYS)));

        entityManager.persist(verificationToken);

        /*
            flush managed entities to the database to populate identifier field
         */
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void testRegistrationConfirm() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(get("/registrationConfirm.html?token=" + token));
        resultActions.andExpect(status().is3xxRedirection());
        resultActions.andExpect(model().attribute("message", "Your account verified successfully"));
        resultActions.andExpect(view().name("redirect:/console.html?lang=en"));
    }

    @Test
    public void testRegistrationValidation() throws Exception {

        final MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("firstName", "");
        param.add("lastName", "");
        param.add("email", "");
        param.add("password", "");
        param.add("matchingPassword", "");

        ResultActions resultActions = this.mockMvc.perform(post("/user/registration").params(param));
        resultActions.andExpect(status().is(400));
        resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.error", is("InvaliduserDto")))
                .andExpect(jsonPath("$.message", containsString("{\"field\":\"lastName\",\"defaultMessage\":\"Length must be greater than 1\"}")));
    }
}