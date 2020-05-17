import { EXERCISE_TYPE } from '../enums/exercise-type.enum';
import { CATEGORY } from '../enums/category.enum';

export class ExerciseSearch {

    public name: string;
    public exerciseType: EXERCISE_TYPE;
    public exerciseWeight: CATEGORY;

    constructor(name: string, exerciseType: EXERCISE_TYPE, exerciseWeight: CATEGORY) {
        this.name = name;
        this.exerciseType = exerciseType;
        this.exerciseWeight = exerciseWeight;
    }
}
