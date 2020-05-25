import { EXERCISE_TYPE } from '../enums/exercise-type.enum';
import { CATEGORY } from '../enums/category.enum';

export class ExerciseSearch {

    public name: string;
    public exerciseType: EXERCISE_TYPE;
    public exerciseWeight: CATEGORY;
    public pageNum: number;

    constructor(name: string, exerciseType: EXERCISE_TYPE, exerciseWeight: CATEGORY, pageNum: number) {
        this.name = name;
        this.exerciseType = exerciseType;
        this.exerciseWeight = exerciseWeight;
        this.pageNum = pageNum;
    }
}
