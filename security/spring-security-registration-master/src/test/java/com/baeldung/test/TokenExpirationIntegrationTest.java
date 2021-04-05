package com.baeldung.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.baeldung.persistence.dao.UserRepository;
import com.baeldung.spring.TestDbConfig;
import com.baeldung.spring.TestTaskConfig;
import com.baeldung.task.TokensPurgeTask;
import com.baeldung.persistence.dao.VerificationTokenRepository;
import com.baeldung.persistence.model.User;
import com.baeldung.persistence.model.VerificationToken;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestDbConfig.class, TestTaskConfig.class })
@Transactional
public class TokenExpirationIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private TokensPurgeTask tokensPurgeTask;

    @PersistenceContext
    private EntityManager entityManager;

    private Long token_id;
    private Long user_id;

    //

    @Before
    public void givenUserWithExpiredToken() {
        User user = new User();
        user.setEmail(UUID.randomUUID().toString() + "@example.com");
        user.setPassword(UUID.randomUUID().toString());
        user.setFirstName("First");
        user.setLastName("Last");

        entityManager.persist(user);
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationToken.setExpiryDate(Date.from(Instant.now().minus(2, ChronoUnit.DAYS)));

        entityManager.persist(verificationToken);

        /*
            flush managed entities to the database to populate identifier field
         */
        entityManager.flush();

        /*
            remove managed entities from the persistence context
            so that subsequent SQL queries hit the database
         */
        entityManager.clear();

        token_id = verificationToken.getId();
        user_id = user.getId();
    }

    @Test
    public void whenContextLoad_thenCorrect() {
        assertNotNull(user_id);
        assertNotNull(token_id);
        assertTrue(userRepository.findById(user_id).isPresent());

        Optional<VerificationToken> verificationToken = tokenRepository.findById(token_id);
        assertTrue(verificationToken.isPresent());
        assertTrue(tokenRepository.findAllByExpiryDateLessThan(Date.from(Instant.now())).anyMatch((token) -> token.equals(verificationToken.get())));
    }

    @After
    public void flushAfter() {
        entityManager.flush();
    }

    @Test
    public void whenRemoveByGeneratedQuery_thenCorrect() {
        tokenRepository.deleteByExpiryDateLessThan(Date.from(Instant.now()));
        assertEquals(0, tokenRepository.count());
    }

    @Test
    public void whenRemoveByJPQLQuery_thenCorrect() {
        tokenRepository.deleteAllExpiredSince(Date.from(Instant.now()));
        assertEquals(0, tokenRepository.count());
    }

    @Test
    public void whenPurgeTokenTask_thenCorrect() {
        tokensPurgeTask.purgeExpired();
        assertFalse(tokenRepository.findById(token_id).isPresent());
    }
}
