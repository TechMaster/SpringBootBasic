package vn.techmaster.demojpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import vn.techmaster.demojpa.model.mapping.Film;
import vn.techmaster.demojpa.repository.FilmRepository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;

@DataJpaTest
@Sql({"/movie.sql"})
public class FilmRepositoryTests {
  @Autowired
  private FilmRepository filmRepo;

  @Test
  public void findAllFilms() {
    List<Film> films = filmRepo.findAll();
    assertThat(films.size()).isGreaterThan(10);
    assertThat(films).extracting("title").contains("Eulogy", "Cherry Tree Lane", "Crazy for Christmas");
  }

  @Test
  public void getSortFilmsByYear() {
    List<Film> films = filmRepo.findAllByOrderByYearAsc();
    films.forEach(System.out::println);
    assertThat(films).isSortedAccordingTo(Comparator.comparing(Film::getYear));

  }
  @Test
  public void getSortFilmsByTitleAndYear() {
    Sort sortByTitleAndYear = Sort.by("title", "year");
    List<Film> films = filmRepo.findAll(sortByTitleAndYear);
    films.forEach(System.out::println);

    assertThat(films).isSortedAccordingTo(Comparator.comparing(Film::getTitle).
    thenComparing(Film::getYear));
  }
  
}
