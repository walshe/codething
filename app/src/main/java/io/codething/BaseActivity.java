package io.codething;

import java.util.Objects;

public abstract class BaseActivity {

	private final int durationInMinutes;

	private final int distanceInMeters;

	// TODO record date of activity for better reporting using the Timeframe enum

	public BaseActivity(final int durationInMinutes, final int distanceInMeters) {
		this.durationInMinutes = durationInMinutes;
		this.distanceInMeters = distanceInMeters;
	}

	public int getDurationInMinutes() {
		return durationInMinutes;
	}

	public int getDistanceInMeters() {
		return distanceInMeters;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseActivity that = (BaseActivity) o;
		return durationInMinutes == that.durationInMinutes && distanceInMeters == that.distanceInMeters;
	}

	@Override
	public int hashCode() {
		return Objects.hash(durationInMinutes, distanceInMeters);
	}
}
