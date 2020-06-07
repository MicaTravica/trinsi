import { EXERCISE_TYPE } from '../enums/exercise-type.enum';
import { CATEGORY } from '../enums/category.enum';

export class MissingExercises {

    private exerciseType: EXERCISE_TYPE;
    private category: CATEGORY;
    private num: number;

    constructor(exerciseType: EXERCISE_TYPE, category: CATEGORY, num: number) {
        this.exerciseType = exerciseType;
        this.category = category;
        this.num = num;
    }
}
