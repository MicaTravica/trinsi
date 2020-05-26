import { UserPlanner } from './user-planner.model';

describe('UserPlanner', () => {
  it('should create an instance', () => {
    expect(new UserPlanner(null, null, null, null, null, null, null)).toBeTruthy();
  });
});
