import { Questionnaire } from '../questionnaire/questionnaire.model';

export class UserHealth {

    public id: number;
    public years: number;
    public height: number;
    public weight: number;
    public upperBloodPressure: number;
    public lowerBloodPressure: number;
    public pulse: number;
    public lastChanged: Date;
    public plannerTaken: boolean;
    public questionnaire: Questionnaire;

    constructor(id: number, years: number, height: number, weight: number, upperBloodPressure: number,
                lowerBloodPressure: number, pulse: number, lastChanged: Date, questionnaire: Questionnaire) {
        this.id = id;
        this.years = years;
        this.height = height;
        this.weight = weight;
        this.upperBloodPressure = upperBloodPressure;
        this.lowerBloodPressure = lowerBloodPressure;
        this.pulse = pulse;
        this.lastChanged = lastChanged;
        this. questionnaire = questionnaire;
    }
}
