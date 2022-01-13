import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarRentalTest {
	static Rental rental;

	@BeforeEach
	public void setUp() {
		rental = new Rental();
		for (int i = 0; i < 3; i++) {
			rental.add_sedan(new Sedan());
			rental.add_suv(new SUV());
			rental.add_van(new Van());
		}
	}

	@Test
	void testReservingWithEmptySchedule() {
		assertTrue(rental.reserve("Sedan", 2022, 1, 1, 8, 8, 8, 3));
		assertEquals(1, rental.sedans.get(0).schedule.size());
	}

	@Test
	void testWrongType() {
		assertFalse(rental.reserve("Nothing", 2022, 1, 2, 8, 8, 8, 3));
	}

	@Test
	void testOverlappedReservationShallReturnFalse() {
		rental.reserve("Sedan", 2022, 1, 1, 8, 8, 8, 3);
		rental.reserve("Sedan", 2022, 1, 1, 8, 8, 8, 3);
		rental.reserve("Sedan", 2022, 1, 1, 8, 8, 8, 3);
		assertFalse(rental.reserve("Sedan", 2021, 12, 31, 8, 8, 8, 2));
		assertFalse(rental.reserve("Sedan", 2022, 1, 2, 8, 8, 8, 3));
		assertFalse(rental.reserve("Sedan", 2022, 1, 4, 8, 8, 8, 3));
		assertTrue(rental.reserve("Sedan", 2022, 1, 6, 8, 8, 8, 3));
	}

}
