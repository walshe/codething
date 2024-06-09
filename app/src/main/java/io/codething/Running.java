package io.codething;

public final class Running extends BaseActivity implements Activity {

	public Running(final int durationInMinutes, final int distanceInMeters) {
		super(durationInMinutes, distanceInMeters);
	}

	@Override
	public int getCaloriesBurnt() {
		return (int) (getDistanceInMeters() * 0.2);
	}
}
