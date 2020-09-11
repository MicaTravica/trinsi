import { Exercise } from '../exercise/exercise.model';
import { MUSCLES_GROUP } from '../enums/muscles-group.enum';
import { ACTIVITY_LEVEL } from '../enums/activity-level.enum';
import { NUTRITION_LEVEL } from '../enums/nutrition-level.enum';
import { BLOOD_PRESSURE_CLASSIFICATION } from '../enums/blood-pressure-classification.enum';
import { INTENSITY } from '../enums/intensity.enum';

export class UserPlanner {

    public id: number;
    public activityLevel: ACTIVITY_LEVEL;
    public nutritionLevel: NUTRITION_LEVEL;
    public bloodPressureClassification: BLOOD_PRESSURE_CLASSIFICATION;
    public intensity: INTENSITY;
    public setsMuscular: number;
    public repetitionMuscular: number;
    public numOfMinutesAerobic: number;
    public numOfMinutesAerobicWarmUp: number;
    public numOfSecondsStretching: number;
    public lowerPulseLimit: number;
    public upperPulseLimit: number;

    public exercisesWarmUp: Exercise[];
    public exercises: Exercise[];
    public exercisesStretching: Exercise[];

}
