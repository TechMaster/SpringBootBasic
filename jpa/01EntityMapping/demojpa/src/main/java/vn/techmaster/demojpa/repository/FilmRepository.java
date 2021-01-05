package vn.techmaster.demojpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.demojpa.model.mapping.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
  public List<Film> findAllByOrderByYearAsc();
}
