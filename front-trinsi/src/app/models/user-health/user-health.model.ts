import { GENDER } from '../enums/gender.enum';

export class UserHealth {

    public gender: GENDER;
    public years: number;
    public height: number;
    public weight: number;
    public upperBloodPressure: number;
    public lowerBloodPressure: number;
    public pulse: number;
    public hoursOfExercise: number;

    constructor(gender: GENDER, years: number, height: number, weight: number, upperBloodPressure: number,
                lowerBloodPressure: number, pulse: number, hoursOfExercise: number) {
        this.gender = gender;
        this.years = years;
        this.height = height;
        this.weight = weight;
        this.upperBloodPressure = upperBloodPressure;
        this.lowerBloodPressure = lowerBloodPressure;
        this.pulse = pulse;
        this.hoursOfExercise = hoursOfExercise;
    }
}
