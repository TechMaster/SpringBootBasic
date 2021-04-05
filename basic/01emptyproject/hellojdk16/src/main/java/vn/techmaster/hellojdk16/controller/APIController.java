package vn.techmaster.hellojdk16.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.hellojdk16.model.Color;
import vn.techmaster.hellojdk16.model.Colour;
import vn.techmaster.hellojdk16.model.Person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("/api")
public class APIController {
	private final Logger logger;

	public APIController(Logger logger) {
		this.logger = logger;
	}

	@GetMapping("/person")
	public List<Person> getPeople() {
		ArrayList<Person> people = new ArrayList<>();
		people.add(new Person(1, "Trịnh Minh Cường"));
		people.add(new Person(2, "Đoàn Xuân Dũng"));
		people.add(new Person(3, "Phạm Nhật Vượng"));
		return people;
	}

	@GetMapping("/colors")
	public List<Color> getColors() {
		ArrayList<Color> colors = new ArrayList<>();
		colors.add(new Color(1, 12, 15, 255));
		colors.add(new Color(2, 255, 0, 0));
		colors.add(new Color(3, 0, 255, 0));
		colors.add(new Color(4, 0, 0, 255));
		return colors;
	}

	@GetMapping("/colours")
	public List<Colour> getColours() {
		ArrayList<Colour> colours = new ArrayList<>();
		colours.add(new Colour(12, 15, 255));
		colours.add(new Colour(255, 0, 0));
		colours.add(new Colour(0, 255, 0));
		colours.add(new Colour(0, 0, 255));
		logger.info("/colours is requested at {}", LocalDateTime.now());
		return colours;
	}
}
