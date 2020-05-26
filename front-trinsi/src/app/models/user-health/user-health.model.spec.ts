import { UserHealth } from './user-health.model';

describe('UserHealth', () => {
  it('should create an instance', () => {
    expect(new UserHealth(null, null, null, null, null, null, null, null, null)).toBeTruthy();
  });
});
