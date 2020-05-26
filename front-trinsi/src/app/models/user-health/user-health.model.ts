import { GENDER } from '../enums/gender.enum';

export class UserHealth {

    public id: number;
    public gender: GENDER;
    public years: number;
    public height: number;
    public weight: number;
    public upperBloodPressure: number;
    public lowerBloodPressure: number;
    public pulse: number;
    public hoursOfExercise: number;

    constructor(id: number, gender: GENDER, years: number, height: number, weight: number, upperBloodPressure: number,
                lowerBloodPressure: number, pulse: number, hoursOfExercise: number) {
        this.id = id;
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
