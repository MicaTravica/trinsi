import { Exercise } from './exercise.model';

describe('Exercise', () => {
  it('should create an instance', () => {
    expect(new Exercise(null, null, null, null, null)).toBeTruthy();
  });
});
