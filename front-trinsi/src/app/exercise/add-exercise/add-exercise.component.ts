import { Component, Inject } from '@angular/core';
import { EXERCISE_TYPE } from 'src/app/models/enums/exercise-type.enum';
import { ToastrService } from 'ngx-toastr';
import { ExerciseService } from 'src/app/services/exercise-service/exercise.service';
import { Exercise, Picture } from 'src/app/models/exercise/exercise.model';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { INTENSITY } from 'src/app/models/enums/intensity.enum';
import { MUSCLES_GROUP } from 'src/app/models/enums/muscles-group.enum';
import { AngularFireStorage } from 'angularfire2/storage';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-add-exercise',
  templateUrl: './add-exercise.component.html',
  styleUrls: ['./add-exercise.component.scss']
})
export class AddExerciseComponent {

  exercise = new Exercise(null, '', '', null, null, null, []);
  exerciseType = [EXERCISE_TYPE.STRETCHES, EXERCISE_TYPE.MUSCULAR, EXERCISE_TYPE.AEROBIC];
  intensity = [INTENSITY.LIGHT, INTENSITY.MODERATE, INTENSITY.VIGOROUS];
  musclesGroup = [null, MUSCLES_GROUP.ARMS, MUSCLES_GROUP.SHOULDERS, MUSCLES_GROUP.CHEST, MUSCLES_GROUP.BACK,
    MUSCLES_GROUP.LEGS, MUSCLES_GROUP.BUTTOCKS, MUSCLES_GROUP.ABDOMEN];
  action = 'Add';
  wait = false;

  constructor(
    private exerciseService: ExerciseService,
    private toastr: ToastrService,
    private afStorage: AngularFireStorage,
    private dialogRef: MatDialogRef<AddExerciseComponent>,
    @Inject(MAT_DIALOG_DATA) private data: Exercise
  ) {
    if (data != null) {
      this.exercise = data;
      this.action = 'Change';
    }
  }


  onSubmit() {
    if (this.exercise.exerciseType === EXERCISE_TYPE.AEROBIC) {
      this.exercise.musclesGroup = null;
    } else {
      this.exercise.intensity = null;
    }

    if (this.exercise.id == null) {
      this.add();
    } else {
      this.update();
    }
  }

  add() {
    this.exerciseService.add(this.exercise).subscribe(
      (data: number) => {
        this.toastr.success('Successful add!');
        this.dialogRef.close(data);
      }
    );
  }

  update() {
    this.exerciseService.update(this.exercise).subscribe(
      (data: number) => {
        this.toastr.success('Successful changed!');
        this.dialogRef.close(data);
      }
    );
  }

  onClose() {
    this.dialogRef.close();
  }

  upload(event: any): void {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      const path = `${Date.now()}_${file.name}`;
      const task = this.afStorage.upload(path, file);
      const ref = this.afStorage.ref(path);
      this.wait = true;
      task.snapshotChanges().pipe(
        finalize(() => {
          ref.getDownloadURL().subscribe((value: string) => {
            const picture = new Picture(null, value, file.name);
            this.exercise.pictures.push(picture);
            this.wait = false;
          });
        })
      ).subscribe();
    }
  }

  remove(i: number) {
    const [file] = this.exercise.pictures.splice(i, 1);
    this.afStorage.storage.refFromURL(file.path).delete();
  }
}
