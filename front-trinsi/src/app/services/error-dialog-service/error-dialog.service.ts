import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class ErrorDialogService {

  private static emitters: { [channel: string]: EventEmitter<any> } = {};
  static get(channel: string): EventEmitter<any> {
    if (!this.emitters[channel]) {
      this.emitters[channel] = new EventEmitter();
    }
    return this.emitters[channel];
  }
}
