import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
	String type;
	List<LocalDateTime[]> schedule;

	public Vehicle(String type) {
		this.type = type;
		schedule = new ArrayList<>();
	}

	// try to reserve selected time slot for this vehicle
	public boolean search(int year, int month, int day, int hour, int minute, int second, int days) {
		LocalDateTime start_date = LocalDateTime.of(year, month, day, hour, minute, second);
		LocalDateTime end_date = start_date.plusDays(days);
		LocalDateTime[] inserted = new LocalDateTime[2];
		inserted[0] = start_date;
		inserted[1] = end_date;
		if (schedule.size() == 0) {
			schedule.add(inserted);
			return true;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (i == schedule.size() - 1) {
				if (start_date.isAfter(schedule.get(i)[1])) {
					schedule.add(inserted);
					return true;
				}
				return false;
			}
			if (start_date.isAfter(schedule.get(i)[1]) && end_date.isBefore(schedule.get(i + 1)[0])) {
				schedule.add(i + 1, inserted);
				return true;
			} else if (start_date.isBefore(schedule.get(i)[1]) || end_date.isAfter(schedule.get(i + 1)[0])) {
				return false;
			}
		}
		return false;
	}
}
