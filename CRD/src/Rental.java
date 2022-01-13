import java.util.ArrayList;
import java.util.List;

public class Rental {
	List<Sedan> sedans;
	List<SUV> suvs;
	List<Van> vans;

	public Rental() {
		sedans = new ArrayList<>();
		suvs = new ArrayList<>();
		vans = new ArrayList<>();
	}

	public void add_sedan(Sedan s) {
		sedans.add(s);
	}

	public void add_suv(SUV s) {
		suvs.add(s);
	}

	public void add_van(Van s) {
		vans.add(s);
	}

	// Reserve for a particular type of vehicle for customized date, time and
	// duration, check if the reservation is successful or not
	public boolean reserve(String type, int year, int month, int day, int hour, int minute, int second, int days) {
		if ("Sedan".equals(type)) {
			if (sedans.size() == 0) {
				return false;
			}
			for (Sedan s : sedans) {
				if (s.search(year, month, day, hour, minute, second, days)) {
					return true;
				}
			}
			return false;
		} else if ("SUV".equals(type)) {
			if (suvs.size() == 0) {
				return false;
			}
			for (SUV s : suvs) {
				if (s.search(year, month, day, hour, minute, second, days)) {
					return true;
				}
			}
			return false;

		} else if ("Van".equals(type)) {
			if (vans.size() == 0) {
				return false;
			}
			for (Van s : vans) {
				if (s.search(year, month, day, hour, minute, second, days)) {
					return true;
				}
			}
			return false;

		} else {
			return false;
		}
	}

}
