package io.codething;

public final class Walking extends BaseActivity implements Activity {

	public Walking(final int durationInMinutes, final int distanceInMeters) {
		super(durationInMinutes, distanceInMeters);
	}

	@Override
	public int getCaloriesBurnt() {
		return (int) (getDistanceInMeters() * 0.1);
	}
}
