import { GENDER } from '../enums/gender.enum';

export class UserHealth {

    public gender: GENDER;
    public dateOfBirth: Date;
    public height: number;
    public weight: number;
    public upperBloodPressure: number;
    public lowerBloodPressure: number;
    public pulse: number;
    public hoursOfExercise: number;

    constructor(gender: GENDER, dateOfBirth: Date, height: number, weight: number, upperBloodPressure: number,
                lowerBloodPressure: number, pulse: number, hoursOfExercise: number) {
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
        this.upperBloodPressure = upperBloodPressure;
        this.lowerBloodPressure = lowerBloodPressure;
        this.pulse = pulse;
        this.hoursOfExercise = hoursOfExercise;
    }
}
