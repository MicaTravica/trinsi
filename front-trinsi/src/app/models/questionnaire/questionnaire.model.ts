export class Questionnaire {

    public vigorousDays: number;
    public vigorousMinutes: number;
    public moderateDays: number;
    public moderateMinutes: number;
    public walkingDays: number;
    public walkingMinutes: number;

    constructor(vigorousDays: number, vigorousMinutes: number, modetateDays: number, moderateMinutes: number,
                walkingDays: number, walkingMinutes: number) {
        this.vigorousDays = vigorousDays;
        this.vigorousMinutes = vigorousMinutes;
        this.moderateDays = modetateDays;
        this.moderateMinutes = moderateMinutes;
        this.walkingDays = walkingDays;
        this.walkingMinutes = walkingMinutes;
    }
}
