import { BLOOD_PRESSURE_CLASSIFICATION } from '../enums/blood-pressure-classification.enum';
import { NUTRITION_LEVEL } from '../enums/nutrition-level.enum';

export class HeartBeatTracking {

    userId: number;
    currentPulse: number;
    lowerPulseLimit: number;
    upperPulseLimit: number;
    bloodPressureClassification: BLOOD_PRESSURE_CLASSIFICATION;
    nutritionLevel: NUTRITION_LEVEL;

    constructor(userId: number, currentPulse: number, lowerPulseLimit: number, upperPulseLimit: number,
                bloodPressureClassification: BLOOD_PRESSURE_CLASSIFICATION, nutritionLevel: NUTRITION_LEVEL) {
        this.userId = userId;
        this.currentPulse = currentPulse;
        this.lowerPulseLimit = lowerPulseLimit;
        this.upperPulseLimit = upperPulseLimit;
        this.bloodPressureClassification = bloodPressureClassification;
        this.nutritionLevel = nutritionLevel;
    }
}
