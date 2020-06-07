import { CATEGORY } from '../enums/category.enum';
import { EXERCISE_TYPE } from '../enums/exercise-type.enum';

export class Report {

    private categories: CATEGORY[];
    private exerciseTypes: EXERCISE_TYPE[];

    constructor(categories: CATEGORY[], exerciseTypes: EXERCISE_TYPE[]) {
        this.categories = categories;
        this.exerciseTypes = exerciseTypes;
    }
}
