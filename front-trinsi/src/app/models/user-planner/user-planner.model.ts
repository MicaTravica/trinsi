import { CATEGORY } from '../enums/category.enum';
import { EXERCISE_TYPE } from '../enums/exercise-type.enum';
import { HEALTH_CONDITION } from '../enums/health-condition.enum';
import { PHYSICAL_CHARACTERISTICS } from '../enums/physical-characteristics.enum';

export class UserPlanner {

    public category: CATEGORY;
    public numCategory: number;
    public physicalCharacteristics: PHYSICAL_CHARACTERISTICS;
    public healthCondition: HEALTH_CONDITION;
    public numOfExercise: number;
    public repetition: number;
    public exerciseType: EXERCISE_TYPE;

    constructor(category: CATEGORY, numCategory: number, physicalCharacteristics: PHYSICAL_CHARACTERISTICS,
                healthCondition: HEALTH_CONDITION, numOfExercise: number, repetition: number, exerciseType: EXERCISE_TYPE) {
        this.category = category;
        this.numCategory = numCategory;
        this.physicalCharacteristics = physicalCharacteristics;
        this.healthCondition = healthCondition;
        this.numOfExercise = numOfExercise;
        this.repetition = repetition;
        this.exerciseType = exerciseType;
    }

}
