import { HEALTH_CONDITION } from '../enums/health-condition.enum';

export class HeartBeatTracking {

    userId: number;
    currentPulse: number;
    targetPulse: number;
    healthCondition: HEALTH_CONDITION;

    constructor(userId: number, currentPulse: number, targetPulse: number, healthCondition: HEALTH_CONDITION) {
        this.userId = userId;
        this.currentPulse = currentPulse;
        this.targetPulse = targetPulse;
        this.healthCondition = healthCondition;
    }
}
