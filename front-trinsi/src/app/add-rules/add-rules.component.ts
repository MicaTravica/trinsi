import { Component, OnInit } from '@angular/core';
import { RulesService } from '../services/rules-service/rules.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-rules',
  templateUrl: './add-rules.component.html',
  styleUrls: ['./add-rules.component.scss']
})
export class AddRulesComponent implements OnInit {

  rule = '';
  wait = false;

  constructor(
    private rulesService: RulesService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
  }

  add() {
    this.wait = true;
    this.rulesService.add(this.rule).subscribe(
      () => {
        this.wait = false;
        this.toastr.success('Successfully added!');
      },
      () => {
        this.wait = false;
      }
    );
  }
}
