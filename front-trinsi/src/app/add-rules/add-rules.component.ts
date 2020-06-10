import { Component, OnInit } from '@angular/core';
import { RulesService } from '../services/rules-service/rules.service';

@Component({
  selector: 'app-add-rules',
  templateUrl: './add-rules.component.html',
  styleUrls: ['./add-rules.component.scss']
})
export class AddRulesComponent implements OnInit {

  rule = '';

  constructor(
    private rulesService: RulesService
  ) { }

  ngOnInit() {
  }

  add() {
    this.rulesService.add(this.rule).subscribe(
      d => {
        console.log(d);
      }
    );
  }
}
