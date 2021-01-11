import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'banka-frontend';

  cardForm: FormGroup;
  loading = false;
  submitted = false;
  paymentId: number;
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private http: HttpClient,
    private activatedRoute: ActivatedRoute
  ) {
    this.activatedRoute.queryParams.subscribe(params => {
      let date = params['paymentId'];
      console.log(date);
      this.paymentId=date;// Print the parameter to the console.
    });
  }

  ngOnInit() {
    this.cardForm = this.formBuilder.group({
      pan: ['', Validators.required],
      securityCode: ['', Validators.required],
      cardHolderName: ['', Validators.required],
      expirationDate: ['', Validators.required]
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
    console.log(this.paymentId);
    this.loading = true;
    this.login(this.f.pan.value, this.f.securityCode.value,this.f.cardHolderName.value,this.f.expirationDate.value,this.paymentId)
      .subscribe(
        data => {
          alert('success');
        },
        error => {
          alert('error');
        });
  }


  login(pan,securityCode,cardHolderName,expirationDate,paymentId) {
    return this.http.post<any>('http://localhost:8082/check', { pan, securityCode,cardHolderName,expirationDate,paymentId})
      .pipe();
  }
}
