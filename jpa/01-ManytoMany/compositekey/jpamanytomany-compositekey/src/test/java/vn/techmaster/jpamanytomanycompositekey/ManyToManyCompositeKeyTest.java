package vn.techmaster.jpamanytomanycompositekey;


import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import vn.techmaster.jpamanytomanycompositekey.model.Course;
import vn.techmaster.jpamanytomanycompositekey.model.CourseRating;
import vn.techmaster.jpamanytomanycompositekey.model.CourseRatingKey;
import vn.techmaster.jpamanytomanycompositekey.model.Student;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ManyToManyCompositeKeyTest {
    @Autowired
    private EntityManager tem;

    @Test
    public void whenCourseRatingPersisted_thenCorrect() {
        Student student = new Student();
        tem.persist(student);

        Course course = new Course();
        tem.persist(course);

        CourseRating cRating = CourseRating.builder().id(new CourseRatingKey())
                                                        .student(student)
                                                        .course(course)
                                                        .rating(100)
                                                        .build();
        tem.persist(cRating);

        CourseRating persitedCourseRating = tem.find(CourseRating.class, new CourseRatingKey(1L, 1L));
        assertThat(persitedCourseRating).isNotNull();
        assertThat(persitedCourseRating.getStudent().getId()).isEqualTo(1L);
        assertThat(persitedCourseRating.getCourse().getId()).isEqualTo(1L);

    }
}
