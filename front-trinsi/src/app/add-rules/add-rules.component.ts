import { Component, OnInit } from '@angular/core';
import { RulesService } from '../services/rules-service/rules.service';

@Component({
  selector: 'app-add-rules',
  templateUrl: './add-rules.component.html',
  styleUrls: ['./add-rules.component.scss']
})
export class AddRulesComponent implements OnInit {

  constructor(
    private rulesService: RulesService
  ) { }

  ngOnInit() {
    this.rulesService.add('package com.app.trinsi.planner ' +
    'rule "Cao" ' +
    'agenda-group "planner" ' +
    'salience 100 ' +
    'when ' +
    'then ' +
    ' System.out.println("CAO"); ' +
    'end').subscribe(
      d => {
        console.log(d);
      }
    );
  }

}
