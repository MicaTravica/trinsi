import { EXERCISE_TYPE } from '../enums/exercise-type.enum';
import { CATEGORY } from '../enums/category.enum';

export class Exercise {

    public id: number;
    public name: string;
    public description: string;
    public exerciseType: EXERCISE_TYPE;
    public exerciseWeight: CATEGORY;

    constructor(id: number, name: string, description: string, exerciseType: EXERCISE_TYPE, exerciseWeight: CATEGORY) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exerciseType = exerciseType;
        this.exerciseWeight = exerciseWeight;
    }
}
