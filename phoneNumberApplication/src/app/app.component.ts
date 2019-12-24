import { Component } from '@angular/core';

import { AppService } from './app.serive';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Phone Number Application';
  number: any;
  allPossibleCombinations: Object;
  errorMessage: boolean;
  pageNo: number;
  pageSize: number;

  constructor(private appService: AppService){
    this.pageNo = 1;
    this.pageSize = 20;
  }
 
  fetchData(number:any) {
    const pattern = /[0-9]*/;
    let inputChar = String.fromCharCode(number);

    if (pattern.test(inputChar) && (number.toString().length ==7 || number.toString().length ==10)) {
      this.errorMessage= false;
      this.number= number;
      this.appService.fetchData(number, this.pageNo, this.pageSize).subscribe(
        data => { this.  allPossibleCombinations= data},
        err => console.error(err)
      ); 
    } else {
      this.allPossibleCombinations = null;
      this.errorMessage = true;
    }
  }
}


