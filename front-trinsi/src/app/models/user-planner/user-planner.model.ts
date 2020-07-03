import { CATEGORY } from '../enums/category.enum';
import { EXERCISE_TYPE } from '../enums/exercise-type.enum';
import { HEALTH_CONDITION } from '../enums/health-condition.enum';
import { PHYSICAL_CHARACTERISTICS } from '../enums/physical-characteristics.enum';
import { ExerciseSearch } from '../exercise-search/exercise-search.model';
import { Exercise } from '../exercise/exercise.model';

export class UserPlanner {

    public id: number;
    public category: CATEGORY;
    public numCategory: number;
    public physicalCharacteristics: PHYSICAL_CHARACTERISTICS;
    public healthCondition: HEALTH_CONDITION;
    public numOfExercise: number;
    public repetition: number;
    public exerciseType: EXERCISE_TYPE;
    public targetPulse: number;
    public exercises: Exercise[];

}
