package io.codething;

public final class Swimming extends BaseActivity implements Activity {

	public Swimming(final int durationInMinutes, final int distanceInMeters) {
		super(durationInMinutes, distanceInMeters);
	}

	@Override
	public int getCaloriesBurnt() {
		return (int) (getDurationInMinutes() * 0.2);
	}
}
