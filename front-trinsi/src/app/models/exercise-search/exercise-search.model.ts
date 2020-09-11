import { EXERCISE_TYPE } from '../enums/exercise-type.enum';
import { INTENSITY } from '../enums/intensity.enum';
import { MUSCLES_GROUP } from '../enums/muscles-group.enum';

export class ExerciseSearch {

    public name: string;
    public exerciseType: EXERCISE_TYPE;
    public intensity: INTENSITY;
    public musclesGroup: MUSCLES_GROUP;

    constructor(name: string, exerciseType: EXERCISE_TYPE, intensity: INTENSITY, musclesGroup: MUSCLES_GROUP) {
        this.name = name;
        this.exerciseType = exerciseType;
        this.intensity = intensity;
        this.musclesGroup = musclesGroup;
    }
}
