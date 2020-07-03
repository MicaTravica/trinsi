import { HeartBeatTracking } from './heart-beat-tracking.model';

describe('HeartBeatTracking', () => {
  it('should create an instance', () => {
    expect(new HeartBeatTracking(null, null, null, null)).toBeTruthy();
  });
});
