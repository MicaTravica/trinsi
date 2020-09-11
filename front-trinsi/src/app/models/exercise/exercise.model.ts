import { EXERCISE_TYPE } from '../enums/exercise-type.enum';
import { INTENSITY } from '../enums/intensity.enum';
import { MUSCLES_GROUP } from '../enums/muscles-group.enum';

export class Exercise {

    public id: number;
    public name: string;
    public description: string;
    public exerciseType: EXERCISE_TYPE;
    public intensity: INTENSITY;
    public musclesGroup: MUSCLES_GROUP;
    public pictures: Picture[];

    constructor(id: number, name: string, description: string, exerciseType: EXERCISE_TYPE, intensity: INTENSITY,
                musclesGroup: MUSCLES_GROUP, pictures: Picture[]) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exerciseType = exerciseType;
        this.intensity = intensity;
        this.musclesGroup = musclesGroup;
        this.pictures = pictures;
    }
}

export class Picture {

    public id: number;
    public path: string;
    public name: string;

    constructor(id: number, path: string, name: string) {
        this.id = id;
        this.path = path;
        this.name = name;
    }
}
