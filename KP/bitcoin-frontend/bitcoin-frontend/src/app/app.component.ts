import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'bitcoin-frontend';

  cardForm: FormGroup;
  loading = false;
  submitted = false;
  amount: number;
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private http: HttpClient,
    private activatedRoute: ActivatedRoute
  ) {
    this.activatedRoute.queryParams.subscribe(params => {
      let date = params['amount'];
      console.log(date);
      this.amount=date;// Print the parameter to the console.
    });
  }

  ngOnInit() {
    this.cardForm = this.formBuilder.group({
      address: ['', Validators.required]
    });

  }

// convenience getter for easy access to form fields
  get f() {
    return this.cardForm.controls;
  }

  onSubmit() {
    this.submitted = true;


    // stop here if form is invalid
    if (this.cardForm.invalid) {
      return;
    }
    console.log(this.amount);
    this.loading = true;
    this.login(this.amount,this.f.address.value)
      .subscribe(
        data => {
          alert('success');
          window.close();
        },
        error => {
          alert('error');
        });
  }


  login(amount,address) {
    return this.http.post<any>('http://localhost:8088/send', {amount,address})
      .pipe();
  }
}
